
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

import codejcore.interfaces.IMetaverseTheme;

/**
 * Class used to initialize a scroll bar button
 * 
 * @author tgreen
 *
 */
public class ScrollbuttonWidgetInitializer {

	/**
	 * Whether the button has the keyboard focus
	 */
	boolean focus = false;

	/**
	 * Gets whether the button has the keyboard focus
	 * 
	 * @return Whether the button has the keyboard focus
	 */
	public boolean isFocus() {
		return focus;
	}

	/**
	 * Sets whether the button has the keyboard focus
	 * 
	 * @param focus Whether the button has the keyboard focus
	 */
	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public ScrollbuttonWidgetInitializer(IMetaverseTheme thm) {
	}

}
