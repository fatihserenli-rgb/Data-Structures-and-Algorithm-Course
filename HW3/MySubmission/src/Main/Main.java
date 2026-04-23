package Main;
/**
 * Programın giriş noktası olan ana sınıftır.
 * Program, çalıştırılırken iki argüman almalıdır: 
 * yapılandırma dosyasının adı ve log dizini.
*/
public class Main{
    /**
     * Programın başlangıç noktasıdır.
     * Kullanıcıdan iki komut satırı argümanı bekler:
     * 1. Yapılandırma (config) dosyasının adı
     * 2. Log dosyalarının kaydedileceği dizin
     *
     * @param args Programın çalıştırılma sırasında alınan argümanlar.
     *             args[0] - Yapılandırma dosyasının adı.
     *             args[1] - Log dosyalarının kaydedileceği dizin.
     */
    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("You have to enter two argument(name of cofig file and log directory) while runing the program.Program will be terminated without them\nExiting..");
            return;
        }
        HWSystem sistem = new HWSystem(args[0],args[1]);
        sistem.run();
    }
}
