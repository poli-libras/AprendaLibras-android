package polilibras.com.br.aprendalibras;

import android.net.Uri;

import java.util.Arrays;

/**
 * Created by marce on 15/02/2016.
 */
public class QuestionProvider {

    private static final QuestionProvider instance = new QuestionProvider();

    private QuestionProvider() {

    }

    public static QuestionProvider getInstance() {
        return instance;
    }


    public Question getNextQuestion() {

        Question q = new Question("android.resource://polilibras.com.br.aprendalibras/" + R.raw.bola, Arrays.asList("Casa", "Bola", "Sapato", "Carro"), 1);

        return q;
    }

    public Question getQuestion(Long id) {
        Question q = new Question("android.resource://polilibras.com.br.aprendalibras/" + R.raw.bola, Arrays.asList("Casa", "Bola", "Sapato", "Carro"), 1);

        return q;
    }


}
