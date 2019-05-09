/*
*Bill Bai
*Mr.Rosen
*01-16-2019
*This is a thread used in Simon.java. It is the starting animation used in the splash
*screen.
*
*                        VARIABLES
*NAME                    TYPE                DESCRIPTION
*---------------------------------------------------------------------------------------------
*N O  G L O B A L   V A  R I A B L E S   A R E   U S E D
*/
import java.awt.*;
import hsa.Console;

public class StartAnimation implements Runnable
{
    private Console c;           // The output console

    //Draws to console
    public StartAnimation (Console con)
    {
	c = con;
    }

    /*
    This method draws a circle expanding on the screen
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    A for loop is used to draw the animation
    A try-catch is used to catch exceptions thrown when using Thread.sleep
    */
    public void drawCircle ()
    {
	//Draws animation
	for (int x = 0 ; x < 640 ; x++)
	{
	    c.setColor (Color.pink); //Sets color to pink
	    c.fillOval (320 - x, 250 - x, 0 + 2 * x, 0 + 2 * x); //Draws oval
	    try
	    {
		Thread.sleep (3); //Delay of 3ms between each frame
	    }
	    catch (Exception e) //Catches Exception
	    {
	    }
	}
    }

    /*
    This method runs the animation in order
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    No input/logic/loop is used.
    */
    public void run ()
    {
	drawCircle ();
    } // main method
} // StartAnimation class
