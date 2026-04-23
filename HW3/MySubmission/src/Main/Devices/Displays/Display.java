package Main.Devices.Displays;
import Main.Devices.*;
/**
 * Abstract base class representing a display device in the system.
 * Extends the base Device class to provide display-specific functionality.
 * All concrete display implementations should inherit from this class.
 */
public abstract class Display extends Device{
    /**
     * Gets the device type (always "Display" for this hierarchy).
     * 
     * @return A string constant "Display" representing the device type
     */
    @Override
    public String getDevType() {
        return "Display";
    }

    /**
     * Displays the provided data on the screen.
     * Concrete display implementations must define how the data is rendered.
     * 
     * @param data The information to be displayed on the screen
     */
    public void printData(String data) {
    	protocol.write(data);
    	System.out.printf("%s:Printing \"%s\".\n",getName(),data);
    }
}
