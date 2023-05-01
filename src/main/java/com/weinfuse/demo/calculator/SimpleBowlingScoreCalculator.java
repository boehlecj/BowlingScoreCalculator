package com.weinfuse.demo.calculator;

import java.util.ArrayList;
import java.util.List;

public class SimpleBowlingScoreCalculator {
    public static Object[] calculateBowlingFrameScores(Object[] rolls) {
       List<Object> score = new ArrayList<>();
        
        int pointer = 0;
        for (int i=0; i < 10; i++) {
            if (!canComplete(rolls.length, pointer)) {
                break;
            }

            if (isStrike(rolls[pointer])) {
                if (canComplete(rolls.length, pointer + 2)) {
                    score.add(10 + parseNextTwoRolls(rolls[pointer + 1], rolls[pointer + 2]));
                } else {
                    score.add("nil");
                }
                pointer++;
            } else if (isSpare(rolls[pointer])) {
                if (canComplete(rolls.length, pointer + 1)) {
                    score.add(10 + parseNextRoll(rolls[pointer + 1]));
                } else {
                    score.add("nil");
                }
                pointer = pointer + 2;
            } else {
                if (canComplete(rolls.length, pointer + 1)) {
                    score.add((Integer) rolls[pointer] + (Integer) rolls[pointer + 1]);
                } else {
                    score.add("nil");
                }
                pointer = pointer + 2;
            }


            
        }
        return score.toArray();
    }

    private static boolean canComplete(int rollLength, int pointerLength) {
        return rollLength > pointerLength;
    }

    private static boolean isStrike(Object rolls) {
        return rolls.equals("X");
    }

    private static boolean isSpare(Object rolls) {
        return rolls.equals("/");
    }

    private static int parseNextTwoRolls(Object roll1, Object roll2) {
        int total = 0;
        if (isStrike(roll1) && isStrike(roll2)) {
            total+= 20;
        } else if (isStrike(roll1)) {
            total+= 10 + (Integer) roll2;
        } else if (isSpare((roll2))) {
            total+= 10;
        } else {
            total = (Integer) roll1 + (Integer) roll2;
        }

        return total;

    }

    private static int parseNextRoll(Object roll1) {
        if (isStrike(roll1) ) {
            return 10;
        }

        return (Integer) roll1;

    }
}
