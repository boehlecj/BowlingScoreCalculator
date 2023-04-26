package com.weinfuse.demo.calculator;

import com.weinfuse.demo.exceptions.InvalidBowlingScoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingScoreCalculatorTest {

    @Test
    void calculateBowlingFrameScoresGoodCase1Test() throws InvalidBowlingScoreException {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();
        Object[] expectedScore = {9, "nil", "nil"};

        Object[] rolls = {4, 5, "X", 8};
        Object[] score = bsc.calculateBowlingFrameScores(rolls);

        assertArrayEquals(expectedScore, score);
    }

    @Test
    void calculateBowlingFrameScoresGoodCase2Test() throws InvalidBowlingScoreException {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();
        Object[] expectedScore = {9, 19, 9};

        Object[] rolls = {4, 5, "X", 8, 1};
        Object[] score = bsc.calculateBowlingFrameScores(rolls);

        assertArrayEquals(expectedScore, score);
    }

    @Test
    void calculateBowlingFrameScoresGoodCase3Test() throws InvalidBowlingScoreException {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();
        Object[] expectedScore = {30,30,30,30,30,30,30,30,30,30};

        Object[] rolls = {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"};
        Object[] score = bsc.calculateBowlingFrameScores(rolls);

        assertArrayEquals(expectedScore, score);
    }

    @Test
    void calculateBowlingFrameScoresGoodCase4Test() throws InvalidBowlingScoreException {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();
        Object[] expectedScore = {9,24,20,20,19,9,7,29,20,20};

        Object[] rolls = {9, 0, "X", "X", 4, "/", "X", 5, 4, 6, 1, "X", "X", 9, "/", "X"};
        Object[] score = bsc.calculateBowlingFrameScores(rolls);

        assertArrayEquals(expectedScore, score);
    }

    @Test
    void calculateBowlingFrameScoresBadCase1Test() {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();

        Object[] invalidRolls = {9, 0, "X", "X", 4, "/", "X", 5, 4, 6, 1, "X", "X", 9, "/", "X",9, 0, "X", "X", 4, "/", "X", 5, 4, 6, 1, "X", "X", 9, "/", "X",9, 0, "X", "X", 4, "/", "X", 5, 4, 6, 1, "X", "X", 9, "/", "X",9, 0, "X", "X", 4, "/", "X", 5, 4, 6, 1, "X", "X", 9, "/", "X"};
        assertThrows(InvalidBowlingScoreException.class, () -> bsc.calculateBowlingFrameScores(invalidRolls), "The number of rolls exceeds what is allowed in bowling.  Score cannot be rendered.");
    }

    @Test
    void calculateBowlingFrameScoresBadCase2Test() {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();

        Object[] invalidRolls = {4, 5, "/", 8, 1};
        assertThrows(InvalidBowlingScoreException.class, () -> bsc.calculateBowlingFrameScores(invalidRolls), "An invalid bowling score character has been found.");
    }

    @Test
    void calculateBowlingFrameScoresBadCase3Test() {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();

        Object[] invalidRolls = {4, 9, "X", 8, 1};
        assertThrows(InvalidBowlingScoreException.class, () -> bsc.calculateBowlingFrameScores(invalidRolls), "An invalid bowling score character has been found.");
    }

    @Test
    void calculateBowlingFrameScoresBadCase4Test() {
        BowlingScoreCalculator bsc = new BowlingScoreCalculator();

        Object[] invalidRolls = {4, "%", "X", 8, 1};
        assertThrows(InvalidBowlingScoreException.class, () -> bsc.calculateBowlingFrameScores(invalidRolls), "An invalid bowling score character has been found.");
    }
}