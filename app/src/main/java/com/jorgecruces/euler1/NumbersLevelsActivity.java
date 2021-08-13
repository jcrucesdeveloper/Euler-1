package com.jorgecruces.euler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class NumbersLevelsActivity extends AppCompatActivity {

    private TextView textViewNumberLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_levels);
        ViewGroup gridLayout = (ViewGroup) findViewById(R.id.gridLayoutNumbers);
        changeTextViewsInsideViewGroup(gridLayout,1);
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
                        Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
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