/*
 * GuessWordz
 * 
 * InputGameBoard.Java
 * Handles Input from Game
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */

package org.khovasoft.droid.guesswordz;

import android.util.Log;
import android.view.KeyEvent;

public class InputGameBoard
{
        /*
         * Handles all input determined for the GameBoard
         */
        public GameBoard checkGameBoardInput(int pKeyCode, int pLetterCode, GameBoard pGame)
        {
                if ((pKeyCode == KeyEvent.KEYCODE_A) || (pLetterCode == 0))
                {
                pGame.letterCheck('a');
                Log.i(GuessWordz.TAG, "A KEY PRESSED");
                pGame.inputData.last_letter_selected = 0;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_B) || (pLetterCode == 1))
                {
                pGame.letterCheck('b');
                Log.i(GuessWordz.TAG, "B KEY PRESSED");
                pGame.inputData.last_letter_selected = 1;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_C) || (pLetterCode == 2))
                {
                pGame.letterCheck('c');
                Log.i(GuessWordz.TAG, "C KEY PRESSED");
                pGame.inputData.last_letter_selected = 2;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_D) || (pLetterCode == 3))
                {
                pGame.letterCheck('d');
                Log.i(GuessWordz.TAG, "D KEY PRESSED");
                pGame.inputData.last_letter_selected = 3;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_E) || (pLetterCode == 4))
                {
                pGame.letterCheck('e');
                Log.i(GuessWordz.TAG, "E KEY PRESSED");
                pGame.inputData.last_letter_selected = 4;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_F) || (pLetterCode == 5))
                {
                pGame.letterCheck('f');
                Log.i(GuessWordz.TAG, "F KEY PRESSED");
                pGame.inputData.last_letter_selected = 5;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_G) || (pLetterCode == 6))
                {
                pGame.letterCheck('g');
                Log.i(GuessWordz.TAG, "G KEY PRESSED");
                pGame.inputData.last_letter_selected = 6;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_H) || (pLetterCode == 7))
                {
                pGame.letterCheck('h');
                Log.i(GuessWordz.TAG, "H KEY PRESSED");
                pGame.inputData.last_letter_selected = 7;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_I) || (pLetterCode == 8))
                {
                pGame.letterCheck('i');
                Log.i(GuessWordz.TAG, "I KEY PRESSED");
                pGame.inputData.last_letter_selected = 8;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_J) || (pLetterCode == 9))
                {
                pGame.letterCheck('j');
                Log.i(GuessWordz.TAG, "J KEY PRESSED");
                pGame.inputData.last_letter_selected = 9;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_K) || (pLetterCode == 10))
                {
                pGame.letterCheck('k');
                Log.i(GuessWordz.TAG, "K KEY PRESSED");
                pGame.inputData.last_letter_selected = 10;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_L) || (pLetterCode == 11))
                {
                pGame.letterCheck('l');
                Log.i(GuessWordz.TAG, "L KEY PRESSED");
                pGame.inputData.last_letter_selected = 11;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_M) || (pLetterCode == 12))
                {
                pGame.letterCheck('m');
                Log.i(GuessWordz.TAG, "M KEY PRESSED");
                pGame.inputData.last_letter_selected = 12;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_N) || (pLetterCode == 13))
                {
                pGame.letterCheck('n');
                Log.i(GuessWordz.TAG, "N KEY PRESSED");
                pGame.inputData.last_letter_selected = 13;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_O) || (pLetterCode == 14))
                {
                pGame.letterCheck('o');
                Log.i(GuessWordz.TAG, "O KEY PRESSED");
                pGame.inputData.last_letter_selected = 14;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_P) || (pLetterCode == 15))
                {
                pGame.letterCheck('p');
                Log.i(GuessWordz.TAG, "P KEY PRESSED");
                pGame.inputData.last_letter_selected = 15;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_Q) || (pLetterCode == 16))
                {
                pGame.letterCheck('q');
                Log.i(GuessWordz.TAG, "Q KEY PRESSED");
                pGame.inputData.last_letter_selected = 16;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_R) || (pLetterCode == 17))
                {
                pGame.letterCheck('r');
                Log.i(GuessWordz.TAG, "R KEY PRESSED");
                pGame.inputData.last_letter_selected = 17;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_S) || (pLetterCode == 18))
                {
                pGame.letterCheck('s');
                Log.i(GuessWordz.TAG, "S KEY PRESSED");
                pGame.inputData.last_letter_selected = 18;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_T) || (pLetterCode == 19))
                {
                pGame.letterCheck('t');
                Log.i(GuessWordz.TAG, "T KEY PRESSED");
                pGame.inputData.last_letter_selected = 19;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_U) || (pLetterCode == 20))
                {
                pGame.letterCheck('u');
                Log.i(GuessWordz.TAG, "U KEY PRESSED");
                pGame.inputData.last_letter_selected = 20;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_V) || (pLetterCode == 21))
                {
                pGame.letterCheck('v');
                Log.i(GuessWordz.TAG, "V KEY PRESSED");
                pGame.inputData.last_letter_selected = 21;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_W) || (pLetterCode == 22))
                {
                pGame.letterCheck('w');
                Log.i(GuessWordz.TAG, "W KEY PRESSED");
                pGame.inputData.last_letter_selected = 22;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_X) || (pLetterCode == 23))
                {
                pGame.letterCheck('x');
                Log.i(GuessWordz.TAG, "X KEY PRESSED");
                pGame.inputData.last_letter_selected = 23;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_Y) || (pLetterCode == 24))
                {
                pGame.letterCheck('y');
                Log.i(GuessWordz.TAG, "Y KEY PRESSED");
                pGame.inputData.last_letter_selected = 24;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_Z) || (pLetterCode == 25))
                {
                pGame.letterCheck('z');
                Log.i(GuessWordz.TAG, "Z KEY PRESSED");
                pGame.inputData.last_letter_selected = 25;
                
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_DPAD_LEFT))
                {
                        pGame.inputData.last_letter_selected -= 1;
                        // Overflow Check
                        if (pGame.inputData.last_letter_selected < 0) { pGame.inputData.last_letter_selected = 25; }
                }
                
                if ((pKeyCode == KeyEvent.KEYCODE_DPAD_RIGHT))
                {
                        pGame.inputData.last_letter_selected += 1;
                        // Overflow Check
                        if (pGame.inputData.last_letter_selected < 25) { pGame.inputData.last_letter_selected = 0; }
                }
            
                pGame.doRefresh = true; 
                
                return pGame;
                
        }
}
