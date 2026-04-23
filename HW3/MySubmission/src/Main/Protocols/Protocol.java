package Main.Protocols;
/**
 * Farklı iletişim protokolleri için bir arayüz sağlar.
 * Bu arayüz, protokol adını almayı, veri okumayı/yazmayı 
 * ve protokolü kapatmayı tanımlar.
 */
public interface Protocol {
    /**
     * Protokolün adını döndürür.
     *
     * @return Protokol adı.
     */
    String getProtocolName();

    /**
     * Protokolden veri okur.
     *
     * @return Okunan veri.
     */
    String read();

    /**
     * Protokole veri yazar.
     *
     * @param data Yazılacak veri.
     */
    void write(String data);

    /**
     * Protokolü kapatır ve isteğe bağlı olarak 
     * belirlenen log dizinine kayıt yapar.
     *
     * @param log_path Log dosyasının kaydedileceği dizin.
     */
    public void close(String log_path);
}


