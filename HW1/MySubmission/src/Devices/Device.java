package Devices;
import Protocols.*;
public abstract class Device{
    protected Protocol protocol;
    protected State state = State.OFF;

    public Device(Protocol protocol) {
        this.protocol = protocol;
    }

    public abstract String getName();
    public abstract String getDevType();
    public abstract void turnON();  // Abstract: En alt sınıflar implemente edecek
    public abstract void turnOFF(); // Abstract: En alt sınıflar implemente edecek

    public State getState() {
        return state;
    }
}
