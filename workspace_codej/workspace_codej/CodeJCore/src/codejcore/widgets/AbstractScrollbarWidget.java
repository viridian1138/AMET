
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
 * An abstract version of a scrollbar widget
 * 
 * @author tgreen
 *
 */
public abstract class AbstractScrollbarWidget extends Widget implements IFocus {

	/**
	 * Default Constructor
	 */
	public AbstractScrollbarWidget() {
	}

	/**
	 * Sets the scrolling location of the scrollbar
	 * 
	 * @param minScroll   The minimum value of the scrollbar
	 * @param maxScroll   The maximum value of the scrollbar
	 * @param startScroll The start value of the scrollbar thumb
	 * @param endScroll   The end value of the scrollbar thumb
	 * @param gc          The graphics context in which to display the change in
	 *                    location
	 */
	public abstract void setLocation(double minScroll, double maxScroll, double startScroll, double endScroll,
			GraphicsContext gc);

	/**
	 * Creates an instance of a scrollbar widget
	 * 
	 * @param gc  The graphics context in which to display the scrollbar
	 * @param pwi Object used to initialize the scrollbar
	 * @return An instance of a scrollbar widget
	 */
	public static AbstractScrollbarWidget create(GraphicsContext gc, ScrollbarWidgetInitializer pwi) {

		System.out.println("Creating Scrollbar Widget");

		return (gc.getCurrentTheme().createVerticalScrollbar(gc, pwi));
	}

}
