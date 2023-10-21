
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

package codejcore.widgets;

import codejcore.interfaces.IMetaverseTheme;

/**
 * Class used to initialize MeshPhongMaterial
 * 
 * @author tgreen
 *
 */
public class MeshPhongMaterialInitializer {

	/**
	 * Hexadecimal string for the color of the material
	 */
	protected String color;

	/**
	 * Gets the color of the material
	 * 
	 * @return Hexadecimal string for the color of the material
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color of the material
	 * 
	 * @param color Hexadecimal string for the color of the material
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Constructor
	 * 
	 * @param thm   Theme used to initialize the material
	 * @param color Hexadecimal string for the color of the material
	 */
	public MeshPhongMaterialInitializer(IMetaverseTheme thm, String color) {
		this.color = color;
	}

}
