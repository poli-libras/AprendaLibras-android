package polilibras.com.br.aprendalibras;

import android.net.Uri;

import java.net.URI;
import java.util.List;

/**
 * Created by marce on 15/02/2016.
 */
public class Question {

    private static final String PACKAGE_NAME = "polilibras.com.br.aprendalibras";

    private Long id;

    private String source;

    private List<String> options;

    private int correctAnswer;

    public Question() {

    }

    public Question(long id, String source, List<String> options, int correctAnswer) {
        this.id = id;
        this.source = source;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
