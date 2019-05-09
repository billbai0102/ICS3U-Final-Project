/*
*Bill Bai
*Mr.Rosen
*01-16-2019
*This program is an accurate replica of the classic game "Simon"
*                        METHODS
*splashScreen() - Animated splash screen of program.
*title() - Clears screen, draws title.
*goodBye() - Displays program info and closes program window.
*pauseProgram() - Pauses program, waits for user input before continuing.
*pauseProgram(int other) - Overloaded pauseProgram() for other methods.
*mainMenu() - Gets the menu option, and passes it to main.
*instructions() - Displays instructions on how to play the game.
*createNewFile() - Creates a new high scores file.
*saveToFile(double score, String name) - Saves new score into the file, and sorts it.
*loadHighScore() - Saves high scores from a file into a String array.
*highScore() - Displays high scores, and has option to clear high scores.
*drawTemplate() - Draws the Simon game template.
*delay(int sleepLength) - Method that uses Thread.sleep() to delay execution.
*strToInt(int convert) - Method that uses parseInt to convert from String to int.
*game() - Actual game that is playable and counts levels.
*colorFlash(int currentNum, int arcColor) - Draws on top of template to create a flash illusion.
*scoreCalculate(double scoreCount) - Calculates score given the level of user.
*sequenceGen() - Randomly pre-generates all 100 levels.
*endScreen() - Displays the end screen.
*difficulty() - Allows user to set difficulty of game.
*main(String[]args) - Main method that controls all program flow.
*
*
*                        VARIABLES
*NAME                    TYPE                DESCRIPTION
*---------------------------------------------------------------------------------------------
*menuOp                  static String       Stores the users menu option input, and passes it to
*                                                main program to redirect to the right screen.
*difficulty              String              Stores the users difficulty option input and sets the
*                                                correct initial delay length.
*userName                String              Stores the user's name if they choose to store their
*                                                score, and stores it in the file.
*initialDelay            int                 The speed at which the game runs at, set by the
*                                                difficulty.
*seqCount                double              Tracks what level the user is at by counting the
*                                                loops.
*finalScore              double              The final score of the user based on difficulty and
*                                                level.
*SEQUENCE_MAX_SIZE       final int           The maximum length of the game.
*HIGHSCORES_FILE_HEADER  final String        Stores the header, to check for correct file header,
*                                                and to write into file when creating new file.
*HIGHSCORES_FILE_NAME    final String        Stores the file name, to check for the right file,
*                                                and to name the file correctly when creating new
*                                                file.
*sequence                int array           Stores the randomized pre-determined sequence that
*                                                the sequences will be based off of.
*highScoresList          String array        Stores the user's name and score in an array to be
*                                                loaded on screen.
*circleGreen             Color               Color variable for the circle's unique green color.
*circleRed               Color               Color variable for the circle's unique red color.
*circleYellow            Color               Color variable for the circle's unique yellow color.
*circleBlue              Color               Color variable for the circle's unique blue color.
*circleFlash             Color               Color variable for the circle's unique flash color.
*goodByeBlue             Color               Color variable for the unique blue color in goodBye()
*goodByeRed              Color               Color variable for the unique red color in goodBye()
*titleFont               Font                Font variable to store font for the title
*endScreenFont           Font                Font variable to store font for the end screen
*goodByeFont             Font                Font variable to store font for the goodbye screen
*/

import java.awt.*;
import hsa.Console;
import hsa.Message;
import java.io.*;

public class SimonSays
{
    static Console c;
    static String menuOp;
    String difficulty, userName;
    int initialDelay;
    double seqCount = 1, finalScore;
    final int SEQUENCE_MAX_SIZE = 100;
    final String HIGHSCORES_FILE_HEADER = "51M0N54Y5BYB1LLB41%H1GHSC0R3S", HIGHSCORES_FILE_NAME = "highscores.smn";
    int[] sequence = new int [SEQUENCE_MAX_SIZE];
    String[] highScoresList = new String [20];

    Color circleGreen = new Color (0, 255, 114);
    Color circleRed = new Color (255, 40, 0);
    Color circleYellow = new Color (250, 255, 0);
    Color circleBlue = new Color (0, 75, 255);
    Color circleFlash = new Color (0, 255, 242);
    Color goodByeBlue = new Color (29, 0, 178);
    Color goodByeRed = new Color (158, 11, 100);

    Font titleFont = new Font ("Courier", 1, 20);
    Font endScreenFont = new Font ("Courier", 1, 25);
    Font goodByeFont = new Font ("Castellar", 1, 30);

    /*
    This method creates a new window and gives it a title.
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    No input/logic/loop is used.
    */
    public SimonSays ()
    {
	c = new Console ("Simon Says!"); //Console
    }


