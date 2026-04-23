import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpellChecker {
    private GTUHashSet<String> dictionary;

    public SpellChecker(String dictionaryPath) throws IOException {
        dictionary = new GTUHashSet<>();
        loadDictionary(dictionaryPath);
    }

    private void loadDictionary(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String word;
        while ((word = reader.readLine()) != null) {
            dictionary.add(word.trim().toLowerCase());
        }
        reader.close();
    }

    private GTUHashSet<String> delete1(String word) {
        GTUHashSet<String> variants = new GTUHashSet<>();
        for (int i = 0; i < word.length(); i++) {
            variants.add(word.substring(0, i) + word.substring(i + 1));
        }
        return variants;
    }

    private GTUHashSet<String> insert1(String word) {
        GTUHashSet<String> variants = new GTUHashSet<>();
        for (int i = 0; i <= word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                variants.add(word.substring(0, i) + c + word.substring(i));
            }
        }
        return variants;
    }

    private GTUHashSet<String> replace1(String word) {
        GTUHashSet<String> variants = new GTUHashSet<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                variants.add(word.substring(0, i) + c + word.substring(i + 1));
            }
        }
        return variants;
    }
    
    private String[][] generateEditDistance1(String word) {
        String[][] variants = new String[3][];
        String lowerWord = word.toLowerCase();
        variants[0] = (delete1(lowerWord).getAll());
        variants[1] = (insert1(lowerWord)).getAll();
        variants[2] = (replace1(lowerWord)).getAll();
        return variants;
    }

    private String[] generateEditDistance2(String[][] distance1Variants) {
        GTUHashSet<String> distance2Variants = new GTUHashSet<>();
        String[] deleteds = distance1Variants[0];
        String[] inserteds = distance1Variants[1];
        String[] replaceds = distance1Variants[2];
        for(String str : deleteds) {
            for(String variant : delete1(str).getAll()) {
                if(dictionary.contains(variant)) {
                    distance2Variants.add(variant);
                }
            }
            for(String variant : replace1(str).getAll()) {
                if(dictionary.contains(variant)) {
                    distance2Variants.add(variant);
                }
            }
        }
        for(String str : inserteds) {
            for(String variant : insert1(str).getAll()) {
                if(dictionary.contains(variant)) {
                    distance2Variants.add(variant);
                }
            }
            for(String variant : replace1(str).getAll()) {
                if(dictionary.contains(variant)) {
                    distance2Variants.add(variant);
                }
            }
        }
        for(String str : replaceds) {
            for(String variant : replace1(str).getAll()) {
                if(dictionary.contains(variant)) {
                    distance2Variants.add(variant);
                }
            }
        }
        return distance2Variants.getAll();
    }

    public void suggestCorrections(String word) {
        if (dictionary.contains(word)) {
            System.out.println("Found in dictionary!");
            return;
        }

        System.out.println("Not found in dictionary! Suggestions:");

        // Distance 1 kontrolü
        System.out.println("--- Edit Distance 1 ---");
        String[][] distance1variants = generateEditDistance1(word);
        for (String[] variantarr : distance1variants) {
            for(String variant : variantarr) {
                if (dictionary.contains(variant)) {
                    System.out.print(variant + ", ");
                }
            }
        }

        // Distance 2 kontrolü
        System.out.println("\n--- Edit Distance 2 ---");
        for (String variant : generateEditDistance2(distance1variants)) {
            System.out.print(variant + ", ");
        }
    }   
}