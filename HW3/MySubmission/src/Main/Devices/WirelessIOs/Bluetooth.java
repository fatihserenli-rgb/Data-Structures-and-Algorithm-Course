package Main.Devices.WirelessIOs;
import Main.Devices.*;
import Main.Protocols.*;
/**
 * Concrete implementation of a Bluetooth wireless communication device.
 * Provides Bluetooth-specific implementation for sending and receiving data
 * using the configured protocol.
 * <p>
 * This class handles basic Bluetooth operations including power management
 * and data transmission.
 */
public class Bluetooth extends WirelessIO {
    /**
     * Constructs a new Bluetooth device with the specified communication protocol.
     * 
     * @param protocol The Bluetooth protocol implementation to be used
     *                 for data transmission and device control
     */
    public Bluetooth(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this wireless device.
     * 
     * @return The constant string "Bluetooth" representing this device's technology
     */
    @Override
    public String getName() {
        return "Bluetooth";
    }

    /**
     * Powers on the Bluetooth device.
     * <p>
     * Performs the following operations:
     * <ol>
     *   <li>Sets the device state to ON</li>
     *   <li>Sends a power-on command through the protocol</li>
     *   <li>Prints a confirmation message to standard output</li>
     * </ol>
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the Bluetooth device.
     * <p>
     * Performs the following operations:
     * <ol>
     *   <li>Sets the device state to OFF</li>
     *   <li>Sends a power-off command through the protocol</li>
     *   <li>Prints a confirmation message to standard output</li>
     * </ol>
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
