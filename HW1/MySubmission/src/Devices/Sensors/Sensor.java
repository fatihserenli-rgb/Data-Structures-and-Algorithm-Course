package Devices.Sensors;
import Devices.*;
import Protocols.*;
public abstract class Sensor extends Device{
    public Sensor(Protocol protocol) {
        super(protocol);
    }

    public abstract String getSensType();
    public abstract String data2String();

    @Override
    public String getDevType() {
        return getSensType() + " Sensor";
    }
}
