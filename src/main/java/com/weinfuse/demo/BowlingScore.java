package com.weinfuse.demo;

import com.weinfuse.demo.calculator.BowlingScoreCalculator;
import com.weinfuse.demo.exceptions.InvalidBowlingScoreException;

public class BowlingScore {
    public static void main(String[] args) {
        Object[] rolls = {"X", "X", "X", "X", 9, "/", 8,1,"X", "X", "X", "X", "X", 5};
        //Object[] rolls = {4, 5, "X", 8};
        try {
            BowlingScoreCalculator calculator = new BowlingScoreCalculator();
            Object[] score = calculator.calculateBowlingFrameScores(rolls);

            for (int i = 0; i < score.length; i++) {
                if (score[i] != null) System.out.println("Score position " + i + "=" + score[i]);
            }
        } catch (InvalidBowlingScoreException e) {
            System.out.println("Scoring Exception: " + e.getMessage());
        }
    }
}
