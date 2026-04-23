package Main;
import Protocols.*;
import Devices.*;
import Devices.Sensors.*;
import Devices.Displays.*;
import Devices.WirelessIOs.*;
import Devices.MotorDrivers.*;
public class Port {
    private Device device;
    private Protocol protocol;
    private int dev_id;
    private int port_id;
    private String dev_type;

    public Port(Protocol protocol,int pID) {
        this.protocol = protocol;
        port_id = pID;
        device = null;
    }
    
    public void connectDevice(Device d,int dID) {
        device = d;
        dev_type = d.getDevType();
        dev_id = dID;
    }

    public boolean disConnectDevice() {
        if(device.getState() == State.ON) {
            System.out.println("Command failed. Device is ON.");
            return false;
        }
        if(device == null) {
            System.out.println("Command failed. Device is already empty.");
            return false;
        }
        device = null;
        dev_id = -1;
        dev_type = null;
        return true;
    }

    public boolean isOccupied() {
        return (device != null);
    }

    public int get_devID() {
        if(device == null) return -1;
        return dev_id;
    }

    public int get_portID() {
        return port_id;
    }

    public Protocol getProtocol(){
        return protocol;
    }

    public String getDevType() {
        return dev_type;
    }
    public Device getDevice() {
        return device;
    }
    public void turnON() {
        device.turnON();
    }

    public void turnOFF() {
        device.turnOFF();
    }
    public void readsens() {
        if(device == null) {
            System.out.println("Command failed.Port is empty.");
            return;
        }
        if(device.getState() == State.OFF) {
            System.out.println("Command failed.Device is OFF");
            return;
        }
        Sensor sensor = createSensor(device.getName(), protocol);
        System.out.printf("%s %s %s\n",sensor.getName(),sensor.getDevType(),sensor.data2String());
    }

    public void printDisp(String data) {
        if(device == null) {
            System.out.println("Command failed.Port is empty.");
            return;
        }
        if(device.getState() == State.OFF) {
            System.out.println("Command failed.Device is OFF");
            return;
        }
        Display display = createDisplay(device.getName(), protocol);
        display.printData(data);
    }

    public void readwless() {
        if(device == null) {
            System.out.println("Command failed.Port is empty.");
            return;
        }
        if(device.getState() == State.OFF) {
            System.out.println("Command failed.Device is OFF");
            return;
        }
        WirelessIO wireless = createWirelessIO(device.getName(), protocol);
        System.out.println(wireless.recvData());
    }

    public void sendwless(String data) {
        if(device == null) {
            System.out.println("Command failed.Port is empty.");
            return;
        }
        if(device.getState() == State.OFF) {
            System.out.println("Command failed.Device is OFF");
            return;
        }
        WirelessIO wireless = createWirelessIO(device.getName(), protocol);
        wireless.sendData(data);
    }

    public void setSpeed(int speed) {
        if(device == null) {
            System.out.println("Command failed.Port is empty.");
            return;
        }
        if(device.getState() == State.OFF) {
            System.out.println("Command failed.Device is OFF");
            return;
        }
        MotorDriver motor = createMotorDriver(device.getName(), protocol);
        motor.setMotorSpeed(speed);
    }

    private Sensor createSensor(String devName, Protocol protocol) {
        if (devName.equals("DHT11") && protocol instanceof OneWire) {
            return new DHT11((OneWire) protocol);
        } else if (devName.equals("BME280") && (protocol instanceof I2C || protocol instanceof SPI)) {
            return new BME280(protocol);
        } else if (devName.equals("MPU6050") && protocol instanceof I2C) {
            return new MPU6050((I2C) protocol);
        } else if (devName.equals("GY951") && (protocol instanceof SPI || protocol instanceof UART)) {
            return new GY951(protocol);
        }
        return null;
    }
    private Display createDisplay(String devName, Protocol protocol) {
        if (devName.equals("LCD") && protocol instanceof I2C) {
            return new LCD((I2C) protocol);
        } else if (devName.equals("OLED") && protocol instanceof SPI) {
            return new OLED((SPI) protocol);
        }
        return null;
    }

    private WirelessIO createWirelessIO(String devName, Protocol protocol) {
        if (devName.equals("Bluetooth") && protocol instanceof UART) {
            return new Bluetooth((UART) protocol);
        } else if (devName.equals("Wifi") && (protocol instanceof SPI || protocol instanceof UART)) {
            return new Wifi(protocol);
        }
        return null;
    }
    private MotorDriver createMotorDriver(String devName, Protocol protocol) {
        if (devName.equals("PCA9685") && protocol instanceof I2C) {
            return new PCA9685((I2C) protocol);
        } else if (devName.equals("SparkFunMD") && protocol instanceof SPI) {
            return new SparkFunMD((SPI) protocol);
        }
        return null;
    }

}
