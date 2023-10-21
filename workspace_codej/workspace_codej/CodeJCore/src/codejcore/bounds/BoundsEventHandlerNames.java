
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

/**
 * Common naming constants for the bounds response web service. Most of these
 * are property names for the properties sent to the web service
 * 
 * @author tgreen
 *
 */
public class BoundsEventHandlerNames {

	/**
	 * Property for the ID of the bounds that was calculated
	 */
	public static final String TID = "tid";

	/**
	 * Property for the minimum X ordinate of the bounds that was calculated
	 */
	public static final String MIN_X = "min_X";

	/**
	 * Property for the minimum Y ordinate of the bounds that was calculated
	 */
	public static final String MIN_Y = "min_Y";

	/**
	 * Property for the minimum Z ordinate of the bounds that was calculated
	 */
	public static final String MIN_Z = "min_Z";

	/**
	 * Property for the maximum X ordinate of the bounds that was calculated
	 */
	public static final String MAX_X = "max_X";

	/**
	 * Property for the maximum Y ordinate of the bounds that was calculated
	 */
	public static final String MAX_Y = "max_Y";

	/**
	 * Property for the maximum Z ordinate of the bounds that was calculated
	 */
	public static final String MAX_Z = "max_Z";

}
