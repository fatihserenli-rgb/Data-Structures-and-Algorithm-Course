package Main;
import Main.HWSystem;
public class Main {
    public static void main(String[] args) {
        HWSystem sistem = new HWSystem("configfile.txt");
        sistem.run();
    }
}
