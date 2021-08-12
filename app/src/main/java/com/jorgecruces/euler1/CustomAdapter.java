package com.jorgecruces.euler1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter
{

    private Context context;
    private List<LevelElement> levelElementList;

    public CustomAdapter(Context context, List<LevelElement> levelElementList)
    {
        this.context = context;
        this.levelElementList = levelElementList;
    }

    @Override
    public int getCount() {
        return levelElementList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LevelElement levelElement = this.levelElementList.get(i);

        if(null == view)
        {
            view = LayoutInflater.from(context).inflate(R.layout.list_view_element,null);
        }

        TextView textViewLevelTitle, textViewLevelNumber;
        textViewLevelTitle = (TextView) view.findViewById(R.id.textViewLevelTitle);
        textViewLevelNumber = (TextView) view.findViewById(R.id.textViewLevelNumber);

        textViewLevelTitle.setText(levelElement.getLevelTitle());
        textViewLevelNumber.setText(levelElement.getLevelNumber());

        return view;
    }
}
