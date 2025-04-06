package simple.software;

public class WordScore {
    private String word;
    private Integer score;

    public WordScore(String word, int score) {
        this.word = word;
        this.score = score;
    }

    public String getWord() {
        return word;
    }

    public Integer getScore() {
        return score;
    }
}
