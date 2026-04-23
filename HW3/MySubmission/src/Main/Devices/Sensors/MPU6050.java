package Main.Devices.Sensors;
import Main.Devices.*;
import Main.Protocols.*;
/**
 * Concrete implementation of MPU6050 6-axis IMU sensor.
 */
public class MPU6050 extends IMUSensor {
    /**
     * Constructs a new MPU6050 sensor with specified protocol.
     * @param protocol The communication protocol implementation
     */
    public MPU6050(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this sensor.
     * @return "MPU6050" string
     */
    @Override
    public String getName() {
        return "MPU6050";
    }

    /**
     * Gets the current acceleration reading.
     * @return Acceleration value in m/s²
     
    @Override
    public float getAccel() {
        return 1.00f;
    }*/

    /**
     * Gets the current rotation rate reading.
     * @return Rotation rate in degrees/s
     
    @Override
    public float getRot() {
        return 0.50f;
    }*/

    /**
     * Powers on the MPU6050 sensor.
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the MPU6050 sensor.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
