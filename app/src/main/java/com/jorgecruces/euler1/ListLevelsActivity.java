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
import com.jorgecruces.euler1.gameLogic.LevelCommunication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListLevelsActivity extends AppCompatActivity
{

    private ListView listViewLevels;
    private List<LevelElement> levelElementList;
    private Map<String,Integer> mapLevels;

    public List<LevelElement> getLevelElements()
    {
        levelElementList = new ArrayList<>();
        String[] titlesArray = getResources().getStringArray(R.array.levels_name);

        mapLevels = new HashMap<String,Integer>();

        int levelNumber = 1;
        for (String title : titlesArray)
        {

            boolean locked = false;

            if("Euler".equals(title))
            {
                locked = false;
            }
            mapLevels.put(title, levelNumber);

            levelElementList.add(new LevelElement(title,Integer.toString(levelNumber),locked));
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
                LevelElement levelElement = levelElementList.get(i);
                numbersLevelActivityInitilization(levelElement);

            }
        });

    }

    public void numbersLevelActivityInitilization(LevelElement levelElement)
    {
        boolean isLocked = levelElement.isLocked();
        String levelTitle = levelElement.getLevelTitle();
        int levelNumber = mapLevels.get(levelTitle);

        if(isLocked)
        {
            Toast.makeText(this,levelTitle + " is Locked", Toast.LENGTH_SHORT).show();
        }
        else
        {

            Intent intentNumbersLevel = new Intent(getBaseContext(),NumbersLevelsActivity.class);

            intentNumbersLevel.putExtra(LevelCommunication.LEVEL_TITLE_NUMBERS_KEY,levelTitle);
            intentNumbersLevel.putExtra(LevelCommunication.LEVEL_NUMBER_NUMBERS_KEY,levelNumber);
            startActivity(intentNumbersLevel);
        }
    }

    public void onClickGoBackToMainScreen(View view)
    {
        finish();
    }

}