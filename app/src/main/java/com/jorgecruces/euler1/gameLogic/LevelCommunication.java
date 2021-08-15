package com.jorgecruces.euler1.gameLogic;

public class LevelCommunication
{
    public static final String LEVEL_TITLE_NUMBERS_KEY = "LEVEL_TITLE_KEY";
    public static final String LEVEL_NUMBER_NUMBERS_KEY = "LEVEL_NUMBER_NUMBERS_KEY";


    public static final String QUESTION_NUMBER_KEY = "QUESTION_NUMBER_KEY";

    public static int getStartNumberUsingLevelNumber(int levelNumber)
    {
        switch (levelNumber)
        {
            case 1:
                return 1;
            case 2:
                return 51;
            case 3:
                return 101;
            case 4:
                return 151;
            case 5:
                return 201;
            case 6:
                return 251;
            case 7:
                return 301;
            case 8:
                return 351;
            case 9:
                return 401;
            case 10:
                return 451;
        }
        return 1;

    }

}
