package com.weinfuse.demo.frames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @Test
    void addBonusPins() {
        NormalFrame frame = new NormalFrame(1);
        frame.setFirstRollPins(4);
        frame.setSecondRollPins(4);

        int expected = 8;
        frame.addBonusPins(5);

        //Total for frame should not have added bonus pins
        assertEquals(expected, frame.getTotal());
    }

    @Test
    void isValidPinCount() {
        NormalFrame frame = new NormalFrame(1);
        frame.setFirstRollPins(4);
        frame.setSecondRollPins(4);

        assertTrue(frame.isValidPinCount());
    }

    @Test
    void isNotValidPinCount() {
        NormalFrame frame = new NormalFrame(1);
        frame.setFirstRollPins(4);
        frame.setSecondRollPins(8);

        assertFalse(frame.isValidPinCount());
    }

    @Test
    void isCompleted() {
        NormalFrame frame = new NormalFrame(1);
        frame.setFirstRollPins(4);
        frame.setSecondRollPins(4);

        assertTrue(frame.isCompleted());
    }

    @Test
    void isNotCompleted() {
        NormalFrame frame = new NormalFrame(1);
        frame.setFirstRollPins(4);

        assertFalse(frame.isCompleted());
    }
}