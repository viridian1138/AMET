
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
 * Class used to initialize BoxWidget.
 * 
 * @author tgreen
 *
 */
public class BoxWidgetInitializer {

	/**
	 * The material for the box
	 */
	protected Material material;

	/**
	 * The center position for the box
	 */
	protected Vector3 position;

	/**
	 * The length of the box along each axis
	 */
	protected Vector3 length;

	/**
	 * Gets the length of the box along each axis
	 * 
	 * @return The length of the box along each axis
	 */
	public Vector3 getLength() {
		if (length != null) {
			return length;
		}

		return (new Vector3(1.0, 3.0, 1.0));
	}

	/**
	 * Sets the length of the box along each axis
	 * 
	 * @param length The length of the box along each axis
	 */
	public void setLength(Vector3 length) {
		this.length = length;
	}

	/**
	 * Gets the center position for the box
	 * 
	 * @return The center position for the box
	 */
	public Vector3 getPosition() {
		if (position != null) {
			return position;
		}

		return (new Vector3(0, 3 - 2, -6.5));
	}

	/**
	 * Sets the center position for the box
	 * 
	 * @param position The center position for the box
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/**
	 * Gets the material for the box
	 * 
	 * @param owner The owning object for the purpose of controlling deletion
	 * @param gc    The graphics context in which to draw the material
	 * @return The material for the box
	 */
	public Material getMaterial(Object owner, GraphicsContext gc) {
		if (material != null) {
			return material;
		}

		MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0x22ff22");
		Material mt = MeshPhongMaterial.create(gc, minit);
		mt.setOwningObject(this);
		return (mt);
	}

	/**
	 * Sets the material for the box
	 * 
	 * @param material The material for the box
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public BoxWidgetInitializer(IMetaverseTheme thm) {
	}

}
