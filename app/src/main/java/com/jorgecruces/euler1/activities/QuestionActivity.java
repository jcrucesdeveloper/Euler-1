package com.jorgecruces.euler1.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgecruces.euler1.R;
import com.jorgecruces.euler1.gameLogic.Question;
import com.jorgecruces.euler1.gameLogic.XmlParserActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.kexanie.library.MathView;


public class QuestionActivity extends XmlParserActivity
{

    private MathView mathView;

    //ArrayList of Questions
    private ArrayList<Question> questionList;

    // Current question
    private Question questionLevel;

    private String correctAlternative;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setUpQuestion();
        renderLabels();
        renderQuestion();
        renderAlternatives();
    }


    /**
     * Get the question from xml asset file and charge it to the
     * Question representation named questionLevel
     *
     */
    public void setUpQuestion()
    {

        // Question number from the intent
        String questionNumberStr;
        questionNumberStr = getIntent().getStringExtra("levelNumber");

        // For some reason, we did not find the question
        if (questionNumberStr == null)
        {
            questionNumberStr = "-1";
        }


        int questionNumber = Integer.parseInt(questionNumberStr) - 1;

        questionList = getQuestionList();

        // Default question if something goes wrong
        questionLevel = new Question();
        questionLevel.setQuestionNumber(questionNumber);

        try
        {
            questionLevel = questionList.get(questionNumber);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

        correctAlternative = questionLevel.getCorrectAlternative();
        // Charged questionLevel and correctAlternative
        // Now we render things
    }

    /**
     * Render the labels of the activity:
     * - Question number (TextView)
     * - Question Title (TextView)(Optional)
     */
    public void renderLabels() {

        // Question Number
        TextView textViewCurrentLevelNumber = (TextView) findViewById(R.id.textViewCurrentLevelNumber);
        textViewCurrentLevelNumber.setText(questionLevel.getQuestionNumberString());

        // Question Title (Optional)
        TextView textViewQuestionTile = (TextView) findViewById(R.id.textViewQuestionTitle);
        textViewQuestionTile.setText(questionLevel.getQuestionTitle());
    }

    /**
     * Render the questionLabel and resize it if necessary
     */
    public void renderQuestion()
    {
        String questionLabel = questionLevel.getQuestionLabel();
        String questionFontSize = "30";

        if(questionLabel.length() > 14) questionFontSize = "20";
        if(questionLabel.length() > 21) questionFontSize = "18";



        mathView = (MathView) findViewById(R.id.mathView);
        mathView.setText("<div style=\"font-size:" + questionFontSize + "px;color:white;\"> $$" + questionLabel + "$$ </div>");
    }

    /**
     * Render the alternatives and shuffle it every time
     */
    public void renderAlternatives()
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
            String alternativeTemp = alternativesList.get(i);
            textViewTemp.setText(alternativeTemp);

            // Resize alternative
            if(alternativeTemp.length() > 10)
            {
                textViewTemp.setTextSize(TypedValue.COMPLEX_UNIT_SP,28f);

            }
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