package Devices.WirelessIOs;
import Devices.*;
import Protocols.*;
public class Bluetooth extends WirelessIO {
    public Bluetooth(UART protocol) {
        super(protocol);
    }

    @Override
    public String getName() {
        return "Bluetooth";
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