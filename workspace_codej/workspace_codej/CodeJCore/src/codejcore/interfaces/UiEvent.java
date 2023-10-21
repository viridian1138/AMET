
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

package codejcore.interfaces;

import java.util.Properties;

import codejcore.widgets.EventHandlerNames;

/**
 * A Metaverse Application user interface event
 * 
 * @author tgreen
 *
 */
public class UiEvent {

	/**
	 * Collection of properties describing the event
	 */
	protected Properties prop;

	/**
	 * Constructs the event
	 * 
	 * @param in Collection of properties describing the event
	 */
	public UiEvent(Properties in) {
		prop = in;
	}

	/**
	 * Gets the type of the event from the properties
	 * 
	 * @return The type of the event
	 */
	public String getEventType() {
		return (prop.getProperty(EventHandlerNames.PROPERTY_EVENT_TYPE));
	}

	/**
	 * For keyboard events gets the key code of the event
	 * 
	 * @return The key code of the event
	 */
	public String getKeyCode() {
		return (prop.getProperty(EventHandlerNames.PROPERTY_EVENT_KEY_CODE));
	}

	/**
	 * Prints the event to the console
	 */
	public void print() {
		System.out.println("Event Properties : ");
		prop.list(System.out);
	}

}
