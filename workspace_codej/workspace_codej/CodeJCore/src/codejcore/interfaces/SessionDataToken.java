
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

import java.util.Hashtable;

/**
 * Using the token pattern from the GOF book, contains a set of data that is
 * stored with each session of a Metaverse Application. The class is struct-like
 * in that its members are accessed directly.
 * 
 * @author tgreen
 *
 */
public class SessionDataToken {

	/**
	 * Set of key-value pair data that is stored with each session.
	 */
	public final Hashtable<String, Object> objects = new Hashtable<String, Object>();

	/**
	 * Default Constructor
	 */
	public SessionDataToken() {
		// Empty
	}

}
