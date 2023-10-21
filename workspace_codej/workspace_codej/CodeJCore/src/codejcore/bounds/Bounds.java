
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

package codejcore.bounds;

import codejcore.widgets.Vector3;

/**
 * The bounds of a block of text that has been calculated on a web client.
 * 
 * @author tgreen
 *
 */
public class Bounds {

	/**
	 * Default Constructor
	 */
	public Bounds() {
		// Empty
	}

	/**
	 * The minimum for all three ordinates
	 */
	Vector3 min;

	/**
	 * The maximum of all three ordinates
	 */
	Vector3 max;

	/**
	 * Gets the minimum for all three ordinates
	 * 
	 * @return The minimum for all three ordinates
	 */
	public Vector3 getMin() {
		return min;
	}

	/**
	 * Sets the minimum for all three ordinates
	 * 
	 * @param min The minimum for all three ordinates
	 */
	public void setMin(Vector3 min) {
		this.min = min;
	}

	/**
	 * Gets the maximum of all three ordinates
	 * 
	 * @return The maximum of all three ordinates
	 */
	public Vector3 getMax() {
		return max;
	}

	/**
	 * Sets the maximum of all three ordinates
	 * 
	 * @param max The maximum of all three ordinates
	 */
	public void setMax(Vector3 max) {
		this.max = max;
	}

}
