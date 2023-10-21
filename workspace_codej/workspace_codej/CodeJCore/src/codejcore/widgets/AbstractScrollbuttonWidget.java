
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

import codejcore.interfaces.IFocus;

/**
 * An abstract version of a scrollbar button widget
 * 
 * @author deck
 *
 */
public abstract class AbstractScrollbuttonWidget extends Widget implements IFocus {

	/**
	 * Default Constructor
	 */
	public AbstractScrollbuttonWidget() {
	}

	/**
	 * Creates an instance of a scrollbar up-button widget
	 * 
	 * @param gc  The graphics context in which to display the button
	 * @param pwi Object used to initialize the button
	 * @return An instance of a scrollbar up-button widget
	 */
	public static AbstractScrollbuttonWidget createUp(GraphicsContext gc, ScrollbuttonWidgetInitializer pwi) {

		System.out.println("Creating Scroll Up Button Widget");

		return (gc.getCurrentTheme().createVerticalScrollbuttonUp(gc, pwi));
	}

	/**
	 * Creates an instance of a scrollbar down-button widget
	 * 
	 * @param gc  The graphics context in which to display the button
	 * @param pwi Object used to initialize the button
	 * @return An instance of a scrollbar down-button widget
	 */
	public static AbstractScrollbuttonWidget createDown(GraphicsContext gc, ScrollbuttonWidgetInitializer pwi) {

		System.out.println("Creating Scroll Down Button Widget");

		return (gc.getCurrentTheme().createVerticalScrollbuttonDown(gc, pwi));
	}

}
