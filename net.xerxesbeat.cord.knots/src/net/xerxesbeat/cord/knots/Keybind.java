package net.xerxesbeat.cord.knots;

import java.awt.AWTException;
import java.awt.Robot;
import net.xerxesbeat.cord.Cord;
import net.xerxesbeat.cord.Knot;
import net.xerxesbeat.cord.event.UpdateEvent;
import net.xerxesbeat.event.Event;

public class Keybind extends Knot
{
	private Robot robot;

	/**
	 * 
	 * @param key java.awt.event.KeyEvent.VK_**
	 * @throws AWTException
	 */
	public Keybind ( int key ) throws AWTException
	{
		robot = new Robot ();
		tie( "key", key );
	}

	@Override
	public void event ( Event event )
	{
		if ( event instanceof UpdateEvent )
		{
			Cord cord = (Cord) event.getValues()[0];
			if ( cord.equals( tug( "input" ) ) )
			{
				Object value = cord.get();
				int key = (int) tug( "key" ).get();
				if ( value.equals( true ) )
				{
					robot.keyRelease( key );
					robot.keyPress( key );
				}
				else if ( cord.get().equals( false ) )
					robot.keyRelease( key );
			}
		}
	}
}