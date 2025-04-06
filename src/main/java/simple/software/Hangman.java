package simple.software;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hangman {
    private final Set<String> usedWordsSet = new HashSet<>();
    private final List<String> wordsList = new ArrayList<>();

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
        for (String result : wordsList) {
            if (result.length() != requestedLength) continue;
            else if (usedWordsSet.add(result)) return result;
        }
        return null;
    }

    public void loadWords() {
        String word;

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Coding\\WordSource.txt"))) {
            while ((word = br.readLine()) != null) {
                wordsList.add(word);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String fetchClue(String word) {
        return "-".repeat(word.length());
    }

    public String fetchClue(String word, String clue, char guess) {
        StringBuilder newClue = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (guess == word.charAt(i) && guess != clue.charAt(i)) {
                newClue.append(guess);
            } else {
                newClue.append(clue.charAt(i));
            }
        }
        return newClue.toString();
    }
}
