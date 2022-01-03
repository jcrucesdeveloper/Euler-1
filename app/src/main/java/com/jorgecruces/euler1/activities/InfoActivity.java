package com.jorgecruces.euler1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jorgecruces.euler1.R;
import com.jorgecruces.euler1.sound.MediaPlayerReproducer;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void onClickGoBackButton(View view) {
        MediaPlayerReproducer.getInstance().reproduceClickSound(this);
        finish();
    }

}