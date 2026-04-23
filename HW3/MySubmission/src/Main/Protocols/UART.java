package Main.Protocols;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.Iterator;
/**
 * UART (Universal Asynchronous Receiver-Transmitter) protokolünü implemente eden sınıftır.
 * Veri okuma, yazma ve log kaydı yapma işlemlerini yönetir.
 */
public class UART implements Protocol{
    /** Protokol işlemlerini kaydetmek için kullanılan mesaj yığını. */
    Stack<String> messages;

    /**
     * UART protokolünü başlatır ve bağlantının açıldığını belirten 
     * bir mesaj ekler.
     */
    public UART() {
        messages = new Stack<>();
        messages.push("Port Opened.");
    }

    /**
     * UART protokolünün adını döndürür.
     *
     * @return "UART" protokol adı.
     */
    @Override
    public String getProtocolName() {
        return "UART";
    }

    /**
     * UART bağlantısından veri okuma işlemini simüle eder.
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
     * UART bağlantısına veri yazma işlemini simüle eder.
     * Yazılan veri bir mesaj olarak kaydedilir.
     *
     * @param data Yazılacak veri.
     */
    @Override
    public void write(String data) {
        messages.push("Writing \"" + data + "\".");
    }

    /**
     * UART bağlantısını kapatır ve tüm mesajları belirtilen log 
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
