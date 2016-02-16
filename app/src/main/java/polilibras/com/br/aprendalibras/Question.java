package polilibras.com.br.aprendalibras;

import java.util.List;

/**
 * Created by marce on 15/02/2016.
 */
public class Question {

    private Long id;

    private QuestionType type;

    private String questionText;
    private String questionRes;

    private List<String> options;

    private int correctAnswer;

    public Question() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionRes() {
        return questionRes;
    }

    public void setQuestionRes(String questionRes) {
        this.questionRes = questionRes;
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
