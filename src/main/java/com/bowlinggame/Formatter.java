package com.bowlinggame;

import java.util.List;

public abstract class Formatter {

    static final String DIVIDER = " | ";
    static final String NEW_LINE = "\n";
    static final String SPACE = " ";
    static final int LAST_FRAME = 9;

    public String toString( List<Frame> frames ) {
        String result = frames.isEmpty() ?
            getHeader() :
            getHeader() + NEW_LINE + getFrameScores( frames ) + NEW_LINE + getAccumulatedScores( frames );
        System.out.println( result );
        return result;
    }

// HEADER SECTION

    protected abstract String getHeader();

// FRAME SCORES SECTION

    protected String getFrameScores( List<Frame> frames ) {
        return getFramesTitle() + DIVIDER + calculateFrameScores( frames );
    }

    protected abstract String getFramesTitle();

    protected final String calculateFrameScores( List<Frame> frames ) {
        String result = "";
        for (Frame frame : frames) {
            result += printFrame( frame );
        }
        return result;
    }

    protected abstract String printFrame( Frame frame );

// ACCUMULATED SCORE SECTION

    protected abstract int getNormalWidth();

    protected abstract int getSpecialWidth();

    protected abstract String getScoreTitle();

    final String getAccumulatedScores( List<Frame> frames ) {
        String toPrint = getScoreTitle() + DIVIDER;
        int accumulator = 0;
        for (int i = 0; i < frames.size() ; i++) {
            Frame currentFrame = frames.get( i );
            if ( currentFrame.isSpare() && i + 1 < frames.size() ) {
                accumulator += frames.get( i + 1 ).getFirstRoll();
            } else if ( currentFrame.isStrike() && i + 1 < frames.size() ) {
                accumulator += frames.get( i + 1 ).isStrike() ? frames.get( i + 1 ).getFirstRoll() + frames.get( i + 2 ).getFirstRoll() : frames.get( i + 1 ).getFirstRoll() + frames.get( i + 1 ).getSecondRoll();
            }
            accumulator += currentFrame.getFirstRoll() + currentFrame.getSecondRoll();
            toPrint += i == LAST_FRAME ?
                padNumber( accumulator, getSpecialWidth() ) :
                padNumber( accumulator, getNormalWidth() );
        }
        return toPrint;
    }

    final String padNumber(int num, int availableSpacing ) {
        String result = String.valueOf( num );
        availableSpacing -= result.length();
        while ( availableSpacing > 0 ) {
            result = availableSpacing % 2 == 0 ? SPACE + result : result + SPACE;
            availableSpacing--;
        }
        result += DIVIDER;
        return result;
    }
}

/*
* private variables in child class, uppercase or camelcase?
*
* */
