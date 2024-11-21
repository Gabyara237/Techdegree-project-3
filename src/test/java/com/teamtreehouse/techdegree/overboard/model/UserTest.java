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

    @Before
    public void setUp() throws Exception {

        board = new Board("Java - Unit Test");

        userNum1= board.createUser("Gabriel");
        userNum2 = board.createUser("Gabriela");

        question = userNum1.askQuestion("What are the advantages of using composition Model in java unit test?");


    }

    @Test
    public void increasingQuestionerReputation()throws Exception {

        int initialReputation = userNum1.getReputation();
        userNum2.upVote(question);
        int actualReputation = userNum1.getReputation();
        assertEquals(5,actualReputation-initialReputation);

    }
}

