package br.com.polilibras.aprendalibras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class FimDeJogoActivity extends AppCompatActivity {

    public final static String EXTRA_GANHOU = "EXTRA_GANHOU";
    public final static String EXTRA_ACERTOS = "EXTRA_ACERTOS";
    public final static String PREFS_RECORDE = "PREFS_RECORDE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_de_jogo);
        Intent intent = getIntent();
        int pontuacao = 10 * intent.getIntExtra(EXTRA_ACERTOS, -1);
        boolean ganhar = intent.getBooleanExtra(EXTRA_GANHOU, false);
        TextView fimJogoTxt = (TextView) findViewById(R.id.fim_jogo_mensagem_txt);
        if (ganhar) {
            fimJogoTxt.setText(getString(R.string.fim_jogo_ganhador));
        } else {
            fimJogoTxt.setText(getString(R.string.fim_jogo_perdedor));
        }
        TextView pontuacaoTxt = (TextView) findViewById(R.id.fim_jogo_pontuacao_txt);
        pontuacaoTxt.setText(getString(R.string.pontuacao, pontuacao));


        // Recorde
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int highScore = sharedPref.getInt(PREFS_RECORDE, 0);
        TextView recordeTxt = (TextView) findViewById(R.id.recorde_txt);

        if (pontuacao <= highScore) {
            recordeTxt.setText(getString(R.string.high_score, highScore));
        } else {
            highScore = pontuacao;
            recordeTxt.setText(getString(R.string.new_high_score, highScore));
            recordeTxt.setAllCaps(true);
            recordeTxt.setTextColor(getResources().getColor(R.color.colorPrimary));
            sharedPref.edit().putInt(PREFS_RECORDE, highScore).apply();
        }

    }

    public void restartGame(View view){
        Intent intent = new Intent(this,QuestionActivity.class);
        startActivity(intent);
        finish();
    }
    public void goToHome(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
