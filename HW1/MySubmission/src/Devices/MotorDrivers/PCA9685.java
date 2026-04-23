package Devices.MotorDrivers;
import Devices.*;
import Protocols.*;
public class PCA9685 extends MotorDriver {
    public PCA9685(I2C protocol) {
        super(protocol);
    }

    @Override
    public String getName() {
        return "PCA9685";
    }

    @Override
    public void turnON() {
        state = State.ON;
        protocol.write("turnON");
        System.out.println(getName() + ": Turning ON");
    }

    @Override
    public void turnOFF() {
        state = State.OFF;
        protocol.write("turnOFF");
        System.out.println(getName() + ": Turning OFF");
    }
}
