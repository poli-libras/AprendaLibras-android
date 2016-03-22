package br.com.polilibras.aprendalibras;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class QuestionActivity extends AppCompatActivity {

    protected QuestionActivity activity = this;

    private Question mCurrentQuestion;

    // Question
    private VideoView mVideoView;

    // Options
    private View mOptionsTextPanel;
    private View mOptionsImagesPanel;
    private View mOptionsPanel;
    private List<Button> mOptionButtons;
    private List<ImageButton> mOptionImageButtons;
    private int mCurrentCorrectBtnIdx;
    private Button mProximaPerguntaBtn;
    private Button mFimDeJogoBtn;

    // Answer
    private View mAnswerPanel;
    private TextView mAnswerMsg;
    private TextView mCorrectAnswerTxt;
    private int mNumErrors;
    private int mPontuacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        mCurrentQuestion = QuestionProvider.getInstance(activity).getNextQuestion();
        mNumErrors = 0;
        mPontuacao = 0;

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
        mVideoView.stopPlayback();
        int resId = this.getResources().getIdentifier(mCurrentQuestion.getQuestionRes(), "raw", getPackageName());
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + resId));
    }

    private void initOptions() {
        mOptionsTextPanel = findViewById(R.id.options_text_layout);
        mOptionsTextPanel.setVisibility(View.GONE);
        mOptionsImagesPanel = findViewById(R.id.options_img_layout);
        mOptionsImagesPanel.setVisibility(View.GONE);
        mOptionsPanel = mOptionsTextPanel;
        mOptionButtons = new ArrayList<>(4);
        mOptionButtons.add((Button) findViewById(R.id.question_option1_btn));
        mOptionButtons.add((Button) findViewById(R.id.question_option2_btn));
        mOptionButtons.add((Button) findViewById(R.id.question_option3_btn));
        mOptionButtons.add((Button) findViewById(R.id.question_option4_btn));
        mOptionImageButtons = new ArrayList<>(4);
        mOptionImageButtons.add((ImageButton) findViewById(R.id.question_option1_img_btn));
        mOptionImageButtons.add((ImageButton) findViewById(R.id.question_option2_img_btn));
        mOptionImageButtons.add((ImageButton) findViewById(R.id.question_option3_img_btn));
        mOptionImageButtons.add((ImageButton) findViewById(R.id.question_option4_img_btn));
        for (int i = 0; i < mOptionButtons.size(); i++) {
            mOptionButtons.get(i).setOnClickListener(new OptionClickListener(i));
        }
        for (int i = 0; i < mOptionImageButtons.size(); i++) {
            mOptionImageButtons.get(i).setOnClickListener(new OptionClickListener(i));
        }
    }

    private void showOptions() {

        List<Integer> indexes = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(indexes);
        mAnswerPanel.setVisibility(View.GONE);

        switch (mCurrentQuestion.getType()) {
            case IMAGE_OPTIONS:
                mOptionsPanel = mOptionsImagesPanel;
                mOptionsPanel.setVisibility(View.VISIBLE);



                for (int i = 0; i < mOptionImageButtons.size(); i++) {
                    ImageButton btn = mOptionImageButtons.get(i);
                    int shuffledIdx = indexes.get(i%4);
                    String option = mCurrentQuestion.getOptions().get(shuffledIdx);
                    int resId = this.getResources().getIdentifier(option.toLowerCase(), "drawable", getPackageName());
                    btn.setImageResource(resId);

                    if (shuffledIdx % 4 == 0) { // É a resposta correta
                        mCurrentCorrectBtnIdx = i%4;
                    }
                }


                break;

            case TEXT_OPTIONS:
            default:
                mOptionsPanel = mOptionsTextPanel;
                mOptionsPanel.setVisibility(View.VISIBLE);


                for (int i = 0; i < mOptionButtons.size(); i++) {
                    Button btn = mOptionButtons.get(i);
                    int shuffledIdx = indexes.get(i%4);
                    String option = mCurrentQuestion.getOptions().get(shuffledIdx);
                    btn.setText(option.toUpperCase());

                    if (shuffledIdx % 4 == 0) { // É a resposta correta
                        mCurrentCorrectBtnIdx = i%4;
                    }
                }
                break;
        }


    }

    private void initAnswer() {
        mAnswerPanel = findViewById(R.id.answer_layout);
        mAnswerMsg = (TextView) findViewById(R.id.answer_msg_txt);
        mCorrectAnswerTxt = (TextView) findViewById(R.id.answer_correct_txt);
        mFimDeJogoBtn = (Button) findViewById(R.id.fim_de_jogo_btn);
        mProximaPerguntaBtn = (Button) findViewById(R.id.answer_next_btn);
        mProximaPerguntaBtn.setVisibility(View.VISIBLE);
        mFimDeJogoBtn.setVisibility(View.GONE);
        mProximaPerguntaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentQuestion = QuestionProvider.getInstance(activity).getNextQuestion();
                showQuestion();
                showOptions();
            }
        });
    }

    private void showAnswer(int option) {

        mOptionsPanel.setVisibility(View.GONE);
        mAnswerPanel.setVisibility(View.VISIBLE);

        final boolean answerIsCorrect = (mCurrentCorrectBtnIdx == option);

        if(answerIsCorrect){

            mPontuacao = mPontuacao + 10;
            if(QuestionProvider.getInstance(this).isLastQuestion() == true){
                mAnswerPanel = findViewById(R.id.answer_layout);
                mAnswerMsg = (TextView) findViewById(R.id.answer_msg_txt);
                mCorrectAnswerTxt = (TextView) findViewById(R.id.answer_correct_txt);
                mFimDeJogoBtn = (Button) findViewById(R.id.fim_de_jogo_btn);
                mProximaPerguntaBtn.setVisibility(View.GONE);
                mFimDeJogoBtn.setVisibility(View.VISIBLE);
                mFimDeJogoBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuestionActivity.this, FimDeJogoActivity.class);
                        intent.putExtra(FimDeJogoActivity.PONTUACAO_EXTRA, mPontuacao);
                        intent.putExtra(FimDeJogoActivity.GANHOU, true);
                        startActivity(intent);
                    }
                });
            }
        }

        else {
            mNumErrors = mNumErrors + 1;
            if(mNumErrors == 3){

                mAnswerPanel = findViewById(R.id.answer_layout);
                mAnswerMsg = (TextView) findViewById(R.id.answer_msg_txt);
                mCorrectAnswerTxt = (TextView) findViewById(R.id.answer_correct_txt);
                mFimDeJogoBtn = (Button) findViewById(R.id.fim_de_jogo_btn);
                mProximaPerguntaBtn.setVisibility(View.GONE);
                mFimDeJogoBtn.setVisibility(View.VISIBLE);
                mFimDeJogoBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(QuestionActivity.this, FimDeJogoActivity.class);
                        intent.putExtra(FimDeJogoActivity.PONTUACAO_EXTRA, mPontuacao);
                        intent.putExtra(FimDeJogoActivity.GANHOU, false);
                        startActivity(intent);
                    }
                });

            }
        }

        mAnswerMsg.setText(answerIsCorrect ? "PARABÉNS!!!" : "NÃO DEU...");
        mCorrectAnswerTxt.setText(getResources().getString(R.string.correct_answer,
                mCurrentQuestion.getOptions().get(0)));


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
