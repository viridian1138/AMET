
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

package codejvrbutton.handlers;

import com.sun.net.httpserver.HttpHandler;

import codejcore.interfaces.CoreNames;
import codejsvr.interfaces.IHttpHandler;
import codejsvr.interfaces.SystemNames;

/**
 * Plugin for a web service to get the JS definition for the button for entering
 * Virtual Reality
 * 
 * @author tgreen
 *
 */
public class VRButtonIHandler implements IHttpHandler {

	@Override
	public String getPath() {
		return ("/" + SystemNames.METAVERSE_WEB_APPLICATION_PATH + CoreNames.VIRTUAL_REALITY_ENTRY_BUTTON_PATH);
	}

	@Override
	public HttpHandler getHandler() {
		return (new VRButtonHandler());
	}

}
