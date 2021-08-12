package com.jorgecruces.euler1.ListLevel;

public class LevelElement
{

    private String levelNumber;
    private String levelTitle;


    public LevelElement(String levelTitle, String levelNumber) {
        this.levelTitle = levelTitle;
        this.levelNumber = levelNumber;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public String getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(String levelNumber) {
        this.levelNumber = levelNumber;
    }


}
