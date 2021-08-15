package com.jorgecruces.euler1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgecruces.euler1.gameLogic.LevelCommunication;
import com.jorgecruces.euler1.gameLogic.Question;
import com.jorgecruces.euler1.gameLogic.XmlParserActivity;

import org.w3c.dom.Text;

import java.util.List;

import io.github.kexanie.library.MathView;


public class QuestionActivity extends XmlParserActivity {

    private MathView mathView;
    private String questionNumber;
    private int questionIndex;

    private List<Question> questionList;
    private Question questionLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        questionNumber = getIntent().getStringExtra(LevelCommunication.QUESTION_NUMBER_KEY);
        questionIndex = Integer.parseInt(questionNumber) - 1;

        questionList = getQuestionList();

        // Question if something went wrong
        questionLevel = new Question("question","1","2","3","4");

        try
        {
            questionLevel = questionList.get(this.questionIndex);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

        setUpQuestion();
        setUpAlternatives();

    }



    public void setUpQuestion()
    {
        String questionLabel = questionLevel.getQuestionLabel();
        mathView = (MathView) findViewById(R.id.mathView);
        mathView.setText("<div style=\"font-size:30px;\"> $$" + questionLabel + "$$ </div>");
    }

    public void setUpAlternatives()
    {
        TextView textViewAlternative1, textViewAlternative2, textViewAlternative3, textViewAlternative4;

        textViewAlternative1 = (TextView) findViewById(R.id.textViewAlternative1);
        textViewAlternative2 = (TextView) findViewById(R.id.textViewAlternative2);
        textViewAlternative3 = (TextView) findViewById(R.id.textViewAlternative3);
        textViewAlternative4 = (TextView) findViewById(R.id.textViewAlternative4);

        textViewAlternative1.setText(questionLevel.getAlternative1());
        textViewAlternative2.setText(questionLevel.getAlternative2());
        textViewAlternative3.setText(questionLevel.getAlternative3());
        textViewAlternative4.setText(questionLevel.getCorrectAlternative());



    }
}