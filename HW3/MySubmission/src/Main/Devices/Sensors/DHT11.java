package Main.Devices.Sensors;
import Main.Devices.*;
import Main.Protocols.*;

/**
 * Concrete implementation of DHT11 temperature and humidity sensor.
 */
public class DHT11 extends TempSensor {
    /**
     * Constructs a new DHT11 sensor with specified protocol.
     * @param protocol The communication protocol implementation
     */
    public DHT11(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this sensor.
     * @return "DHT11" string
     */
    @Override
    public String getName() {
        return "DHT11";
    }

    /**
     * Gets the current temperature reading.
     * @return Temperature value in Celsius
     
    @Override
    public float getTemp() {
        return 24.00f;
    }*/

    /**
     * Powers on the DHT11 sensor.
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the DHT11 sensor.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
