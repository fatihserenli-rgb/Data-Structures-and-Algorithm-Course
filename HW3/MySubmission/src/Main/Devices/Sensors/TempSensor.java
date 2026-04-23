package Main.Devices.Sensors;

/**
 * Abstract base class for temperature sensor devices.
 */
public abstract class TempSensor extends Sensor {
    /**
     * Constructs a new temperature sensor instance.
     */
    public TempSensor() {
    }

    /**
     * Gets the current temperature reading.
     * @return The temperature value in Celsius
     */
    public float getTemp() {
    	protocol.read();
    	return 24.00f;
    }

     /**
     * Gets the sensor type identifier.
     * @return "TempSensor" string
     */
    @Override
    public String getSensType() {
        return "TempSensor";
    }

    /**
     * Formats the temperature data as a string.
     * @return Formatted string with temperature value
     */
    @Override
    public String data2String() {
        return String.format("Temperature: %.2fC.", getTemp());
    }
}

