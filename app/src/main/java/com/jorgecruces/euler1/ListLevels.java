package com.jorgecruces.euler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListLevels extends AppCompatActivity
{

    private ListView listViewLevels;
    private List<LevelElement> levelElementList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_levels);

        listViewLevels = (ListView) findViewById(R.id.listViewLevelElements);

        levelElementList = new ArrayList<>();
        levelElementList.add(new LevelElement("Jorge","1"));
        levelElementList.add(new LevelElement("Jorge","2"));
        levelElementList.add(new LevelElement("Jorge","3"));

        CustomAdapter adapter = new CustomAdapter(this,levelElementList);
        listViewLevels.setAdapter(adapter);

    }



    public void onClickGoBackToMainScreen(View view)
    {
        Intent intentGoBack = new Intent(this, MainActivity.class);
        startActivity(intentGoBack);
    }

}