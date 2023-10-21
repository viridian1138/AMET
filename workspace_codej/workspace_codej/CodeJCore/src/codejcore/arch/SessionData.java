
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

import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.SessionDataToken;

/**
 * Identifier for a session that connected to the CodeJ metaverse web
 * application and the data associated with that session
 * 
 * @author tgreen
 *
 */
public class SessionData extends Session {

	/**
	 * The current Metaverse application running in the session
	 */
	protected MetaverseApplication currentApplication = null;

	/**
	 * The current Metaverse application instance running in the session
	 */
	protected MetaverseApplicationInstance currentApplicationInstance = null;

	/**
	 * The current Metaverse theme running in the session
	 */
	protected IMetaverseTheme currentTheme = null;

	/**
	 * Set of data that is stored with each session of a Metaverse Application
	 */
	protected SessionDataToken sessionDataToken = new SessionDataToken();

	/**
	 * Facade of the Session Data Token given to applications
	 */
	protected SessionDataApplicationToken token = new SessionDataApplicationToken(sessionDataToken);

	/**
	 * Gets the facade of the Session Data Token given to applications
	 * 
	 * @return The facade of the Session Data Token given to applications
	 */
	public SessionDataApplicationToken getToken() {
		return token;
	}

	/**
	 * Gets the current Metaverse application running in the session
	 * 
	 * @return The current Metaverse application running in the session
	 */
	public MetaverseApplication getCurrentApplication() {
		return currentApplication;
	}

	/**
	 * Gets the current Metaverse application instance running in the session
	 * 
	 * @return The current Metaverse application instance running in the session
	 */
	public MetaverseApplicationInstance getCurrentApplicationInstance() {
		return currentApplicationInstance;
	}

	/**
	 * Sets the current Metaverse application running in the session
	 * 
	 * @param currentApplication The current Metaverse application running in the
	 *                           session
	 */
	public void setCurrentApplication(MetaverseApplication currentApplication) {
		this.currentApplication = currentApplication;
		this.currentApplicationInstance = currentApplication.cresteInstance();
		token = new SessionDataApplicationToken(sessionDataToken);
	}

	/**
	 * Gets the current Metaverse theme running in the session
	 * 
	 * @return The current Metaverse theme running in the session
	 */
	public IMetaverseTheme getCurrentTheme() {
		return currentTheme;
	}

	/**
	 * Sets the current Metaverse theme running in the session
	 * 
	 * @param currentTheme The current Metaverse theme running in the session
	 */
	public void setCurrentTheme(IMetaverseTheme currentTheme) {
		this.currentTheme = currentTheme;
	}

	/**
	 * Constructor
	 * 
	 * @param _archTime          Thd timestamp at which the session was created
	 * @param _archId            The unique ID number for the session
	 * @param initialApplication The initial Metaverse application
	 * @param initialTheme       The initial Metaverse theme
	 */
	public SessionData(long _archTime, long _archId, MetaverseApplication initialApplication,
			IMetaverseTheme initialTheme) {
		super(_archTime, _archId);
		currentApplication = initialApplication;
		this.currentApplicationInstance = currentApplication.cresteInstance();
		currentTheme = initialTheme;
	}

	/**
	 * Gets a string identifier for the session
	 * 
	 * @return The string identifier for the session
	 */
	public String getApplicationContext() {
		return ("ac_" + (archTime) + "_" + (archId));
	}

}
