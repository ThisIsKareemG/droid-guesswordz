package org.khovasoft.droid.guesswordz;

import android.util.Log;

public class GameScore extends GameObjects {
	
	 public static final int DEFAULT_POINTS = 10;
	
	// ===========================================================
	// Variables
	// ===========================================================

	int current_points;
	int current_level;
	int number_right;
	int number_wrong;
	int streak_number_right;
	int streak_number_wrong;
	int bombs_diffused;
	int golden_balls_used;
	int num_perfect_rounds;
	int lives_left;
	int gold_balls;
	int number_wrong_level;
	int last_perfect_round;
	int last_reward_level; // give gold ball ever 5000 points
	int next_reward_level;
	
	/*
	 * Initialize the GameScore Class
	 */
	public GameScore()
	{
		current_points = 0 ;
		current_level = 0 ;
		number_right = 0 ;
		number_wrong = 0 ;
		streak_number_right = 0 ;
		streak_number_wrong = 0 ;
		bombs_diffused = 0 ;
		golden_balls_used = 0 ;
		num_perfect_rounds = 0 ;
		lives_left = 0 ;
		gold_balls = 0 ;
		number_wrong_level= 0 ;
		last_perfect_round = 0 ;
		last_reward_level = 0 ; // give gold ball ever 5000 points
		next_reward_level = 0 ;
	}
	
	/*
	 * Add Points to Current Score
	 */
	public void addscore()
	{
        current_points += DEFAULT_POINTS * number_right;
        
	}
	
	/*
	 * Records Correct Guesses
	 */
	public void guessRight()
	{
        number_wrong = 0;
        number_right += 1;
        
        if (number_right > streak_number_right)
        	{ streak_number_right = number_right; }	
        
        Log.i(GuessWordz.TAG, "[GameScore] Good Guess");
	}
	
	/*
	 * Records Incorrect Guess
	 */
	public void guessWrong()
	{
		 number_right = 0;
         number_wrong += 1;
         lives_left -= 1;
         number_wrong_level += 1;
         if (number_wrong > streak_number_wrong)
         { streak_number_wrong = number_wrong; }
         
         Log.i(GuessWordz.TAG, "[GameScore] Bad Guess");
	}
	
	public int getScore()
	{
		return current_points;
	}
	

}
