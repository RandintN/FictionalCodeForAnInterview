package simple.software;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hangman {
    public ScoreDB scoreDB = new ScoreDB();

    public static final String PATH = "C:\\Coding\\WordSource.txt";
    public static final int MAX_TRIALS = 10;

    private final Set<String> usedWordsSet = new HashSet<>();
    final List<String> wordsList = new ArrayList<>();

    public int remainingTrials;
    public int score;

    MockScoreDB externalDBMock;

    public Hangman(MockScoreDB externalDBMock) {
        this.externalDBMock = externalDBMock;
    }

    public Hangman() {
    }

    public int countCharactersInAWord(String word, char alphabetCharacter) {
        return (int) word.chars()
                .filter(c -> c == alphabetCharacter)
                .count();
    }

    public int countCharactersInAWordOldVersion(String word, char alphabetCharacter) {
        int result = 0;

        for (char eachLetter : word.toCharArray()) {
            if (eachLetter == alphabetCharacter) result++;
        }
        return result;
    }

    public String fetchWord(int requestedLength) {
        remainingTrials = MAX_TRIALS;

        for (String result : wordsList) {
            if (result.length() != requestedLength) continue;
            else if (usedWordsSet.add(result)) return result;
        }
        return null;
    }

    public void loadWords() {
        var path = Paths.get(PATH);

        try (var lines = Files.lines(path)) {
            lines.map(String::trim)
                    .filter(trim -> !trim.isEmpty())
                    .forEach(wordsList::add);
        } catch (IOException e) {
            e.printStackTrace(); // logging later
        }

    }

    public String fetchClue(String word) {
        return "-".repeat(word.length());
    }

    public String fetchClue(String word, String clue, char guess) {
        remainingTrials--;

        if (guess >= 'A' && guess <= 'Z') guess += 32;
        if (guess < 'a' || guess > 'z') throw new IllegalArgumentException("Blah");

        StringBuilder newClue = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (guess == word.charAt(i) && guess != clue.charAt(i)) {
                newClue.append(guess);
                score += MAX_TRIALS / word.length();
            } else {
                newClue.append(clue.charAt(i));
            }
        }
        return newClue.toString();
    }

    public boolean saveScore(WordScore wordScore) {
        return scoreDB.writeScoreDB(wordScore);
    };
}
