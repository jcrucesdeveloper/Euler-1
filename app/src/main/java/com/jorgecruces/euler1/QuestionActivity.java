package com.jorgecruces.euler1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgecruces.euler1.gameLogic.LevelCommunication;
import com.jorgecruces.euler1.gameLogic.Question;
import com.jorgecruces.euler1.gameLogic.XmlParserActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.github.kexanie.library.MathView;


public class QuestionActivity extends XmlParserActivity
{

    private MathView mathView;
    private String questionNumber;
    private int questionIndex;

    private List<Question> questionList;
    private Question questionLevel;

    private String correctAlternative;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        questionNumber = getIntent().getStringExtra(LevelCommunication.QUESTION_NUMBER_KEY);
        questionIndex = Integer.parseInt(questionNumber) - 1;

        questionList = getQuestionList();

        // Question if something went wrong
        questionLevel = new Question("question","1","2","3","4","");

        try
        {
            questionLevel = questionList.get(this.questionIndex);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

        correctAlternative = questionLevel.getCorrectAlternative();


        // Question Number
        TextView textViewCurrentLevelNumber = (TextView) findViewById(R.id.textViewCurrentLevelNumber);
        textViewCurrentLevelNumber.setText(questionNumber);

        // Question Title (Optional)
        TextView textViewQuestionTile = (TextView) findViewById(R.id.textViewQuestionTitle);
        textViewQuestionTile.setText(questionLevel.getQuestionTitle());

        setUpQuestion();
        setUpAlternatives();
    }

    public void setUpQuestion()
    {
        String questionLabel = questionLevel.getQuestionLabel();
        String questionFontSize = "30";

        if(questionLabel.length() > 14) questionFontSize = "20";
        if(questionLabel.length() > 21) questionFontSize = "18";



        mathView = (MathView) findViewById(R.id.mathView);
        mathView.setText("<div style=\"font-size:" + questionFontSize + "px;\"> $$" + questionLabel + "$$ </div>");
    }

    public void setUpAlternatives()
    {
        TextView textViewAlternative1, textViewAlternative2, textViewAlternative3, textViewAlternative4;

        textViewAlternative1 = (TextView) findViewById(R.id.textViewAlternative1);
        textViewAlternative2 = (TextView) findViewById(R.id.textViewAlternative2);
        textViewAlternative3 = (TextView) findViewById(R.id.textViewAlternative3);
        textViewAlternative4 = (TextView) findViewById(R.id.textViewAlternative4);

        List<TextView> textViewList = new ArrayList<TextView>();

        textViewList.add(textViewAlternative1);
        textViewList.add(textViewAlternative2);
        textViewList.add(textViewAlternative3);
        textViewList.add(textViewAlternative4);

        String alternative1,alternative2,alternative3,alternative4;
        alternative1 = questionLevel.getAlternative1();
        alternative2 = questionLevel.getAlternative2();
        alternative3 = questionLevel.getAlternative3();
        alternative4 = questionLevel.getCorrectAlternative();

        List<String> alternativesList = new ArrayList<String>();

        alternativesList.add(alternative1);
        alternativesList.add(alternative2);
        alternativesList.add(alternative3);
        alternativesList.add(alternative4);

        Collections.shuffle(alternativesList);

        for(int i = 0; i < textViewList.size(); i++)
        {
            TextView textViewTemp = textViewList.get(i);
            String  alternativeTemp = alternativesList.get(i);
            textViewTemp.setText(alternativeTemp);
        }

    }


    public void onClickAlternative(View view)
    {
        TextView textViewAlternative = (TextView) view;
        String stringAlternative = textViewAlternative.getText().toString();

        if(stringAlternative.equals(correctAlternative))
        {
            Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();

        }
    }
}