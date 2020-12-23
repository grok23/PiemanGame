package edu.itsligo.simongame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    EditText playerName;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        sharedPreferences = getSharedPreferences("score_ref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int s = sharedPreferences.getInt("score",0);
        playerName = findViewById(R.id.userNameEd);

        score = findViewById(R.id.score);
        score.setText(String.valueOf(s));

        editor.putInt("score",0);
        editor.commit();
    }

    public void doReplay(View view) {
        Intent i = new Intent(GameOver.this, MainActivity.class);
        startActivity(i);
    }

    public void doHighScore(View view) {

        editor.putString("playerName", playerName.getText().toString());
        int s = sharedPreferences.getInt("score", 0);
        editor.commit();
        Intent i = new Intent(GameOver.this, HighScores.class);
        i.putExtra("score",s);

        startActivity(i);
    }
}