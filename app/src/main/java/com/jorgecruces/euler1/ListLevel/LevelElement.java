package com.jorgecruces.euler1.ListLevel;

public class LevelElement
{

    private String levelNumber;
    private String levelTitle;
    private boolean locked;


    public LevelElement(String levelTitle, String levelNumber,boolean locked) {
        this.levelTitle = levelTitle;
        this.levelNumber = levelNumber;
        this.locked = locked;
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

    public void setLocked(boolean locked) { this.locked = locked;}

    public boolean isLocked() {return this.locked;}

}
