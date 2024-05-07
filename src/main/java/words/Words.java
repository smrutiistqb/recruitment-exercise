package words;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
        String[] words = sentence.split("\\s+");
        Set<String> uniqueWordsSet = new HashSet<String>();
        for (String s : words) {
            uniqueWordsSet.add(s);
        }
        return new ArrayList<String>(uniqueWordsSet);

    }
}