    /*
    This method displays the splash screen.
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    A try-catch structure is used to catch exceptions when using the Thread class
    */
    public void splashScreen ()
    {
	CircleIn a = new CircleIn (c); //Draws thread into console
	a.start (); //Starts thread
	//Catches errors when joining threads
	try
	{
	    a.join (); //Makes the next thread wait until this thread fiished
	}
	catch (Exception e)  //Catches Exception
	{
	}
	pauseProgram (1); //Pauses
	StartAnimation b = new StartAnimation (c); //Draws runnable into console
	b.run (); //
    }


    /*
    This method clears the screen and draws a title.
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    A for loop is used to draw the inanimate background
    */
    public void title ()
    {
	c.clear (); //clears screen
	c.setTextBackgroundColor (Color.pink); //sets text background color to pink
	c.setColor (Color.pink); //sets color to pink
	//draws a background that covers the whole screen
	for (int x = 0 ; x < 650 ; x++)
	{
	    c.drawLine (0 + x, 500, 0 + x, 0);
	}
	c.setColor (Color.black); //sets color to black
	c.setFont (titleFont); //sets font to titleFont
	c.drawString ("Simon Says!", 260, 20); //draws title
    }


    /*
    This method displays a goodbye message and closes the window.
    -----------------------------
    Local Variables: None.
    Global Variables:
    NAME            TYPE        DESCRIPTION
    ----------------------------------------------------------------
    goodByeFont     Font        Designated font for goodBye() method
    goodByeBlue     Color       Blue color used in goodBye()
    goodByeRed      Color       Red color used in goodBye()
    ----------------------------------------------------------------
    getChar() is used to wait for user input before closing
    */
    public void goodBye ()
    {
	title (); //clears screen, draws title
	c.setColor (Color.white); //sets color to white
	c.setFont (goodByeFont); //sets font to goodByeFont
	c.drawString ("Program Developer: Bill Bai", 30, 100); //draws text
	c.drawString ("Thank you for playing!", 80, 150); //draws text
	c.setColor (goodByeBlue); //sets color to goodByeBlue
	c.fillStar (100, 170, 100, 100); //draws star
	c.fillStar (440, 170, 100, 100); //draws star
	c.fillStar (100, 400, 100, 100); //draws star
	c.fillStar (440, 400, 100, 100); //draws star
	c.setColor (goodByeRed); //sets color to goodByeRed
	c.fillStar (270, 285, 100, 100); //draws star
	c.getChar (); //pauses program until user input is gotten
	c.close (); //closes window
    }


    /*
    This method pauses the program.
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    getChar() is used to wait for user input before resuming
    */
    private void pauseProgram ()
    {
	c.println ();
	c.println ("Press any key to continue... ");
	c.getChar (); //pauses program until user input is gotten
    }


    /*
    This is an overloaded method for pauseProgram waits for userInput
    but has a different on-screen design
    ----------------------------------------------------------------
    Local Variables: None.
    Global Variables:
    NAME            TYPE        DESCRIPTION
    ----------------------------------------------------------------
    titleFont       Font        Big font used for title
    ----------------------------------------------------------------
    getChar() is used to wait for user input before closing
    */
    private void pauseProgram (int other)
    {
	c.setFont (titleFont); //sets font to titleFont
	c.drawString ("Press any key to start!", 183, 255); //draws text
	c.getChar (); //pauses program until user input is gotten
    }


    /*
    This method get input for which screen they want to go to and
    passes it to main
    ----------------------------------------------------------------
    Local Variables: None.
    Global Variables:
    NAME            TYPE        DESCRIPTION
    ----------------------------------------------------------------
    menuOp          String
    ----------------------------------------------------------------
    A while loop is used to keep asking for input until the input
	is in the correct format
    readLine() is used to get input for menuOp
    The first if statement is used to check if menuOp length is 1 or
	not
    The second if statement is used to check if menuOp is between 1
	and 4
    A break statement is used to break the loop when menuOp is
	correctly inputted
    */
    public void mainMenu ()
    {
	//This loop keeps getting the menu option until the format is correct
	while (true) //loop keeps going until stopped
	{
	    title (); //clears screen, draws title
	    c.setColor (Color.red); //sets color to red
	    c.setFont (new Font ("Courier", 1, 20)); //draws text
	    c.drawString ("[1] Start!", 260, 60); //draws text
	    c.drawString ("[2] Instructions", 220, 90); //draws text
	    c.drawString ("[3] Highscores", 223, 120); //draws text
	    c.drawString ("[4] Exit", 265, 150); //draws text
	    //Prompt for user input
	    c.setCursor (10, 1); //sets cursor to Row: 10, Col: 1
	    c.print ("Choose one: "); //prompt
	    menuOp = c.readLine (); //Gets menu option
	    //If statement to limit string to one character
	    if (menuOp.length () != 1)
	    {
		new Message ("Please enter a valid option!", "Invalid Option"); //Error message
	    }
	    else //checks if menuOp < 5 and > 0
	    {
		//If statement to check whether user selects a valid option
		if (menuOp.charAt (0) >= '1' && menuOp.charAt (0) <= '4')
		    break; //Breaks inf. for loop
		new Message ("Please enter a valid option!", "Invalid Option"); //Error message
	    }
	}
    }


