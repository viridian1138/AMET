
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

import codejcore.interfaces.IFont;
import codejcore.interfaces.IMetaverseTheme;

/**
 * Class used to initialize TextWidget
 * 
 * @author tgreen
 *
 */
public class TextWidgetInitializer {

	/**
	 * The spatial position for the text
	 */
	Vector3 position = null;

	/**
	 * The material for the front of the text
	 */
	protected Material material;

	/**
	 * The material for the side of the text
	 */
	protected Material sideMaterial;

	/**
	 * The font size for the text
	 */
	protected Double fontSize;

	/**
	 * Gets the font size for the text
	 * 
	 * @return The font size for the text
	 */
	public double getFontSize() {
		double mult = font.getFontScale();

		if (fontSize != null) {
			return mult * fontSize;
		}

		return (mult * 49.5);
	}

	/**
	 * Sets the font size for the text
	 * 
	 * @param fontSize The font size for the text
	 */
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * Sets the material for the front of the text
	 * 
	 * @param owner The owning object for the purpose of controlling deletion
	 * @param gc    The graphics context in which to draw the material
	 * @return The material for the front of the text
	 */
	public Material getMaterial(Object owner, GraphicsContext gc) {
		if (material != null) {
			return material;
		}

		Material mt = null;
		if (theme != null) {
			mt = theme.getTitleTextMaterial(gc);
		} else {
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xffc000");
			mt = MeshPhongMaterial.create(gc, minit);
		}

		mt.setOwningObject(this);
		return (mt);
	}

	/**
	 * Gets the material for the front of the text
	 * 
	 * @param material The material for the front of the text
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Sets the material for the side of the text
	 * 
	 * @param owner The owning object for the purpose of controlling deletion
	 * @param gc    The graphics context in which to draw the material
	 * @return The material for the side of the text
	 */
	public Material getSideMaterial(Object owner, GraphicsContext gc) {
		if (sideMaterial != null) {
			return sideMaterial;
		}

		MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xf1edc2");
		Material mt = MeshPhongMaterial.create(gc, minit);
		mt.setOwningObject(this);
		return (mt);
	}

	/**
	 * Gets the material for the side of the text
	 * 
	 * @param material The material for the side of the text
	 */
	public void setSideMaterial(Material material) {
		this.sideMaterial = material;
	}

	/**
	 * Gets the spatial position for the text
	 * 
	 * @return The spatial position for the text
	 */
	public Vector3 getPosition() {
		if (position != null) {
			return position;
		}

		return (new Vector3(0.0, 3.5, -7.0));
	}

	/**
	 * Sets the spatial position for the text
	 * 
	 * @param position The spatial position for the text
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/**
	 * Gets the text that the widget is to display
	 * 
	 * @return The text that the widget is to display
	 */
	public String getDisplayText() {
		if (displayText != null) {
			return displayText;
		}

		return ("Sample Display Text");
	}

	/**
	 * Sets the text that the widget is to display
	 * 
	 * @param displayText The text that the widget is to display
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	/**
	 * The text that the widget is to display
	 */
	String displayText;

	/**
	 * The theme for which to initialize the initializer
	 */
	IMetaverseTheme theme;

	/**
	 * The font used to display the text
	 */
	IFont font;

	/**
	 * Sets the font used to display the text
	 * 
	 * @param font The font used to display the text
	 */
	public void setFont(IFont font) {
		this.font = font;
	}

	/**
	 * Gets the font used to display the text
	 * 
	 * @return The font used to display the text
	 */
	public IFont getFont() {
		return font;
	}

	/**
	 * Constructor
	 * 
	 * @param thm The theme for which to initialize the initializer
	 */
	public TextWidgetInitializer(IMetaverseTheme thm) {
		theme = thm;
		font = thm.getTitleFont();
	}

}
