package com.jorgecruces.euler1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jorgecruces.euler1.R;
import com.jorgecruces.euler1.view.numberLevelActivity.LevelButton;



/**
 * Activity with the number levels (1-300)
 */
public class NumbersLevelsActivity extends AppCompatActivity {

    private TextView textViewLevelTitleNumbers;
    private ViewGroup gridLayout;
    private int lastLevelNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_levels);

        // Set title
        textViewLevelTitleNumbers = (TextView) findViewById(R.id.textViewLevelLabel);
        textViewLevelTitleNumbers.setText(R.string.app_name);

        gridLayout = (ViewGroup) findViewById(R.id.gridLayoutNumbers);
        setUpLevelNumbersButtons();

        // The currently level that is the player
        lastLevelNumber = getLastLevelInteger();
        Log.i("LAST LEVEL NUMBER: ", String.valueOf(lastLevelNumber));
    }

    /**
     * Current level player
     * @return the last Level that is currently the player
     */
    public int getLastLevelInteger()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(String.valueOf(R.string.level),0);
    }


    /**
     * Go back to the main menu (MainActivity)
     */
    public void onClickGoBackArrow(View view) {
        finish();
    }

    /**
     * Set up 100th levelButtons
     */
    public void setUpLevelNumbersButtons() {
        for (int i = 1; i <= 100; i++) {
            LevelButton levelButton = new LevelButton(this, i);
            levelButton.setLastLevelNumber(this.lastLevelNumber);
            gridLayout.addView(levelButton);
        }

    }

}