    /*
    This method displays the instructions
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    ----------------------------------------------------------------
    Input is gotten with the pauseProgram() method to pause the program
    */
    public void instructions ()
    {
	title (); //Clears screen, draws title
	c.print (' ', 2); //indent
	c.println ("Instructions: In this game, there will be a sequence of flashing colors that"); //text
	c.print (' ', 2); //indent
	c.println ("will speed up as the difficulty progresses. Your job is to select the lights"); //text
	c.print (' ', 20); //indent
	c.println ("afterwards in the correct sequence. GLHF!"); //text
	pauseProgram (); //pauses program before returning to mainMenu()
    }


    /*
    This method creates a new highscores file
    ---------------------------------------------------------------
    Local Variables:
    NAME                    TYPE               DESCRIPTION
    ----------------------------------------------------------------
    output                  PrintWriter        PrintWriter class used to draw text into
						the highScores file
    ----------------------------------------------------------------
    Global Variables:
    NAME                    TYPE               DESCRIPTION
    ----------------------------------------------------------------
    HIGHSCORES_FILE_NAME    final String       Designated font for goodBye() method
    HIGHSCORES_FILE_HEADER  final String       Blue color used in goodBye()
    ----------------------------------------------------------------
    A try-catch structure is used to catch IOExceptions when using PrintWriter class
    */
    private void createNewFile ()
    {
	PrintWriter output; //PrintWriter class
	//try catch used to catch IOExceptions from PrintWriter class
	try
	{
	    output = new PrintWriter (new FileWriter (HIGHSCORES_FILE_NAME)); //creates a file named the String in HIGHSCORES_FILE_NAME
	    output.println (HIGHSCORES_FILE_HEADER); //writes in first line as header
	    output.close (); //closes file output
	}
	catch (IOException e)  //catches IOExceptions
	{
	    new Message ("Something went wrong"); //error message
	}
    }


    /*
    This method saves a name and highscore to a file, and organizes it.
    ---------------------------------------------------------------
    Local Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    output                  PrintWriter         PrintWriter class used to draw text into
						    the highScores file
    tempScore               double              Stores a temporary score to compare
    tempName                String              Stores a temporary name that was assigned
						    to the tempScore
    endLoop                 boolean             Chooses whether to end a loop or not
    ----------------------------------------------------------------
    Global Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    HIGHSCORES_FILE_NAME    final String        Designated font for goodBye() method
    HIGHSCORES_FILE_HEADER  final String        Blue color used in goodBye()
    highScoresList          String array        Stores the highscores
    name                    String              Stores the user's name
    score                   double              Stores the user's highscore
    ----------------------------------------------------------------
    At the top, there is a try-catch structure which is used to catch IOExceptions and
	NumberFormatExceptions when using the PrintWriter class and parsing to double.
    The for loop inside of the try-catch is used to cycle through each element in the highscores
	list until the user's score is bigger than the temp score
    The if statement inside the for loop checks if there is a value stored at that location.
	If there is, then it continues in the else, but if not, it puts the name and score at that
	location and ends the loop
    The try-catch statement inside the else is used to catch NumberFormatException when parsing to
	double.
    The if statement after is used to check if the user's score is larger than the temp
	score. If it is, then it prints the user's score then uses a for loop to print the rest of the
	elements in the array, until an if statement inside of that for loop finds a null. Then it stops.
	If not, then it just prints the temporary score again.
    The last if statement checks if endLoop is true. If it is, then it breaks the for loop it is in.
    */
    private void saveToFile (double score, String name)
    {
	PrintWriter output; //This class is used to be able to print into a file
	double tempScore = 0; //Stores temp score
	String tempName; //Stores temp name
	loadHighScore (); //Loads all highscores into an array
	boolean endLoop = false; //Sets default value to false
	//try-catch used to catch various exceptions
	try
	{
	    output = new PrintWriter (new FileWriter (HIGHSCORES_FILE_NAME)); //Prints the text into specified file
	    output.println (HIGHSCORES_FILE_HEADER); //Prints header first thing
	    //Cycles through all score values in the file
	    for (int x = 0 ; x < 20 ; x += 2)
	    {
		//Checks if the current value is null or not
		if (highScoresList [x] == null || highScoresList [x + 1] == null)
		{
		    output.println (name); //If so, prints name
		    output.println (score); //If so, prints score
		    endLoop = true; //Ends loop. Sorting is done
		}
		//If the current value is not null
		else
		{
		    tempName = highScoresList [x]; //Sets the temp. name to value at x
		    //try-catch used to convert String to double
		    try
		    {
			tempScore = Double.parseDouble (highScoresList [x + 1]); //Converts the Strings at index x to double
		    }
		    catch (NumberFormatException e)  //Catches NumbrFormatException
		    {
		    }
		    //If user's score is larger than score in file
		    if (score > tempScore)
		    {
			output.println (name); //Prints user's name
			output.println (score); //Prints user's score
			//Prints the remaining values after the user's
			for (int y = x ; y < 20 ; y++)
			{
			    //If the value is null
			    if (highScoresList [y] == null)
			    {
				break; //Ends printing remaining values
			    }
			    //If the value is a String
			    else
			    {
				output.println (highScoresList [y]); //Prints the value at index y
			    }
			}
			endLoop = true; //Ends loop. Sorting is done
		    }
		    //If user's score is smaller
		    else
		    {
			output.println (tempName); //Prints the value in file
			output.println (tempScore); //Prints the value in file
		    }
		}
		//If endLoop is true
		if (endLoop)
		    break; //Ends loop. Sorting is done
	    }
	    output.close (); //Closes the PrintWriter
	}
	catch (IOException e)  //Catches IOException
	{
	    createNewFile (); //Creates fresh file
	    saveToFile (finalScore, userName); //Prints user's name and score into file
	}
	catch (NumberFormatException e)  //Catches NumberFormatException
	{
	    createNewFile (); //Creates fresh file
	    saveToFile (finalScore, userName); //Prints user's name and score into file
	}
    }


