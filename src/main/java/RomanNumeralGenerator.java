public class RomanNumeralGenerator {
    public static Object romanFor(int decimal) {
        StringBuilder roman = new StringBuilder();

        for (RomanToDecimal romanToDecimal : RomanToDecimal.values()) {
            while (decimal >= romanToDecimal.decimal) {
                roman.append(romanToDecimal.roman);
                decimal -= romanToDecimal.decimal;
            }
        }

        return roman.toString();
    }

    enum RomanToDecimal {
        THOUSAND("M", 1000),
        NINE_HUNDRED("CM", 900),
        FIVE_HUNDRED("D", 500),
        FOUR_HUNDRED("CD", 400),
        HUNDRED("C", 100),
        NINETY("C", 90),
        FIFTY("L", 50),
        FORTY("XL", 40),
        TEN("X", 10),
        NINE("IX", 9),
        FIVE("V", 5),
        FOUR("IV", 4),
        ONE("I", 1);

        private final String roman;
        private final int decimal;

        RomanToDecimal(String roman, int decimal) {
            this.roman = roman;
            this.decimal = decimal;
        }
    }
}
