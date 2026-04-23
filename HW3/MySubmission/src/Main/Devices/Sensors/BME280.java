package Main.Devices.Sensors;
import Main.Devices.*;
import Main.Protocols.*;
/**
 * Concrete implementation of BME280 environmental sensor (temperature).
 */
public class BME280 extends TempSensor {
    /**
     * Constructs a new BME280 sensor with specified protocol.
     * @param protocol The communication protocol implementation
     */
    public BME280(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this sensor.
     * @return "BME280" string
     */
    @Override
    public String getName() {
        return "BME280";
    }

    /**
     * Gets the current temperature reading.
     * @return Temperature value in Celsius
     
    @Override
    public float getTemp() {
        return 24.00f;
    }*/

    /**
     * Powers on the BME280 sensor.
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the BME280 sensor.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