    /*
    This method reads all values from the high scores file and saves
    it to a String array.
    ---------------------------------------------------------------
    Local Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    input                   BufferedReader      Used to read elements in file
    header                  String              Used to check if header is correct
    ----------------------------------------------------------------
    Global Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    HIGHSCORES_FILE_NAME    final String        Designated font for goodBye() method
    HIGHSCORES_FILE_HEADER  final String        Blue color used in goodBye()
    highScoresList          String array        Stores the highscores
    ----------------------------------------------------------------
    At the beginning there is a try-catch to catch exceptions thrown when
	using BufferedReader and comparing headers.
    The if statement inside the try-catch is used to check if the header belongs
	to this file
    The for loop inside the if statement is used to store the values into an array
    */
    private void loadHighScore ()
    {
	BufferedReader input; //BufferedReader is needed to read from a file
	//try-catch to catch exceptions thrown when using BufferedReader and comparing headers
	try
	{
	    input = new BufferedReader (new FileReader (HIGHSCORES_FILE_NAME)); //Reads from specified file
	    String header = input.readLine (); //Reads first line, stores it in String header
	    //Checks if header belongs to this file
	    if (header.equals (HIGHSCORES_FILE_HEADER))
	    {
		//Stores first 20 values into an array
		for (int x = 0 ; x < 20 ; x++)
		{
		    highScoresList [x] = input.readLine (); //Stores value at line x+1 (the 1 is the header)
		}
	    }
	    //If header isn't correct
	    else
	    {
		createNewFile (); //Creates fresh file
	    }
	}
	catch (IOException e)  //Catches IOException
	{
	    createNewFile (); //Creates fresh file
	}
	catch (Exception e)  //Catches every other Exception (When comparing header)
	{
	    createNewFile (); //Creates fresh file
	}
    }


    /*
    This method takes the top 10 values stored in the highscores file
    and displays them
    ---------------------------------------------------------------
    Local Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    clear                   char                Pauses program and is used to check whether
						    user wants to clear highscores or not
    ----------------------------------------------------------------
    Global Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    highScoresList          String array        Stores the highscores
    ----------------------------------------------------------------
    A for loop is used to cycle through all 10 scores to display them on the screen
    The if statement inside the loop is used to check whether the current value is null or not
    getChar() is used to pause program and check whether user wants to clear scores or not
    After the getChar(), an if statement checks if user wants to clear the file, or to exit
    */
    public void highScore ()
    {
	char clear; //char used to check whether user wants to clear file or not
	//do-while that keeps looping until user wants to exit
	do
	{
	    title (); //Clears screen, draws title
	    loadHighScore (); //Loads highscores into array
	    c.setCursor (3, 5); //sets cursor to ROW:3 COL: 5
	    c.print ("NAME", 15); //text
	    c.println ("SCORE"); //text
	    //for loop that cycles through all values to print onto screen
	    for (int x = 0 ; x < highScoresList.length ; x += 2)
	    {
		c.print (x / 2 + 1 + ". ", 4); //Prints the rank
		//If the value is not null
		if (highScoresList [x] != null)
		{
		    c.print (highScoresList [x], 15); //prints name
		    c.println (highScoresList [x + 1]); // prints score
		}
		//If the value is null
		else
		{
		    c.println (); //Prints empty line
		}
	    }
	    c.println (); //Adds empty line
	    c.println ("Press 'x' to clear all scores . . . "); //text
	    c.println ("Press any other key to continue . . . "); //text
	    clear = c.getChar (); //gets value for char clear
	    //If clear is not 'x'
	    if (clear != 'x')
	    {
		break; //goes to main menu
	    }
	    createNewFile (); //creates fresh file
	    new Message ("New file created.", "Cleared"); //message informing user that scores have been cleared
	}
	while (clear == 'x'); //continues while clear is equal to 'x'
    }


