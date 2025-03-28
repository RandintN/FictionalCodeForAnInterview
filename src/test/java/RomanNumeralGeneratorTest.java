import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class RomanNumeralGeneratorShould {

    @Test
    public void generate_a_roman_numeral_for_a_given_decimal_number() {
        assertThat(RomanNumeralGenerator.romanFor(1)).isEqualTo("I");
        assertThat(RomanNumeralGenerator.romanFor(2)).isEqualTo("II");
    }
}
