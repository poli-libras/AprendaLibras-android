package br.com.polilibras.aprendalibras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class FimDeJogoActivity extends AppCompatActivity {

    public final static String PONTUACAO_EXTRA = "PONTUACAO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_de_jogo);
        Intent intent = getIntent();
        int pontuacao = intent.getIntExtra(PONTUACAO_EXTRA,-1);
        TextView textView = (TextView) findViewById(R.id.fim_jogo_pontuacao_txt);
        textView.setText(getString(R.string.pontuacao_txt, pontuacao));

    }
}
