package com.jorgecruces.euler1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.kexanie.library.MathView;


public class QuestionActivity extends AppCompatActivity {

    private MathView mathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

          mathView = (MathView) findViewById(R.id.mathView);

        mathView.setText("<div style=\"font-size:30px;\"> $$2 + 2 = 4$$ </div>");

    }
}