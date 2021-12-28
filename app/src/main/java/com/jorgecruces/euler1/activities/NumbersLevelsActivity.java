package com.jorgecruces.euler1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_levels);

        // Set title
        textViewLevelTitleNumbers = (TextView) findViewById(R.id.textViewLevelLabel);
        textViewLevelTitleNumbers.setText("Euler 1");

        gridLayout = (ViewGroup) findViewById(R.id.gridLayoutNumbers);
        setUpLevelNumbersButtons();
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
            gridLayout.addView(levelButton);
        }

    }

}