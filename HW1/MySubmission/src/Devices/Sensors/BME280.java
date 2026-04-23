package Devices.Sensors;
import Devices.*;
import Protocols.*;
public class BME280 extends TempSensor {
    public BME280(Protocol protocol) {
        super(protocol);
        if (!(protocol instanceof I2C || protocol instanceof SPI)) {
            throw new IllegalArgumentException("BME280 only supports I2C and SPI protocols.");
        }
    }

    @Override
    public String getName() {
        return "BME280";
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
