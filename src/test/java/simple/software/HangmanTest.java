package simple.software;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class HangmanTest {

    static Random random;
    static Hangman hangmanUnderTest;
    int requestedLength;

    @BeforeAll
    static void setupClass() {
        random = new Random();
        hangmanUnderTest = new Hangman();
        hangmanUnderTest.loadWords();
    }

    @BeforeEach
    void setUp() {
        requestedLength = random.nextInt(6) + 5;
    }

    @Test
    void count_how_many_of_a_character_a_word_has_test() {
        // Arrange
        final String word = "pizza";
        char alphabetCharacterToBeCounted = 'z';
        final var expectedCount = 2;

        // Act
        int actualCount = hangmanUnderTest.countCharactersInAWord(word, alphabetCharacterToBeCounted);

        // Assert
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    void count_how_many_of_a_character_a_word_has_with_forEach_test() {
        // Arrange
        final String word = "pizza";
        final var expectedCount = 1;
        final var alphabetCharacterToBeCounted = 'a';

        // Act
        final var actualCount = hangmanUnderTest.countCharactersInAWordOldVersion(word, alphabetCharacterToBeCounted);

        // Assert
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    void test_fetch_word_random() {
        // Arrange
        final var random = new Random();

        // Act
        final var word = hangmanUnderTest.fetchWord(requestedLength);
        final var actualLength = word.length();

        // Assert
        assertThat(actualLength).isEqualTo(requestedLength);
    }

    @Test
    void test_uniquenessOfFetchedWord() {


        final var usedWordsSet = new HashSet<>();
        var round = 0;

        String word;

        while (round < 100) {
            word = hangmanUnderTest.fetchWord(requestedLength);
            round++;

            assertThat(usedWordsSet.add(word)).isEqualTo(true);
        }
    }

    @Test
    void test_fetchClueBeforeAnyGuess() {
        // Arrange
        final var expectedClue = "-----";

        // Act
        final var actualClue = hangmanUnderTest.fetchClue("pizza");

        // Assert
        assertThat(actualClue).isEqualTo(expectedClue);
    }

    @Test
    void test_fetchClueAfterCurrentGuess() {
        // Arrange
        final var hangmanUnderTest = new Hangman();
        final var expectedClue = "----a";

        // Act
        final var actualClue = hangmanUnderTest.fetchClue("pizza");
        final var actualNewClue = hangmanUnderTest.fetchClue("pizza", actualClue, 'a');

        // Assert
        assertThat(actualNewClue).isEqualTo(expectedClue);
    }

    @Test
    void test_fetchClueAfterWrongGuess() {
        // Arrange
        final var expectedClue = "-----";

        // Act
        final var actualClue = hangmanUnderTest.fetchClue("pizza");
        final var actualNewClue = hangmanUnderTest.fetchClue("pizza", actualClue, 'x');

        // Assert
        assertThat(actualNewClue).isEqualTo(expectedClue);
    }
}
