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

import java.util.ArrayList;


/**
 * Activity with the number levels (1-300)
 */
public class NumbersLevelsActivity extends AppCompatActivity {

    private TextView textViewLevelTitleNumbers;
    private ViewGroup gridLayout;

    private ArrayList<LevelButton> levelButtons;

    private int lastLevelNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_levels);

        levelButtons = new ArrayList<>();

        // Set title
        textViewLevelTitleNumbers = (TextView) findViewById(R.id.textViewLevelLabel);
        textViewLevelTitleNumbers.setText(R.string.app_name);

        gridLayout = (ViewGroup) findViewById(R.id.gridLayoutNumbers);

        this.lastLevelNumber = getLastLevelInteger();
        setUpLevelNumbersButtons(this.lastLevelNumber);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.lastLevelNumber = getLastLevelInteger();
        updateButtonsState();
    }


    /**
     * Current level player
     * @return the last Level that is currently the player
     */
    public int getLastLevelInteger()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(getString(R.string.level),1);
    }


    /**
     * Go back to the main menu (MainActivity)
     */
    public void onClickGoBackArrow(View view)
    {
        finish();
    }

    /**
     * Set up 100th levelButtons
     */
    public void setUpLevelNumbersButtons(int lastLevelNumber)
    {
        for (int i = 1; i <= 100; i++)
        {
            LevelButton levelButtonTemp = new LevelButton(this, i,lastLevelNumber);
            levelButtons.add(levelButtonTemp);
            gridLayout.addView(levelButtonTemp);
        }
    }

    private void updateButtonsState()
    {
        for (LevelButton levelButton : levelButtons)
        {
            levelButton.setLastLevelNumber(this.lastLevelNumber);
            levelButton.updateButton();
        }
    }

}