package com.weinfuse.demo.frames;

import com.weinfuse.demo.utils.BowlingConstants;

public class SpareFrame implements Frame {
    private int frameId;
    private int firstRollPins;
    private boolean firstRollPinsSet = false;
    private int secondRollPins;

    private int bonusPins;
    private int bonusPinsAdded = 0;

    public SpareFrame(int frameId) {
        this.frameId = frameId;
    }

    public int getFrameId() {
        return frameId;
    }

    public int getFirstRollPins() {
        return firstRollPins;
    }

    public void setFirstRollPins(int firstRollPins) {
        this.firstRollPins = firstRollPins;
        firstRollPinsSet = true;
        secondRollPins = BowlingConstants.SPARE_TOTAL - firstRollPins;
    }

    public boolean isFirstRollPinsSet() {
        return firstRollPinsSet;
    }

    public int getSecondRollPins() {
        return secondRollPins;
    }

    public void setSecondRollPins(int secondRollPins) {
        //Invalid method;
    }

    public boolean isSecondRollPinsSet() {
        return true;
    }

    public int getTotal() {
        return firstRollPins + secondRollPins + bonusPins;
    }

    public Frame addBonusPins(int pins) {
        if (bonusPinsAdded < 1) {
            bonusPins += pins;
            bonusPinsAdded++;
        }
        return this;
    }

    public boolean isValidPinCount() {
        return firstRollPins + secondRollPins + bonusPins <= BowlingConstants.MAXIMUM_SPARE_FRAME_COUNT;
    }

    public boolean isCompleted() {
        return bonusPinsAdded == 1;
    }

    public Frame processBonusPinsFromAnotherFrame(Frame nextFrame) {
        this.addBonusPins(nextFrame.getFirstRollPins());
        return this;
    }
}
