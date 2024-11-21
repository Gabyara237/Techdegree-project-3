package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;

import static org.junit.Assert.*;
public class UserTest {
    private Answer answer;
    private Board board;
    private User userWhoAnswers;
    private Question question;
    private User userWhoAsks;

    @Before
    public void setUp() throws Exception {

        answer = new Answer(question,userWhoAnswers,"When using the composition model of code re-use, you are in more control of what public methods you expose, as well as the naming of the methods to your specific use case");
        board = new Board("Java - Unit Test");
        userWhoAnswers = new User(board,"Gabriela");
        userWhoAsks = new User(board,"Gabriel");
        question = new Question(userWhoAsks,"What are the advantages of using composition Model in java unit test?");
    }
}