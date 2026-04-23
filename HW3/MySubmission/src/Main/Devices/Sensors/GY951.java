package Main.Devices.Sensors;
import Main.Devices.*;
import Main.Protocols.*;
/**
 * Concrete implementation of GY951 IMU sensor.
 */
public class GY951 extends IMUSensor {
    /**
     * Constructs a new GY951 sensor with specified protocol.
     * @param protocol The communication protocol implementation
     */
    public GY951(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this sensor.
     * @return "GY951" string
     */
    @Override
    public String getName() {
        return "GY951";
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
     * Powers on the GY951 sensor.
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

     /**
     * Powers off the GY951 sensor.
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
