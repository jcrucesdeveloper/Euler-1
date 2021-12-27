package com.jorgecruces.euler1.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jorgecruces.euler1.R;
import com.jorgecruces.euler1.view.numberLevelActivity.LevelButton;

import java.util.ArrayList;
import java.util.logging.Level;


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

    public void setUpLevelNumbersButtons() {
        for (int i = 1; i <= 100; i++) {
            LevelButton levelButton = new LevelButton(this, i);
            gridLayout.addView(levelButton);
        }

    }

}