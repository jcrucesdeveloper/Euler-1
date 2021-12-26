package com.jorgecruces.euler1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgecruces.euler1.R;


/**
 * Activity with the number levels (1-300)
 */
public class NumbersLevelsActivity extends AppCompatActivity {

    private TextView textViewLevelTitleNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_levels);

        // Set title
        textViewLevelTitleNumbers = (TextView) findViewById(R.id.textViewLevelLabel);
        textViewLevelTitleNumbers.setText("Euler 1");

        ViewGroup gridLayout = (ViewGroup) findViewById(R.id.gridLayoutNumbers);

    }

}