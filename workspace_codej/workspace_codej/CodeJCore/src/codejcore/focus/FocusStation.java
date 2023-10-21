
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

package codejcore.focus;

import codejcore.interfaces.IEventHandler;
import codejcore.interfaces.IFocus;

/**
 * A station that handles keyboard focus for a set of widgets
 * 
 * @author tgreen
 *
 */
public class FocusStation {

	/**
	 * The name of the station
	 */
	String stationName = "<Unknown>";

	/**
	 * The focus handler for the station
	 */
	IFocus focus = null;

	/**
	 * The keyboard event handler for the station
	 */
	IEventHandler handler = null;

	/**
	 * Default Constructor
	 */
	public FocusStation() {
		// Empty
	}

	/**
	 * Gets the name of the station
	 * 
	 * @return The name of the station
	 */
	public String getStationName() {
		return stationName;
	}

	/**
	 * Sets the name of the station
	 * 
	 * @param stationName The name of the station
	 */
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	/**
	 * Gets the focus handler for the station
	 * 
	 * @return The focus handler for the station
	 */
	public IFocus getFocus() {
		return focus;
	}

	/**
	 * Sets the focus handler for the station
	 * 
	 * @param focus The focus handler for the station
	 */
	public void setFocus(IFocus focus) {
		this.focus = focus;
	}

	/**
	 * Gets the keyboard event handler for the station
	 * 
	 * @return The keyboard event handler for the station
	 */
	public IEventHandler getHandler() {
		return handler;
	}

	/**
	 * Sets the keyboard event handler for the station
	 * 
	 * @param handler The keyboard event handler for the station
	 */
	public void setHandler(IEventHandler handler) {
		this.handler = handler;
	}

}
