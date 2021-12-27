package com.jorgecruces.euler1.view.numberLevelActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;



/**
 * ButtonLevel that will be replicated 300th times
 */
@SuppressLint("AppCompatCustomView")
public class LevelButton extends Button {

    Integer number;

    public LevelButton(Context context, Integer number) {
        super(context);
        this.number = number;
        configureButton();
    }

    /**
     * Configure the button
     */
    public void configureButton() {
        String levelNumberStr = Integer.toString(this.number);
        setText(levelNumberStr);
        this.setPadding(3,3,3,3);
        this.setBackgroundColor(Color.rgb(200,200,200));

    }

    /**
     * Get the level number (Integer)
     * @return number the level number
     */
    public int getNumber() {
        return this.number;
    }
}

