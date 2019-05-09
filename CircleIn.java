/*
*Bill Bai
*Mr.Rosen
*01-16-2019
*This program is an accurate replica of the classic game "Simon"
*
*                       VARIABLES
*NAME                   TYPE                DESCRIPTION
*---------------------------------------------------------------------------------------------
*dullGray               Color               Gray color used in this thread animation
*circleGreen            Color               Green color used in this thread animation
*circleRed              Color               Red color used in this thread animation
*circleYellow           Color               Yellow color used in this thread animation
*circleBlue             Color               Blue color used in this thread animation
*/

import java.awt.*;
import hsa.Console;
import java.lang.*;

public class CircleIn extends Thread
{
    private Console c; //The output console

    Color dullGray = new Color (150, 160, 180);
    Color circleGreen = new Color (0, 255, 114);
    Color circleRed = new Color (255, 40, 0);
    Color circleYellow = new Color (250, 255, 0);
    Color circleBlue = new Color (0, 75, 255);


    /*
    This method draws to the console.
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    No input/logic/loop is used.
    */
    public CircleIn (Console con)
    {
	c = con;
    }


    /*
    This method draws a circle coming in from the left
    -----------------------------
    Local Variables: None.
    Global Variables:
    dullGray    Color   Used to draw a gray color
    -----------------------------
    A for loop is used to draw the animation
    A try catch is used to catch exceptions thrown when using Thread.sleep
    */
    public void circleFromLeft ()
    {
	for (int x = 0 ; x < 570 ; x++)
	{
	    //erase
	    c.setColor (Color.white);
	    c.fillOval (-501 + x, 0, 500, 500);
	    //animation
	    c.setColor (dullGray);
	    c.fillOval (-500 + x, 0, 500, 500);

	    try
	    {
		sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /*
    This method draws a circle expanding
    -----------------------------
    Local Variables: None.
    Global Variables:
    circleGreen     Color   Green color used in this animation
    circleRed       Color   Red color used in this animation
    circleYellow    Color   Yellow color used in this animation
    circleBlue      Color   Blue color used in this animation
    -----------------------------
    A for loop is used to draw the animation
    A try catch is used to catch exceptions thrown when using Thread.sleep
    */
    public void circleOpens ()
    {
	for (int x = 0 ; x < 92 ; x++)
	{
	    //Animation
	    c.setColor (circleGreen);
	    c.fillArc (69, 0, 500, 500, 90, 0 + x);
	    c.setColor (circleRed);
	    c.fillArc (69, 0, 500, 500, 180, 0 + x);
	    c.setColor (circleYellow);
	    c.fillArc (69, 0, 500, 500, 270, 0 + x);
	    c.setColor (circleBlue);
	    c.fillArc (69, 0, 500, 500, 0, 0 + x);
	    //Catches Exception
	    try
	    {
		sleep (10); //Delays by 10
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /*
    This method draws the inner of the circle expanding and lines falling through
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    A for loop is used to draw the animation
    A try catch is used to catch exceptions thrown when using Thread.sleep
    */
    public void drawInnerCircle ()
    {
	for (int x = 0 ; x < 130 ; x++)
	{
	    //Animations
	    c.setColor (Color.black);
	    c.fillOval (320 - x, 250 - x, 0 + 2 * x, 0 + 2 * x);
	    //catches Exception
	    try
	    {
		sleep (10); //delays by 10ms
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 0 ; x < 500 ; x++)
	{
	    //Animations
	    c.setColor (Color.black);
	    c.drawLine (310, 0 + x, 330, 0 + x);
	    c.drawLine (69 + x, 240, 69 + x, 260);
	    try
	    {
		sleep (5); //Delay by 5 ms
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    /*
    This method draws the game's title on the screen
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    A try catch is used to catch exceptions thrown when using Thread.sleep
    */
    public void drawText ()
    {
	c.setColor (Color.white); //Sets color to white
	c.setFont (new Font ("Courier", 1, 20)); //Sets font to as specified
	c.drawString ("Simon", 290, 190); //Draws text
	c.drawString ("Says", 297, 205); //Draws text
	try
	{
	    sleep (1000); //delays 1s
	}
	catch (Exception e)
	{
	}
    }


    /*
    This method runs the animations in order
    -----------------------------
    Local Variables: None.
    Global Variables: None.
    -----------------------------
    No input/logic/loop is used.
    */
    public void run ()
    {
	circleFromLeft ();
	circleOpens ();
	drawInnerCircle ();
	drawText ();
    } // main method
} // CircleIn class


