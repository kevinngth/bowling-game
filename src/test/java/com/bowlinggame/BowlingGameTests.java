package com.bowlinggame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTests {

	@Test
	void shouldReturn0() {
		BowlingGame bowlingGame = new BowlingGame();
		assertThat( bowlingGame.getScore() ).isEqualTo( 0 );
	}

	@Test
	void shouldReturnScore() {
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.roll( new Frame( 0, 2, null ) );
		assertThat( bowlingGame.getScore() ).isEqualTo( 2 );
	}

	@Test
	void shouldReturnNormalCalculation() {
		BowlingGame bowlingGame = new BowlingGame();
		Frame f1 = new Frame( 0, 0, null );
		Frame f2 = new Frame( 2, 7, f1 );
		bowlingGame.roll( f1, f2 );
		assertThat( bowlingGame.getScore() ).isEqualTo( 9 );
	}

	@Test
	void shouldCalculateBonusFromSpare() {
		BowlingGame bowlingGame = new BowlingGame();
		Frame f1 = new Frame( 0, 0, null );
		Frame f2 = new Frame( 2, 8, f1 );
		Frame f3 = new Frame( 8, 0, f2 );
		Frame f4 = new Frame( 0, 0, f3 );
		bowlingGame.roll( f1, f2, f3 );
		assertThat( bowlingGame.getScore() ).isEqualTo( 26 );
	}

	@Test
	void shouldCalculateBonusFromStrike() {
		BowlingGame bowlingGame = new BowlingGame();
		Frame f1 = new Frame( 10, 0, null );
		Frame f2 = new Frame( 2, 7, f1 );
		Frame f3 = new Frame( 8, 0, f2 );
		Frame f4 = new Frame( 0, 0, f3 );
		bowlingGame.roll( f1, f2, f3, f4 );
		assertThat( bowlingGame.getScore() ).isEqualTo( 36 );
	}

	@Test
	void shouldCalculateBonusFromTurkey() {
		BowlingGame bowlingGame = new BowlingGame();
		Frame f1 = new Frame( 0, 0, null );
		Frame f2 = new Frame( 2, 8, f1 );
		Frame f3 = new Frame( 8, 0, f2 );
		Frame f4 = new Frame( 10, 0, f3 );
		Frame f5 = new Frame( 10, 0, f4 );
		Frame f6 = new Frame( 10, 0, f5 );
		Frame f7 = new Frame( 2, 8, f6 );
		Frame f8 = new Frame( 5, 2, f7 );
		bowlingGame.roll( f1, f2, f3, f4, f5, f6, f7, f8 );
		assertThat( bowlingGame.getScore() ).isEqualTo( 120 );
	}

	@Test
	void shouldConsiderBonusRollOnTenthFrame() {
		BowlingGame bowlingGame = new BowlingGame();
		Frame f1 = new Frame( 0, 0, null );
		Frame f2 = new Frame( 2, 8, f1 );
		Frame f3 = new Frame( 8, 0, f2 );
		Frame f4 = new Frame( 10, 0, f3 );
		Frame f5 = new Frame( 10, 0, f4 );
		Frame f6 = new Frame( 10, 0, f5 );
		Frame f7 = new Frame( 2, 8, f6 );
		Frame f8 = new Frame( 5, 2, f7 );
		Frame f9 = new Frame( 4, 2, f8 );
		Frame f10 = new Frame( 2, 8, 2, f9 );
		bowlingGame.roll( f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 );
		assertThat( bowlingGame.getScore() ).isEqualTo( 138 );
	}

	@Test
	void shouldConsiderAllStrikes() {
		BowlingGame bowlingGame = new BowlingGame();
		Frame f1 = new Frame( 10, 0, null );
		Frame f2 = new Frame( 10, 0, f1 );
		Frame f3 = new Frame( 10, 0, f2 );
		Frame f4 = new Frame( 10, 0, f3 );
		Frame f5 = new Frame( 10, 0, f4 );
		Frame f6 = new Frame( 10, 0, f5 );
		Frame f7 = new Frame( 10, 0, f6 );
		Frame f8 = new Frame( 10, 0, f7 );
		Frame f9 = new Frame( 10, 0, f8 );
		Frame f10 = new Frame( 10, 10, 10, f9 );
		bowlingGame.roll( f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 );
		assertThat( bowlingGame.getScore() ).isEqualTo( 300 );
	}
}