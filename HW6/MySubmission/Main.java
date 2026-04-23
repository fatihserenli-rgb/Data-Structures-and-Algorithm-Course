import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            SpellChecker checker = new SpellChecker("dictionary.txt");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter a word (or 'q' to exit): ");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("q")) break;

                long startTime = System.nanoTime();
                checker.suggestCorrections(input);
                long endTime = System.nanoTime();

                System.out.printf("\nTime: %.2f ms\n", (endTime - startTime) / 1e6);
            }
            scanner.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