    /*
    This method draws a template of the Simon game
    ---------------------------------------------------------------
    Local Variables: None.
    Global Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    circleGreen             Color               Green color used in the game
    circleRed               Color               Red color used in the game
    circleYellow            Color               Yellow color used in the game
    circleBlue              Color               Blue color used in the game
    ----------------------------------------------------------------
    All for loops used in this methods draw inanimate shapes
    */
    private void drawTemplate ()
    {
	//template
	//inner circle
	c.setColor (Color.black); //Sets color to black
	for (int x = 0 ; x < 240 ; x++) //Used to draw inanimate shape
	{
	    c.drawRect (200, 140, 240 - x, 240 - x); //Draws rectangle
	}
	//green arc
	c.setColor (circleGreen); //Sets color to circleGreen
	for (int x = 10 ; x < 135 ; x++) //Used to draw inanimate shape
	{
	    c.drawArc (85 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 90, 90); //Draws arc
	    c.drawArc (84 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 90, 90); //Draws second arc to fill in blank spots
	}
	//red arc
	c.setColor (circleRed); //Sets color to circleRed
	for (int x = 10 ; x < 135 ; x++) //Used to draw inanimate shape
	{
	    c.drawArc (105 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 0, 90); //Draws arc
	    c.drawArc (104 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 0, 90); //Draws second arc to fill in blank spots
	}
	//yellow arc
	c.setColor (circleYellow); //Sets color to circleYellow
	for (int x = 10 ; x < 135 ; x++) //Used to draw inanimate shape
	{
	    c.drawArc (85 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 180, 90); //Draws arc
	    c.drawArc (84 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 180, 90); //Draws second arc to fill in blank spots
	}
	//blue arc
	c.setColor (circleBlue); //Sets color to circleBlue
	for (int x = 10 ; x < 135 ; x++) //Used to draw inanimate shape
	{
	    c.drawArc (105 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 270, 90); //Draws arc
	    c.drawArc (104 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 270, 90); //Draws second arc to fill in blank spots
	}
	//lines in between colors
	c.setColor (Color.black); //Sets color to black
	for (int x = 0 ; x < 450 ; x++) //Used to draw inanimate shape
	{
	    c.drawRect (310, 30, 20 - x, 450 - x); //Draws rectangle
	    c.drawRect (95, 245, 450 - x, 20 - x); //Draws rectangle
	}
	c.setColor (Color.white); //Sets color to white
	c.drawString ("Simon", 290, 190); //Draws text
	c.drawString ("Says", 297, 205); //Draws text
	c.setColor (Color.black); //Sets color to black
	c.drawString ("[1]", 180, 150); //Draws text
	c.drawString ("[2]", 420, 150); //Draws text
	c.drawString ("[3]", 180, 390); //Draws text
	c.drawString ("[4]", 420, 390); //Draws text
    }


    /*
    This private method creates a delay of specified size.
    ---------------------------------------------------------------
    Local Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    sleepLength             int                 Used to create delay of that length
    ----------------------------------------------------------------
    Global Variables: None.
    ----------------------------------------------------------------
    A try-catch is used to catch exceptions thrown when using thread class
    */
    private void delay (int sleepLength)
    {
	//try-catch used to catch exceptions thrown when using thread class
	try
	{
	    Thread.sleep (sleepLength); //Delays for sleepLength length
	}
	catch (Exception e)  //Catches Exceptions
	{
	}
    }


    /*
    This private method gets a String and parses it to int
    ---------------------------------------------------------------
    Local Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    toConvert               String              Converts this String into a returnable int
    ----------------------------------------------------------------
    Global Variables: None.
    ----------------------------------------------------------------
    A try catch is used to catch exception thrown when parsing
    */
    private int strToInt (String toConvert)
    {
	//used to catch exception thrown when parsing
	try
	{
	    return Integer.parseInt (toConvert); //returns the converted int
	}
	catch (NumberFormatException e)  //catches NumberFormatException
	{
	    return 1; //returns 1
	}
    }


