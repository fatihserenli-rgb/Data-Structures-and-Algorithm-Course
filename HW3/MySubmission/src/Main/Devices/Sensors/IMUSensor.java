package Main.Devices.Sensors;

/**
 * Abstract base class for IMU (Inertial Measurement Unit) sensor devices.
 */
public abstract class IMUSensor extends Sensor {

    /**
     * Constructs a new IMU sensor instance.
     */
    public IMUSensor() {
    }

    /**
     * Gets the current acceleration reading.
     * @return The acceleration value in m/s²
     */
    public float getAccel(){
    	protocol.read();
    	return 1.00f;
    }

    /**
     * Gets the current rotation rate reading.
     * @return The rotation rate in degrees/s
     */
    public float getRot(){
    	protocol.read();
    	return 0.50f;
    }

    /**
     * Gets the sensor type identifier.
     * @return "IMUSensor" string
     */
    @Override
    public String getSensType() {
        return "IMUSensor";
    }

    /**
     * Formats the IMU data as a string.
     * @return Formatted string with acceleration and rotation values
     */
    @Override
    public String data2String() {
        return String.format("Accel: %.2f m/s, Rot: %.2f/s.", getAccel(), getRot());
    }
}
