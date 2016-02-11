package polilibras.com.br.aprendalibras;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class QuestionActivity extends AppCompatActivity {

    private Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        ImageView img = (ImageView)findViewById(R.id.question_image_view);
        img.setBackgroundResource(R.drawable.vj_futebol);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();

        findViewById(R.id.question_option1_btn).setOnClickListener(new OptionClickListener(this, true));
        findViewById(R.id.question_option2_btn).setOnClickListener(new OptionClickListener(this, false));
        findViewById(R.id.question_option3_btn).setOnClickListener(new OptionClickListener(this, false));
        findViewById(R.id.question_option4_btn).setOnClickListener(new OptionClickListener(this, false));
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
        }
    }
}
