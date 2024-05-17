import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'noPrefix' function below.
     *
     * The function accepts STRING_ARRAY words as parameter.
     */

    public static void noPrefix(List<String> words) {
        // Need to move forward while comparing each new word
        // against the set of already seen words

        // A more efficient approach involves using TrieNode ds (this is not that approach)
        String setRes = "GOOD SET";
        String resWord = "";

        if (words.size() == 1) {
            System.out.println(setRes);
            return;
        }

        Set<String> seen = new HashSet<>();
        seen.add(words.get(0));

        for (int i = 1; i < words.size(); i++) {
            String unseenWord = words.get(i);

            for (String seenWord : seen) {
                if (unseenWord.startsWith(seenWord) || seenWord.startsWith(unseenWord)) {
                    setRes = "BAD SET";
                    resWord = unseenWord;
                    break;
                }
            }
            if (!resWord.isEmpty()) break;
            seen.add(unseenWord);
        }
        System.out.println(setRes);
        if (!resWord.isEmpty()) System.out.println(resWord);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        Result.noPrefix(words);

        bufferedReader.close();
    }
}
