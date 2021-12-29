package com.jorgecruces.euler1.view.numberLevelActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import com.jorgecruces.euler1.activities.QuestionActivity;


/**
 * ButtonLevel that will be replicated 300th times
 */
@SuppressLint("AppCompatCustomView")
public class LevelButton extends Button {

    // the number of the button
    private int number;
    // the last level where the player is in
    private int lastLevelNumber;

    private Activity numbersLevelActivity;

    public LevelButton(Activity numbersLevelActivity,int number, int lastLevelNumber) {
        super(numbersLevelActivity);
        this.number = number;
        this.lastLevelNumber = lastLevelNumber;
        this.numbersLevelActivity = numbersLevelActivity;
        configureButton();
    }

    public void setLastLevelNumber(int lastLevelNumber)
    {
        this.lastLevelNumber = lastLevelNumber;
    }
    /**
     * Configure the button:
     *  - Add listener to go to the next activity
     *  - Set text (the number of the level)
     *  - Add padding
     *  - Add background color
     */
    public void configureButton()
    {
        this.setOnClickListener(view -> onClickLevelButton());
        String levelNumberStr = Integer.toString(this.number);
        setText(levelNumberStr);
        this.setPadding(3,3,3,3);
        // We update the state of the button depending of the last level number
        updateButton();
        this.setTextColor(Color.BLACK);
    }

    public void updateButton()
    {
        if (this.number <= this.lastLevelNumber)
        {
            this.setBackgroundColor(Color.WHITE);
            this.setEnabled(true);
        }
        else
        {
            this.setBackgroundColor(Color.GRAY);
            this.setEnabled(false);
        }
    }


    /**
     * OnClickLevel button, it go to the current questionActivity if allowed
     * We use the number of this button to go the QuestionActivity using that number
     */
    public void onClickLevelButton()
    {
        Intent questionActivityIntent = new Intent(this.numbersLevelActivity, QuestionActivity.class);
        questionActivityIntent.putExtra("levelNumber",Integer.toString(this.number));
        this.numbersLevelActivity.startActivity(questionActivityIntent);
    }


    /**
     * Get the level number (Integer)
     * @return number the level number
     */
    public int getNumber()
    {
        return this.number;
    }
}

