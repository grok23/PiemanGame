package edu.itsligo.simongame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HighScores extends AppCompatActivity {
    ListView topfive;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main.xml);

        setContentView(R.layout.activity_high_scores);

        topfive = findViewById(R.id.scoreBoard);
        DatabaseHandler db = new DatabaseHandler(this);


        sharedPreferences = getSharedPreferences("score_ref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int s = sharedPreferences.getInt("score", 0);
        //  Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();

        if (s != 0) {

            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            db.addHighScore(new HighScore(date,
                    sharedPreferences.getString("player_name", ""),
                    s));

            //Toast.makeText(this, "Now we can store in db " + s, Toast.LENGTH_SHORT).show();

        }

        editor.putString("player_name", "");
        editor.putInt("Level", 1);
        editor.putInt("seqcount", 4);
        editor.putInt("score", 0);
        editor.commit();


//         db.emptyHiScores();     // empty table if required
//
//         Inserting hi scores
//        Log.i("Insert: ", "Inserting ..");
//        db.addHighScore(new HiScore("20 OCT 2020", "Frodo", 0));
//        db.addHighScore(new HiScore("28 OCT 2020", "Dobby", 1));
//        db.addHighScore(new HiScore("20 NOV 2020", "DarthV", 3));
//        db.addHighScore(new HiScore("20 NOV 2020", "Bob", 2));
//        db.addHighScore(new HiScore("22 NOV 2020", "Gemma", 1));
//        db.addHighScore(new HiScore("30 NOV 2020", "Joe", 0));
//        db.addHighScore(new HiScore("01 DEC 2020", "DarthV", 0));
//        db.addHighScore(new HiScore("02 DEC 2020", "Gandalf", 0));
//

        // Reading all scores
        Log.i("Reading: ", "Reading all scores..");
        List<HighScore> hiScores = db.getAllHighScores();


        for (HighScore hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");

//        HiScore singleScore = db.getHiScore(5);
//        Log.i("High Score 5 is by ", singleScore.getPlayer_name() + " with a score of " +
//                singleScore.getScore());

        Log.i("divider", "====================");

        // Calling SQL statement
        List<HighScore> top5HighScores = db.getTopFiveScores();

        for (HighScore hs : top5HighScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }
        Log.i("divider", "====================");

//        HiScore hiScore = top5HighScores.get(top5HighScores.size() - 1);
        // hiScore contains the 5th highest score
        ///    Log.i("fifth Highest score: ", String.valueOf(highScore.getScore()));

        // simple test to add a hi score
        int myCurrentScore = 40;
        // if 5th highest score < myCurrentScore, then insert new score
//        if (highScore.getScore() < myCurrentScore) {
//            db.addHighScore(new HiScore("08 DEC 2020", "Elrond", 11));
//        }

        Log.i("divider", "====================");

        // Calling SQL statement
        top5HighScores = db.getTopFiveScores();
        List<String> scoresStr;
        scoresStr = new ArrayList<>();

        int j = 1;
        for (HighScore hs : top5HighScores) {

            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // store score in string array
            scoresStr.add(j++ + " : " +
                    hs.getPlayer_name() + "\t" +
                    hs.getScore());
            // Writing HighScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");
        Log.i("divider", "Scores in list <>>");
        for (String ss : scoresStr) {
            Log.i("Score: ", ss);
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, R.layout.rows, scoresStr);
        topfive.setAdapter(itemsAdapter);
    }

    public void doStart(View view) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
    }

    // add name
    public void onClick(View view) {
    }

}