package Main.Devices.WirelessIOs;
import Main.Devices.*;
import Main.Protocols.*;

/**
 * Concrete implementation of a WiFi wireless communication device.
 */
public class Wifi extends WirelessIO {
    /**
     * Constructs a new WiFi device with the specified communication protocol.
     * @param protocol The WiFi protocol implementation
     */
    public Wifi(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this wireless device.
     * @return The device name
     */
    @Override
    public String getName() {
        return "Wifi";
    }

    /**
     * Powers on the WiFi device.
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the WiFi device.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
