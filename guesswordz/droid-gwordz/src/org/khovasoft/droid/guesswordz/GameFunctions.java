/*
 * GuessWordz
 * 
 * GameFunctions.Java
 * Assorted Game Dependent Calculations
 * 
 * (c) 2011-2012 Kareem J. Glover dba KHOVASoft
 */


package org.khovasoft.droid.guesswordz;

public class GameFunctions{
    
    /*
     * Convert Character to Lowercase
     */
    public static char convertChar(char pchar)
    {
            char lower = Character.toLowerCase(pchar);
           
            return lower;
    }
    
    
    /*
     * Determine if coordinates are within a Sprite Box
     */
    public static boolean isInBox(float xpos, float ypos, float x1, float x2, float y1, float y2)
    {
    	boolean value = false;
    	
    	if ((xpos >= x1) && (xpos <=x2) && (ypos >=y1) && (ypos <= y2))
    	{
    		value = true; // In Box
    	}
    	
    	return value;
    }
    
	/*
	 * Convert Number into Corresponding Letter
	 */
   public static char number2letter(int pvalue)
   {
	   char thechar = 'a';
	   
	   switch (pvalue)
	   {
       case 0:
           thechar = 'a';
           break;
       case 1:
    	   thechar = 'b';
           break;
       case 2:
    	   thechar = 'c';
           break;
       case 3:
    	   thechar = 'd';
           break;
       case 4:
    	   thechar = 'e';
           break;
       case 5:
    	   thechar = 'f';
           break;
       case 6:
    	   thechar = 'g';
           break;
       case 7:
    	   thechar = 'h';
           break;
       case 8:
    	   thechar = 'i';
           break;
       case 9:
    	   thechar = 'j';
           break;
       case 10:
    	   thechar = 'k';
           break;
       case 11:
    	   thechar = 'l';
           break;
       case 12:
    	   thechar = 'm';
           break;
       case 13:
    	   thechar = 'n';
           break;
       case 14:
    	   thechar = 'o';
           break;
       case 15:
    	   thechar = 'p';
           break;
       case 16:
    	   thechar = 'q';
           break;
       case 17:
    	   thechar = 'r';
           break;
       case 18:
    	   thechar = 's';
           break;
       case 19:
    	   thechar = 't';
           break;
       case 20:
    	   thechar = 'u';
           break;
       case 21:
    	   thechar = 'v';
           break;
       case 22:
    	   thechar = 'w';
           break;
       case 23:
    	   thechar = 'x';
           break;
       case 24:
    	   thechar = 'y';
           break;
       case 25:
    	   thechar = 'z';
           break;
  
       default:
    	   thechar = 'a';
           break;
	   }
	   
	   return thechar;
   }
    
    /*
     * Coordinate Letter/Value with Position
     */
    public static int findLetterGFX(char axis, char theletter, int width, int height)
    {
    	int xpos, ypos, value;
	
	    switch (theletter)
	    {
	        case 'a':
	            xpos = 0; ypos = 0;
	            break;
	        case 'b':
	            xpos = width; ypos = 0;
	            break;
	        case 'c':
	            xpos = width * 2; ypos = 0;
	            break;
	        case 'd':
	            xpos = width * 3; ypos = 0;
	            break;
	        case 'e':
	            xpos = width * 4; ypos = 0;
	            break;
	        case 'f':
	            xpos = 0; ypos = height;
	            break;
	        case 'g':
	            xpos = width; ypos = height;
	            break;
	        case 'h':
	            xpos = width * 2; ypos = height;
	            break;
	        case 'i':
	            xpos = width * 3; ypos = height;
	            break;
	        case 'j':
	            xpos = width * 4; ypos = height;
	            break;
	        case 'k':
	            xpos = 0; ypos = height * 2;
	            break;
	        case 'l':
	            xpos = width; ypos = height * 2;
	            break;
	        case 'm':
	            xpos = width * 2; ypos = height * 2;
	            break;
	        case 'n':
	            xpos = width * 3; ypos = height * 2;
	            break;
	        case 'o':
	            xpos = width * 4; ypos = height * 2;
	            break;
	        case 'p':
	            xpos = 0; ypos = height * 3;
	            break;
	        case 'q':
	            xpos = width; ypos = height * 3;
	            break;
	        case 'r':
	            xpos = width * 2; ypos = height * 3;
	            break;
	        case 's':
	            xpos = width * 3; ypos = height * 3;
	            break;
	        case 't':
	            xpos = width * 4; ypos = height * 3;
	            break;
	        case 'u':
	            xpos = 0; ypos = height * 4;
	            break;
	        case 'v':
	            xpos = width; ypos = height * 4;
	            break;
	        case 'w':
	            xpos = width * 2; ypos = height * 4;
	            break;
	        case 'x':
	            xpos = width * 3; ypos = height * 4;
	            break;
	        case 'y':
	            xpos = width * 4; ypos = height * 4;
	            break;
	        case 'z':
	            xpos = 0; ypos = height * 5;
	            break;
	        case '-':
	            xpos = width * 1; ypos = height * 5;
	            break;
	        case '/':
	            xpos = width * 2; ypos = height * 5;
	            break;
	        case '\'':
	            xpos = width * 3; ypos = height * 5;
	            break;
	        case ',':
	            xpos = width * 4; ypos = height * 5;
	            break;
	        case GuessWordz.REDBLOCK:
	            xpos = width * 1; ypos = height * 6;
	            break;
	        case GuessWordz.BLUEBLOCK:
	            xpos = width * 0; ypos = height * 6;
	            break;
	
	
	        default:
	            xpos = 0; ypos = 0;
	
	            break;
	
	    }
	    if (axis == 'x')
	    {
	        value = xpos;
	    }
	    else
	    {
	        value = ypos;
	    }
	    return value;


    }
     
   
}


