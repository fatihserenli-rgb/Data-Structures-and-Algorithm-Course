package Main.Devices.MotorDrivers;
import Main.Devices.*;
import Main.Protocols.*;

/**
 * Concrete implementation of the PCA9685 PWM motor driver.
 * This class provides specific control for the PCA9685 I²C PWM driver chip,
 * commonly used for controlling multiple motors or servos.
 * <p>
 * Implements motor speed control and power management through a specified protocol.
 */
public class PCA9685 extends MotorDriver {
    /**
     * Constructs a new PCA9685 motor driver instance with the specified communication protocol.
     * 
     * @param protocol The communication protocol implementation to be used
     *                 for controlling the PCA9685 chip (typically I²C)
     */
    public PCA9685(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Gets the name of this specific motor driver.
     * 
     * @return The constant string "PCA9685" representing this driver's model name
    */
    @Override
    public String getName() {
        return "PCA9685";
    }

    /**
     * Sets the speed for the connected motor.
     * <p>
     * The actual speed control is implemented by sending PWM commands through
     * the configured protocol to the PCA9685 chip.
     * 
     * @param speed The desired speed value (0-100 range representing percentage
     *              of maximum speed)
     
    @Override
    public void setMotorSpeed(int speed) {
        protocol.write("Speed: " + speed);
    }*/

    /**
     * Powers on the PCA9685 motor driver.
     * <p>
     * Performs the following operations:
     * <ol>
     *   <li>Sets the device state to ON</li>
     *   <li>Sends a power-on command through the protocol</li>
     *   <li>Prints a confirmation message to standard output</li>
     * </ol>
     */
    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON.");
    }

    /**
     * Powers off the PCA9685 motor driver.
     * <p>
     * Performs the following operations:
     * <ol>
     *   <li>Sets the device state to OFF</li>
     *   <li>Sends a power-off command through the protocol</li>
     *   <li>Prints a confirmation message to standard output</li>
     * </ol>
     */
    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF.");
    }
}
