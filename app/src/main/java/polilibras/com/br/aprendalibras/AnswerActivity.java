package polilibras.com.br.aprendalibras;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends VideoActivity {

    public static final String INTENT_QUESTION_ID = "INTENT_QUESTION_ID";
    public static final String INTENT_CORRECT_ANSWER_BOOL = "INTENT_CORRECT_ANSWER_BOOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_activity);

        Intent intent = getIntent();
        long questionId = intent.getLongExtra(INTENT_QUESTION_ID, -1);
        boolean correctAnswer = intent.getBooleanExtra(INTENT_CORRECT_ANSWER_BOOL, false);

        Question question = QuestionProvider.getInstance().getQuestion(questionId);
        mVideoView.setVideoURI(Uri.parse(question.getSource()));

        TextView msgTxt = (TextView) findViewById(R.id.answer_msg_txt);

        if (correctAnswer) {
            msgTxt.setText("PARABÉNS!!!");
        } else {
            msgTxt.setText("NÃO DEU...");
        }

        TextView answerTxt = (TextView) findViewById(R.id.answer_correct_txt);
        answerTxt.setText(getResources().getString(R.string.correct_answer, question.getOptions().get(question.getCorrectAnswer())));

        findViewById(R.id.answer_next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
