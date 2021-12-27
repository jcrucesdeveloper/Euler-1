package com.jorgecruces.euler1.view.numberLevelActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;

import com.jorgecruces.euler1.activities.QuestionActivity;


/**
 * ButtonLevel that will be replicated 300th times
 */
@SuppressLint("AppCompatCustomView")
public class LevelButton extends Button {

    private int number;
    private Context context;

    public LevelButton(Context context, Integer number) {
        super(context);
        this.context = context;
        this.number = number;
        configureButton();
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
        if (this.number > 2)
        {
            this.setBackgroundColor(Color.GRAY);

        }
        else
        {
            this.setBackgroundColor(Color.WHITE);
        }
        this.setTextColor(Color.BLACK);

    }

    /**
     * OnClickLevel button, it go to the current questionActivity if allowed
     * We use the number of this button to go the QuestionActivity using that number
     */
    public void onClickLevelButton()
    {
        Intent questionActivityIntent = new Intent(this.context, QuestionActivity.class);
        questionActivityIntent.putExtra("levelNumber",Integer.toString(this.number));
        this.context.startActivity(questionActivityIntent);
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

