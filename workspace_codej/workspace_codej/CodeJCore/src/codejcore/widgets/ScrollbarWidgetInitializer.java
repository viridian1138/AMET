
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
 * Class used to initialize a scroll bar
 * 
 * @author tgreen
 *
 */
public class ScrollbarWidgetInitializer {

	/**
	 * The minimum value of the scrollbar
	 */
	protected double minScroll = 0.0;

	/**
	 * The maximum value of the scrollbar
	 */
	protected double maxScroll = 100.0;

	/**
	 * The start value of the scrollbar thumb
	 */
	protected double startScroll = 50.0;

	/**
	 * The end value of the scrollbar thumb
	 */
	protected double endScroll = 75.0;

	/**
	 * Whether the scroll bar has the keyboard focus
	 */
	boolean focus = false;

	/**
	 * Gets whether the scroll bar has the keyboard focus
	 * 
	 * @return Whether the scroll bar has the keyboard focus
	 */
	public boolean isFocus() {
		return focus;
	}

	/**
	 * Sets whether the scroll bar has the keyboard focus
	 * 
	 * @param focus Whether the scroll bar has the keyboard focus
	 */
	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	/**
	 * Gets the minimum value of the scrollbar
	 * 
	 * @return The minimum value of the scrollbar
	 */
	public double getMinScroll() {
		return minScroll;
	}

	/**
	 * Sets the minimum value of the scrollbar
	 * 
	 * @param minScroll The minimum value of the scrollbar
	 */
	public void setMinScroll(double minScroll) {
		this.minScroll = minScroll;
	}

	/**
	 * Gets the maximum value of the scrollbar
	 * 
	 * @return The maximum value of the scrollbar
	 */
	public double getMaxScroll() {
		return maxScroll;
	}

	/**
	 * Sets the maximum value of the scrollbar
	 * 
	 * @param maxScroll The maximum value of the scrollbar
	 */
	public void setMaxScroll(double maxScroll) {
		this.maxScroll = maxScroll;
	}

	/**
	 * Gets the start value of the scrollbar thumb
	 * 
	 * @return The start value of the scrollbar thumb
	 */
	public double getStartScroll() {
		return startScroll;
	}

	/**
	 * Sets the start value of the scrollbar thumb
	 * 
	 * @param startScroll The start value of the scrollbar thumb
	 */
	public void setStartScroll(double startScroll) {
		this.startScroll = startScroll;
	}

	/**
	 * Gets the end value of the scrollbar thumb
	 * 
	 * @return The end value of the scrollbar thumb
	 */
	public double getEndScroll() {
		return endScroll;
	}

	/**
	 * Sets the end value of the scrollbar thumb
	 * 
	 * @param endScroll The end value of the scrollbar thumb
	 */
	public void setEndScroll(double endScroll) {
		this.endScroll = endScroll;
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public ScrollbarWidgetInitializer(IMetaverseTheme thm) {
	}

}
