
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

package codejappgrandcentralstation.handlers;

import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.widgets.EventHandlerNames;

/**
 * Plug-in for a central starting point Metaverse Application
 * 
 * @author tgreen
 *
 */
public class GrandCentralStationApp extends MetaverseApplication {

	/**
	 * Default Constructor
	 */
	public GrandCentralStationApp() {
	}

	@Override
	public String getApplicationName() {
		return (EventHandlerNames.GRAND_CENTRAL_STATION_APP);
	}

	@Override
	public MetaverseApplicationInstance cresteInstance() {
		return (new GrandCentralStationAppInstance());
	}

}
