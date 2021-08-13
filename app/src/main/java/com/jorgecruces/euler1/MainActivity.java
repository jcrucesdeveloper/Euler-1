package com.jorgecruces.euler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    TextView textViewQuote, textViewTitle;
    ImageView imageViewInfoButton, imageViewPlayButton,imageViewPlayButtonBackground,imageViewEulerPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuote = (TextView) findViewById(R.id.textViewQuote);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);

        imageViewInfoButton = (ImageView) findViewById(R.id.imageViewInfoButton);
        imageViewPlayButton = (ImageView) findViewById(R.id.imageViewPlayButton);
        imageViewPlayButtonBackground = (ImageView) findViewById(R.id.imageViewPlayButtonBackground);
        imageViewEulerPrincipal = (ImageView) findViewById(R.id.imageViewEulerMain);

        fadeInAnimation();
        selectQuote();
    }

    public void onClickListLevels(View view)
    {
        Intent intentListLevels = new Intent(getApplicationContext(), ListLevelsActivity.class);
        startActivity(intentListLevels);
    }

    public void fadeInAnimation()
    {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        textViewTitle.startAnimation(fadeInAnimation);
        textViewQuote.startAnimation(fadeInAnimation);
        imageViewEulerPrincipal.startAnimation(fadeInAnimation);
    }

    public void selectQuote()
    {
        String[] quotes = getResources().getStringArray(R.array.quotes_from_euler);
        Random randomNumber = new Random();
        int quoteNumber = randomNumber.nextInt(quotes.length);
        String quoteToDisplay = quotes[quoteNumber];
        textViewQuote.setText(quoteToDisplay);
    }



}