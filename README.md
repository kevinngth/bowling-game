# TDD: Bowling game

## A quick summary of Bowlinggame

Write a `BowlingGame` object with methods `roll(pins): void` and `getScore(): number`.
This will be the game engine which follows the rules of bowling:
* The game consists of 10 frames, in each frame the player has the ability to knock down 10 pins.
* The score for the frame is the total number of pins knocked down + bonuses for strikes and spares.
* A spare is when the player knocks down all 10 pins in 2 tries. The bonus for a spare is the next roll.
* A strike is when the player knocks down all 10 pins in 1 try. The bonus is the next 2 rolls.
* In the tenth frame a player who rolls a spare / strike gets an extra roll(s) to complete the frame.
* No more than 3 rolls can be rolled in the 10th frame.
## Bonus: BowlingScoreDisplay
Write a `BowlingGameDisplay` object that accepts a `BowlingGame` object and has method `display()`:
It should render the score something like this:
```
Name     |   1   |   2   |   3   |  4    |   5   |  6    |   7   |   8   |  9    |    10     |
----------------------------------------------------------------------------------------------
Player 1 | 0 | - | 2 | / | 8 | - | X | - | X | - | X | - | 2 | / | 5 | 2 | 4 | 2 | 2 | / | 2 |
         |   0   |   18  |  26   |  56   |  78   |  98   |  113  |  120  |  126  |    138    |
```
## Super Bonus: Multiple Players
Write a `MultiplayerBowlingGame` with method
 * `register(name): void`
 * `roll(name, pins): void`
 * `displayScore(name): BowlingGameDisplay`
 * `winner(): string` - get the winner
I can see the current `BowlingScoreDisplay` of a given player `name`.
I can see the winner at the end of the `BowlingScoreGame`.
I cannot move on the next frame when not all players has finished the roll.
It logs Turkey!!! If a player hits three strikes in a row.
## Hint
### Spare
```
roll(2)
  -> roll(8)
  -> roll(4)
  -> roll(1)
  -> score() => Frame 1 (2 + 8 + 4) + Frame 2 (4 + 1) = 19
                                 ^
			                  Bonus from next Roll
```
### Spare then Spare
```
roll(2)
  -> roll(8)
  -> roll(2)
  -> roll(8)
  -> roll(1)
  -> roll(2)
  -> score() => Frame 1 (2 + 8 + 2) + Frame 2 (2 + 8 + 1) + Frame 3 (1 + 2) = 
                                 ^                     ^
			            Bonus from next Roll                Bonus from next Roll 
```
### Strike
```
roll(10)
	-> roll(4)
	-> roll(1)
	-> score() => Frame 1 (10 + 4 + 1) + Frame 2 (4 + 1) = 20
                              ^   ^
			              Bonus from next Two Rolls
```
### Spare then Strike
```
roll(2)       <-- Frame 1
  -> roll(8)
  -> roll(10) <-- Frame 2
  -> roll(5)  <-- Frame 3
  -> roll(5)  
  -> roll(0)  <-- Frame 4
  -> roll(0)
  -> score() => Frame 1 (2 + 8 + 10) + Frame 2 (10 + (-) + 5 + 5) + Frame 3 (5 + 5 + 0) + Frame 4 (0 + 0) = 50 
                                  ^                        ^   ^                     ^
			            Bonus from next Roll         Bonus from next 2 Rolls       Bonus from next Roll
```
### Turkey!!
```
roll(10)        <-- Frame 1
  -> roll(10)   <-- Frame 2
	-> roll(10)   <-- Frame 3
	-> roll(5)           Bonus from next Two Rolls
	-> roll(1)                   V    V
	-> score() => Frame 1 (10 + 10 + 10)
	  	  	    + Frame 2 (10 + 10 + 5) 
	            + Frame 3 (10 +  5 + 1) <-- Bonus from next Two Rolls (5 + 1)
	            + Frame 4 ( 5 +  1)
```
