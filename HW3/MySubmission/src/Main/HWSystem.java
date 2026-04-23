package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Iterator;
import Main.Protocols.*;
import Main.Devices.*;
import Main.Devices.Displays.*;
import Main.Devices.MotorDrivers.*;
import Main.Devices.Sensors.*;
import Main.Devices.WirelessIOs.*;

/**
 * Main hardware system class that manages devices, ports, and commands.
 */
public class HWSystem {
    private Queue<String> commands;
    private ArrayList<Port> ports;
    private String log_directory;

    private int sens_count;
    private int display_count;
    private int wireless_count;
    private int motorD_count;

    private boolean[] is_connect_sensor;
    private boolean[] is_connect_display;
    private boolean[] is_connect_wireless;
    private boolean[] is_connect_motorD;

    private int[] sensor_port_ids;
    private int[] display_port_ids;
    private int[] wireless_port_ids;
    private int[] motorD_port_ids;

    /**
     * Constructs a new HWSystem with configuration file and log directory.
     * @param arg1 Path to configuration file
     * @param arg2 Path to log directory
     */
    public HWSystem(String arg1,String arg2){
        ports = new ArrayList<>();
        commands = new LinkedList<>();
        loadConfig(arg1);
        log_directory = arg2;
        File dizin = new File(log_directory);
        if (!dizin.exists()) {
            dizin.mkdirs();
        }
    }

