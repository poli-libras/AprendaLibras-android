package br.com.polilibras.aprendalibras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class FimDeJogoActivity extends AppCompatActivity {

    public final static String GANHOU_EXTRA = "GANHOU";
    public final static String EXTRA_ACERTOS = "ACERTOS";

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
        }
        else{
            text.setText(getString(R.string.fim_jogo_perdedor));
        }
        TextView textView = (TextView) findViewById(R.id.fim_jogo_pontuacao_txt);
        textView.setText(getString(R.string.pontuacao_txt, pontuacao));}

    public void sendMessage1(View view){
        Intent intent = new Intent(this,QuestionActivity.class);
        startActivity(intent);
    }
    public void sendMessage2(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
