package com.weinfuse.demo.calculator;

import com.weinfuse.demo.exceptions.InvalidBowlingScoreException;
import com.weinfuse.demo.frames.Frame;
import com.weinfuse.demo.frames.NormalFrame;
import com.weinfuse.demo.frames.SpareFrame;
import com.weinfuse.demo.frames.StrikeFrame;
import com.weinfuse.demo.utils.BowlingConstants;

import java.util.*;

/**
 * BowlingScoreCalculator assists in computing valid bowling scores from an array of individual rolls
 * Normal frames will include a pair of integers such as [5, 2]
 * Spare frames will include an integer and a spare symbol "/" such as [9, "/"]
 * Strike frames will include only one symbol ["X"]
 *
 * Frame data will be validated in accordance with current accepted bowling rules. Any frame score which exceeds the
 * boundary of this scoring model will result in an InvalidBowlingScoreException:
 * NormalFrame: 9 pins or less
 * SpareFrame: 20 pins or less
 * StrikeFrame: 30 pins or less
 *
 * Invalid roll sequences may also result in an error. If a spare symbol "/" follows a strike symbol "X" (which does
 * not exist in the bowling universe) the result will be an exception.
 */
public class BowlingScoreCalculator {
    private List<Object> score = new ArrayList<>();
    private Map<Integer, Frame> frames = new HashMap();

    public Object[] calculateBowlingFrameScores(Object[] rolls) throws InvalidBowlingScoreException {
        //There are only 10 frames in bowling. 9 frames with a maximum of 2 rolls each and a tenth frame with a maximum of 3 rolls
        if (rolls.length > 21) {
            throw new InvalidBowlingScoreException("The number of rolls exceeds what is allowed in bowling.  Score cannot be rendered.");
        }

        convertRollsToFrames(rolls);
        return score.toArray();
    }

    /**
     * This method takes an Object array of rolls during a bowling match and converts them to valid bowling frames.
     * If there are two integers in a row, the frame is a NormalFrame
     * If there is an integer and a "/" symbol, the frame is a SpareFrame (and must add the next roll as bonus pins)
     * If there is an "X" symbol, the frame is a StrikeFrame (and must add the next two rolls as bonus pins)
     * @param rolls An Object array of rolls during a bowling game
     * @throws InvalidBowlingScoreException
     */
    private void convertRollsToFrames(Object[] rolls) throws InvalidBowlingScoreException {
        Iterator<Object> rollIterator = Arrays.asList(rolls).iterator();
        int frameId = 1;

        while (rollIterator.hasNext()) {
            Frame currentFrame = null;
            Object roll = rollIterator.next();
            if (isStrikeSymbol(roll)) {
                //Check if this is a strike and move on to calculate
                currentFrame = new StrikeFrame(frameId);
                frames.put(frameId, currentFrame);
                frameId++;
            } else if (roll instanceof Integer) {
                //This frame is NOT a strike so the next character MUST bet an integer
                int firstRoll = (Integer) roll;
                //Move on to the next character which must be a spare or an integer (or an incomplete frame, if next is not available)
                if (rollIterator.hasNext()) {
                    roll = rollIterator.next();
                    //checking next roll for a completed frame or a spare
                    if (isSpareSymbol(roll)) {
                        //This frame is a spare, create a spare object
                        currentFrame = new SpareFrame(frameId);
                        currentFrame.setFirstRollPins(firstRoll);
                        frames.put(frameId, currentFrame);
                        frameId++;
                    }  else if (roll instanceof Integer) {
                        //This is a normal frame, much like my own bowling frames
                        currentFrame = new NormalFrame(frameId);
                        currentFrame.setFirstRollPins(firstRoll);
                        currentFrame.setSecondRollPins((Integer) roll);
                        frames.put(frameId, currentFrame);
                        frameId++;
                    } else if (!isSpareSymbol(roll)){
                        //The second roll is NOT an integer but is also not a spare symbol
                        throw new InvalidBowlingScoreException("An invalid bowling score has been found and cannot proceed with calculating");
                    }
                } else {
                    //This is an incomplete frame and cannot be calculated yet
                    currentFrame = new NormalFrame(frameId);
                    currentFrame.setFirstRollPins(firstRoll);
                    frames.put(frameId, currentFrame);
                    frameId++;

                }
            } else {
                //The first sequence was neither a strike nor an integer.  This is not allowed in the bowling universe.
                throw new InvalidBowlingScoreException("An invalid bowling score character has been found.");
            }

            //Add bonus points to previous frames as each frame is being processed
            processBonusPins(currentFrame);

        }

        //All frames processed, update the final score array
        updateScore();

    }

    /**
     * This method inspects the bowling frame previous to the current frame.  If the previous frame is a spare or a strike,
     * it applies the proper bonus pins from the current frame.
     * @param currentFrame
     */
    private void processBonusPins(Frame currentFrame) {
        //Check if previous frame needs bonus pins added
        if (frames.containsKey(currentFrame.getFrameId() - 1)) {
            //Process the previous frame's bonus pins
            Frame previousFrame = frames.get(currentFrame.getFrameId() - 1).processBonusPinsFromAnotherFrame(currentFrame);

            if(previousFrame instanceof StrikeFrame && frameHasPriorStrike(previousFrame)) {
                //If there are two strikes in a row we need to also apply bonus pins from the current frame to the first strike
                Frame priorStrikeFrame = frames.get(currentFrame.getFrameId() - 2);
                if (!priorStrikeFrame.isCompleted()) {
                    priorStrikeFrame.processBonusPinsFromAnotherFrame(currentFrame);
                }
            }

            if (!previousFrame.isCompleted()) {
                processBonusPins(previousFrame);
            }
        }
    }

    /**
     * Update the final frame score to the score array
     * @see InvalidBowlingScoreException if a frame score that is outside the boundries of a valid bowling frame score
     * i.e. A normal frame greater than 9 pins, a spare frame greater than 20 pins or a strike frame greater than 30
     * pins, an InvalidBowlingScoreException will be thrown
     */
    private void updateScore() throws InvalidBowlingScoreException{
        Iterator<Map.Entry<Integer, Frame>> iterator = frames.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, Frame> entry = iterator.next();
            Frame frame = entry.getValue();
            if (frame.getFrameId() <= BowlingConstants.MAXIMUM_FRAMES) {
                if (frame.isCompleted() && frame.isValidPinCount()) {
                    score.add(frame.getFrameId() - 1, frame.getTotal());
                } else if (frame.isValidPinCount()){
                    score.add(frame.getFrameId() - 1, BowlingConstants.NIL_SYMBOL);
                } else {
                    throw new InvalidBowlingScoreException("An invalid pin count was detected");
                }
            }

        }
    }

    /**
     * Convenience method to determine if the previous frame to the current one was a strike
     * @param currentFrame
     * @return true if the previous frame was a strike frame
     */
    private boolean frameHasPriorStrike(Frame currentFrame) {
        if (frames.containsKey(currentFrame.getFrameId() - 1)) {
            Frame previousFrame = frames.get(currentFrame.getFrameId() - 1);
            return previousFrame instanceof StrikeFrame;
        }

        return false;
    }

    private boolean isStrikeSymbol(Object roll) {
        return roll instanceof String && ((String) roll).equalsIgnoreCase(BowlingConstants.STRIKE_SYMBOL);
    }

    private boolean isSpareSymbol(Object roll) {
        return roll instanceof String && ((String) roll).equalsIgnoreCase(BowlingConstants.SPARE_SYMBOL);
    }
}
