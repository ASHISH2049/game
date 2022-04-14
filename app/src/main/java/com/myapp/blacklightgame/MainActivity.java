package com.myapp.blacklightgame;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView orangeBox, blueBox, yellowBox, greenBox, scoreCount;
    private Handler handler, touchEventHandler;
    private Runnable r;
    private int userClickedGrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreCount = findViewById(R.id.scoreCount);
        orangeBox = findViewById(R.id.orangeBox);
        blueBox = findViewById(R.id.blueBox);
        yellowBox = findViewById(R.id.yellowBox);
        greenBox = findViewById(R.id.greenBox);

        scoreCount.setText(String.valueOf(0));

        welcomeDialog();
    }

    private void welcomeDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Welcome to BlackLight Gaming");
        dialog.setCancelable(false);

        dialog.setPositiveButton("Start Game",
                (arg0, arg1) -> {
                    initializeTouchEvent();
                    gameStart();
                });


        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }

    private void gameStart() {

        handler = new Handler();
        handler.postDelayed(new Runnable() {

            int score = 0;

            @Override
            public void run() {

                Random rd = new Random();
                int randomGreyGenerated = rd.nextInt(4 - 1 + 1) + 1;

                switch (randomGreyGenerated) {

                    case 1:

                        orangeBox.setBackgroundColor(Color.parseColor("#858687"));
                        blueBox.setBackgroundColor(Color.parseColor("#11B5F6"));
                        yellowBox.setBackgroundColor(Color.parseColor("#EFAF0F"));
                        greenBox.setBackgroundColor(Color.parseColor("#5C8F22"));
                        break;

                    case 2:

                        blueBox.setBackgroundColor(Color.parseColor("#858687"));
                        orangeBox.setBackgroundColor(Color.parseColor("#E87A19"));
                        yellowBox.setBackgroundColor(Color.parseColor("#EFAF0F"));
                        greenBox.setBackgroundColor(Color.parseColor("#5C8F22"));
                        break;

                    case 3:

                        yellowBox.setBackgroundColor(Color.parseColor("#858687"));
                        orangeBox.setBackgroundColor(Color.parseColor("#E87A19"));
                        blueBox.setBackgroundColor(Color.parseColor("#11B5F6"));
                        greenBox.setBackgroundColor(Color.parseColor("#5C8F22"));
                        break;

                    case 4:

                        greenBox.setBackgroundColor(Color.parseColor("#858687"));
                        orangeBox.setBackgroundColor(Color.parseColor("#E87A19"));
                        blueBox.setBackgroundColor(Color.parseColor("#11B5F6"));
                        yellowBox.setBackgroundColor(Color.parseColor("#EFAF0F"));
                        break;

                }

                orangeBox.setOnClickListener(view -> {
                    userClickedGrey = 1;

                    if (userClickedGrey == randomGreyGenerated) {
                        score++;
                        scoreCount.setText(String.valueOf(score));
                    } else {
                        handler.removeCallbacksAndMessages(null);
                        touchEventHandler.removeCallbacksAndMessages(null);
                        gameOver();
                    }
                });

                blueBox.setOnClickListener(view -> {
                    userClickedGrey = 2;

                    if (userClickedGrey == randomGreyGenerated) {
                        score++;
                        scoreCount.setText(String.valueOf(score));
                    } else {
                        handler.removeCallbacksAndMessages(null);
                        touchEventHandler.removeCallbacksAndMessages(null);
                        gameOver();
                    }
                });

                yellowBox.setOnClickListener(view -> {
                    userClickedGrey = 3;

                    if (userClickedGrey == randomGreyGenerated) {
                        score++;
                        scoreCount.setText(String.valueOf(score));
                    } else {
                        handler.removeCallbacksAndMessages(null);
                        touchEventHandler.removeCallbacksAndMessages(null);
                        gameOver();
                    }
                });

                greenBox.setOnClickListener(view -> {
                    userClickedGrey = 4;

                    if (userClickedGrey == randomGreyGenerated) {
                        score++;
                        scoreCount.setText(String.valueOf(score));
                    } else {
                        handler.removeCallbacksAndMessages(null);
                        touchEventHandler.removeCallbacksAndMessages(null);
                        gameOver();
                    }
                });

                handler.postDelayed(this, 1000);

            }
        }, 0);


    }

    private void gameOver() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Game Over"+"  "+scoreCount.getText().toString());
        dialog.setCancelable(false);

        dialog.setPositiveButton("Restart",
                (arg0, arg1) -> {
                    scoreCount.setText(String.valueOf(0));
                    initializeTouchEvent();
                    gameStart();
                });


        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }

    private void initializeTouchEvent() {

        touchEventHandler = new Handler();
        r = () -> {
            // TODO Auto-generated method stub
            handler.removeCallbacksAndMessages(null);
            touchEventHandler.removeCallbacksAndMessages(null);
            gameOver();
        };
        stopHandler();
        startHandler();

    }

    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        stopHandler();
        startHandler();
    }

    public void stopHandler() {
        touchEventHandler.removeCallbacks(r);
    }

    public void startHandler() {
        touchEventHandler.postDelayed(r, 5000);
    }

}