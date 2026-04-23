package Main;
import Main.Port;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Protocols.*;
import Devices.*;
import Devices.Sensors.*;
import Devices.Displays.*;
import Devices.WirelessIOs.*;
import Devices.MotorDrivers.*;
public class HWSystem {
    private ArrayList<Port> ports;
    private int sens_count;
    private int display_count;
    private int wireless_count;
    private int motorD_count;
    private boolean[] is_connect_sensor;
    private boolean[] is_connect_display;
    private boolean[] is_connect_wireless;
    private boolean[] is_connect_motorD;
    public HWSystem(String configFile) {
        ports = new ArrayList<>();
        loadConfig(configFile);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;
        
        do {
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim();
            String[] parts = command.split(" ");
    
            if (parts.length == 0) {
                System.out.println("Invalid command.");
                continue;
            }
    
            switch (parts[0]) {
                case "addDev":
                    if (parts.length == 4) {
                        try {
                            String devName = parts[1];
                            int portID = Integer.parseInt(parts[2]);
                            int devID = Integer.parseInt(parts[3]);
                            addDev(devName, portID, devID);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for addDev.");
                        }
                    } else {
                        System.out.println("Wrong Usage: addDev <devName> <portID> <devID>");
                    }
                    break;
    
                case "rmDev":
                    if (parts.length == 2) {
                        try {
                            int portID = Integer.parseInt(parts[1]);
                            remvDevice(portID);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for rmDev.");
                        }
                    } else {
                        System.out.println("Wrong Usage: rmDev <portID>");
                    }
                    break;
    
                case "turnON":
                    if (parts.length == 2) {
                        try {
                            int portID = Integer.parseInt(parts[1]);
                            turnON(portID);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for turnON.");
                        }
                    } else {
                        System.out.println("Wrong Usage: turnON <portID>");
                    }
                    break;
    
                case "turnOFF":
                    if (parts.length == 2) {
                        try {
                            int portID = Integer.parseInt(parts[1]);
                            turnOFF(portID);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for turnOFF.");
                        }
                    } else {
                        System.out.println("Wrong Usage: turnOFF <portID>");
                    }
                    break;
    
                case "list":
                    if (parts.length == 2) {
                        if (parts[1].equals("ports")) {
                            showPorts();
                        } else if (parts[1].equals("Sensor")) {
                            listSensors();
                        } else if (parts[1].equals("Display")) {
                            listDisplays();
                        } else if (parts[1].equals("WirelessIO")) {
                            listWirelessIOs();
                        } else if (parts[1].equals("MotorDriver")) {
                            listMotorDrives();
                        }
                    } else {
                        System.out.println("Wrong Usage: list <ports | Sensor | Display | WirelessIO | MotorDriver | devID>");
                    }
                    break;
    
                case "readSensor":
                    if (parts.length == 2) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            readSensor(devID);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for readSensor.");
                        }
                    } else {
                        System.out.println("Wrong Usage: readSensor <devID>");
                    }
                    break;
    
                case "printDisplay":
                    if (parts.length >= 3) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            String data = command.substring(command.indexOf(parts[2]));
                            print_display(devID, data);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for printDisplay.");
                        }
                    } else {
                        System.out.println("Wrong Usage: printDisplay <devID> <String>");
                    }
                    break;
    
                case "readWireless":
                    if (parts.length == 2) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            readWireless(devID);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for readWireless.");
                        }
                    } else {
                        System.out.println("Wrong Usage: readWireless <devID>");
                    }
                    break;
    
                case "writeWireless":
                    if (parts.length >= 3) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            String data = command.substring(command.indexOf(parts[2]));
                            sendWireless(devID, data);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for writeWireless.");
                        }
                    } else {
                        System.out.println("Wrong Usage: writeWireless <devID> <String>");
                    }
                    break;
    
                case "setMotorSpeed":
                    if (parts.length == 3) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            int speed = Integer.parseInt(parts[2]);
                            setMSpeed(devID, speed);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments for setMotorSpeed.");
                        }
                    } else {
                        System.out.println("Wrong Usage: setMotorSpeed <devID> <integer>");
                    }
                    break;
    
                case "exit":
                    System.out.println("Exiting system...");
                    break;
    
                default:
                    System.out.println("Invalid command.");
            }
            System.out.println("\n\n");
        } while (!"exit".equals(command));
    
        scanner.close();
    }

    private void loadConfig(String configFile) {
        try {
            Scanner scanner = new Scanner(new File(configFile));
            String[] portTypes = scanner.nextLine().split(":")[1].split(",");
            int id = 0;
            for (String portType : portTypes) {
                switch (portType.trim()) {
                    case "I2C":
                        ports.add(new Port(new I2C(),id));
                        break;
                    case "SPI":
                        ports.add(new Port(new SPI(),id));
                        break;
                    case "OneWire":
                        ports.add(new Port(new OneWire(),id));
                        break;
                    case "UART":
                        ports.add(new Port(new UART(),id));
                        break;
                    default:
                        System.out.println("Unknown protocol int the configuration file: " + portType);
                }
                ++id;
            }
            try {
                String num = scanner.nextLine().split(":")[1];
                sens_count = Integer.parseInt(num);
                num = scanner.nextLine().split(":")[1];
                display_count = Integer.parseInt(num);
                num = scanner.nextLine().split(":")[1];
                wireless_count = Integer.parseInt(num);
                num = scanner.nextLine().split(":")[1];
                motorD_count = Integer.parseInt(num);
                scanner.close();
                is_connect_sensor = new boolean[sens_count];
                is_connect_display = new boolean[display_count];
                is_connect_wireless = new boolean[wireless_count];
                is_connect_motorD = new boolean[motorD_count];
            }catch (NumberFormatException e) {
                System.out.println("Invalid arguments in the configuration file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found!");
        }
    }

    private void addDev(String devName, int pID,int dID) {
        if (pID < 0 || pID >= ports.size()) {
            System.out.println("Command failed.Invalid port ID.");
            return;
        }
        if (ports.get(pID).isOccupied()) {
            System.out.println("Command failed.Port is already occupied.");
            return;
        }
        Protocol protocol = ports.get(pID).getProtocol();
        Device device = createDevice(devName, protocol);
        if(device == null) {
            System.out.println("Command failed.Wrong device-protocol assigment");
            return;
        }
        if("TempSensor Sensor".equals(device.getDevType()) || "IMUSensor Sensor".equals(device.getDevType())) {
            if(dID < 0 || dID >= sens_count) {
                System.out.println("Command failed.Invalid device ID for Sensor");
                return;
            }
            if(is_connect_sensor[dID]) {
                System.out.println("Command failed.This Sensor device is already connected.");
                return;
            }
            is_connect_sensor[dID] = true;
        }
        else if("Display".equals(device.getDevType())) {
            if(dID < 0 || dID >= display_count) {
                System.out.println("Command failed.Invalid device ID for Display");
                return;
            }
            if(is_connect_display[dID]) {
                System.out.println("Command failed.This Display device is already connected.");
                return;
            }
            is_connect_display[dID] = true;
        }
        else if("WirelessIO".equals(device.getDevType())) {
            if(dID < 0 || dID >= wireless_count) {
                System.out.println("Command failed.Invalid device ID for WirelessIO");
                return;
            }
            if(is_connect_wireless[dID]) {
                System.out.println("Command failed.This WirelessIO device is already connected.");
                return;
            }
            is_connect_wireless[dID] = true;
        }
        else if("MotorDriver".equals(device.getDevType())) {
            if(dID < 0 || dID >= motorD_count) {
                System.out.println("Command failed.Invalid device IDfor MotorDriver");
                return;
            }
            if(is_connect_motorD[dID]) {
                System.out.println("Command failed.This MotorDriver device is already connected.");
                return;
            }
            is_connect_motorD[dID] = true;
        }
        ports.get(pID).connectDevice(device,dID);
    }

    private void remvDevice(int pID) {
        if (pID < 0 || pID >= ports.size()) {
            System.out.println("Command failed.Invalid port ID.");
            return;
        }
        if (!ports.get(pID).isOccupied()) {
            System.out.println("Command failed.Port is already empty.");
            return;
        }
        Device d = ports.get(pID).getDevice();
        int dID = ports.get(pID).get_devID();
        if(ports.get(pID).disConnectDevice()) {
            if("TempSensor Sensor".equals(d.getDevType()) || "IMUSensor Sensor".equals(d.getDevType())) {
                is_connect_sensor[dID] = false;
            }
            else if("Display".equals(d.getDevType())) {
                is_connect_display[dID] = false;
            }
            else if("WirelessIO".equals(d.getDevType())) {
                is_connect_wireless[dID] = false;
            }
            else if("MotorDriver".equals(d.getDevType())) {
                is_connect_motorD[dID] = false;
            }
        }
    }

    private Device createDevice(String devName, Protocol protocol) {
        if (devName.equals("DHT11") && protocol instanceof OneWire) {
            return new DHT11((OneWire) protocol);
        } else if (devName.equals("BME280") && (protocol instanceof I2C || protocol instanceof SPI)) {
            return new BME280(protocol);
        } else if (devName.equals("MPU6050") && protocol instanceof I2C) {
            return new MPU6050((I2C) protocol);
        } else if (devName.equals("GY951") && (protocol instanceof SPI || protocol instanceof UART)) {
            return new GY951(protocol);
        } else if (devName.equals("LCD") && protocol instanceof I2C) {
            return new LCD((I2C) protocol);
        } else if (devName.equals("OLED") && protocol instanceof SPI) {
            return new OLED((SPI) protocol);
        } else if (devName.equals("Bluetooth") && protocol instanceof UART) {
            return new Bluetooth((UART) protocol);
        } else if (devName.equals("Wifi") && (protocol instanceof SPI || protocol instanceof UART)) {
            return new Wifi(protocol);
        } else if (devName.equals("PCA9685") && protocol instanceof I2C) {
            return new PCA9685((I2C) protocol);
        } else if (devName.equals("SparkFunMD") && protocol instanceof SPI) {
            return new SparkFunMD((SPI) protocol);
        }
        return null;
    }

    private void turnON(int pID) {
        if (pID < 0 || pID >= ports.size()) {
            System.out.println("Command failed.Invalid port ID.");
            return;
        }
        if (!ports.get(pID).isOccupied()) {
            System.out.println("Command failed.Port is empty.");
            return;
        }
        ports.get(pID).turnON();
    }

    private void turnOFF(int pID) {
        if (pID < 0 || pID >= ports.size()) {
            System.out.println("Command failed.Invalid port ID.");
            return;
        }
        if (!ports.get(pID).isOccupied()) {
            System.out.println("Command failed.Port is empty.");
            return;
        }
        ports.get(pID).turnOFF();
    }

    private void showPorts() {
        for(Port p : ports) {
            if(p.isOccupied()) {
                Device d = p.getDevice();
                Protocol pro = p.getProtocol();
                String state = (d.getState() == State.ON) ? "ON" : "OFF";
                System.out.printf("%d %s occupied %s %s %s\n",p.get_portID(),pro.getProtocolName(),d.getName(),d.getDevType(),state);
            }
            else {
                System.out.printf("%d %s empty\n",p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    private void listSensors() {
        for(Port p : ports) {
            if("TempSensor Sensor".equals(p.getDevType()) || "IMUSensor Sensor".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    private void listDisplays() {
        for(Port p : ports) {
            if("Display".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    private void listWirelessIOs() {
        for(Port p : ports) {
            if("WirelessIO".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    private void listMotorDrives() {
        for(Port p : ports) {
            if("MotorDriver".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    private void readSensor(int dID) {
        for(Port p : ports) {
            if(("TempSensor Sensor".equals(p.getDevType()) || "IMUSensor Sensor".equals(p.getDevType())) && p.get_devID() == dID) {
                p.readsens();
                return;
            }
        }
        System.out.println("Command failed.Dev id not found.");
    }

    private void print_display(int dID,String data) {
        for(Port p : ports) {
            if("Display".equals(p.getDevType()) && p.get_devID() == dID) {
                p.printDisp(data);
                return;
            }
        }
        System.out.println("Command failed.Dev id not found.");
    }

    private void readWireless(int dID) {
        for(Port p : ports) {
            if("WirelessIO".equals(p.getDevType()) && p.get_devID() == dID) {
                p.readwless();
                return;
            }
        }
        System.out.println("Command failed.Dev id not found.");
    }

    private void sendWireless(int dID,String data) {
        for(Port p : ports) {
            if("WirelessIO".equals(p.getDevType()) && p.get_devID() == dID) {
                p.sendwless(data);
                return;
            }
        }
        System.out.println("Command failed.Dev id not found.");
    }

    private void setMSpeed(int dID,int speed) {
        for(Port p : ports) {
            if("MotorDriver".equals(p.getDevType()) && p.get_devID() == dID) {
                p.setSpeed(speed);
                return;
            }
        }
        System.out.println("Command failed.Dev id not found.");
    }
    

}
