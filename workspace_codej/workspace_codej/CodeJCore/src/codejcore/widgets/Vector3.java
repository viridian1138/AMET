
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

/**
 * A vector in three-dimensional space.
 * 
 * @author tgreen
 *
 */
public class Vector3 {

	/**
	 * The X-Coordinate value
	 */
	double x;

	/**
	 * The Y-Coordinate value
	 */
	double y;

	/**
	 * The Z-Coordinate value
	 */
	double z;

	/**
	 * Constructor
	 * 
	 * @param _x The X-Coordinate value
	 * @param _y The Y-Coordinate value
	 * @param _z The Z-Coordinate value
	 */
	public Vector3(double _x, double _y, double _z) {
		x = _x;
		y = _y;
		z = _z;
	}

	/**
	 * Gets the X-Coordinate value
	 * 
	 * @return The X-Coordinate value
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the X-Coordinate value
	 * 
	 * @param x The X-Coordinate value
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gets the Y-Coordinate value
	 * 
	 * @return The Y-Coordinate value
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the Y-Coordinate value
	 * 
	 * @param y The Y-Coordinate value
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Gets the Z-Coordinate value
	 * 
	 * @return The Z-Coordinate value
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Sets the Z-Coordinate value
	 * 
	 * @param z The Z-Coordinate value
	 */
	public void setZ(double z) {
		this.z = z;
	}

}
