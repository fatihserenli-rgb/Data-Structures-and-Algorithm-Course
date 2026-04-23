package Main.Devices;
import Main.Protocols.*;
/**
 * Abstract base class representing a generic device in the system.
 * This class provides common functionality and defines abstract methods
 * that concrete device implementations must provide.
 */
public abstract class Device{
    /** The communication protocol used by the device */
    protected Protocol protocol;

    /** The current operational state of the device */
    protected State state;

    /**
     * Constructs a new Device instance.
     * Initializes the device state to OFF by default.
     */
    public Device() {
        state = State.OFF;
    }

    /**
     * Gets the name of the device.
     * 
     * @return The name of the device as a String
     */
    public abstract String getName();

    /**
     * Gets the type of the device.
     * 
     * @return The device type as a String
     */
    public abstract String getDevType();

    /**
     * Turns the device on.
     * Concrete implementations must define the specific behavior.
     */
    public abstract void turnON();

     /**
     * Turns the device off.
     * Concrete implementations must define the specific behavior.
     */
    public abstract void turnOFF();

     /**
     * Gets the current state of the device.
     * 
     * @return The current state of the device (ON or OFF)
     */
    public State getState() {
        return state;
    }
}
