package simple.software;

import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DisplayNameGenerator.*;
import static simple.software.Hangman.MAX_TRIALS;

@DisplayNameGeneration(ReplaceUnderscores.class)
@DisplayName("Testes para o jogo da forca")
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
        hangmanUnderTest.score = 0;
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

    @Test
    void test_whenInvalidGuessThenFetchCLueThrowsException() {
        assertThatThrownBy(() -> hangmanUnderTest.fetchClue("pizza", "-----", '1'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Blah");
    }

    @Test
    void test_remainingTrialsBeforeAnyGuess() {
        hangmanUnderTest.fetchWord(requestedLength);
        assertThat(MAX_TRIALS).isEqualTo(hangmanUnderTest.remainingTrials);
    }

    @Test
    void make_a_guess() {
        hangmanUnderTest.fetchWord(requestedLength);
        hangmanUnderTest.fetchClue("pizza", "-----", 'a');

        assertThat(MAX_TRIALS - 1).isEqualTo(hangmanUnderTest.remainingTrials);
    }

    @Test
    void score_before_any_guess() {
        hangmanUnderTest.fetchWord(requestedLength);

        assertThat(hangmanUnderTest.score).isEqualTo(0);
    }

    @Test
    void test_score_after_correct_guess() {
        String word = "pizza";
        String clue = "-----";
        char guess = 'a';

        hangmanUnderTest.fetchClue(word, clue, guess);

        assertThat(hangmanUnderTest.score).isEqualTo(MAX_TRIALS / word.length());
    }
}
