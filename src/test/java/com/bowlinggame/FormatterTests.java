package com.bowlinggame;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FormatterTests {
    @Test
    void shouldDisplayHeader() {
        BowlingGame game = new BowlingGame();
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains( "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " );
    }

    @Test
    void shouldDisplayBorder() {
        BowlingGame game = new BowlingGame();
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains( "----------------------------------------------------------------------------------------------" );
    }

    @Test
    void shouldDisplayTemplate() {
        BowlingGame game = new BowlingGame();
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
            "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " + "\n" +
            "----------------------------------------------------------------------------------------------"
        );
    }

    @Test
    void shouldDisplayOneFrameScore() {
        BowlingGame game = new BowlingGame();
        Frame f1 = new Frame( 5, 2, null );
        game.roll( f1 );
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
            "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " + "\n" +
            "----------------------------------------------------------------------------------------------" + "\n" +
            "Player 1 | 5 | 2 |"
        );
    }

    @Test
    void shouldDisplaySpare() {
        BowlingGame game = new BowlingGame();
        Frame f1 = new Frame( 5, 5, null );
        game.roll( f1 );
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
                "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " + "\n" +
                "----------------------------------------------------------------------------------------------" + "\n" +
                "Player 1 | 5 | / |"
        );
    }

    @Test
    void shouldDisplayStrike() {
        BowlingGame game = new BowlingGame();
        Frame f1 = new Frame( 10, 0, null );
        game.roll( f1 );
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
                "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " + "\n" +
                        "----------------------------------------------------------------------------------------------" + "\n" +
                        "Player 1 | X | - |"
        );
    }

    @Test
    void shouldDisplayMoreThanOneFrame() {
        BowlingGame game = new BowlingGame();
        Frame f1 = new Frame( 0, 0, null );
        Frame f2 = new Frame( 2, 8, f1 );
        Frame f3 = new Frame( 8, 0, f2 );
        Frame f4 = new Frame( 10, 0, f3 );
        Frame f5 = new Frame( 10, 0, f4 );
        Frame f6 = new Frame( 10, 0, f5 );
        Frame f7 = new Frame( 2, 8, f6 );
        Frame f8 = new Frame( 5, 2, f7 );
        Frame f9 = new Frame( 4, 2, f8 );
        game.roll( f1, f2, f3, f4, f5, f6, f7, f8, f9 );
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
                "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " + "\n" +
                "----------------------------------------------------------------------------------------------" + "\n" +
                "Player 1 | - | - | 2 | / | 8 | - | X | - | X | - | X | - | 2 | / | 5 | 2 | 4 | 2 |"
        );
    }

    @Test
    void shouldDisplayEntireGame() {
        BowlingGame game = new BowlingGame();
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
        game.roll( f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 );
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
                "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " + "\n" +
                "----------------------------------------------------------------------------------------------" + "\n" +
                "Player 1 | - | - | 2 | / | 8 | - | X | - | X | - | X | - | 2 | / | 5 | 2 | 4 | 2 | 2 | / | 2 |"
        );
    }

    @Test
    void shouldDisplaySimpleAccumulatedScore() {
        BowlingGame game = new BowlingGame();
        Frame f1 = new Frame( 0, 0, null );
        Frame f2 = new Frame( 2, 7, f1 );
        Frame f3 = new Frame( 8, 0, f2 );
        Frame f4 = new Frame( 5, 0, f3 );
        Frame f5 = new Frame( 4, 0, f4 );
        Frame f6 = new Frame( 6, 0, f5 );
        Frame f7 = new Frame( 2, 4, f6 );
        Frame f8 = new Frame( 5, 2, f7 );
        Frame f9 = new Frame( 4, 2, f8 );
        Frame f10 = new Frame( 2, 4, 0, f9 );
        game.roll( f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 );
        Formatter format = new LongFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
                "Name     |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |   9   |    10     | " + "\n" +
                "----------------------------------------------------------------------------------------------" + "\n" +
                "Player 1 | - | - | 2 | 7 | 8 | - | 5 | - | 4 | - | 6 | - | 2 | 4 | 5 | 2 | 4 | 2 | 2 | 4 | - | " + "\n" +
                "         |   0   |   9   |  17   |  22   |  26   |  32   |  38   |  45   |  51   |    57     |"
        );
    }

    @Test
    void shouldDisplayShortFormatter() {
        BowlingGame game = new BowlingGame();
        Frame f1 = new Frame( 0, 0, null );
        Frame f2 = new Frame( 2, 7, f1 );
        Frame f3 = new Frame( 8, 0, f2 );
        Frame f4 = new Frame( 5, 0, f3 );
        Frame f5 = new Frame( 4, 0, f4 );
        Frame f6 = new Frame( 6, 0, f5 );
        Frame f7 = new Frame( 2, 4, f6 );
        Frame f8 = new Frame( 5, 2, f7 );
        Frame f9 = new Frame( 4, 2, f8 );
        Frame f10 = new Frame( 2, 4, 0, f9 );
        game.roll( f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 );
        Formatter format = new ShortFormatter();
        game.setFormatter(format);
        assertThat( game.format() ).contains(
                "Game   =" + "\n" +
                "Frames = | 0,0 | 2,7 | 8,0 | 5,0 | 4,0 | 6,0 | 2,4 | 5,2 | 4,2 | 2,4,0 | " + "\n" +
                "Scores = |  0  |  9  | 17  | 22  | 26  | 32  | 38  | 45  | 51  |  57   | "
        );
    }
}
