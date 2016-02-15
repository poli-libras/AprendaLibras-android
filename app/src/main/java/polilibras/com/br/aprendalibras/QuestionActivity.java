package polilibras.com.br.aprendalibras;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends VideoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        Question question = QuestionProvider.getInstance().getNextQuestion();
        mVideoView.setVideoURI(Uri.parse(question.getSource()));

        List<Button> optionBtns = new ArrayList<>(4);
        optionBtns.add((Button) findViewById(R.id.question_option1_btn));
        optionBtns.add((Button) findViewById(R.id.question_option2_btn));
        optionBtns.add((Button) findViewById(R.id.question_option3_btn));
        optionBtns.add((Button) findViewById(R.id.question_option4_btn));

        for (int i = 0; i < optionBtns.size(); i++) {
            optionBtns.get(i).setText(question.getOptions().get(i).toUpperCase());
            optionBtns.get(i).setOnClickListener(new OptionClickListener(activity, question.getCorrectAnswer() == i));
        }
    }

    private class OptionClickListener implements View.OnClickListener {

        private boolean mCorrectOption;
        private Context mContext;

        public OptionClickListener(Context context, boolean correctOption) {
            mContext = context;
            mCorrectOption = correctOption;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, AnswerActivity.class);
            intent.putExtra(AnswerActivity.INTENT_CORRECT_ANSWER_BOOL, mCorrectOption);
            startActivity(intent);
            finish();
        }
    }
}
