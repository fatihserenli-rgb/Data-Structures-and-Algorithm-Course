package Devices.Sensors;
import Devices.*;
import Protocols.*;
public class MPU6050 extends IMUSensor {
    public MPU6050(I2C protocol) {
        super(protocol);
    }

    @Override
    public String getName() {
        return "MPU6050";
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
