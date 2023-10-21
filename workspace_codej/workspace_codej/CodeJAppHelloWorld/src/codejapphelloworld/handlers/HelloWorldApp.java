
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

package codejapphelloworld.handlers;

import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;

/**
 * Plug-in for a "Hello World" Metaverse Application
 * 
 * @author tgreen
 *
 */
public class HelloWorldApp extends MetaverseApplication {

	/**
	 * Default Constructor
	 */
	public HelloWorldApp() {
	}

	@Override
	public String getApplicationName() {
		return ("Hello World");
	}

	@Override
	public MetaverseApplicationInstance cresteInstance() {
		return (new HelloWorldAppInstance());
	}

}
