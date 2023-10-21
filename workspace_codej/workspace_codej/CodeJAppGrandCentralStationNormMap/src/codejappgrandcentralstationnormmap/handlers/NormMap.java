
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

package codejappgrandcentralstationnormmap.handlers;

import codejcore.interfaces.CoreNames;
import codejcore.interfaces.INormMap;

/**
 * Web service locations at which to query the plane normal/texture map content
 * for the Grand Central Station application
 * 
 * @author tgreen
 *
 */
public class NormMap implements INormMap {

	@Override
	public String getTextureName() {
		return ("Grand Central Station");
	}

	@Override
	public String getTextureMapReference() {
		return (CoreNames.TEXTURE_RESOURCE_PATH + "GrandCentralStation/plane.jpg");
	}

	@Override
	public String getNormReference() {
		return (CoreNames.TEXTURE_RESOURCE_PATH + "GrandCentralStation/plane_norm.jpg");
	}

}
