


//$$strtCprt
/**
* Another Metaverse Toolkit (AMET)
* 
* Copyright (C) 2023 Thornton Green
* 
* This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
* published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
* of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
* You should have received a copy of the GNU General Public License along with this program; if not, 
* see <http://www.gnu.org/licenses>.
* Additional permission under GNU GPL version 3 section 7
*
*/
//$$endCprt



package codejcore.widgets;

import java.util.Random;

import codejcore.arch.SessionData;
import codejcore.arch.SessionStore;
import codejcore.bounds.BoundsResponseHandler;
import codejcore.bounds.IBoundsHandler;
import codejcore.interfaces.CoreNames;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;

/**
 * A graphics context for a Metaverse Application
 * @author tgreen
 *
 */
public class GraphicsContext {
	
	/**
	 * Collection of JS code for the graphics context
	 */
	StringBuilder sb;
	
	/**
	 * The current session for the graphics context
	 */
	SessionData sd;
	
	/**
	 * A string identifier for the session
	 */
	String applicationContext;

	/**
	 * Gets a string identifier for the session
	 * @return The string identifier for the session
	 */
	public String getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Constructor
	 * @param _sb Collection of JS code for the graphics context
	 * @param _sd The current session for the graphics context
	 */
	public GraphicsContext( StringBuilder _sb , SessionData _sd ) {
		sb = _sb;
		sd = _sd;
		applicationContext = sd.getApplicationContext();
	}
	
	/**
	 * Appends a JS string to the graphics context
	 * @param in The JS string to be appended
	 */
	public void appendJs( String in )
	{
		sb.append( in );
	}
	
	
	/**
	 * Inserts front JS for background tasks
	 */
	public void insertBkFront()
	{
		appendJs(  "\n"
				+ "		requestIdleCallback( function() { \n" );
	}
	
	
	/**
	 * Inserts end JS for background tasks
	 */
	public void insertBkEnd()
	{
		appendJs(  "\n"
				+ "\n"
				+ " } \n"
					+ " , {timeout: 30000} ); \n"
				+ "\n"
				+ "\n" );
	}
	
	
	/**
	 * Generates a JS reference for a new widget
	 * @return The JS reference for the new widget
	 */
	public String generateNewWidgetContext()
	{
		final long time = System.currentTimeMillis();
		final long id = SessionStore.currentId;
		SessionStore.currentId++;

		Random r1 = new Random( time );
		for( int cnt = 0 ; cnt < 5 ; cnt++ )
		{
			r1.nextLong();
		}


		final long xtime = Math.abs( r1.nextLong() );


		Random r2 = new Random( id );
		for( int cnt = 0 ; cnt < 5 ; cnt++ )
		{
			r2.nextLong();
		}


		final long xid = Math.abs( r2.nextLong() );
		
		String wc = "wc_" + xtime + "_" + xid;
		return( wc );
	}
	
	
	/**
	 * Generates a timeout event
	 * @param timeout The number of milliseconds at which to time out
	 */
	public void generateTimeout( int timeout )
	{
		
		System.out.println( "Generating Timeout" );
		
		
		appendJs( "{\n" );
		
		insertBkFront();
		
		appendJs( 
			  "setTimeout(() => {\n"
			+ "var dstr = \"\\n\";"
			+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_TIME + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_TIME + " + \"\\n\";\n"
			+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_ID + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_ID + " + \"\\n\";\n"
					
			+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_EVENT_TYPE + "=" + EventHandlerNames.TIMEOUT_EVENT_TYPE + "\\n\";\n"
						
			+ "var aaa = $.post( \"" + CoreNames.METAVERSE_CORE_SYSTEM_PATH + "\" , dstr , function(data, status) {\n"
			+ "console.log( \"data: \" + data + \" status: \" + status );\n"
			+ "eval( data );\n"
			+ "console.log( \"zmsg: \" + tmpObj.zmsg );\n"
			+ "   });\n" // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			+ "}, " + timeout + " );\n"
			);
		
		insertBkEnd();
		
		appendJs( "}\n" );
	}
	
	
	/**
	 * Prints the JS generated for the graphics context
	 */
	public void printJs()
	{
		String s = sb.toString();
		System.out.println( s );
	}
	
	
	
	
	/**
	 * Switches to a different metaverse application
	 * @param newApp The metaverse application to which to change
	 * @param e UI event that triggered starting the new metaverse application
	 */
	public void changeApplication( MetaverseApplication newApp , UiEvent e )
	{
		sd.getCurrentApplicationInstance().systemStopApplication(e, sd.getToken(), this);
		
		sd.setCurrentApplication( newApp );
		
		appendJs( "{\n" );
		
		insertBkFront();

		appendJs( 
				    "var dstr = \"\\n\";"
						+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_TIME + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_TIME + " + \"\\n\";\n"
						+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_ID + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_ID + " + \"\\n\";\n"
					
						+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_EVENT_TYPE + "=" + EventHandlerNames.START_EVENT_TYPE + "\\n\";\n"
						
						+ "var aaa = $.post( \"" + CoreNames.METAVERSE_CORE_SYSTEM_PATH + "\" , dstr , function(data, status) {\n"
						+ "console.log( \"data: \" + data + \" status: \" + status );\n"
						+ "eval( data );\n"
						+ "console.log( \"zmsg: \" + tmpObj.zmsg );\n"
						+ "   });\n" // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				);
		
		insertBkEnd();
		
		appendJs( "}\n" );
		
	}
	
	
	
	
	/**
	 * Switches to a different metaverse theme
	 * @param newTheme The metaverse theme to which to change
	 * @param e UI event that triggered switching to the new metaverse theme
	 */
	public void changeTheme( IMetaverseTheme newTheme , UiEvent e )
	{
		sd.setCurrentTheme( newTheme );
		
	}
	
	
	/**
	 * Gets the current metaverse theme
	 * @return The current metaverse theme
	 */
	public IMetaverseTheme getCurrentTheme()
	{
		return( sd.getCurrentTheme() );
	}
	
	
	/**
	 * Gets the current session
	 * @return The current session
	 */
	public SessionDataApplicationToken getToken()
	{
		return( sd.getToken() );
	}
	
	
	
	/**
	 * Initiates the calculation of the bounds of a block of text
	 * @param geometryContext Reference to the string for which to compute the bounds
	 * @param tid The ID of the bounds to be calculated
	 */
	public void initiateBoundsGeneration( 
			final String geometryContext , final String tid)
	{
		BoundsResponseHandler.initiateBoundsGeneration(sd.getToken(), geometryContext, tid, this);
	}
	
	
	/**
	 * Handles a request to generate the bounds of a block of text
	 * @param tid The ID of the bounds to be calculated
	 * @param iBoundsHandler The event handler to be invoked upon the calculation of the bounds
	 */
	public void handleBoundsGeneration(
			String tid, IBoundsHandler iBoundsHandler) 
	{
		BoundsResponseHandler.handleBoundsGeneration(sd.getToken(), this, tid, iBoundsHandler);
	}
	
	
	
	

	
}

