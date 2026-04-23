package Devices.MotorDrivers;
import Devices.*;
import Protocols.*;
public abstract class MotorDriver extends Device{
    
    public MotorDriver(Protocol protocol) {
        super(protocol);
    }

    @Override
    public String getDevType() {
        return "MotorDriver";
    }

    public void setMotorSpeed(int speed) {
        protocol.write("Speed: " + speed);
    }
}
