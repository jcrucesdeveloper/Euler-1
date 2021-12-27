package com.jorgecruces.euler1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.jorgecruces.euler1.R;

import java.util.Random;

/**
 * Entry point of the App, the screen with Euler image and Play Button
 */
public class MainActivity extends AppCompatActivity
{

    TextView textViewQuote, textViewTitle;
    ImageView imageViewInfoButton, imageViewPlayButton,imageViewEulerPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Title and Quote
        textViewQuote = (TextView) findViewById(R.id.textViewQuote);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);

        // InfoButton, PlayButton,  and Euler image
        imageViewInfoButton = (ImageView) findViewById(R.id.imageViewInfoButton);
        imageViewPlayButton = (ImageView) findViewById(R.id.imageViewPlayButton);
        imageViewEulerPrincipal = (ImageView) findViewById(R.id.imageViewEulerMain);

        // At the start we fade and select a quote
        fadeInAnimation();
        selectQuote();
    }

    /**
     * Go to next activity that is Numbers level
     * @param view the button being clicked
     */
    public void onClickNextActivityNumbersLevel(View view)
    {
        Intent intentNumbersLevel = new Intent(getApplicationContext(), NumbersLevelsActivity.class);
        startActivity(intentNumbersLevel);

    }

    /**
     * Fade animation of the textViews and the Euler image
     */
    public void fadeInAnimation()
    {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        textViewTitle.startAnimation(fadeInAnimation);
        textViewQuote.startAnimation(fadeInAnimation);
        imageViewEulerPrincipal.startAnimation(fadeInAnimation);
    }

    /**
     * Select a random quote to put in the MainActivity
     */
    public void selectQuote()
    {
        String[] quotes = getResources().getStringArray(R.array.quotes_from_euler);
        Random randomNumber = new Random();
        int quoteNumber = randomNumber.nextInt(quotes.length);
        String quoteToDisplay = quotes[quoteNumber];
        textViewQuote.setText(quoteToDisplay);
    }



}