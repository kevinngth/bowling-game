package com.bowlinggame;

public class LongFormatter extends Formatter {
    private static final String NIL = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String framesTitle = "Player 1";
    private final String header = "Name    ";
    private final String scoreTitle = "        ";
    private int specialWidth = 9;
    private int normalWidth = 5;

    @Override
    protected String getHeader() {
        String result = header + DIVIDER;
        for (int i = 1; i <= LAST_FRAME + 1; i++) {
            result += i == 10 ? padNumber( i, specialWidth) : padNumber( i, normalWidth );
        }
        return result + NEW_LINE + getBorder();
    }

    private String getBorder() {
        String border = "";
        for (int i = 0; i < 94; i++ ) {
            border += NIL;
        }
        return border;
    }

    @Override
    protected String getScoreTitle() {
        return scoreTitle;
    }

    @Override
    protected int getSpecialWidth() {
        return specialWidth;
    }

    @Override
    protected int getNormalWidth() {
        return normalWidth;
    }

// FRAME SCORES SECTION

    @Override
    protected String getFramesTitle() {
        return framesTitle;
    }

    @Override
    protected String printFrame( Frame frame ) {
        String result;
        if ( frame.isStrike() ) {
            result = STRIKE + DIVIDER + NIL;
        } else if ( frame.isSpare() ) {
            result = toStringScore( frame.getFirstRoll() ) + DIVIDER + SPARE;
        } else {
            result = toStringScore( frame.getFirstRoll() ) + DIVIDER + toStringScore( frame.getSecondRoll() );
        }
        result += frame.getSpecialRoll() == -1 ? "" : DIVIDER + toStringScore( frame.getSpecialRoll() );
        return result + DIVIDER;
    }

    private String toStringScore( int score ) {
        return score == 0 ? NIL : String.valueOf(score);
    }
}
