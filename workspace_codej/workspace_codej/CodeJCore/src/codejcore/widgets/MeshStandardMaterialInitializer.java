
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
 * Class used to initialize MeshStandardMaterial
 * 
 * @author tgreen
 *
 */
public class MeshStandardMaterialInitializer {

	/**
	 * Hexadecimal string for the color of the material
	 */
	protected String color;

	/**
	 * The opacity of the material
	 */
	protected double opacity = 0.99058;

	/**
	 * Hexadecimal string indicating the extent to which the material is emissive
	 */
	protected String emissive = "0x000000";

	/**
	 * Whether the material is transparent
	 */
	protected boolean transparent = true;

	/**
	 * The roughness of the material
	 */
	protected double roughness = 0.3;

	/**
	 * The metalness of the material
	 */
	protected double metalness = 1.0;

	/**
	 * Gets the metalness of the material
	 * 
	 * @return The metalness of the material
	 */
	public double getMetalness() {
		return metalness;
	}

	/**
	 * Sets the metalness of the material
	 * 
	 * @param metalness The metalness of the material
	 */
	public void setMetalness(double metalness) {
		this.metalness = metalness;
	}

	/**
	 * Gets the roughness of the material
	 * 
	 * @return The roughness of the material
	 */
	public double getRoughness() {
		return roughness;
	}

	/**
	 * Sets the roughness of the material
	 * 
	 * @param roughness The roughness of the material
	 */
	public void setRoughness(double roughness) {
		this.roughness = roughness;
	}

	/**
	 * Gets whether the material is transparent
	 * 
	 * @return Whether the material is transparent
	 */
	public boolean isTransparent() {
		return transparent;
	}

	/**
	 * Sets whether the material is transparent
	 * 
	 * @param transparent Whether the material is transparent
	 */
	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}

	/**
	 * Gets the extent to which the material is emissive
	 * 
	 * @return Hexadecimal string indicating the extent to which the material is
	 *         emissive
	 */
	public String getEmissive() {
		return emissive;
	}

	/**
	 * Sets the extent to which the material is emissive
	 * 
	 * @param emissive Hexadecimal string indicating the extent to which the
	 *                 material is emissive
	 */
	public void setEmissive(String emissive) {
		this.emissive = emissive;
	}

	/**
	 * Gets the opacity of the material
	 * 
	 * @return The opacity of the material
	 */
	public double getOpacity() {
		return opacity;
	}

	/**
	 * Sets the opacity of the material
	 * 
	 * @param opacity The opacity of the material
	 */
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

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
	public MeshStandardMaterialInitializer(IMetaverseTheme thm, String color) {
		this.color = color;
	}

}
