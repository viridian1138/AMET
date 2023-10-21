
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
 * A triangular face used in a PrismWidget
 * 
 * @author tgreen
 *
 */
public class Face3 {

	/**
	 * The index of the first vertex of the face
	 */
	int index0;

	/**
	 * The index of the second vertex of the face
	 */
	int index1;

	/**
	 * The index of the third vertex of the face
	 */
	int index2;

	/**
	 * Constructor
	 * 
	 * @param i0 The index of the first vertex of the face
	 * @param i1 The index of the second vertex of the face
	 * @param i2 The index of the third vertex of the face
	 */
	public Face3(int i0, int i1, int i2) {
		index0 = i0;
		index1 = i1;
		index2 = i2;
	}

	/**
	 * Gets the index of the first vertex of the face
	 * 
	 * @return The index of the first vertex of the face
	 */
	public int getIndex0() {
		return index0;
	}

	/**
	 * Gets the index of the second vertex of the face
	 * 
	 * @return The index of the second vertex of the face
	 */
	public int getIndex1() {
		return index1;
	}

	/**
	 * Gets the index of the third vertex of the face
	 * 
	 * @return The index of the third vertex of the face
	 */
	public int getIndex2() {
		return index2;
	}

}
