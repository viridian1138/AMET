
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

import java.util.ArrayList;

/**
 * Description of the calculation of the bounds of a particular block of text
 * within a particular client session.
 * 
 * @author tgreen
 *
 */
public class SessionBoundsLoadingIdDesc {

	/**
	 * Default Constructor
	 */
	public SessionBoundsLoadingIdDesc() {
		// Empty
	}

	/**
	 * Event handlers to be invoked once the bounds are loaded
	 */
	public final ArrayList<BoundsConditionalResponse> events = new ArrayList<BoundsConditionalResponse>();

	/**
	 * Whether the bounds has been loaded
	 */
	public Bounds loadedBounds = null;

	/**
	 * Whether the bounds is loading
	 */
	public boolean boundsLoading = false;

}
