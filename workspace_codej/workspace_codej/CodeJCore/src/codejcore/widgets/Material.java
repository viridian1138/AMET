
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

/**
 * Abstract base class for material definitions (such as Phong) used in
 * metaverse rendering
 * 
 * @author tgreen
 *
 */
public abstract class Material {

	/**
	 * The owning object for the purpose of controlling deletion
	 */
	Object owningObject = null;

	/**
	 * Gets the owning object for the purpose of controlling deletion
	 * 
	 * @return The owning object for the purpose of controlling deletion
	 */
	public Object getOwningObject() {
		return owningObject;
	}

	/**
	 * Sets the owning object for the purpose of controlling deletion
	 * 
	 * @param owningObject The owning object for the purpose of controlling deletion
	 */
	public void setOwningObject(Object owningObject) {
		this.owningObject = owningObject;
	}

	/**
	 * Default Constructor
	 */
	protected Material() {
	}

	/**
	 * Gets the reference to the JS object for the THREE.JS material
	 * 
	 * @return Reference to the JS object for the THREE.JS material
	 */
	public abstract String getMaterialContext();

	/**
	 * Disposes the object
	 * 
	 * @param gc The graphics context in which to dispose the object
	 */
	public abstract void dispose(GraphicsContext gc);

}
