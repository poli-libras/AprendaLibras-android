package polilibras.com.br.aprendalibras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    public static final String INTENT_CORRECT_ANSWER_BOOL = "INTENT_CORRECT_ANSWER_BOOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_activity);


        Intent intent = getIntent();
        boolean correctAnswer = intent.getBooleanExtra(INTENT_CORRECT_ANSWER_BOOL, false);

        TextView msgTxt = (TextView) findViewById(R.id.answer_msg_txt);

        if (correctAnswer) {
            msgTxt.setText("PARABÉNS!!");
        } else {
            msgTxt.setText("NÃO DEU...");
        }
    }
}
