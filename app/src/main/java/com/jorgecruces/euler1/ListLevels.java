package com.jorgecruces.euler1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jorgecruces.euler1.ListLevel.CustomAdapter;
import com.jorgecruces.euler1.ListLevel.LevelElement;

import java.util.ArrayList;
import java.util.List;

public class ListLevels extends AppCompatActivity
{

    private ListView listViewLevels;
    private List<LevelElement> levelElementList;

    public List<LevelElement> getLevelElements()
    {
        levelElementList = new ArrayList<>();
        String[] titlesArray = getResources().getStringArray(R.array.levels_name);

        int levelNumber = 1;
        for (String title : titlesArray)
        {
            levelElementList.add(new LevelElement(title,Integer.toString(levelNumber)));
            levelNumber++;
        }

        return levelElementList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_levels);

        listViewLevels = (ListView) findViewById(R.id.listViewLevelElements);

        CustomAdapter adapter = new CustomAdapter(this,getLevelElements());
        listViewLevels.setAdapter(adapter);

        listViewLevels.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                LevelElement element = levelElementList.get(i);
                Toast.makeText(getBaseContext(), element.getLevelTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }




    public void onClickGoBackToMainScreen(View view)
    {
        Intent intentGoBack = new Intent(this, MainActivity.class);
        startActivity(intentGoBack);
    }

}