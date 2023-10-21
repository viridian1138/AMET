
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

import java.util.ArrayList;

import codejcore.interfaces.IMetaverseTheme;

/**
 * Class used to initialize PrismWidget
 * 
 * @author tgreen
 *
 */
public class PrismWidgetInitializer {

	/**
	 * The material for the prism
	 */
	protected Material material;

	/**
	 * The list of vertices for the prism
	 */
	protected ArrayList<Vector3> vertices;

	/**
	 * The list of triangular faces for the prism
	 */
	protected ArrayList<Face3> faces;

	/**
	 * The spatial position for the prism
	 */
	protected Vector3 position;

	/**
	 * Gets the faces for the prism
	 * 
	 * @return The faces for the prism
	 */
	public ArrayList<Face3> getFaces() {
		if (faces != null) {
			return faces;
		}

		// 0 1 2
		// 3 4 5

		ArrayList<Face3> ar = new ArrayList<Face3>();
		ar.add(new Face3(0, 1, 2));
		ar.add(new Face3(3, 4, 5));
		ar.add(new Face3(0, 2, 5));
		ar.add(new Face3(0, 3, 5));
		ar.add(new Face3(0, 1, 4));
		ar.add(new Face3(0, 3, 4));
		ar.add(new Face3(1, 2, 5));
		ar.add(new Face3(1, 4, 5));
		return (ar);
	}

	/**
	 * Sets the faces for the prism
	 * 
	 * @param faces The faces for the prism
	 */
	public void setFaces(ArrayList<Face3> faces) {
		this.faces = faces;
	}

	/**
	 * Gets the spatial position for the prism
	 * 
	 * @return The spatial position for the prism
	 */
	public Vector3 getPosition() {
		if (position != null) {
			return position;
		}

		return (new Vector3(0, 3 - 2, -6.5));
	}

	/**
	 * Sets the spatial position for the prism
	 * 
	 * @param position The spatial position for the prism
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/**
	 * Gets the vertices for the prism
	 * 
	 * @return The vertices for the prism
	 */
	public ArrayList<Vector3> getVertices() {
		if (vertices != null) {
			return vertices;
		}

		ArrayList<Vector3> ar = new ArrayList<Vector3>();
		ar.add(new Vector3(-1.0, 1.5, -0.15));
		ar.add(new Vector3(-1.0, -1.5, -0.15));
		ar.add(new Vector3(1.0, -1.5, -0.15));
		ar.add(new Vector3(-1.0, 1.5, 0.15));
		ar.add(new Vector3(-1.0, -1.5, 0.15));
		ar.add(new Vector3(1.0, -1.5, 0.15));
		return (ar);
	}

	/**
	 * Sets the vertices for the prism
	 * 
	 * @param vertices The vertices for the prism
	 */
	public void setVertices(ArrayList<Vector3> vertices) {
		this.vertices = vertices;
	}

	/**
	 * Gets the material for the prism
	 * 
	 * @param owner The owning object for the purpose of controlling deletion
	 * @param gc    The graphics context in which to draw the material
	 * @return The material for the prism
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
	 * Sets the material for the prism
	 * 
	 * @param material The material for the prism
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public PrismWidgetInitializer(IMetaverseTheme thm) {
	}

}
