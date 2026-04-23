package Devices.WirelessIOs;
import Devices.*;
import Protocols.*;
public class Wifi extends WirelessIO {
    public Wifi(Protocol protocol) {
        super(protocol);
        if (!(protocol instanceof SPI || protocol instanceof UART)) {
            throw new IllegalArgumentException("WiFi only supports SPI and UART protocols.");
        }
    }

    @Override
    public String getName() {
        return "Wifi";
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
