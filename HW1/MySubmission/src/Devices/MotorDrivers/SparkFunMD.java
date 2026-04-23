package Devices.MotorDrivers;
import Devices.*;
import Protocols.*;
public class SparkFunMD extends MotorDriver {
    public SparkFunMD(SPI protocol) {
        super(protocol);
    }

    @Override
    public String getName() {
        return "SparkFunMD";
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