    /*
    This method combines multiple methods to run the actual game component.
    It is playable by the user.
    ---------------------------------------------------------------
    Local Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    levelFontColor          Color               Color used to display level
    correct                 boolean             Checks if user gets the level correct
    guess                   String              Gets the user's guess
    ----------------------------------------------------------------
    Global Variables:
    NAME                    TYPE                DESCRIPTION
    ----------------------------------------------------------------
    seqCount                double              Counts which level user has gone to
    sequence                String array        Randomly generated sequence that the game uses
    initialDelay            int                 The initial delay, based on what difficulty they choose
    ----------------------------------------------------------------
    The while loop that surrounds everything keeps the game going, as long as the user is correct and
	haven't gone over the max level
    The first for loop flashes the colors in the correct sequence
    The second for loop gets and checks the user's answers
    The while loop inside keeps looping until answer is submitted in the correct format
    A readString is used to get user's answer
    The if statement inside of the while is used to check if answer is submitted correctly
    The if statement after that while loop is used to check if answer is correct
    The last if statement does calculations to delay and score if user is correct
    Inside that if statement is another if statement that decreases the delay based on progression
    */
    public void game ()
    {
	Color levelFontColor = new Color (83, 73, 150); //Local Color
	boolean correct = true; //boolean used to see if user gets answer correct
	String guess; //String used to store user's answer
	seqCount = 0; //Resets seqCount if user plays game more than once
	title (); //Clears screen, draws title
	sequenceGen (); //Creates sequence
	drawTemplate (); //Draws the template
	//While loop keeps game going as long as user is correct and havent gone over max level
	while (correct && seqCount < 100)
	{
	    c.setColor (levelFontColor); //Sets color to levelFontColor
	    c.drawString ("Level: " + String.valueOf (seqCount), 20, 470); //Draws level
	    //For loop that flashes colors in correct sequence
	    for (int x = 0 ; x < seqCount ; x++)
	    {
		colorFlash (sequence [x], 5); //flashes color at location x
		delay (initialDelay); //delay (length based on difficulty and progression)
		colorFlash (sequence [x], sequence [x]); //erases the color flash
		delay (initialDelay); //delay
	    }

	    //For loop that check's users guesses
	    for (int x = 0 ; x < seqCount ; x++)
	    {
		c.setColor (Color.white); //Sets color to white
		//Loop that doesnt end until guess is inputted correctly
		while (true)
		{
		    c.drawString ("Enter your #" + String.valueOf (x + 1), 480, 455); //Draws text
		    c.drawString ("guess:", 480, 475); //Draws text
		    c.setCursor (24, 70); //Sets cursor to ROW: 24 COL: 70
		    guess = c.readString (); //gets guess
		    //If guess length is not 1
		    if (guess.length () != 1)
		    {
			new Message ("Please enter a valid guess! It can only be 1 character long.", "Error: Guess too long"); //Error message
		    }
		    //If guess length is 1
		    else
		    {
			c.setColor (Color.pink); //Sets color to pink
			c.fillRect (480, 435, 160, 65); //Erase
			break;
		    }
		}
		//If guess is wrong
		if (strToInt (guess) != sequence [x])
		{
		    correct = false; //Sets correct to false
		    break; //Breaks for loop
		}
		c.setColor (Color.pink); //Sets color to pink
		c.drawString ("Correct!", 270, 250); //Draws text
		delay (200); //delays 200ms
		c.setColor (Color.black); //Sets color to black
		c.fillRect (270, 235, 91, 15); //Erase
	    }
	    //If user is still in
	    if (correct)
	    {
		seqCount++; //increases seqCount
		//If seqCount is moddable by 5
		if (seqCount % 5 == 0)
		{
		    initialDelay -= 20; //Decreases delay by 20
		}
		c.setColor (Color.pink); //Sets color to pink
		c.fillRect (20, 457, 140, 15); //Erase
	    }
	}
    }


    /*
    This private method draws a colorflash on the screen, given the
    color and the position
    ---------------------------------------------------------------
    Local Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    currentNum          int                 Position of the flash
    arcColor            int                 Color of the flash
    drawColor           Color               Stores the color of the flash as a Color
    ----------------------------------------------------------------
    Global Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    circleGreen         Color               Green color used in the game
    circleRed           Color               Red color used in the game
    circleYellow        Color               Yellow color used in the game
    circleBlue          Color               Blue color used in the game
    circleFlash         Color               Bluish color used in the game
    ----------------------------------------------------------------
    The first if statement converts the int into a color and sets it
    The second if draws the arc at the correct position
    In the if statement, there are multiple for loops which draw inanimate shapes
    */
    private void colorFlash (int currentNum, int arcColor)
    {
	Color drawColor; //Declares a Color variable
	//If statement that sets Color based on what arcColor is
	if (arcColor == 1)
	    drawColor = circleGreen;
	else if (arcColor == 2)
	    drawColor = circleRed;
	else if (arcColor == 3)
	    drawColor = circleYellow;
	else if (arcColor == 4)
	    drawColor = circleBlue;
	else
	    drawColor = circleFlash;
	c.setColor (drawColor); //Sets color to drawColor which was declared above
	//If currentNum is 1
	if (currentNum == 1)
	{
	    //For loop that draws shape
	    for (int x = 10 ; x < 135 ; x++)
	    {
		c.drawArc (85 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 90, 90); //Draws Arc
		c.drawArc (84 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 90, 90); //Draws second arc to fill in blanks
	    }
	    c.setColor (Color.black); //Sets color to black
	    c.drawString ("[1]", 180, 150); //Draws text
	}
	//If currentNum is 2
	else if (currentNum == 2)
	{
	    //For loop that draws shape
	    for (int x = 10 ; x < 135 ; x++)
	    {
		c.drawArc (105 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 0, 90); //Draws Arc
		c.drawArc (104 + x, 20 + x, 450 - 2 * x, 450 - 2 * x, 0, 90); //Draws second arc to fill in blanks
	    }
	    c.setColor (Color.black); //Sets color to black
	    c.drawString ("[2]", 420, 150); //Draws text
	}
	//If currentNum is 3
	else if (currentNum == 3)
	{
	    //For loop that draws shape
	    for (int x = 10 ; x < 135 ; x++)
	    {
		c.drawArc (85 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 180, 90); //Draws Arc
		c.drawArc (84 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 180, 90); //Draws second arc to fill in blanks
	    }
	    c.setColor (Color.black); //Sets color to black
	    c.drawString ("[3]", 180, 390); //Draws text
	}
	//If currentNum is anything else
	else
	{
	    //For loop that draws shape
	    for (int x = 10 ; x < 135 ; x++)
	    {
		c.drawArc (105 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 270, 90); //Draws arc
		c.drawArc (104 + x, 40 + x, 450 - 2 * x, 450 - 2 * x, 270, 90); //Draws second arc to fill in blanks
	    }
	    c.setColor (Color.black); //Sets color to black
	    c.drawString ("[4]", 420, 390); //Draws text
	}
    }


