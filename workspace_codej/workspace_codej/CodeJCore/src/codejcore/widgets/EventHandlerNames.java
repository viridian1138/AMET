
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

/**
 * Common naming constants used in CodeJ core event handling.
 * 
 * @author deck
 *
 */
public class EventHandlerNames {

	/**
	 * Default Constructor
	 */
	public EventHandlerNames() {
		// Empty
	}

	/**
	 * The name of the default Grand Central Station metaverse application
	 */
	public static final String GRAND_CENTRAL_STATION_APP = "Grand Central Station";

	/**
	 * The name of the default Emerald metaverse theme
	 */
	public static final String EMERALD_THEME = "Emerald";

	/**
	 * Property name for the event type
	 */
	public static final String PROPERTY_EVENT_TYPE = "event";

	/**
	 * Property for the ID of the event
	 */
	public static final String PROPERTY_ARCH_ID = "xid";

	/**
	 * Property for the timestamp at which the event has generated
	 */
	public static final String PROPERTY_ARCH_TIME = "xtime";

	/**
	 * Event type indicating that a metaverse application is starting
	 */
	public static final String START_EVENT_TYPE = "start";

	/**
	 * Event type indicating that a key was pressed
	 */
	public static final String KEYDOWN_EVENT_TYPE = "keydown";

	/**
	 * Event type indicating that a font was loaded
	 */
	public static final String FONT_LOADED_EVENT_TYPE = "font";

	/**
	 * Event type indicating that the bounds of a block of text was calculated
	 */
	public static final String BOUNDS_GENERATED_EVENT_TYPE = "bounds";

	/**
	 * Event type indicating that a sound was loaded
	 */
	public static final String SOUND_LOADED_EVENT_TYPE = "sound";

	/**
	 * Event type indicating a timeout
	 */
	public static final String TIMEOUT_EVENT_TYPE = "timeout";

	/**
	 * For key-down events, property for the key code of the event
	 */
	public static final String PROPERTY_EVENT_KEY_CODE = "keyCode";

}
