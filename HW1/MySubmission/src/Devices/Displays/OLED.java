package Devices.Displays;
import Devices.*;
import Protocols.*;
public class OLED extends Display {
    public OLED(SPI protocol) {
        super(protocol);
    }

    @Override
    public String getName() {
        return "OLED";
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
