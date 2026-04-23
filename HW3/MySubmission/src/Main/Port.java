package Main;
import Main.Devices.*;
import Main.Protocols.*;
/**
 * Represents a physical port that can connect devices using a specific protocol.
 */
public class Port {
    
    private Protocol protocol;
    private Device device;
    private int pID;
    private int dID;

    /**
     * Constructs a new Port instance with specified protocol and port ID.
     * @param protocol The communication protocol used by this port
     * @param pID The unique identifier for this port
     */
    public Port(Protocol protocol, int pID) {
        this.protocol = protocol;
        this.pID = pID;
        device = null;
    }

    /**
     * Gets the connected device ID.
     * @return The device ID
     */
    public int get_devID() {
        return dID;
    }

    /**
     * Gets the port ID.
     * @return The port identifier
     */
    public int get_portID() {
        return pID;
    }

    /**
     * Gets the type of connected device.
     * @return The device type string, or null if no device connected
     */
    public String getDevType() {
        if(device != null) {
            return device.getDevType();
        }
        else return null;
    }

    /**
     * Gets the connected device instance.
     * @return The Device object, or null if no device connected
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Gets the protocol used by this port.
     * @return The Protocol instance
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Writes logs to the specified directory.
     * @param logDirectory The directory path for log files
     */
    public void writeLogs(String logDirectory) {
        protocol.close(logDirectory + "/" + protocol.getProtocolName() + "_" + pID + ".log");
    }

    /**
     * Checks if the port has a connected device.
     * @return true if a device is connected, false otherwise
     */
    public boolean isOccupied() {
        return (device != null);
    }

    /**
     * Connects a device to this port.
     * @param d The device to connect
     * @param id The device ID to assign
     */
    public void connectDevice(Device d, int id) {
        device = d;
        dID = id;
    }

    /**
     * Disconnects the current device from this port.
     */
    public void disConnectDevice() {
        device = null;
    }

    /**
     * Turns on the connected device.
     */
    public void turnON() {
        device.turnON();
    }

    /**
     * Turns off the connected device.
     */
    public void turnOFF() {
        device.turnOFF();
    }
}
