
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

package codejthemeemerald.handlers;

import codejcore.interfaces.IFont;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.AbstractScrollbarWidget;
import codejcore.widgets.AbstractScrollbuttonWidget;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.Material;
import codejcore.widgets.MeshStandardMaterial;
import codejcore.widgets.MeshStandardMaterialInitializer;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.ScrollbarWidgetInitializer;
import codejcore.widgets.ScrollbuttonWidgetInitializer;
import codejfontjsecommodore.handlers.FontJseCommodore;

/**
 * Plug-in for the Emerald metaverse UI theme
 * 
 * @author tgreen
 *
 */
public class Theme implements IMetaverseTheme {

	/**
	 * Gets an instance of the prototype material for the primary color of the theme
	 * 
	 * @param gc The graphics context in which to create the material
	 * @return An instance of the prototype material for the primary color of the
	 *         theme
	 */
	public Material getProtoMaterial(GraphicsContext gc) {
		MeshStandardMaterialInitializer minit = new MeshStandardMaterialInitializer(this, "0x4acf2d");
		minit.setOpacity(0.58);
		minit.setEmissive("0xaf04a");
		return (MeshStandardMaterial.create(gc, minit));
	}

	/**
	 * Gets an instance of the prototype material for the alternate/accent color of
	 * the theme
	 * 
	 * @param gc The graphics context in which to create the material
	 * @return An instance of the prototype material for the alternate/accent color
	 *         of the theme
	 */
	public Material getProtoAltMaterial(GraphicsContext gc) {
		MeshStandardMaterialInitializer minit = new MeshStandardMaterialInitializer(this, "0x1a50a0");
		minit.setOpacity(0.58);
		minit.setEmissive("0x226ebe");
		return (MeshStandardMaterial.create(gc, minit));
	}

	/**
	 * Gets an instance of the material used to indicate the highlighting when a
	 * widget has the keyboard focus
	 * 
	 * @param gc The graphics context in which to create the material
	 * @return An instance of the material used to indicate the highlighting when a
	 *         widget has the keyboard focus
	 */
	public Material getHilightMaterial(GraphicsContext gc) {
		MeshStandardMaterialInitializer minit = new MeshStandardMaterialInitializer(this, "0xffffff");
		minit.setOpacity(0.90);
		minit.setEmissive("0xffffff");
		return (MeshStandardMaterial.create(gc, minit));
	}

	/**
	 * The font employed by the theme
	 */
	protected static IFont ifont = new FontJseCommodore();

	/**
	 * Default Constructor
	 */
	public Theme() {
		// Empty
	}

	@Override
	public String getThemeName() {
		return (EventHandlerNames.EMERALD_THEME);
	}

	@Override
	public IFont getTitleFont() {
		return ifont;
	}

	@Override
	public IFont getButtonFont() {
		return ifont;
	}

	@Override
	public Material getTitleTextMaterial(GraphicsContext gc) {
		MeshStandardMaterialInitializer minit = new MeshStandardMaterialInitializer(this, "0x4acf2d");
		return (MeshStandardMaterial.create(gc, minit));
	}

	@Override
	public AbstractScrollbarWidget createVerticalScrollbar(GraphicsContext gc, ScrollbarWidgetInitializer pwi) {
		return (VerticalScrollbarWidget.create(gc, pwi));
	}

	@Override
	public AbstractScrollbuttonWidget createVerticalScrollbuttonUp(GraphicsContext gc,
			ScrollbuttonWidgetInitializer pwi) {
		return (ScrollButtonUpWidget.create(gc, pwi));
	}

	@Override
	public AbstractScrollbuttonWidget createVerticalScrollbuttonDown(GraphicsContext gc,
			ScrollbuttonWidgetInitializer pwi) {
		return (ScrollButtonDownWidget.create(gc, pwi));
	}

	@Override
	public AbstractPushbuttonWidget createPushbutton(GraphicsContext gc, PushbuttonWidgetInitializer pwi) {
		return (PushButtonWidget.create(gc, pwi));
	}

}
