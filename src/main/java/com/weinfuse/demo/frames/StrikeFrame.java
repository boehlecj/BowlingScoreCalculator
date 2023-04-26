package com.weinfuse.demo.frames;

import com.weinfuse.demo.utils.BowlingConstants;

public class StrikeFrame implements Frame {
    private int frameId;
    private int firstRollPins;

    private int bonusPins;
    private int bonusPinsAdded = 0;

    public StrikeFrame(int frameId) {
        this.frameId = frameId;
        this.firstRollPins = BowlingConstants.STRIKE;
    }

    public int getFrameId() {
        return frameId;
    }

    public int getFirstRollPins() {
        return firstRollPins;
    }

    public void setFirstRollPins(int firstRollPins) {
        //Not Implemented
    }

    public boolean isFirstRollPinsSet() {
        return true;
    }

    public int getSecondRollPins() {
        return 0;
    }

    public void setSecondRollPins(int secondRollPins) {
        //Not Implemented
    }

    public boolean isSecondRollPinsSet() {
        return true;
    }

    public int getTotal() {
        return firstRollPins + bonusPins;
    }

    public Frame addBonusPins(int pins) {
        if (bonusPinsAdded < 2) {
            bonusPins += pins;
            bonusPinsAdded++;
        }
        return this;
    }

    public boolean isValidPinCount() {
        return firstRollPins + bonusPins <= BowlingConstants.MAXIMUM_STRIKE_FRAME_COUNT;
    }

    public boolean isCompleted() {
        return bonusPinsAdded == 2;
    }

    public Frame processBonusPinsFromAnotherFrame(Frame nextFrame) {
        if ((nextFrame instanceof NormalFrame || nextFrame instanceof SpareFrame)) {
            this.addBonusPins(nextFrame.getFirstRollPins());

            if (bonusPinsAdded < 2 && nextFrame.isSecondRollPinsSet()) {
                this.addBonusPins(nextFrame.getSecondRollPins());
            }
        } else if (nextFrame instanceof StrikeFrame) {
            if (bonusPinsAdded < 2) this.addBonusPins(nextFrame.getFirstRollPins());
        }

        return this;
    }
}
