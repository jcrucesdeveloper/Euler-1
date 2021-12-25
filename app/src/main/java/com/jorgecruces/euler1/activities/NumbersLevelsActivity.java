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
        changeTextViewsInsideViewGroup(gridLayout,0);

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
                        String questionNumber = ((TextView) v).getText().toString();

                        Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
                        intent.putExtra(LevelCommunication.QUESTION_NUMBER_KEY,questionNumber);
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