    /**
     * Starts the system and processes user commands.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            input = scanner.nextLine();
            commands.add(input);
        }while(!"exit".equals(input));
        scanner.close();
        Execute();
    }

    /**
     * Executes all queued commands.
     */
    private void Execute() {
        String command;
        boolean flag = true;
        while(flag) {
            command = commands.poll().trim();
            String[] parts = command.split(" ");
            switch(parts[0]) {
                case "addDev":
                if (parts.length == 4) {
                    try {
                        String devName = parts[1];
                        int portID = Integer.parseInt(parts[2]);
                        int devID = Integer.parseInt(parts[3]);
                        addDev(devName, portID, devID,command);
                    } catch (NumberFormatException e) {
                        System.err.printf("Invalid command. \"%s\"(portID and devID must be integers for addDev.)\n",command);
                    }
                } else {
                    System.err.printf("Invalid command. \"%s\"(wrong parameters for addDev)\n",command);
                }
                    break;

                case "rmDev":
                    if (parts.length == 2) {
                        try {
                            int portID = Integer.parseInt(parts[1]);
                            remvDevice(portID,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\"(portID must be an integer for rmDev.)\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\"(wrong parameters for rmDev)\n",command);
                    }
                    break;
                case "turnON":
                    if (parts.length == 2) {
                        try {
                            int portID = Integer.parseInt(parts[1]);
                            turnON(portID,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\"(portID must be an integer for turnON.)\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\"(wrong paramaters for turnON)\n",command);
                    }
                    break;

                case "turnOFF":
                    if (parts.length == 2) {
                        try {
                            int portID = Integer.parseInt(parts[1]);
                            turnOFF(portID,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\"(portID must be an integer for turnOFF.)\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\"(wrong paramaters for turnOFF)\n",command);
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
                        else {
                            System.err.printf("Invalid command.\"%s\" (argument must be a device type)\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\" (wrong paramaters for list)\n",command);
                    }
                    break;

                case "readSensor":
                    if (parts.length == 2) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            readSensor(devID,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\" (devID must be an integer for readSensor.)\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\" (wrong paramaters for readSensor)\n",command);
                    }
                    break;

                case "printDisplay":
                    if (parts.length >= 3) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            String data = command.substring(command.indexOf(parts[2]));
                            print_display(devID, data,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\" (devID must be an integer for printDisplay.)\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\" (wrong paramaters for printDisplay)\n",command);
                    }
                    break;

                case "readWireless":
                    if (parts.length == 2) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            readWireless(devID,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\" (devID must be an integer for readWireless.)\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\" (wrong paramaters for readWireless)\n",command);
                    }
                    break;

                case "writeWireless":
                    if (parts.length >= 3) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            String data = command.substring(command.indexOf(parts[2]));
                            sendWireless(devID, data,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\" (devID must be an integer for writeWireless.)\n\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\" (wrong paramaters for writeWireless)\n",command);
                    }
                    break;

                case "setMotorSpeed":
                    if (parts.length == 3) {
                        try {
                            int devID = Integer.parseInt(parts[1]);
                            int speed = Integer.parseInt(parts[2]);
                            setMSpeed(devID, speed,command);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid command. \"%s\" (devID must be an integer for setMotorSpeed.\n)\n\n",command);
                        }
                    } else {
                        System.err.printf("Invalid command. \"%s\" (wrong paramaters for setMotorSpeed)\n",command);
                    }
                    break;

                case "exit":
                    for(Port port : ports) {
                        port.writeLogs(log_directory);
                    }
                    System.out.println("Exiting...");
                    flag = false;
                    break;
                
                default:
                    System.err.printf("Invalid command. \"%s\" (undefined command)\n",command);
            }
        }
    }

    /**
     * Loads system configuration from file.
     * @param configFile Path to configuration file
     */
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
                        System.err.printf("Invalid protocol in the configuration file: \"%s\" (program will continue without it)\n" , portType);
                        --id;
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
                sensor_port_ids = new int[sens_count];
                display_port_ids = new int[display_count];
                wireless_port_ids = new int[wireless_count];
                motorD_port_ids = new int[motorD_count];
            }catch (NumberFormatException e) {
                System.err.println("Invalid argument in the configuration file.(NumberFormatException error.You must fix the configuration file to use program.)");
                System.exit(1);
            }catch(ArrayIndexOutOfBoundsException e) {
                System.err.println("Invalid argument in the configuration file.(ArrayIndexOutOfBounds error.You must fix the configuration file to use program.)");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found! Exitting the program");
            System.exit(1);
        }
    }

    /**
     * Creates a device instance based on name and protocol.
     * @param devName Name of the device
     * @param protocol Communication protocol
     * @return Device instance or null if invalid
     */
    private Device createDevice(String devName, Protocol protocol) {
        if (devName.equals("DHT11") && protocol instanceof OneWire) {
            return new DHT11(protocol);
        }
        else if (devName.equals("BME280") && (protocol instanceof I2C || protocol instanceof SPI)) {
            return new BME280(protocol);
        }
        else if (devName.equals("MPU6050") && protocol instanceof I2C) {
            return new MPU6050(protocol);
        }
        else if (devName.equals("GY951") && (protocol instanceof SPI || protocol instanceof UART)) {
            return new GY951(protocol);
        }
        else if (devName.equals("LCD") && protocol instanceof I2C) {
            return new LCD(protocol);
        }
        else if (devName.equals("OLED") && protocol instanceof SPI) {
            return new OLED(protocol);
        }
        else if (devName.equals("Bluetooth") && protocol instanceof UART) {
            return new Bluetooth(protocol);
        }
        else if (devName.equals("Wifi") && (protocol instanceof SPI || protocol instanceof UART)) {
            return new Wifi(protocol);
        }
        else if (devName.equals("PCA9685") && protocol instanceof I2C) {
            return new PCA9685(protocol);
        }
        else if (devName.equals("SparkFunMD") && protocol instanceof SPI) {
            return new SparkFunMD(protocol);
        }
        return null;
    }

    /**
     * Adds a device to the specified port with validation checks.
     * @param devName Name of the device to add
     * @param pID Port ID where device will be connected
     * @param dID Device ID to assign
     * @param command Original command string for error reporting
     */
    private void addDev(String devName,int pID,int dID,String command) {
        if(pID < 0 || pID >= ports.size()) {
            System.err.printf("Command failed:\"%s\".(Invalid Port id)\n" ,command);
            return;
        }
        if (ports.get(pID).isOccupied()) {
            System.err.printf("Command failed:\"%s\".Port is already occupied.\n",command);
            return;
        }
        Protocol protocol = ports.get(pID).getProtocol();
        Device device = createDevice(devName,protocol);
        if(device == null) {
            System.err.printf("Command failed:\"%s\".Wrong device-protocol assigment\n",command);
            return;
        }
        if("TempSensor Sensor".equals(device.getDevType()) || "IMUSensor Sensor".equals(device.getDevType())) {
            if(dID < 0 || dID >= sens_count) {
                System.err.printf("Command failed:\"%s\".Invalid device ID for Sensor.\n",command);
                return;
            }
            if(is_connect_sensor[dID]) {
                System.err.printf("Command failed:\"%s\".Sensor %d is already connected.\n",command,dID);
                return;
            }
            is_connect_sensor[dID] = true;
            sensor_port_ids[dID] = pID;
        }
        else if("Display".equals(device.getDevType())) {
            if(dID < 0 || dID >= display_count) {
                System.err.printf("Command failed:\"%s\".Invalid device ID for Display\n",command);
                return;
            }
            if(is_connect_display[dID]) {
                System.err.printf("Command failed:\"%s\".Display %d is already connected.\n",command,dID);
                return;
            }
            is_connect_display[dID] = true;
            display_port_ids[dID] = pID;
        }
        else if("WirelessIO".equals(device.getDevType())) {
            if(dID < 0 || dID >= wireless_count) {
                System.err.printf("Command failed:\"%s\".Invalid device ID for WirelessIO.\n",command);
                return;
            }
            if(is_connect_wireless[dID]) {
                System.err.printf("Command failed:\"%s\".WirelessIO %d is already connected.\n",command,dID);
                return;
            }
            is_connect_wireless[dID] = true;
            wireless_port_ids[dID] = pID;
        }
        else if("MotorDriver".equals(device.getDevType())) {
            if(dID < 0 || dID >= motorD_count) {
                System.err.printf("Command failed:\"%s\".Invalid device ID for MotorDriver.\n",command);
                return;
            }
            if(is_connect_motorD[dID]) {
                System.err.printf("Command failed:\"%s\".MotorDriver %d is already connected.\n",command,dID);
                return;
            }
            is_connect_motorD[dID] = true;
            motorD_port_ids[dID] = pID;
        }
        ports.get(pID).connectDevice(device, dID);
        System.out.println("Device added.");
    }

    /**
     * Removes a device from the specified port with validation checks.
     * @param pID Port ID to disconnect device from
     * @param command Original command string for error reporting
     */
    private void remvDevice(int pID,String command) {
        if (pID < 0 || pID >= ports.size()) {
            System.err.printf("Command failed:\"%s\".(Invalid Port id)\n",command);
            return;
        }
        if (!ports.get(pID).isOccupied()) {
            System.err.printf("Command failed:\"%s\".Port is already empty.\n",command);
            return;
        }
        Device d = ports.get(pID).getDevice();
        if(d.getState() == State.ON) {
            System.err.printf("Command failed:\"%s\".Device is ON.\n",command);
            return;
        }
        int dID = ports.get(pID).get_devID();
        ports.get(pID).disConnectDevice();
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
        System.out.println("Device removed."); 
    }

    /**
     * Turns on the device connected to specified port with validation checks.
     * @param pID Port ID of device to turn on
     * @param command Original command string for error reporting
     */
    private void turnON(int pID,String command) {
        if (pID < 0 || pID >= ports.size()) {
            System.err.printf("Command failed:\"%s\".Invalid port ID.\n",command);
            return;
        }
        if (!ports.get(pID).isOccupied()) {
            System.err.printf("Command failed:\"%s\".Port is empty.\n",command);
            return;
        }
        if(ports.get(pID).getDevice().getState() == State.ON) {
            System.err.printf("Command failed:\"%s\".Device is already ON.\n",command);
            return;
        }
        ports.get(pID).turnON();
    }

    /**
     * Turns off the device connected to specified port with validation checks.
     * @param pID Port ID of device to turn off
     * @param command Original command string for error reporting
     */
    private void turnOFF(int pID,String command) {
        if (pID < 0 || pID >= ports.size()) {
            System.err.printf("Command failed:\"%s\".Invalid port ID.\n",command);
            return;
        }
        if (!ports.get(pID).isOccupied()) {
            System.err.printf("Command failed:\"%s\".Port is empty.\n",command);
            return;
        }
        if(ports.get(pID).getDevice().getState() == State.OFF) {
            System.err.printf("Command failed:\"%s\".Device is already OFF.\n",command);
            return;
        }
        ports.get(pID).turnOFF();
    }

    /**
     * Displays the current status of all ports in the system.
     * Shows port ID, protocol, and device information (if occupied).
     */
    private void showPorts() {
        System.out.println("list of ports:");
        Iterator<Port> iterator = ports.iterator();
        while(iterator.hasNext()) {
            Port p = iterator.next();
            if(p.isOccupied()) {
                Device d = p.getDevice();
                String state = (d.getState() == State.ON) ? "ON" : "OFF";
                System.out.printf("%d %s occupied %s %s %s\n",p.get_portID(),p.getProtocol().getProtocolName(),d.getName(),d.getDevType(),state);
            }
            else {
                System.out.printf("%d %s empty\n",p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    /**
     * Lists all connected sensor devices with their details.
     * Includes sensor name, device ID, port ID, and protocol.
     */
    private void listSensors() {
        System.out.println("list of Sensor:");
        Iterator<Port> iterator = ports.iterator();
        while(iterator.hasNext()) {
            Port p = iterator.next();
            if("TempSensor Sensor".equals(p.getDevType()) || "IMUSensor Sensor".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    /**
     * Lists all connected display devices with their details.
     * Includes display name, device ID, port ID, and protocol.
     */
    private void listDisplays() {
        System.out.println("list of Displays:");
        for(Port p : ports) {
            if("Display".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    /**
     * Lists all connected wireless I/O devices with their details.
     * Includes device name, device ID, port ID, and protocol.
     */
    private void listWirelessIOs() {
        System.out.println("list of WirelesssIOs:");
        Iterator<Port> iterator = ports.iterator();
        while(iterator.hasNext()) {
            Port p = iterator.next();
            if("WirelessIO".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    /**
     * Lists all connected motor driver devices with their details.
     * Includes driver name, device ID, port ID, and protocol.
     */
    private void listMotorDrives() {
        System.out.println("list of MotorDrives:");
        Iterator<Port> iterator = ports.iterator();
        while(iterator.hasNext()) {
            Port p = iterator.next();
            if("MotorDriver".equals(p.getDevType())) {
                Device d = p.getDevice();
                System.out.printf("%s %d %d %s\n",d.getName(),p.get_devID(),p.get_portID(),p.getProtocol().getProtocolName());
            }
        }
    }

    /**
     * Reads sensor data from specified device ID and prints formatted output.
     * @param dID The device ID of the sensor to read
     * @param command The original command string for error reporting
     */
    private void readSensor(int dID,String command) {
    	if(dID < 0 || dID >= sens_count) {
    	    System.err.printf("Command failed:\"%s\".Invalid device id for Sensor.\n",command);
    	    return;
    	} 
        if(!is_connect_sensor[dID]) {
            System.err.printf("Command failed:\"%s\".Sensor %d is not connected.\n",command,dID);
            return;
        }
        int pID = sensor_port_ids[dID];
        Device d = ports.get(pID).getDevice();
        if(d.getState() == State.OFF) {
            System.err.printf("Command failed:\"%s\".State of Sensor %d is OFF.\n",command,dID);
            return;
        }
        Protocol p = ports.get(pID).getProtocol();
        Sensor sensor = createSensor(d.getName(), p);
        System.out.printf("%s %s %s\n",sensor.getName(),sensor.getDevType(),sensor.data2String());
    }

    /**
     * Creates a Sensor instance based on device name and protocol.
     * @param devName The name of the sensor device
     * @param protocol The communication protocol to use
     * @return Sensor instance or null if invalid
     */
    private Sensor createSensor(String devName, Protocol protocol) {
        if (devName.equals("DHT11")) {
            return new DHT11(protocol);
        } else if (devName.equals("BME280")) {
            return new BME280(protocol);
        } else if (devName.equals("MPU6050")) {
            return new MPU6050(protocol);
        } else if (devName.equals("GY951")) {
            return new GY951(protocol);
        }
        return null;
    }

    /**
     * Prints data to specified display device.
     * @param dID The device ID of the display
     * @param data The data to display
     * @param command The original command string for error reporting
     */
    private void print_display(int dID,String data,String command) {
        if(dID < 0 || dID >= display_count) {
    	    System.err.printf("Command failed:\"%s\".Invalid device id for Display.\n",command);
    	    return;
    	}
        if(!is_connect_display[dID]) {
            System.err.printf("Command failed:\"%s\".Display %d is not connected.\n",command,dID);
            return;
        }
        int pID = display_port_ids[dID];
        Device d = ports.get(pID).getDevice();
        if(d.getState() == State.OFF) {
            System.err.printf("Command failed:\"%s\".State of Display %d is OFF.\n",command,dID);
            return;
        }
        Protocol p = ports.get(pID).getProtocol();
        Display display = createDisplay(d.getName(),p);
        display.printData(data);
    }

    /**
     * Creates a Display instance based on device name and protocol.
     * @param devName The name of the display device
     * @param protocol The communication protocol to use
     * @return Display instance or null if invalid
     */
    private Display createDisplay(String devName, Protocol protocol) {
        if (devName.equals("LCD")) {
            return new LCD(protocol);
        } else if (devName.equals("OLED") && protocol instanceof SPI) {
            return new OLED(protocol);
        }
        return null;
    }

    /**
     * Reads data from specified wireless device.
     * @param dID The device ID of the wireless device
     * @param command The original command string for error reporting
     */
    private void readWireless(int dID,String command) {
        if(dID < 0 || dID >= wireless_count) {
    	    System.err.printf("Command failed:\"%s\".Invalid device id for WirelessIO.\n",command);
    	    return;
    	}
        if(!is_connect_wireless[dID]) {
            System.err.printf("Command failed:\"%s\".Wİreless %d is not connected.\n",command,dID);
            return;
        }
        int pID = wireless_port_ids[dID];
        Device d = ports.get(pID).getDevice();
        if(d.getState() == State.OFF) {
            System.err.printf("Command failed:\"%s\".State of Wireless %d is OFF.\n",command,dID);
            return;
        }
        Protocol p = ports.get(pID).getProtocol();
        WirelessIO wireless = createWirelessIO(d.getName(), p);
        System.out.println(wireless.recvData());
    }

    /**
     * Creates a WirelessIO instance based on device name and protocol.
     * @param devName The name of the wireless device
     * @param protocol The communication protocol to use
     * @return WirelessIO instance or null if invalid
     */
    private WirelessIO createWirelessIO(String devName, Protocol protocol) {
        if (devName.equals("Bluetooth")) {
            return new Bluetooth(protocol);
        }
        else if (devName.equals("Wifi")) {
            return new Wifi(protocol);
        }
        return null;
    }

    /**
     * Sends data to specified wireless device.
     * @param dID The device ID of the wireless device
     * @param data The data to send
     * @param command The original command string for error reporting
     */
    private void sendWireless(int dID,String data,String command) {
    	if(dID < 0 || dID >= wireless_count) {
    	    System.err.printf("Command failed:\"%s\".Invalid device id for WirelessIO.\n",command);
    	    return;
    	}
        if(!is_connect_wireless[dID]) {
            System.err.printf("Command failed:\"%s\".Wireless %d is not connected.\n",command,dID);
            return;
        }
        int pID = wireless_port_ids[dID];
        Device d = ports.get(pID).getDevice();
        if(d.getState() == State.OFF) {
            System.err.printf("Command failed:\"%s\".State of Wireless %d is OFF.\n",command,dID);
            return;
        }
        Protocol p = ports.get(pID).getProtocol();
        WirelessIO wireless = createWirelessIO(d.getName(), p);
        wireless.sendData(data);
    }

    /**
     * Sets motor speed for specified motor driver.
     * @param dID The device ID of the motor driver
     * @param speed The speed value to set
     * @param command The original command string for error reporting
     */
    private void setMSpeed(int dID,int speed,String command) {
    	if(dID < 0 || dID >= motorD_count) {
    	    System.err.printf("Command failed:\"%s\".Invalid device id for MotorDriver.\n",command);
    	    return;
    	}
        if(!is_connect_motorD[dID]) {
            System.err.printf("Command failed:\"%s\".MotorDriver %d is not connected.\n",command,dID);
            return;
        }
        int pID = motorD_port_ids[dID];
        Device d = ports.get(pID).getDevice();
        if(d.getState() == State.OFF) {
            System.err.printf("Command failed:\"%s\".State of MotorDriver %d is OFF.\n",command,dID);
            return;
        }
        Protocol p = ports.get(pID).getProtocol();
        MotorDriver motor = createMotorDriver(d.getName(), p);
        motor.setMotorSpeed(speed);
    }

    /**
     * Creates a MotorDriver instance based on device name and protocol.
     * @param devName The name of the motor driver
     * @param protocol The communication protocol to use
     * @return MotorDriver instance or null if invalid
     */
    private MotorDriver createMotorDriver(String devName, Protocol protocol) {
        if (devName.equals("PCA9685")) {
            return new PCA9685(protocol);
        }
        else if (devName.equals("SparkFunMD")) {
            return new SparkFunMD(protocol);
        }
        return null;
    }
}
