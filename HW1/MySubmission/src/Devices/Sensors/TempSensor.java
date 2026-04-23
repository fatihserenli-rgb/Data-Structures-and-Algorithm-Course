package Devices.Sensors;
import java.util.Random;
import Protocols.*;
// TempSensor.java - Abstract Class (Extends Sensor)
public abstract class TempSensor extends Sensor {
    protected Random random = new Random();

    public TempSensor(Protocol protocol) {
        super(protocol);
    }

    public float getTemp() {
        return 15 + random.nextFloat() * 10; // 15°C - 25°C arasında rastgele bir sıcaklık
    }

    @Override
    public String getSensType() {
        return "TempSensor";
    }

    @Override
    public String data2String() {
        return String.format("Temperature: %.2f°C", getTemp());
    }
}