    /*
    This return method returns a calculated score based on what level they got to and difficulty
    ---------------------------------------------------------------
    Local Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    scoreCount          double              User's score before adding multipliers
    SCORE_MULTIPLIER    final double        Score multiplier, based on user progression
    ----------------------------------------------------------------
    Global Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    difficulty          String              Game's difficulty selected by user
    ----------------------------------------------------------------
    The if statement checks if user only got to level 1. If so, returns a score of ZERO
    The second if statement adds multipliers based on progression
    */
    private double scoreCalculator (double scoreCount)
    {
	final double SCORE_MULTIPLIER = scoreCount / 5; //score multiplier, based on how far user has progressed
	//Returns score of 0 if user only gets to lv1
	if (seqCount <= 1)
	{
	    return 0; //Returns 0
	}
	//If user selects easy difficulty
	if (difficulty.equals ("1"))
	    return scoreCount * SCORE_MULTIPLIER; //Return final score
	//If user selects intermidiate difficulty
	else if (difficulty.equals ("2"))
	    return (int) (Math.round (scoreCount * 1.5 * SCORE_MULTIPLIER)); //Return final score
	//If user selects advanced difficulty
	else
	    return (int) (Math.round (scoreCount * 2.5 * SCORE_MULTIPLIER)); //Return final score
    }


    /*
    This method generates an array sequence randomly up to 100
    ---------------------------------------------------------------
    Local Variables: None.
    Global Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    SEQUENCE_MAX_SIZE   final int           maximum size of the sequence
    sequence            int array           array that stores the random sequence
    ----------------------------------------------------------------
    A for loop is used to create 100 random numbers in the sequence
    */
    private void sequenceGen ()
    {
	//Creates sequence
	for (int x = 0 ; x < SEQUENCE_MAX_SIZE ; x++)
	{
	    sequence [x] = (int) (Math.random () * 4 + 1); //Random value
	}
    }


