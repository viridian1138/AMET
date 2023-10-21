
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
 * An abstract version of a push button widget
 * 
 * @author tgreen
 *
 */
public abstract class AbstractPushbuttonWidget extends Widget implements IFocus {

	/**
	 * Default constructor
	 */
	public AbstractPushbuttonWidget() {
	}

	/**
	 * Creates an instance of a push-button widget
	 * 
	 * @param gc  The graphics context in which to display the push-button
	 * @param pwi Object used to initialize the push-button
	 * @return An instance of a push-button widget
	 */
	public static AbstractPushbuttonWidget create(GraphicsContext gc, PushbuttonWidgetInitializer pwi) {

		System.out.println("Creating Push Button Widget");

		return (gc.getCurrentTheme().createPushbutton(gc, pwi));
	}

}
