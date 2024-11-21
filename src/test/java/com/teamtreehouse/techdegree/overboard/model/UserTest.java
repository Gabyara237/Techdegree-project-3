package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class UserTest {
    private Answer answer;
    private Board board;
    private User userNum2;
    private Question question;
    private User userNum1;
    private User userWhoAsks2;
    private Question question2;
    private User userWhoAnswers2;
    private Answer answer2;
    private User userNum3;
    public int initialReputation;
    public int actualReputation;

    @Before
    public void setUp() throws Exception {

        board = new Board("Java - Unit Test");

        userNum1= board.createUser("Gabriel");
        userNum2 = board.createUser("Gabriela");
        userNum3 = board.createUser("Andres");
        question = userNum1.askQuestion("What are the advantages of using composition Model in java unit test?");

        answer= userNum2.answerQuestion(question,"When using the composition modal of code re-use, you are in more control of what public methods you expose, as well as the naming of the methods to your specific use case.");

    }

    @Test
    public void increasingQuestionerReputation()throws Exception {

        initialReputation = userNum1.getReputation();
        userNum2.upVote(question);
        actualReputation = userNum1.getReputation();
       
        assertEquals(5,actualReputation-initialReputation);

    }

    @Test
    public void increasingResponderReputation() throws Exception{

        initialReputation = userNum2.getReputation();
        userNum1.upVote(answer);
        actualReputation = userNum2.getReputation();

        assertEquals(10, actualReputation-initialReputation);
        
    }


}

