package com.jorgecruces.euler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgecruces.euler1.gameLogic.LevelCommunication;


public class NumbersLevelsActivity extends AppCompatActivity {

    private TextView textViewLevelTitleNumbers;
    private String levelTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_levels);


        // Set title
        levelTitle = getIntent().getStringExtra(LevelCommunication.LEVEL_TITLE_NUMBERS_KEY);
        textViewLevelTitleNumbers = (TextView) findViewById(R.id.textViewLevelTitleNumbers);
        textViewLevelTitleNumbers.setText(levelTitle);

        // levelNumber
        int levelNumber = getIntent().getIntExtra(LevelCommunication.LEVEL_NUMBER_NUMBERS_KEY,0);
        int startNumber = LevelCommunication.getStartNumberUsingLevelNumber(levelNumber);



        // Put numbers
        ViewGroup gridLayout = (ViewGroup) findViewById(R.id.gridLayoutNumbers);
        changeTextViewsInsideViewGroup(gridLayout,startNumber);

    }


    public void changeTextViewsInsideViewGroup(ViewGroup viewGroup,int startNumber)
    {

        for(int i = 0; i < viewGroup.getChildCount(); i++)
        {
            View v = viewGroup.getChildAt(i);

            if(v instanceof TextView)
            {
                ((TextView) v).setText(Integer.toString(startNumber));
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String levelNumber = ((TextView) v).getText().toString();
                        // TODO
                        Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
                        intent.putExtra("lol", intent);
                        startActivity(intent);
                    }
                });
                startNumber++;
            }



        }

    }

    public void onClickGoToLevel(TextView view)
    {
        String out = view.getText().toString();
        Toast.makeText(this, out, Toast.LENGTH_SHORT).show();
    }


}