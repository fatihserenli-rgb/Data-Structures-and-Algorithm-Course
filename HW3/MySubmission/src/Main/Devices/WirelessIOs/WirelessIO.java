package Main.Devices.WirelessIOs;
import Main.Devices.*;

/**
 * Abstract base class representing a wireless input/output device.
 * Provides the fundamental structure for wireless communication devices
 * capable of sending and receiving data.
 * <p>
 * Concrete implementations must extend this class to provide specific
 * wireless protocol implementations (e.g., WiFi, Bluetooth, Zigbee).
 */
public abstract class WirelessIO extends Device{
    /**
     * Constructs a new WirelessIO device instance.
     * Initializes the device with default wireless settings.
     */
    public WirelessIO() {
    }

    /**
     * Gets the device type identifier for this wireless I/O device.
     * 
     * @return The constant string "WirelessIO" representing this device type
     */
    @Override
    public String getDevType() {
        return "WirelessIO";
    }

    /**
     * Sends data wirelessly to a remote device.
     * <p>
     * Concrete implementations must define the specific wireless transmission
     * protocol and handle any necessary data formatting or encoding.
     * 
     * @param data The data to be transmitted wirelessly
     */
    public void sendData(String data){
    	protocol.write(data);
    	System.out.printf("%s:Sending \"%s\".\n",getName(),data);
    }

    /**
     * Receives data from a wireless connection.
     * <p>
     * Concrete implementations must define the specific wireless reception
     * protocol and handle any necessary data decoding or validation.
     * 
     * @return The received data as a String
     */
    public String recvData(){
    	protocol.read();
    	return getName() + ":" + "Received \"Some Data\".";
    }
}
