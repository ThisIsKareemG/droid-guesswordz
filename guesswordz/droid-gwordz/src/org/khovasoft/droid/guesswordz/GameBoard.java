/*
 * GuessWordz
 * 
 * GameBoard.Java
 * Set GameBlock Properties
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */


package org.khovasoft.droid.guesswordz;



import android.util.Log;

//===================================STRUCTS=================================

//PhraseData - Struct that holds the data for game phrases
class PhraseData {
   String title;
   String line1;
   String line2;
   String line3;
   String line4;
   String hint;
     
}

//InputData - Struct that holds the data from external input
class InputData {
	public int last_letter_selected;
}


/*
 * GameBoard class droves games
 */
public class GameBoard extends GameObjects {
     
     public static final int MAX_PIECES = 56; // Set Max Squares on Board
     public static final int BOARD_NUM_OF_ROWS = 4;
     public static final int BOARD_ROW_LENGTH = 14;
     
     public PhraseData thePhrase = new PhraseData();
     public GameScore theScore = new GameScore();
     public InputData inputData = new InputData();
       
     String gamePhrase = "";
    
     //VARIABLES
     public GameBlock[] block = new GameBlock[MAX_PIECES];
     public boolean doRefresh = false; 
     
     
     /*
      * GameBoard() Initialize the loading of the GameBoard. 
      * This should only be called once per game.
      */
     public GameBoard() {
             
             // Loop for Creating Board Pieces
             for (int count = 0; count < MAX_PIECES; count++) {
                     block[count] = new GameBlock();            
             }
             
            // theScore.current_points = 0;                                        
     }
     
     /*
      * Load Pieces into Block
      */
     public void loadPuzzle() {
     
    	 //TODO XML loading of phrases
             
         String title = "Android App Developer";
         String phrase = "Kareem Jamaal Glover";
         String hint = "Married to Carmen Neal";
         char[] letterArray;
         
         // Convert Phrase Into Lines
         thePhrase = _convertPhrase(phrase, hint, title);
         
         // Center the Phrase
         thePhrase.line1 = _CenterPhrase(thePhrase.line1);
         thePhrase.line2 = _CenterPhrase(thePhrase.line2);
         thePhrase.line3 = _CenterPhrase(thePhrase.line3);
         thePhrase.line4 = _CenterPhrase(thePhrase.line4);
         
         // Compile Formated Phrase into One Variable
         gamePhrase = thePhrase.line1 + thePhrase.line2 + thePhrase.line3 + thePhrase.line4;
     
         //Load Formated Phrase onto blocks
         letterArray = gamePhrase.toCharArray();
         _populateBlocks(letterArray);
     
     }
     
     // ===========================================================
     // Methods
     // ===========================================================
     
     /*
      * Check if Letter Exist
      */
     public void letterCheck(char ptheLetter)
     {
    	 boolean goodGuess = false;
    	 
             for (int i = 0; i < 56; i++)
             {
                     if (convertChar(block[i].dspValue()) == ptheLetter)
                     {
                             if (block[i].isLetterVisible() == false)
                             {
                                     block[i].setLetterVisible(true);
                                     goodGuess = true;
                                     theScore.guessRight();
                                     theScore.addscore();
                                     
                             }else
                             {
                                     //Letter Already Active
                             }
                             
                     }
             }
             
             if (goodGuess == false) {theScore.guessWrong();}
             
     }
     
     private char convertChar(char pchar)
     {
             char lower = Character.toLowerCase(pchar);
             
             return lower;
     }
     
     //Load phrase data into game blocks
     private void _populateBlocks(char[] phraseArray){
             
             // Turn off all the blocks
             for (int i = 0; i < MAX_PIECES; i++)
             {
                     block[i].setBlockVisible(false);
                     block[i].setLetterVisible(false);
             }
             
             // Load Char Array Phrase into the Blocks
             
             for (int i = 0; i < gamePhrase.length(); i++)
             {
                     block[i].setValue(phraseArray[i]);
                     if (block[i].dspValue() != ' ')
                             block[i].setBlockVisible(true);
                     
                     // Light up Board on Freebies
         if (block[i].dspValue() == '\'') { block[i].setLetterVisible(true); }
         if (block[i].dspValue() == '/') { block[i].setLetterVisible(true);}
         if (block[i].dspValue() == '-') { block[i].setLetterVisible(true); }
         if (block[i].dspValue() == ',') { block[i].setLetterVisible(true);}
                     
             }
             
             
     }
     
