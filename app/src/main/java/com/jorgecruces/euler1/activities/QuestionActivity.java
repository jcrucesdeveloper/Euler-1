package com.jorgecruces.euler1.activities;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.jorgecruces.euler1.R;
import com.jorgecruces.euler1.enviromentMode.EnvironmentModeHandler;
import com.jorgecruces.euler1.logic.ReaderQuestions;
import com.jorgecruces.euler1.model.Alternative;
import com.jorgecruces.euler1.model.Question;
import com.jorgecruces.euler1.sound.MediaPlayerReproducer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.github.kexanie.library.MathView;


/**
 * QuestionActivity, it has the questionLabel and the Alternatives
 */
public class QuestionActivity extends AppCompatActivity
{

    private MathView mathView;

    // ArrayList of Questions (Logical Questions)
    private ArrayList<Question> questionList;

    // Current question (Logical Question)
    private Question questionLevel;

    // Correct alternative Level
    private Alternative correctAlternative;

    // Current level number 1 - 100
    private int levelNumber;

    // Numbers of levels played for an ad to be shown
    private final int maxNumberLevelsForAds = 20;

    private boolean answerIncorrectly;


    // Ads
    private boolean adReady;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        adReady = false;
        initializeInterstitialAd();
        setUpQuestion();
        renderLabels();
        renderQuestion();
        renderAlternatives();
        checkIfRateItDialog();
    }

    /**
     * Check if we have to run ads depending on how many levels have we played
     */
    public void checkAdsRequisite()
    {
        int numbersLevelPlayed = getNumbersLevelPlayed();
        if (numbersLevelPlayed >= maxNumberLevelsForAds)
        {
            if (mInterstitialAd != null && adReady)
            {
                mInterstitialAd.show(this);
            }
            saveSharedPreferencesLevelsPlayed(0);
        }
    }
    /**
     * Get from SharedPreferences the numbers of levels played
     * @return the numbers of level played (integer)
     */
    public int getNumbersLevelPlayed()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(getString(R.string.ads),0);
    }

    /**
     * Save in SharedPreferences the numbers of level Played
     * @param n the number of levels
     */
    public void saveSharedPreferencesLevelsPlayed(int n)
    {
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.ads),n);
        editor.apply();
    }
    /**
     * Initialize the Ads
     */
    public void initializeInterstitialAd()
    {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-2905296254158275/9456411900", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        adReady = true;
                        Log.i("ANUCIOS", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }


    /**
     * Get the question from xml asset file and charge it to the
     * Question representation named questionLevel
     *
     */
    public void setUpQuestion()
    {

        // Question number Str
        String questionNumberStr = getIntent().getStringExtra("levelNumber");
        levelNumber = Integer.parseInt(questionNumberStr);

        answerIncorrectly = false;

        questionList = getQuestionArrayList();

        // Default question if something goes wrong
        questionLevel = new Question();

        int questionIndex = this.levelNumber - 1;
        questionLevel = questionList.get(questionIndex);

        this.correctAlternative = questionLevel.getCorrectAlternative();
    }

    public ArrayList<Question> getQuestionArrayList() {
        ReaderQuestions readerQuestions = new ReaderQuestions();
        return readerQuestions.readQuestions(this);
    }

    /**
     * Render the labels of the activity:
     * - Question number (TextView)
     * - Question Title (TextView)(Optional)
     */
    public void renderLabels() {

        // Question Number
        TextView textViewCurrentLevelNumber = (TextView) findViewById(R.id.textViewCurrentLevelNumber);
        textViewCurrentLevelNumber.setText(Integer.toString(this.levelNumber));
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
        alternative1 = questionLevel.getAlternativeByNumber(0).getValue();
        alternative2 = questionLevel.getAlternativeByNumber(1).getValue();
        alternative3 = questionLevel.getAlternativeByNumber(2).getValue();
        alternative4 = questionLevel.getCorrectAlternative().getValue();

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

        if(stringAlternative.equals(correctAlternative.getValue()))
        {
            // Correct Alternative
            answeredCorrectly();
        }
        else
        {
            // Incorrect Alternative
            answeredIncorrectly();
        }
    }

    /**
     * Check if it's level 20 and show rate it dialog
     */
    public void checkIfRateItDialog()
    {
        switch (levelNumber)  {
            case 15:
            case 50:
            case 80:
                showDialogRateIt();
                break;


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
        saveNumbersLevelPlayed();
        showDialogNextLevel();
    }

    /**
     * Save in SharedPreferences the numbers of levels that we have played
     */
    public void saveNumbersLevelPlayed()
    {
        int numbersLevelPlayed = getNumbersLevelPlayed();
        saveSharedPreferencesLevelsPlayed(numbersLevelPlayed + 1);
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

        // Animation fadeInAnimation
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein_pop_up);
        ViewGroup dialogLayout = (ViewGroup) nextLevelDialog.findViewById(R.id.dialogLayout);
        dialogLayout.startAnimation(fadeInAnimation);

        // Button and textView of the popUp
        Button nextLevelButton = (Button) nextLevelDialog.findViewById(R.id.buttonRateIt);
        TextView messageNextLevel = (TextView) nextLevelDialog.findViewById(R.id.buttonTitleRate);

        // Random phrase
        messageNextLevel.setText(getNextLevelPhrase());

        if (levelNumber >= 100)
        {
            // Last Level
            nextLevelButton.setOnClickListener(view -> toInfoActivity());

        } else
        {
            nextLevelButton.setOnClickListener(view -> toNextLevel());
        }
        nextLevelDialog.show();

    }

    public void showDialogRateIt()
    {
        Dialog rateItDialog = new Dialog(this);
        rateItDialog.setContentView(R.layout.rate_app_dialog);


        // fadeInAnimation
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein_pop_up);
        ViewGroup dialogLayout = (ViewGroup) rateItDialog.findViewById(R.id.dialogLayout);
        dialogLayout.startAnimation(fadeInAnimation);

        // Button, imageViewStart, textView
        Button buttonRateIt = (Button) rateItDialog.findViewById(R.id.buttonRateIt);
        ImageView imageViewStart = (ImageView) rateItDialog.findViewById(R.id.buttonStar);
        TextView textViewRateIt = (TextView) rateItDialog.findViewById(R.id.buttonTitleRate);

        // CloseDialog
        ImageView imageViewCloseDialog = (ImageView) rateItDialog.findViewById(R.id.buttonCloseDialog);

        // Go to the appStore and rate it
        buttonRateIt.setOnClickListener(view -> goToStore());
        imageViewStart.setOnClickListener(view -> goToStore());
        textViewRateIt.setOnClickListener(view -> goToStore());

        // close
        imageViewCloseDialog.setOnClickListener(view -> rateItDialog.dismiss());
        rateItDialog.show();
    }

    public void goToStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.jorgecruces.euler1"));
        startActivity(intent);
    }

    /**
     * Go to InfoActivity
     */
    public void toInfoActivity()
    {
        Intent intentNumbersLevel = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(intentNumbersLevel);
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

    /**
     * Go to the next Level
     */
    public void toNextLevel() {

        String nextLevelStr = Integer.toString(this.levelNumber + 1);
        // We go to the same activity but with different levelNumber
        Intent intent = getIntent();
        intent.putExtra("levelNumber",nextLevelStr);
        finish();
        startActivity(intent);

    }


    /**
     * If we Answered Incorrectly we run ads, vibrate and show up a Toast
     */
    public void answeredIncorrectly() {

        boolean testMode = EnvironmentModeHandler.getEnvironmentModeHandler().testMode();
        // Ad
        if ( (adReady && mInterstitialAd != null) && !testMode)
        {
            mInterstitialAd.show(this);
        }
        // Vibration
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);

        // Toast
        Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();


    }

}