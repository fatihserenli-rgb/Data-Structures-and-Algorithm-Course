package Main.Protocols;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.Iterator;
/**
 * SPI (Serial Peripheral Interface) protokolünü implemente eden sınıftır.
 * Veri okuma, yazma ve log kaydı yapma işlemlerini yönetir.
 */
public class SPI implements Protocol{
    /** Protokol işlemlerini kaydetmek için kullanılan mesaj yığını. */
    Stack<String> messages;

    /**
     * SPI protokolünü başlatır ve bağlantının açıldığını belirten 
     * bir mesaj ekler.
     */
    public SPI() {
        messages = new Stack<>();
        messages.push("Port Opened.");
    }

    /**
     * SPI protokolünün adını döndürür.
     *
     * @return "SPI" protokol adı.
     */
    @Override
    public String getProtocolName() {
        return "SPI";
    }

    /**
     * SPI bağlantısından veri okuma işlemini simüle eder.
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
     * SPI bağlantısına veri yazma işlemini simüle eder.
     * Yazılan veri bir mesaj olarak kaydedilir.
     *
     * @param data Yazılacak veri.
     */
    @Override
    public void write(String data) {
        messages.push("Writing \"" + data + "\".");
    }

    /**
     * SPI bağlantısını kapatır ve tüm mesajları belirtilen log 
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
