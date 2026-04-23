package Main.Protocols;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.Iterator;
/**
 * I2C (Inter-Integrated Circuit) protokolünü implemente eden sınıftır.
 * Veri okuma, yazma ve log kaydı yapma işlemlerini yönetir.
 */
public class I2C implements Protocol{
    /** Protokol işlemlerini kaydetmek için kullanılan mesaj yığını. */
    Stack<String> messages;

    /**
     * I2C protokolünü başlatır ve bağlantının açıldığını belirten 
     * bir mesaj ekler.
     */
    public I2C() {
        messages = new Stack<>();
        messages.push("Port Opened.");
    }

    /**
     * I2C protokolünün adını döndürür.
     *
     * @return "I2C" protokol adı.
     */
    @Override
    public String getProtocolName() {
        return "I2C";
    }

    /**
     * I2C bağlantısından veri okuma işlemini simüle eder.
     * Okuma işlemi bir mesaj olarak kaydedilir.
     *
     * @return Okuma işleminin mesajı.
     */
    @Override
    public String read() {
        messages.push("Reading.");
        return "Reading.";
    }

    /**
     * I2C bağlantısına veri yazma işlemini simüle eder.
     * Yazılan veri bir mesaj olarak kaydedilir.
     *
     * @param data Yazılacak veri.
     */
    @Override
    public void write(String data) {
        messages.push("Writing \"" + data + "\".");
    }

    /**
     * I2C bağlantısını kapatır ve tüm mesajları belirtilen log 
     * dosyasına kaydeder.
     *
     * @param log_path Log dosyasının kaydedileceği dizin.
     */
    @Override
    public void close(String log_path) {
        try{
            FileWriter writer = new FileWriter(log_path);
            Iterator<String> iterator = messages.iterator();
            while(iterator.hasNext()) {
                writer.write(messages.pop() + "\n");
            }
            writer.close();
        }
        catch (IOException e) {
                System.err.println("Log yazma hatası: " + e.getMessage());
        }
    }
}
