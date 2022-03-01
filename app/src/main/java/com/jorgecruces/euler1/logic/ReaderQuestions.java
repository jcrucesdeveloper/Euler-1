package com.jorgecruces.euler1.logic;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.jorgecruces.euler1.input.xml.TagGroup;
import com.jorgecruces.euler1.input.xml.XMLInputHandler;
import com.jorgecruces.euler1.input.xml.XMLParser;
import com.jorgecruces.euler1.model.Question;

import java.util.ArrayList;

public class ReaderQuestions {

    public ReaderQuestions() {

    }

    public ArrayList<Question> readQuestions(Context context) {

        ArrayList<Question> questions = new ArrayList<>();

        XMLInputHandler xmlInputHandler = new XMLInputHandler("questions.xml",context);
        XMLParser xmlParser = new XMLParser(xmlInputHandler.getDocumentFromInputStream());

        xmlParser.setParentTag("question");

        for (int i = 0; i < xmlParser.getSizeQuestions(); i++) {
            Question questionTemp = new Question();
            TagGroup tagGroup = xmlParser.getTagGroupByNumber(i);

            questionTemp.setQuestionLabel(tagGroup.getChildTagByName("questionLabel").getValue());


            String stringAlternative1 = tagGroup.getChildTagByName("alternative1").getValue();
            String stringAlternative2 = tagGroup.getChildTagByName("alternative2").getValue();
            String stringAlternative3 = tagGroup.getChildTagByName("alternative3").getValue();
            String stringCorrectAlternative = tagGroup.getChildTagByName("correctAlternative").getValue();

            questionTemp.addAlternative(1,stringAlternative1);
            questionTemp.addAlternative(2,stringAlternative2);
            questionTemp.addAlternative(3,stringAlternative3);
            questionTemp.setCorrectAlternative(stringCorrectAlternative);
            questions.add(questionTemp);
        }
        return questions;
    }
}
