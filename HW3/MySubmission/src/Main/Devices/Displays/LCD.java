package Main.Devices.Displays;
import Main.Devices.*;
import Main.Protocols.*;
/**
 * Concrete implementation of an LCD display device.
 * This class provides specific functionality for LCD-type displays
 * including power management and data display capabilities.
 */
public class LCD extends Display {
    /**
     * Constructs a new LCD display with the specified communication protocol.
     * 
     * @param protocol The communication protocol to be used by this LCD display
     */
    public LCD(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this device.
     * 
     * @return The string "LCD" representing this device's name
     */
    @Override
    public String getName() {
        return "LCD";
    }

    /**
     * Displays the provided data on the LCD screen.
     * Writes the data to the device using the configured protocol.
     * 
     * @param data The information to be displayed on the LCD screen
     
    @Override
    public void printData(String data) {
        protocol.write(data); 
    }*/

    /**
     * Powers on the LCD display.
     * Updates the device state to ON, writes a log entry through the protocol,
     * and prints a confirmation message to standard output.
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the LCD display.
     * Updates the device state to OFF, writes a log entry through the protocol,
     * and prints a confirmation message to standard output.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
