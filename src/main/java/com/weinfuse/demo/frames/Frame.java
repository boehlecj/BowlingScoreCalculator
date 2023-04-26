package com.weinfuse.demo.frames;

public interface Frame {
    int getFrameId();
    int getFirstRollPins();
    void setFirstRollPins(int firstRollPins);
    int getSecondRollPins();
    void setSecondRollPins(int secondRollPins);
    int getTotal();
    Frame addBonusPins(int pins);
    Frame processBonusPinsFromAnotherFrame(Frame nextFrame);
    boolean isValidPinCount();
    boolean isCompleted();
    boolean isFirstRollPinsSet();
    boolean isSecondRollPinsSet();
}
