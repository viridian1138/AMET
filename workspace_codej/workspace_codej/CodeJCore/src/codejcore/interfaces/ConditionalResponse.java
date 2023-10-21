
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

/**
 * Event that is delayed until a particular condition (such as an initialization
 * condition) is met.
 * 
 * @author tgreen
 *
 */
public class ConditionalResponse {

	/**
	 * The session in which to deliver the event
	 */
	SessionDataApplicationToken sess;

	/**
	 * The handler to receive the dispatching of the event
	 */
	ICompletionHandler hndl;

	/**
	 * Gets the session in which to deliver the event
	 * 
	 * @return The session in which to deliver the event
	 */
	public SessionDataApplicationToken getSess() {
		return sess;
	}

	/**
	 * Gets the handler to receive the dispatching of the event
	 * 
	 * @return The handler to receive the dispatching of the event
	 */
	public ICompletionHandler getHndl() {
		return hndl;
	}

	/**
	 * Constructor
	 * 
	 * @param _sess The session in which to deliver the event
	 * @param _hndl The handler to receive the dispatching of the event
	 */
	public ConditionalResponse(SessionDataApplicationToken _sess, ICompletionHandler _hndl) {
		sess = _sess;
		hndl = _hndl;
	}

}
