package com.weinfuse.demo.frames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpareFrameTest {

    @Test
    void addBonusPins() {
        SpareFrame frame = new SpareFrame(1);
        frame.setFirstRollPins(4);

        int expected = 15;
        frame.addBonusPins(5);

        //Total for frame should have added bonus pins
        assertEquals(expected, frame.getTotal());
    }

    @Test
    void isValidPinCount() {
        SpareFrame frame = new SpareFrame(1);
        frame.setFirstRollPins(4);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(5);
        normalFrame.setSecondRollPins(3);

        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertTrue(frame.isValidPinCount());
    }

    @Test
    void isNotValidPinCount() {
        SpareFrame frame = new SpareFrame(1);
        frame.setFirstRollPins(4);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(12);
        normalFrame.setSecondRollPins(3);

        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertFalse(frame.isValidPinCount());
    }

    @Test
    void isCompleted() {
        SpareFrame frame = new SpareFrame(1);
        frame.setFirstRollPins(4);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(5);
        normalFrame.setSecondRollPins(3);

        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertTrue(frame.isCompleted());
    }

    @Test
    void isNotCompleted() {
        SpareFrame frame = new SpareFrame(1);
        frame.setFirstRollPins(4);

        assertFalse(frame.isCompleted());
    }

    @Test
    void processBonusPinsFromAnotherFrame() {
        SpareFrame frame = new SpareFrame(1);
        frame.setFirstRollPins(4);

        NormalFrame normalFrame = new NormalFrame(2);
        normalFrame.setFirstRollPins(5);
        normalFrame.setSecondRollPins(3);

        //Expected in the total spare plus the first roll of the next frame
        int expected = 15;
        frame.processBonusPinsFromAnotherFrame(normalFrame);

        assertEquals(expected, frame.getTotal());
    }
}