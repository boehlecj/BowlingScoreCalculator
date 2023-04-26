package com.weinfuse.demo.frames;

import com.weinfuse.demo.utils.BowlingConstants;

public class NormalFrame implements Frame {
    private int frameId;
    private int firstRollPins;
    private boolean firstRollPinsSet = false;
    private int secondRollPins;
    private boolean secondRollPinsSet = false;

    public NormalFrame(int frameId) {
        this.frameId = frameId;
    }

    public int getFrameId() {
        return frameId;
    }

    public int getTotal() {
        return firstRollPins + secondRollPins;
    }

    public int getFirstRollPins() {
        return firstRollPins;
    }

    public void setFirstRollPins(int firstRollPins) {
        this.firstRollPins = firstRollPins;
        firstRollPinsSet = true;
    }

    public boolean isFirstRollPinsSet() {
        return firstRollPinsSet;
    }

    public int getSecondRollPins() {
        return secondRollPins;
    }

    public void setSecondRollPins(int secondRollPins) {
        this.secondRollPins = secondRollPins;
        secondRollPinsSet = true;
    }

    public boolean isSecondRollPinsSet() {
        return secondRollPinsSet;
    }

    public Frame addBonusPins(int pins) {
        return this;
    }

    public boolean isValidPinCount() {
        return firstRollPins + secondRollPins <= BowlingConstants.MAXIMUM_NORMAL_FRAME_COUNT;
    }

    public boolean isCompleted() {
        return (firstRollPinsSet && secondRollPinsSet);
    }

    public Frame processBonusPinsFromAnotherFrame(Frame nextFrame) {
        return this;
    }
}
