package polilibras.com.br.aprendalibras;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    protected QuestionActivity activity = this;

    private VideoView mVideoView;
    private View mOptionsPanel;
    private View mAnswerPanel;

    private Question mCurrentQuestion;

    // Options
    private List<Button> mOptionButtons;

    // Answer
    private TextView mAnswerMsg;
    private TextView mCorrectAnswerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        mCurrentQuestion = QuestionProvider.getInstance().getNextQuestion();

        initQuestion();
        initOptions();
        initAnswer();

        showQuestion();
        showOptions();
    }

    private void initQuestion() {

        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });
        findViewById(R.id.video_repeat_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.seekTo(0);
                mVideoView.start();
            }
        });
    }

    private void showQuestion() {
        mVideoView.setVideoURI(Uri.parse(mCurrentQuestion.getSource()));
    }

    private void initOptions() {
        mOptionsPanel = findViewById(R.id.options_layout);
        mOptionButtons = new ArrayList<>(4);
        mOptionButtons.add((Button) findViewById(R.id.question_option1_btn));
        mOptionButtons.add((Button) findViewById(R.id.question_option2_btn));
        mOptionButtons.add((Button) findViewById(R.id.question_option3_btn));
        mOptionButtons.add((Button) findViewById(R.id.question_option4_btn));
        for (int i = 0; i < mOptionButtons.size(); i++) {
            mOptionButtons.get(i).setOnClickListener(new OptionClickListener(i));
        }
    }

    private void showOptions() {
        mOptionsPanel.setVisibility(View.VISIBLE);
        mAnswerPanel.setVisibility(View.GONE);

        for (int i = 0; i < mOptionButtons.size(); i++) {
            mOptionButtons.get(i).setText(mCurrentQuestion.getOptions().get(i).toUpperCase());
        }
    }

    private void initAnswer() {
        mAnswerPanel = findViewById(R.id.answer_layout);
        mAnswerMsg = (TextView) findViewById(R.id.answer_msg_txt);
        mCorrectAnswerTxt = (TextView) findViewById(R.id.answer_correct_txt);
        findViewById(R.id.answer_next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestion = QuestionProvider.getInstance().getNextQuestion();
                showQuestion();
                showOptions();
            }
        });
    }

    private void showAnswer(int option) {

        mOptionsPanel.setVisibility(View.GONE);
        mAnswerPanel.setVisibility(View.VISIBLE);

        boolean answerIsCorrect = (mCurrentQuestion.getCorrectAnswer() == option);

        mAnswerMsg.setText(answerIsCorrect ? "PARABÉNS!!!" : "NÃO DEU...");
        mCorrectAnswerTxt.setText(getResources().getString(R.string.correct_answer,
                mCurrentQuestion.getOptions().get(mCurrentQuestion.getCorrectAnswer())));


    }

    private class OptionClickListener implements View.OnClickListener {

        private int mOption;

        public OptionClickListener(int option) {
            super();
            mOption = option;
        }

        @Override
        public void onClick(View v) {
            showAnswer(mOption);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
    }
}
