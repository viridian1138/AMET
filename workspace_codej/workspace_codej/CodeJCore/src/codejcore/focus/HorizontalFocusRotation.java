
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

import java.util.ArrayList;

import codejcore.interfaces.IEventHandler;
import codejcore.interfaces.IFocus;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.KeyNames;

/**
 * A keyboard focus handler with a single horizontal row of entries that rotates
 * keyboard focus right-to-left or left-to-right.
 * 
 * @author tgreen
 *
 */
public class HorizontalFocusRotation implements IEventHandler {

	/**
	 * The horizontal row of stations
	 */
	ArrayList<FocusStation> stations = new ArrayList<FocusStation>();

	/**
	 * The current index into the row of stations with the focus
	 */
	int currentIndex = 0;

	/**
	 * Default Constructor
	 */
	public HorizontalFocusRotation() {
		// Empty
	}

	/**
	 * Adds a focus station to the row
	 * 
	 * @param stationName The name of the station
	 * @param focus       The focus handler for the station
	 * @param handler     The keyboard event handler for the station
	 */
	public void add(String stationName, IFocus focus, IEventHandler handler) {
		FocusStation foc = new FocusStation();

		foc.setStationName(stationName);
		foc.setFocus(focus);
		foc.setHandler(handler);

		stations.add(foc);
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		if (stations.size() < 2) {
			return;
		}

		String keyCode = e.getKeyCode();

		boolean handled = false;

		if (keyCode.equals(KeyNames.ARROW_RIGHT_KEY)) {
			System.out.println("Horizontal Focus Rotation Attempting Station Increment");

			stations.get(currentIndex).getFocus().setFocus(false, gc);

			currentIndex++;

			handled = true;

			if (currentIndex >= stations.size()) {
				currentIndex = 0;
			}

			if ((currentIndex >= 0) && (currentIndex < stations.size())) {
				System.out.println("Incremented To Station " + (stations.get(currentIndex).getStationName()));

				stations.get(currentIndex).getFocus().setFocus(true, gc);

			}
		}

		if (keyCode.equals(KeyNames.ARROW_LEFT_KEY)) {
			System.out.println("Horizontal Focus Rotation Attempting Station Decrement");

			stations.get(currentIndex).getFocus().setFocus(false, gc);

			currentIndex--;

			handled = true;

			if (currentIndex < 0) {
				currentIndex = stations.size() - 1;
			}

			if ((currentIndex >= 0) && (currentIndex < stations.size())) {
				System.out.println("Decremented To Station " + (stations.get(currentIndex).getStationName()));

				stations.get(currentIndex).getFocus().setFocus(true, gc);

			}
		}

		if (!handled) {
			if ((keyCode.equals(KeyNames.SPACE_KEY)) || (keyCode.equals(KeyNames.ARROW_UP_KEY))
					|| (keyCode.equals(KeyNames.ARROW_DOWN_KEY))) {
				System.out.println("Firing Key Event At Station " + (stations.get(currentIndex).getStationName()));

				stations.get(currentIndex).getHandler().dispatchEvent(e, sess, gc);
			}
		}

	}

}
