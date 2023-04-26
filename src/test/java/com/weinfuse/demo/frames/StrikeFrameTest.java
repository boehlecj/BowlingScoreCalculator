package com.weinfuse.demo.frames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrikeFrameTest {

    @Test
    void addBonusPins() {
        StrikeFrame frame = new StrikeFrame(1);

        int expected = 15;
        frame.addBonusPins(5);

        //Total for frame should have added bonus pins
        assertEquals(expected, frame.getTotal());
    }

    @Test
    void isValidPinCount() {
        SpareFrame frame = new SpareFrame(1);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(5);
        normalFrame.setSecondRollPins(3);

        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertTrue(frame.isValidPinCount());
    }

    @Test
    void isNotValidPinCount() {
        StrikeFrame frame = new StrikeFrame(1);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(12);
        normalFrame.setSecondRollPins(50);

        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertFalse(frame.isValidPinCount());
    }

    @Test
    void isCompleted() {
        StrikeFrame frame = new StrikeFrame(1);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(5);
        normalFrame.setSecondRollPins(3);

        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertTrue(frame.isCompleted());
    }

    @Test
    void isNotCompleted() {
        StrikeFrame frame = new StrikeFrame(1);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(5);

        assertFalse(frame.isCompleted());
    }

    @Test
    void processBonusPinsFromAnotherFrame() {
        StrikeFrame frame = new StrikeFrame(1);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(5);
        normalFrame.setSecondRollPins(3);

        //Expected in the total strike plus the next two rolls of the next frame
        int expected = 18;
        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertEquals(expected, frame.getTotal());
    }

    @Test
    void processBonusPinsFromAnotherFrame2() {
        StrikeFrame frame = new StrikeFrame(1);

        StrikeFrame nextFrame = new StrikeFrame(2);

        NormalFrame thirdFrame = new NormalFrame(3);
        thirdFrame.setFirstRollPins(5);
        thirdFrame.setSecondRollPins(3);

        //Expected in the total strike plus the 2nd strike and the first roll of the third frame
        int expected = 25;
        frame.processBonusPinsFromAnotherFrame(nextFrame);
        frame.processBonusPinsFromAnotherFrame(thirdFrame);

        assertEquals(expected, frame.getTotal());
    }
}