
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

import codejcore.interfaces.SessionDataApplicationToken;

/**
 * Event whose execution is to be delayed until the condition that the bounds of
 * a block of text has been calculated on a web client
 * 
 * @author tgreen
 *
 */
public class BoundsConditionalResponse {

	/**
	 * The session that is to receive the event
	 */
	SessionDataApplicationToken sess;

	/**
	 * The event handler that is to receive the event
	 */
	IBoundsHandler hndl;

	/**
	 * Gets the session that is to receive the event
	 * 
	 * @return The session that is to receive the event
	 */
	public SessionDataApplicationToken getSess() {
		return sess;
	}

	/**
	 * Gets the event handler that is to receive the event
	 * 
	 * @return The event handler that is to receive the event
	 */
	public IBoundsHandler getHndl() {
		return hndl;
	}

	/**
	 * Constructor
	 * 
	 * @param _sess The session that is to receive the event
	 * @param _hndl The event handler that is to receive the event
	 */
	public BoundsConditionalResponse(SessionDataApplicationToken _sess, IBoundsHandler _hndl) {
		sess = _sess;
		hndl = _hndl;
	}

}
