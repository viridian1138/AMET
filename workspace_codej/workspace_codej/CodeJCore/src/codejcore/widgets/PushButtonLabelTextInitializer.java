
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
 * Class used to initialize the label text of a push button
 * 
 * @author tgreen
 *
 */
public class PushButtonLabelTextInitializer extends TextWidgetInitializer {

	/**
	 * Gets the font size for the label
	 * 
	 * @return The font size for the label
	 */
	public double getFontSize() {
		double mult = font.getFontScale();

		if (fontSize != null) {
			return mult * fontSize;
		}

		return (mult * 25.0);
	}

	/**
	 * Gets the material for the label
	 * 
	 * @param owner The owning object for the purpose of controlling deletion
	 * @param gc    The graphics context in which to draw the material
	 * @return The material for the label
	 */
	public Material getMaterial(Object owner, GraphicsContext gc) {
		if (material != null) {
			return material;
		}

		Material mt = null;
		if (theme != null) {
			mt = theme.getTitleTextMaterial(gc);
		} else {
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0x000000");
			mt = MeshPhongMaterial.create(gc, minit);
		}

		mt.setOwningObject(this);
		return (mt);
	}

	/**
	 * Gets the text to be displayed for the label
	 * 
	 * @return The text to be displayed for the label
	 */
	public String getDisplayText() {
		if (displayText != null) {
			return displayText;
		}

		return ("Protoy :_{}<>#$%^&*");
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public PushButtonLabelTextInitializer(IMetaverseTheme thm) {
		super(thm);
		font = thm.getButtonFont();
	}

}
