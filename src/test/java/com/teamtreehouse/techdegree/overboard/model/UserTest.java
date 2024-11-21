package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    // Declaration of variables
    private Answer answer;
    private Board board;
    private User userNum2;
    private Question question;
    private User userNum1;
    private Question question2;
    private Answer answer2;
    private User userNum3;
    public int initialReputation;
    public int actualReputation;


    // Rule
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // Before
    @Before
    public void setUp() throws Exception {

        // Board
        board = new Board("Java - Unit Test");

        // Users
        userNum1 = board.createUser("Gabriel");
        userNum2 = board.createUser("Gabriela");
        userNum3 = board.createUser("Andres");

        // Questions
        question = userNum1.askQuestion("What are the advantages of using composition Model in java unit test?");
        question2 = userNum2.askQuestion("What is TDD in Java unit testing?");

        // Answers
        answer = userNum2.answerQuestion(question, "When using the composition modal of code re-use, you are in more control of what public methods you expose, as well as the naming of the methods to your specific use case.");
        answer2 = userNum3.answerQuestion(question2, "Is a development practice where you write tests before the code");
    }

    // Test method that proves that the reputation of the questioner increases by 5 points if a person votes positively on the question asked.
    @Test
    public void increasingQuestionerReputation() throws Exception {

        initialReputation = userNum1.getReputation();
        userNum2.upVote(question);
        actualReputation = userNum1.getReputation();

        assertEquals(5, actualReputation - initialReputation);

    }

    // Test method that proves that the reputation of the person who answers a question increases by 10 points if a person votes positively on the answer given.
    @Test
    public void increasingResponderReputation() throws Exception {

        initialReputation = userNum2.getReputation();
        userNum1.upVote(answer);
        actualReputation = userNum2.getReputation();

        assertEquals(10, actualReputation - initialReputation);

    }

    // Test method that proves that the reputation of the person answering a question increases by 15 points if the owner of the question accepts the answer given.
    @Test
    public void increasingResponderReputationWhenAnswerIsAccepted() throws Exception {

        initialReputation = userNum2.getReputation();
        userNum1.acceptAnswer(answer);
        actualReputation = userNum2.getReputation();

        assertEquals(15, actualReputation - initialReputation);

    }

    // Test method that validates that a person cannot upvote an own question.
    @Test
    public void tryingToGetTheUserToVoteInFavorOfYourOwnQuestion() {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");
        userNum1.upVote(question);

    }

    // Test method that validates that a person cannot downVote an own question.
    @Test
    public void tryingToHaveTheUserToDownVoteTheirOwnQuestion() {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");
        userNum1.downVote(question);
    }

    // Test method that validates that a person cannot upvote an own answer.
    @Test
    public void tryingToHaveTheUserUpvoteTheirOwnAnswer() {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");
        userNum2.upVote(answer);
    }

    // Test method that validates that a person cannot downVote an own answer.
    @Test
    public void tryingToHaveTheUserDownVoteTheirOwnAnswer() {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");
        userNum2.downVote(answer);
    }

    // Test method that validates that only the owner of a question can accept an answer.
    @Test
    public void tryingToHaveANonAuthorUserAcceptAnAnswerToTheQuestion() {
        thrown.expect(AnswerAcceptanceException.class);
        thrown.expectMessage("Only " + ((answer.getQuestion()).getAuthor()).getName() + " can accept this answer as it is their question");
        userNum3.acceptAnswer(answer);

    }

    // Test method that verifies that an answer was successfully accepted after the owner of the question accepted it.
    @Test
    public void makingTheAuthorOfTheQuestionAcceptAnAnswer() {
        userNum1.acceptAnswer(answer);
        userNum1.acceptAnswer(answer);
        assertTrue(answer.isAccepted());
    }


    // Test method that verifies that a negative vote on a question does not affect the reputation of the questioner.
    @Test
    public void votingNegativelyOnAQuestionDoesNotAffectReputation() {
        initialReputation = userNum1.getReputation();
        userNum2.downVote(question);
        actualReputation = userNum1.getReputation();

        assertEquals(initialReputation, actualReputation);

    }

    // Test method that verifies that a negative vote in response affects the answerer by one point.
    @Test
    public void votingNegativelyOnAnAnswerReducesTheReputationOfTheAnswerer() {
        initialReputation = userNum3.getReputation();
        userNum2.downVote(answer2);
        actualReputation = userNum3.getReputation();

        assertEquals(1, initialReputation - actualReputation);

    }
}

