package Main.Devices.MotorDrivers;
import Main.Devices.*;
/**
 * Abstract base class representing a motor driver device in the system.
 * Provides the fundamental structure for controlling motor speed and inherits
 * basic device functionality from the Device class.
 * <p>
 * Concrete motor driver implementations must extend this class and provide
 * specific implementations for motor control operations.
 */
public abstract class MotorDriver extends Device{
    /**
     * Constructs a new MotorDriver instance.
     * Initializes the motor driver with default settings.
     */
    public MotorDriver() {
    }

    /**
     * Gets the device type identifier for this motor driver.
     * 
     * @return The constant string "MotorDriver" representing this device type
     */
    @Override
    public String getDevType() {
        return "MotorDriver";
    }

    /**
     * Sets the operating speed of the connected motor.
     * <p>
     * Concrete implementations must define the specific behavior for speed control,
     * including any range validation or conversion to physical units (e.g., RPM).
     * 
     * @param speed The desired speed value to set (implementation-specific units)
     */
    public void setMotorSpeed(int speed) {
    	protocol.write("Speed: " + speed);
    	System.out.printf("%s:Setting speed to %d.\n",getName(),speed);
    }
}
