package com.jorgecruces.euler1.gameLogic;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParserActivity extends AppCompatActivity
{
    public List<Question> getQuestionList()
    {
        List<Question> questionList = new ArrayList<Question>();

        try
        {

            InputStream in = getAssets().open("questions.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);
            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("question");
            for(int i = 0; i < nList.getLength(); i++)
            {
                Node node = nList.item(i);
                Element explorer = (Element) node;

                Node nodeQuestionLabel,nodeAlternative1,nodeAlternative2,nodeAlternative3,nodeCorrectAlternative;

                nodeQuestionLabel = explorer.getElementsByTagName("questionLabel").item(0).getFirstChild();
                nodeAlternative1 = explorer.getElementsByTagName("alternative1").item(0).getFirstChild();
                nodeAlternative2 = explorer.getElementsByTagName("alternative2").item(0).getFirstChild();
                nodeAlternative3 = explorer.getElementsByTagName("alternative3").item(0).getFirstChild();
                nodeCorrectAlternative = explorer.getElementsByTagName("correctAlternative").item(0).getFirstChild();

                String questionLabel, alternative1,alternative2,alternative3,correctAlternative;

                questionLabel = nodeQuestionLabel.getNodeValue();
                alternative1 = nodeAlternative1.getNodeValue();
                alternative2 = nodeAlternative2.getNodeValue();
                alternative3 = nodeAlternative3.getNodeValue();
                correctAlternative = nodeCorrectAlternative.getNodeValue();

                Question questionTemp = new Question(questionLabel,alternative1,alternative2,alternative3,correctAlternative);
                questionList.add(questionTemp);
            }

        }
        catch (IOException | SAXException | ParserConfigurationException e)
        {

            Toast.makeText(this, "An error ocurred, restart the app", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return questionList;
    }

}