     // Pad String Right
     private static String padRight(String s, int n) {
          return String.format("%1$-" + n + "s", s);  
     }

     // Pad String Left
     private static String padLeft(String s, int n) {
         return String.format("%1$#" + n + "s", s);  
     }
     
     // Center the Phrase for the board
     private String _CenterPhrase(String phraseLine)
     {
    	 int center, value;
    	 String phrase = "";

	     value = (BOARD_ROW_LENGTH - phraseLine.length());
	     phrase = padRight(phrase, value);
	     center = (BOARD_ROW_LENGTH - phraseLine.length()) / 2;
	     StringBuffer s1 = new StringBuffer(phrase);
	     s1.insert(center, phraseLine);
	     phrase = s1.toString();
	         
	     Log.i(GuessWordz.TAG, "[GameBoard] _CenterPhrase: " + phrase +"*");
	     Log.i(GuessWordz.TAG, "[GameBoard] phrase Length: " + phrase.length());
	
	     return phrase;

 }
     
     /*
      * _convertPhrase Handles the converting of phrase into game data. 
      * This takes the string and breaks it into separate rows.  The loop 
      * takes the word and assign it to corresponding row line depending 
      * on the number of free letter spaces available.
      */
     private PhraseData _convertPhrase(String phrase, String hint, String title){
              
             PhraseData tempPhrase = new PhraseData();
             String line1, line2, line3, line4, space, tempword;
             
             int wordcount = 0;
             String[] words = phrase.split(" ");
             
             //Initialize Variables
             line1 = "";
             line2 = "";
             line3 = "";
             line4 = "";
             space = "";
             tempword = "";
     
             // Start Word Splitting Loop
             int linecount = 1;
     
		     for (String word : words)
		     {
		             if (linecount == 1)
		             {
		                     if (line1.length() <= BOARD_ROW_LENGTH)
		                     {
		                      if (line1.length() > 0) { space = " "; }
		                  tempword = line1;
		                  tempword += space + "" + word;
		   
		                  if (tempword.length() < BOARD_ROW_LENGTH)
		                  {
		                      line1 += space + "" + word;
		                  }
		                  else
		                  {
		                      linecount = 2;
		                      space = "";
		                  }  
		                     }
		             }  
		    
		             if (linecount == 2)
		         {
		             if (line1.length() <= BOARD_ROW_LENGTH)
		             {
		                 if (line2.length() > 0) { space = " "; }
		                 tempword = line2;
		                 tempword += space + "" + word;
		                 
		                 if (tempword.length() < BOARD_ROW_LENGTH)
		                 {
		                     line2 += space + "" + word;
		                 }
		                 else
		                 {
		                     linecount = 3;
		                     space = "";
		
		                 }
		             }
		
		         } 
		             
		         if (linecount == 3)
		         {
		             if (line3.length() <= BOARD_ROW_LENGTH)
		             {
		                 if (line1.length() > 0) { space = " "; }
		                 tempword = line3;
		                 tempword += space + " " + word;
		                 
		                 if (tempword.length() < BOARD_ROW_LENGTH)
		                 {
		                     line3 += space + " " + word; 
		                 }
		                 else
		                 {
		                     linecount = 4;
		                     space = "";
		                 }
		             }
		
		         }
		         
		         if (linecount == 4)
		         {
		             if (line1.length() <= BOARD_ROW_LENGTH)
		             {
		                 if (line4.length() > 0) { space = " "; }
		                 tempword = line4;
		                 tempword += space + " " + word;
		                 
		                 if (tempword.length() < BOARD_ROW_LENGTH)
		                 {
		                     line4 += space + " " + word; 
		                 }
		                 else
		                 {
		                     linecount = 5;
		                 }
		             }
		
		         }
		
		         wordcount += 1;
		     }
		
		     tempPhrase.hint = hint;
		     tempPhrase.title = title;
		     tempPhrase.line1 = line1;
		     tempPhrase.line2 = line2;
		     tempPhrase.line3 = line3;
		     tempPhrase.line4 = line4;
		     
		     Log.i(GuessWordz.TAG, "tempPhraseTitle=" + tempPhrase.title.toString());
		     Log.i(GuessWordz.TAG, "tempLine1=" + tempPhrase.line1);
		     Log.i(GuessWordz.TAG, "tempLine2=" + tempPhrase.line2);
		     Log.i(GuessWordz.TAG, "tempLine3=" + tempPhrase.line3);
		     Log.i(GuessWordz.TAG, "tempLine4=" + tempPhrase.line4);
		     
		     return tempPhrase;
	     }
		             

}

