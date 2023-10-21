
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
 * Class used to initialize a push button
 * 
 * @author tgreen
 *
 */
public class PushbuttonWidgetInitializer {

	/**
	 * Whether the button has the keyboard focus
	 */
	boolean focus = false;

	/**
	 * Object used to initialize the label text of the button
	 */
	PushButtonLabelTextInitializer labelText;

	/**
	 * The spatial position for the button
	 */
	Vector3 position = null;

	/**
	 * Gets the object used to initialize the label text of the button
	 * 
	 * @return The object used to initialize the label text of the button
	 */
	public PushButtonLabelTextInitializer getLabelText() {
		return labelText;
	}

	/**
	 * Sets the object used to initialize the label text of the button
	 * 
	 * @param labelText The object used to initialize the label text of the button
	 */
	public void setLabelText(PushButtonLabelTextInitializer labelText) {
		this.labelText = labelText;
	}

	/**
	 * Gets whether the button has the keyboard focus
	 * 
	 * @return Whether the button has the keyboard focus
	 */
	public boolean isFocus() {
		return focus;
	}

	/**
	 * Sets whether the button has the keyboard focus
	 * 
	 * @param focus Whether the button has the keyboard focus
	 */
	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	/**
	 * Gets the spatial position for the button
	 * 
	 * @return The spatial position for the button
	 */
	public Vector3 getPosition() {
		if (position != null) {
			return position;
		}

		return (new Vector3(0.0, 3.5 - 1.0, -7.0));
	}

	/**
	 * Sets the spatial position for the button
	 * 
	 * @param position The spatial position for the button
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public PushbuttonWidgetInitializer(IMetaverseTheme thm) {
		labelText = new PushButtonLabelTextInitializer(thm);
	}

}
