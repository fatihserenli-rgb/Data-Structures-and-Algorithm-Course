package Main.Devices.MotorDrivers;
import Main.Devices.*;
import Main.Protocols.*;

/**
 * Concrete implementation of a SparkFun motor driver device.
 * This class provides control for SparkFun motor driver boards,
 * implementing standard motor control functionality through a specified protocol.
 * <p>
 * Supports basic motor operations including speed control and power management.
 */
public class SparkFunMD extends MotorDriver {
     /**
     * Constructs a new SparkFun motor driver instance with the specified communication protocol.
     * 
     * @param protocol The communication protocol implementation to be used
     *                 for controlling the motor driver (typically Serial or I²C)
     */
    public SparkFunMD(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this specific motor driver implementation.
     * 
     * @return The constant string "SparkFunMD" representing this driver's manufacturer and type
     */
    @Override
    public String getName() {
        return "SparkFunMD";
    }

    /**
     * Sets the speed for the connected motor.
     * <p>
     * The speed control is implemented by sending commands through
     * the configured protocol to the SparkFun motor driver.
     * 
     * @param speed The desired speed value (typically 0-255 range representing
     *              PWM duty cycle or similar control parameter)
     
    @Override
    public void setMotorSpeed(int speed) {
        protocol.write("Speed: " + speed);
    }*/

    /**
     * Powers on the SparkFun motor driver.
     * <p>
     * Performs the following sequence:
     * <ol>
     *   <li>Sets the device state to ON</li>
     *   <li>Sends a power-on command through the protocol</li>
     *   <li>Outputs a confirmation message to standard output</li>
     * </ol>
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the SparkFun motor driver.
     * <p>
     * Performs the following sequence:
     * <ol>
     *   <li>Sets the device state to OFF</li>
     *   <li>Sends a power-off command through the protocol</li>
     *   <li>Outputs a confirmation message to standard output</li>
     * </ol>
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
