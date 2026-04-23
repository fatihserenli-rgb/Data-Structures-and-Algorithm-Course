package Devices.Sensors;
import java.util.Random;
import Protocols.*;
public abstract class IMUSensor extends Sensor {
    protected Random random = new Random();

    public IMUSensor(Protocol protocol) {
        super(protocol);
    }

    public float getAccel() {
        return random.nextFloat() * 9.8f; // 0 - 9.8 m/s² arasında rastgele bir ivme
    }

    public float getRot() {
        return random.nextFloat() * 360; // 0 - 360 derece/sn arasında rastgele dönüş hızı
    }

    @Override
    public String getSensType() {
        return "IMUSensor";
    }

    @Override
    public String data2String() {
        return String.format("Accel: %.2f m/s², Rot: %.2f°/s", getAccel(), getRot());
    }
}
