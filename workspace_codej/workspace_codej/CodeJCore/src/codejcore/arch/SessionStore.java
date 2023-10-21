
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

import java.util.Hashtable;

import codejcore.interfaces.IFont;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.MetaverseApplication;

/**
 * The store of sessions for the CodeJ metaverse web application
 * 
 * @author deck
 *
 */
public class SessionStore {

	/**
	 * The store of session data instances indexed by the session keys
	 */
	public static final Hashtable<Session, SessionData> sessionTable = new Hashtable<Session, SessionData>();

	/**
	 * The current ID for the generation of new objects
	 */
	public static long currentId = 0;

	/**
	 * Store of Metaverse applications indexed by application name
	 */
	public static final Hashtable<String, MetaverseApplication> applications = new Hashtable<String, MetaverseApplication>();

	/**
	 * Store of Metaverse themes indexed by theme name
	 */
	public static final Hashtable<String, IMetaverseTheme> themes = new Hashtable<String, IMetaverseTheme>();

	/**
	 * Store of fonts indexed by font name
	 */
	public static final Hashtable<String, IFont> fonts = new Hashtable<String, IFont>();

}
