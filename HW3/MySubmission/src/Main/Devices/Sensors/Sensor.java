package Main.Devices.Sensors;
import Main.Devices.*;

/**
 * Abstract base class representing a sensor device in the system.
 */
public abstract class Sensor extends Device{
    /**
     * Constructs a new Sensor instance.
     */
    public Sensor() {
    }

    /**
     * Gets the specific type of this sensor.
     * @return The sensor type
     */
    public abstract String getSensType();

    /**
     * Converts sensor data to string representation.
     * @return String representation of sensor data
     */
    public abstract String data2String();

    /**
     * Gets the device type (sensor type + " Sensor").
     * @return The full device type string
     */
    @Override
    public String getDevType() {
        return getSensType() + " Sensor";
    }
}
