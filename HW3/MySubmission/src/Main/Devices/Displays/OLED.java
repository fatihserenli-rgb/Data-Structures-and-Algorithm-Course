package Main.Devices.Displays;
import Main.Devices.*;
import Main.Protocols.*;
/**
 * Concrete implementation of an OLED display device.
 * Represents an Organic Light-Emitting Diode display with specific
 * power management and display capabilities.
 * Inherits common display functionality from the Display abstract class.
 */
public class OLED extends Display {
    /**
     * Constructs a new OLED display with the specified communication protocol.
     * 
     * @param protocol The communication protocol implementation to be used
     *                 for this OLED display's operations
     */
    public OLED(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the specific name of this display device.
     * 
     * @return The constant string "OLED" representing this device's type
     */
    @Override
    public String getName() {
        return "OLED";
    }

    /**
     * Displays the provided data on the OLED screen.
     * Uses the configured protocol to write the data to the display.
     * 
     * @param data The content to be displayed on the OLED screen.
     *             Can include text or special formatting commands.
     
    @Override
    public void printData(String data) {
        protocol.write(data); 
    }*/

    /**
     * Powers on the OLED display.
     * Performs the following operations:
     * 1. Sets the device state to ON
     * 2. Logs the power-on event through the protocol
     * 3. Prints a confirmation message to standard output
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the OLED display.
     * Performs the following operations:
     * 1. Sets the device state to OFF
     * 2. Logs the power-off event through the protocol
     * 3. Prints a confirmation message to standard output
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
