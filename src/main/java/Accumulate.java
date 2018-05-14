import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accumulate {

    private static final String FILE_PATH = "enetr your path"; 

    private static final String SEPARATOR = "( )";

    public static void main(String[] args) {


        Map<String, Integer> map = new HashMap<>();
        try (FileReader fr = new FileReader(FILE_PATH);
             BufferedReader br = new BufferedReader(fr)){
            String line;
            int cnt = 0;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(SEPARATOR);
                for (String word : words) {
                    if (!word.isEmpty()) {
                        if (map.containsKey(word)) {
                            int count = map.get(word) + 1;
                            map.put(word, count);
                        } else {
                            map.put(word, 1);
                            cnt++;
                        }
                    }
                }
            }
            System.out.println("The number of words = "+cnt);
        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりませんでした。");
        } catch (IOException e) {
            System.out.println("読み取りに失敗しました。");
        }

        List<String> list = new ArrayList<>();
        int maxLengthOfSpelling = 0;
        for (String key : map.keySet()) {
            list.add(key);

            if (maxLengthOfSpelling < key.length()) {
                maxLengthOfSpelling = key.length();
            }
        }
        Collections.sort(list, (o1, o2) -> {
            return - map.get(o1) + map.get(o2);
        });


        String format = "%-" + maxLengthOfSpelling + "s: %3d";
        for (String word : list) {
            int count = map.get(word);
            if (1045 <= count) {
                System.out.printf(format, word, count);
                System.out.println();
            }
        }
    }
}