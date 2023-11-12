
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
 * Class used to initialize TetrahedronWidget.
 * 
 * @author tgreen
 *
 */
public class TetrahedronWidgetInitializer {

	/**
	 * The material for the tetrahedron
	 */
	protected Material material;

	/**
	 * The center position for the tetrahedron
	 */
	protected Vector3 position;

	/**
	 * The radius of the tetrahedron
	 */
	protected double radius;

	/**
	 * The detail number of the tetrahedron
	 */
	protected int detail = 0;

	/**
	 * Gets the radius of the tetrahedron
	 * 
	 * @return The radius of the tetrahedron
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets the radius of the tetrahedron
	 * 
	 * @param radius The radius of the tetrahedron
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Gets the detail number of the tetrahedron
	 * 
	 * @return The detail number of the tetrahedron
	 */
	public int getDetail() {
		return detail;
	}

	/**
	 * Sets the detail number of the tetrahedron
	 * 
	 * @param detail The detail number of the tetrahedron
	 */
	public void setDetail(int detail) {
		this.detail = detail;
	}

	/**
	 * Gets the center position for the tetrahedron
	 * 
	 * @return The center position for the tetrahedron
	 */
	public Vector3 getPosition() {
		if (position != null) {
			return position;
		}

		return (new Vector3(0, 3 - 2, -6.5));
	}

	/**
	 * Sets the center position for the tetrahedron
	 * 
	 * @param position The center position for the tetrahedron
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/**
	 * Gets the material for the tetrahedron
	 * 
	 * @param owner The owning object for the purpose of controlling deletion
	 * @param gc    The graphics context in which to draw the material
	 * @return The material for the tetrahedron
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
	 * Sets the material for the tetrahedron
	 * 
	 * @param material The material for the tetrahedron
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public TetrahedronWidgetInitializer(IMetaverseTheme thm) {
	}

}
