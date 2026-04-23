package Devices.Sensors;
import Devices.*;
import Protocols.*;
public class GY951 extends IMUSensor {
    public GY951(Protocol protocol) {
        super(protocol);
        if (!(protocol instanceof SPI || protocol instanceof UART)) {
            throw new IllegalArgumentException("GY951 only supports SPI and UART protocols.");
        }
    }

    @Override
    public String getName() {
        return "GY951";
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
