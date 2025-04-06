package simple.software;

import java.util.HashMap;
import java.util.Map;

public class ScoreDB {
    private final Map<String, Integer> wordScoreMap = new HashMap<>();
    public boolean writeScoreDBCalled;

    public boolean writeScoreDB(WordScore wordScore) {
        writeScoreDBCalled = true;

        wordScoreMap.put(wordScore.getWord(), wordScore.getScore());
        return true;
    }

    public double readScoreDB(String word) {
        return wordScoreMap.get(word);
    }
}

interface MockScoreDB {
    int getValue(String key);

    Boolean writeScoreDB(String word, Double score);
}
