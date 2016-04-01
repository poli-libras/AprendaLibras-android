package br.com.polilibras.aprendalibras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class FimDeJogoActivity extends AppCompatActivity {

    public final static String GANHOU_EXTRA = "GANHOU";
    public final static String EXTRA_ACERTOS = "ACERTOS";
    int newHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_de_jogo);
        Intent intent = getIntent();
        int pontuacao = 10 * intent.getIntExtra(EXTRA_ACERTOS, -1);
        boolean ganhar = intent.getBooleanExtra(GANHOU_EXTRA, false);
        TextView text = (TextView) findViewById(R.id.fim_jogo_mensagem_txt);
        if (ganhar == true) {
            text.setText(getString(R.string.fim_jogo_ganhador_txt));
        } else {
            text.setText(getString(R.string.fim_jogo_perdedor));
        }
        TextView textView = (TextView) findViewById(R.id.fim_jogo_pontuacao_txt);
        textView.setText(getString(R.string.pontuacao_txt, pontuacao));

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        newHighScore = sharedPref.getInt("pontuacao", 0);

        if (newHighScore > pontuacao) {
            TextView textView2 = (TextView) findViewById(R.id.recorde_txt);
            textView2.setText(getString(R.string.high_score_txt, newHighScore));
        } else {
            newHighScore = pontuacao;
            TextView textView2 = (TextView) findViewById(R.id.recorde_txt);
            textView2.setText(getString(R.string.high_score_txt, newHighScore));
            sharedPref.edit().putInt("pontuacao", newHighScore).apply();
        }

    }


    public void sendMessage1(View view){
        Intent intent = new Intent(this,QuestionActivity.class);
        startActivity(intent);
    }
    public void sendMessage2(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
