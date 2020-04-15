package com.bowlinggame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

	public List<Frame> frames = new ArrayList<Frame>();

	private int score = 0;
	private Formatter formatter;

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public int getScore() {
		for ( Frame frame : frames ) {
			score += frame.calculateScore();
		}
		return score;
	}
	public void roll(Frame... rolls) {
		for (Frame roll : rolls) {
			frames.add( roll );
		}
	}
	public String format() {
		return formatter.toString( frames );
	}
}
