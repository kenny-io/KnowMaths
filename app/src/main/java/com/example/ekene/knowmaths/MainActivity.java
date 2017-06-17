package com.example.ekene.knowmaths;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView sumTexTView;
    TextView correctTexTView;
    TextView resultTextView;
    TextView timerTextView;
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button buttonSummation;
    Button buttonDivision;
    Button buttonMultiplication;
    Button buttonSubstraction;
    Button playAgainButton;
    int score = 0;
    int numberOfQuestions = 0;
    RelativeLayout gameRelativeLayout;
    GridLayout gridLayout;
    GridLayout choiceGridLayout;
    MediaPlayer mplayer;


    public void buttonSummation(View view){

        gameRelativeLayout.setVisibility(GridLayout.VISIBLE);
        playAgain(findViewById(R.id.button3));
        mplayer.start();
        choiceGridLayout.setVisibility(GridLayout.INVISIBLE);
    }


    public void playAgain(View view) {
        playAgainButton.setVisibility(view.INVISIBLE);
//initialize variables//
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        resultTextView.setText("0/0");
        correctTexTView.setText("");
        generateQuestion();
        gridLayout.setVisibility(GridLayout.VISIBLE);
        mplayer.start();


//set the countdowntimer//

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000 + "s"));

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                correctTexTView.setText("your score :" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(GridLayout.INVISIBLE);

            }
        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTexTView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswers;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswers = rand.nextInt(41);

                while (incorrectAnswers == a + b) {

                    incorrectAnswers = rand.nextInt(41);
                }

                answers.add(incorrectAnswers);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        answers.clear();

    }

    public void startButton(View view) {

        startButton.setVisibility(view.INVISIBLE);
        choiceGridLayout.setVisibility(GridLayout.VISIBLE);

    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            correctTexTView.setText("Correct!");


        } else {

            correctTexTView.setText("Wrong!");

        }
        numberOfQuestions++;
        resultTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));


        generateQuestion();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTexTView = (TextView) findViewById(R.id.sumTextView);
        startButton = (Button) findViewById(R.id.startButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        correctTexTView = (TextView) findViewById(R.id.correctTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        buttonSummation = (Button) findViewById(R.id.buttonSummation);
        buttonDivision = (Button) findViewById(R.id.buttonDivision);
        buttonMultiplication = (Button) findViewById(R.id.buttonMultiplication);
        buttonSubstraction = (Button) findViewById(R.id.buttonSubstraction);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout2);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        mplayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        choiceGridLayout = (GridLayout)findViewById(R.id.choiceGridLayout);
        generateQuestion();

    }
}


