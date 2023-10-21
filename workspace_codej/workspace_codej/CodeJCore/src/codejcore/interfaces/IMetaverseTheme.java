
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

package codejcore.interfaces;

import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.AbstractScrollbarWidget;
import codejcore.widgets.AbstractScrollbuttonWidget;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.Material;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.ScrollbarWidgetInitializer;
import codejcore.widgets.ScrollbuttonWidgetInitializer;

/**
 * An abstract representation of a metaverse user interface theme
 * 
 * @author tgreen
 *
 */
public interface IMetaverseTheme {

	/**
	 * Gets the name of the theme
	 * 
	 * @return The name of the theme
	 */
	public String getThemeName();

	/**
	 * Creates a scroll bar
	 * 
	 * @param gc  The graphics context in which to create the scroll bar
	 * @param pwi Instance used to initialize the scroll bar
	 * @return The created scroll bar
	 */
	public AbstractScrollbarWidget createVerticalScrollbar(GraphicsContext gc, ScrollbarWidgetInitializer pwi);

	/**
	 * Gets the font to use for title text
	 * 
	 * @return The font to use for title text
	 */
	public IFont getTitleFont();

	/**
	 * Gets the material to use for title text
	 * 
	 * @param gc The graphics context in which to create the material
	 * @return The material to use for title text
	 */
	public Material getTitleTextMaterial(GraphicsContext gc);

	/**
	 * Creates a scroll-up button for a scroll bar
	 * 
	 * @param gc  The graphics context in which to create the button
	 * @param pwi Instance used to initialize the button
	 * @return The created button
	 */
	public AbstractScrollbuttonWidget createVerticalScrollbuttonUp(GraphicsContext gc,
			ScrollbuttonWidgetInitializer pwi);

	/**
	 * Creates a scroll-down button for a scroll bar
	 * 
	 * @param gc  The graphics context in which to create the button
	 * @param pwi Instance used to initialize the button
	 * @return The created button
	 */
	public AbstractScrollbuttonWidget createVerticalScrollbuttonDown(GraphicsContext gc,
			ScrollbuttonWidgetInitializer pwi);

	/**
	 * Creates a pushbutton
	 * 
	 * @param gc  The graphics context in which to create the button
	 * @param pwi Instance used to initialize the button
	 * @return The created button
	 */
	public AbstractPushbuttonWidget createPushbutton(GraphicsContext gc, PushbuttonWidgetInitializer pwi);

	/**
	 * Gets the font for pushbuttons
	 * 
	 * @return The font for pushbuttons
	 */
	public IFont getButtonFont();

}
