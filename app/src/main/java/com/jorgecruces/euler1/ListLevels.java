package com.jorgecruces.euler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_levels);
    }

    public void onClickGoBackToMainScreen(View view)
    {
        Intent intentGoBack = new Intent(this, MainActivity.class);
        startActivity(intentGoBack);
    }

}