    /*
    This method displays an end screen and saves user's highscore if they want
    ---------------------------------------------------------------
    Local Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    save                String              Stores input for whether user wants to save or not
    ----------------------------------------------------------------
    Global Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    seqCount            int                 User's level
    sequence            int array           Since it is made of random ints, it is used to
						randomize the end message
    userName            String              Stores user's name
    finalScore          int                 Stores user's score
    ----------------------------------------------------------------
    The first if statement is used to see if user has gone to over level 5 or not to display
	randomized message
    The if statement inside randomizes the end comments based on the randomly generated sequence
    A while loop is then used to ask user if they want to store their highscore and doesn't end
	until user correctly inputs reply
    A for loop inside is used to erase text
    An if statement inside is used to display error messages if not inputted correct
    After the while loop is another if statement that gets the user's name which doesnt stop looping
	until the if statement inside detects that name has correct format
    */
    public void endScreen ()
    {
	String save; //Store whether user wants to save or not
	title (); //Clears screen, draws title
	c.setColor (Color.white); //Sets color to white
	c.setFont (endScreenFont); //Sets font to endScreenFont
	//If user didn't get past lv5
	if (seqCount <= 5)
	{
	    c.drawString ("Aww, shucks! You only made it to level " + String.valueOf (seqCount) + "!", 0, 100); //Draws end message
	}
	//If user got past lv5
	else
	{
	    //Completely random end messages
	    if (sequence [1] == 1)
	    {
		c.drawString ("Awesome Sauce! You made it to level " + String.valueOf (seqCount) + "!", 25, 100); //Draws end message
	    }
	    else if (sequence [1] == 2)
	    {
		c.drawString ("Congratulations! You made it to level " + String.valueOf (seqCount) + "!", 10, 100); //Draws end message
	    }
	    else if (sequence [1] == 3)
	    {
		c.drawString ("Wowie! You made it to level " + String.valueOf (seqCount) + "!", 80, 100); //Draws end message
	    }
	    else if (sequence [1] == 4)
	    {
		c.drawString ("Nice Job! You made it to level " + String.valueOf (seqCount) + "!", 70, 100); //Draws end message
	    }
	}
	c.drawString ("Your total score is: " + String.valueOf (scoreCalculator (seqCount)), 120, 140); //Draws end message
	//Asks user if they want to upload score. Doesn't stop looping until input is correct
	while (true)
	{
	    c.setColor (Color.pink); //Sets color to pink
	    //Erase
	    for (int x = 0 ; x < 640 ; x++)
	    {
		c.drawRect (0, 180, 640 - x, 400 - x); //Draws rectangle
	    }
	    c.setCursor (10, 1); //Sets cursor to ROW 10 COL 1
	    c.print ("Would you like to upload your score? Type [Y] for yes, [N] for no: "); //Prompt
	    save = c.readLine (); //Gets save
	    //If length of save isn't 1
	    if (save.length () != 1)
	    {
		new Message ("Please enter a valid option!", "Invalid Option"); //Error message
	    }
	    //If save length is 1
	    else
	    {
		save = save.toLowerCase (); //Converts to lower case
		//If save is either "y" or "n"
		if (save.equals ("y") || save.equals ("n"))
		    break; //Ends loop
		new Message ("Please enter a valid option!", "Invalid Option"); //Error message
	    }
	}
	//If user chooses to save
	if (save.equals ("y"))
	{
	    //Loop that doesn't end until name is less than 11 chars
	    while (true)
	    {
		c.setCursor (11, 1); //Sets cursor to ROW 11 COL 1
		c.print ("What would you like the display name be? (Max 10 characters): "); //Prompt
		userName = c.readLine (); //Gets user's name
		//If name's length is less than 11
		if (userName.length () <= 10)
		    break; //Ends loop
		new Message ("Username can be a maximum of 10 characters!!", "Username too long"); //Error message
	    }
	    finalScore = scoreCalculator (seqCount); //Calculates final score
	    saveToFile (finalScore, userName); //Uploads name and score for sorting
	    new Message ("Saved to file.", "Saved"); //Success message
	}
    }


    /*
    This method gets what difficulty the user wants to go to
    ---------------------------------------------------------------
    Local Variables: None.
    Global Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    difficulty          String              Stores user's difficulty
    ----------------------------------------------------------------
    A while loop is used to get user's choice until entered correctly
    The if statement inside is used to check if it it is entered well
    The if statement at the end of the method sets the delay according
	to difficulty
    */
    public void difficulty ()
    {
	//Loop doesn't end until choice entered well
	while (true)
	{
	    title (); //Clears screen, draws title
	    c.setColor (Color.white); //Sets color to white
	    c.drawString ("[1]EASY", 0, 70); //Draws text
	    c.drawString ("[2]INTERMEDIATE", 0 , 90); //Draws text
	    c.drawString ("[3]ADVANCED", 0, 110); //Draws text
	    c.setCursor (7, 1); //Sets cursor to ROW 7 COL 1
	    c.print ("Choose one: "); //Prompt
	    difficulty = c.readLine (); //Gets menu option
	    //If statement to limit string to one character
	    if (difficulty.length () != 1)
	    {
		new Message ("Please enter a valid option!", "Invalid Option"); //Error message
	    }
	    else
	    {
		//If statement to check whether user selects a valid option
		if (difficulty.charAt (0) >= '1' && difficulty.charAt (0) <= '3')
		    break; //Breaks loop
		new Message ("Please enter a valid option!", "Invalid Option"); //Error message
	    }
	}
	//sets delay according to difficulty
	if (difficulty.equals ("1"))
	    initialDelay = 1300;
	else if (difficulty.equals ("2"))
	    initialDelay = 1000;
	else
	    initialDelay = 700;
    }

    /*
    This is the main method. It controls all program flow
    ---------------------------------------------------------------
    Local Variables: None.
    Global Variables:
    NAME                TYPE                DESCRIPTION
    ----------------------------------------------------------------
    menuOp              static String       User's menu option
    ----------------------------------------------------------------
    A do-while loop keeps running the program until user wishes to go to goodBye()
    The if statements inside control program flow
    */
    public static void main (String[] args)
    {
	SimonSays c = new SimonSays ();
	c.splashScreen (); //Program's splash screen
	//Loop doesnt stop until user chooses to exit
	do
	{
	    c.mainMenu (); //Goes to main menu
	    if (menuOp.equals ("1"))
	    {
		c.difficulty (); //Goes to difficulty screen
		c.game (); //Goes to game
		c.endScreen (); //Goes to endscreen
	    }
	    else if (menuOp.equals ("2"))
	    {
		c.instructions (); //Goes to instructions
	    }
	    else if (menuOp.equals ("3"))
	    {
		c.highScore (); //Goes to highscores
	    } 
	}
	while (!(menuOp.equals ("4")));
	c.goodBye (); //end
    } // main method
} // Simon class


