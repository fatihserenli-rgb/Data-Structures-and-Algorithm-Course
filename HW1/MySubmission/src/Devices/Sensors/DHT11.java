package Devices.Sensors;
import Devices.*;
import Protocols.*;
public class DHT11 extends TempSensor {
    public DHT11(OneWire protocol) {
        super(protocol);
    }

    @Override
    public String getName() {
        return "DHT11";
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
