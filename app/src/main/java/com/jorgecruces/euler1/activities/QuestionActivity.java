package com.jorgecruces.euler1.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgecruces.euler1.R;
import com.jorgecruces.euler1.gameLogic.Question;
import com.jorgecruces.euler1.gameLogic.XmlParserActivity;
import com.jorgecruces.euler1.sound.MediaPlayerReproducer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.github.kexanie.library.MathView;


public class QuestionActivity extends XmlParserActivity
{

    private MathView mathView;

    //ArrayList of Questions
    private ArrayList<Question> questionList;

    // Current question
    private Question questionLevel;

    private String correctAlternative;

    // Current level number 1 - 100
    private int levelNumber;

    // Last Level number
    private int lastLevelNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setUpQuestion();
        renderLabels();
        renderQuestion();
        renderAlternatives();
    }


    /**
     * Get the question from xml asset file and charge it to the
     * Question representation named questionLevel
     *
     */
    public void setUpQuestion()
    {

        // Question number from the intent
        String questionNumberStr;
        questionNumberStr = getIntent().getStringExtra("levelNumber");


        this.levelNumber = Integer.parseInt(questionNumberStr);

        // For some reason, we did not find the question
        if (questionNumberStr == null)
        {
            questionNumberStr = "-1";
        }


        int questionIndex = this.levelNumber - 1;
        questionList = getQuestionList();

        // Default question if something goes wrong
        questionLevel = new Question();

        try
        {
            questionLevel = questionList.get(questionIndex);
            questionLevel.setQuestionNumber(questionIndex + 1);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

        correctAlternative = questionLevel.getCorrectAlternative();
        // Charged questionLevel and correctAlternative
        // Now we render things
    }

    /**
     * Render the labels of the activity:
     * - Question number (TextView)
     * - Question Title (TextView)(Optional)
     */
    public void renderLabels() {

        // Question Number
        TextView textViewCurrentLevelNumber = (TextView) findViewById(R.id.textViewCurrentLevelNumber);
        textViewCurrentLevelNumber.setText(questionLevel.getQuestionNumberString());

        // Question Title (Optional)
        TextView textViewQuestionTile = (TextView) findViewById(R.id.textViewQuestionTitle);
        textViewQuestionTile.setText(questionLevel.getQuestionTitle());
    }

    /**
     * Render the questionLabel and resize it if necessary
     */
    public void renderQuestion()
    {
        String questionLabel = questionLevel.getQuestionLabel();
        String questionFontSize = "30";

        if(questionLabel.length() > 14) questionFontSize = "20";
        if(questionLabel.length() > 21) questionFontSize = "18";



        mathView = (MathView) findViewById(R.id.mathView);
        mathView.setText("<div style=\"font-size:" + questionFontSize + "px;color:white;\"> $$" + questionLabel + "$$ </div>");
    }

    /**
     * Render the alternatives and shuffle it every time
     */
    public void renderAlternatives()
    {
        TextView textViewAlternative1, textViewAlternative2, textViewAlternative3, textViewAlternative4;

        textViewAlternative1 = (TextView) findViewById(R.id.textViewAlternative1);
        textViewAlternative2 = (TextView) findViewById(R.id.textViewAlternative2);
        textViewAlternative3 = (TextView) findViewById(R.id.textViewAlternative3);
        textViewAlternative4 = (TextView) findViewById(R.id.textViewAlternative4);

        List<TextView> textViewList = new ArrayList<TextView>();

        textViewList.add(textViewAlternative1);
        textViewList.add(textViewAlternative2);
        textViewList.add(textViewAlternative3);
        textViewList.add(textViewAlternative4);

        String alternative1,alternative2,alternative3,alternative4;
        alternative1 = questionLevel.getAlternative1();
        alternative2 = questionLevel.getAlternative2();
        alternative3 = questionLevel.getAlternative3();
        alternative4 = questionLevel.getCorrectAlternative();

        List<String> alternativesList = new ArrayList<String>();

        alternativesList.add(alternative1);
        alternativesList.add(alternative2);
        alternativesList.add(alternative3);
        alternativesList.add(alternative4);

        Collections.shuffle(alternativesList);

        for(int i = 0; i < textViewList.size(); i++)
        {
            TextView textViewTemp = textViewList.get(i);
            String alternativeTemp = alternativesList.get(i);
            textViewTemp.setText(alternativeTemp);

            // Resize alternative
            if(alternativeTemp.length() > 10)
            {
                textViewTemp.setTextSize(TypedValue.COMPLEX_UNIT_SP,28f);

            }
        }

    }

    /**
     * We go back to the last activity (Levels activity)
     */
    public void onClickGoBackArrow(View view)
    {
        finish();
    }


    /**
     * We check the answer to the Question:
     * if this is correct we create a custom PopUp
     * if false we run an add
     * @param view
     */
    public void onClickAlternative(View view)
    {
        MediaPlayerReproducer.getInstance().reproduceClickSound(this);
        TextView textViewAlternative = (TextView) view;
        String stringAlternative = textViewAlternative.getText().toString();

        if(stringAlternative.equals(correctAlternative))
        {
            // Correct Correctly
            answeredCorrectly();
        }
        else
        {
            // Incorrect Alternative
            answeredIncorrectly();
        }
    }

    /**
     * The user answered the Question correctly
     * We store in memory if the current level is greater than the last level
     * And we show the dialog
     */
    public void answeredCorrectly()
    {
        MediaPlayerReproducer.getInstance().reproduceWinSound(this);
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        int lastLevelNumber = sharedPreferences.getInt(getString(R.string.level),1);

        if (this.levelNumber >= lastLevelNumber)
        {
            storeLastLevelInMemory();
        }
        showDialogNextLevel();
    }

    /**
     * We store the current level as the last level to play
     */
    public void storeLastLevelInMemory()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // We save the next level as the lasLevelNumber, because here is where we are
        editor.putInt(getString(R.string.level), (this.levelNumber + 1));
        editor.apply();

    }

    /**
     * Show the dialog to the next Level
     */
    public void showDialogNextLevel()
    {
        // Show dialog
        Dialog nextLevelDialog = new Dialog(this);
        nextLevelDialog.setContentView(R.layout.custom_dialog_winning);

        // Animation popUp
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein_pop_up);

        // fadeIn animation
        ViewGroup dialogLayout = (ViewGroup) nextLevelDialog.findViewById(R.id.dialogLayout);
        dialogLayout.startAnimation(fadeInAnimation);

        // Button and textView of the popUp
        Button nextLevelButton = (Button) nextLevelDialog.findViewById(R.id.confirmResetLevelsButton);
        TextView messageNextLevel = (TextView) nextLevelDialog.findViewById(R.id.nextLevelTextView);

        // Random phrase
        messageNextLevel.setText(getNextLevelPhrase());
        nextLevelButton.setOnClickListener(view -> goingNextLevel());
        nextLevelDialog.show();

    }

    /**
     * Get random phrases to the next level PopUp
     * @return a random phrases
     */
    public String getNextLevelPhrase() {
        String[] phrases = getResources().getStringArray(R.array.next_level_phrases);
        Random randomNumber = new Random();
        int quoteNumber = randomNumber.nextInt(phrases.length);
        return phrases[quoteNumber];
    }

    public void goingNextLevel() {
        String nextLevelStr = Integer.toString(questionLevel.getQuestionNumber() + 1);

        // We go to the same activity but with different levelNumber
        Intent intent = getIntent();
        intent.putExtra("levelNumber",nextLevelStr);
        finish();
        startActivity(intent);

    }

    public void answeredIncorrectly() {

        // Vibration
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(300);

        // Ad




    }

}