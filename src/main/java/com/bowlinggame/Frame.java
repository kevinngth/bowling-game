package com.bowlinggame;

public class Frame {
    private int firstRoll;
    private int secondRoll;
    private int specialRoll = -1;
    private Frame previousFrame;

    public Frame( int firstRoll, int secondRoll, Frame previousFrame ) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
        this.previousFrame = previousFrame;
    }
    public Frame( int firstRoll, int secondRoll, int specialRoll, Frame previousFrame ) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
        this.specialRoll = specialRoll;
        this.previousFrame = previousFrame;
    }
    public boolean isSpare() {
        return firstRoll != 10 && this.calculateBasicScore() == 10;
    }
    public int calculateScore() {
        int score = calculateBasicScore();
        score += calculateBonuses();
        score += calculateSpecialRollBonus();
        return score;
    }
    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public int getSpecialRoll() {
        return specialRoll;
    }
    public boolean isStrike() {
        return this.firstRoll == 10;
    }
    public int calculateBasicScore() {
        return firstRoll + secondRoll;
    }
    private boolean isConsecutiveStrike() {
        return previousFrame != null && previousFrame.isStrike() && this.isStrike();
    }
    private int calculateSpecialRollBonus() {
        if ( specialRoll == -1 ) { return 0; }
        return isStrike() ? this.specialRoll + 10 : this.specialRoll;
    }
    private int calculateBonuses() {
        if ( previousFrame == null ) { return 0; }
        if ( previousFrame.isConsecutiveStrike() ) { return 10 + this.firstRoll; }
        if ( previousFrame.isStrike() ) {
            return calculateBasicScore();
        } else if ( previousFrame.isSpare() ) {
            return this.firstRoll;
        } else {
            return 0;
        }
    }
}