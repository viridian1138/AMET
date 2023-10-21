
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

import codejcore.widgets.GraphicsContext;

/**
 * Abstract representation of keyboard focus for a set of widgets in a Metaverse
 * Application
 * 
 * @author tgreen
 *
 */
public interface IFocus {

	/**
	 * Gets whether the set of widgets has the focus
	 * 
	 * @return Whether the set of widgets has the focus
	 */
	boolean getFocus();

	/**
	 * Sets whether the set of widgets has the focus
	 * 
	 * @param in Whether the set of widgets has the focus
	 * @param gc The graphics context in which to draw the changes
	 */
	void setFocus(boolean in, GraphicsContext gc);

}
