
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

package codejcore.arch;

/**
 * Identifier for a session that connected to the CodeJ metaverse web
 * application
 * 
 * @author tgreen
 * @param <T> Generic type for the session
 *
 */
public class Session<T extends Comparable<T>> implements Comparable<Session<T>> {

	@Override
	public int compareTo(Session arg0) {
		int ret = archTime.compareTo(arg0.archTime);

		if (ret == 0) {
			ret = archId.compareTo(arg0.archId);
		}

		return ret;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Session) {
			Session s0 = (Session) arg0;
			return ((archTime.longValue() == s0.archTime.longValue()) && (archId.longValue() == s0.archId.longValue()));
		}

		return (false);
	}

	@Override
	public int hashCode() {
		return ((int) (archTime + archId));
	}

	/**
	 * Constructs the session ID
	 * 
	 * @param _archTime The time at which the session was created
	 * @param _archId   The unique ID number of the session
	 */
	public Session(long _archTime, long _archId) {
		archTime = _archTime;
		archId = _archId;
	}

	/**
	 * Prints the session to the console
	 */
	public void print() {
		System.out.println("archTime " + archTime);
		System.out.println("archId " + archId);
	}

	/**
	 * The time at which the session was created
	 */
	protected Long archTime;

	/**
	 * The unique ID number of the session
	 */
	protected Long archId;

}
