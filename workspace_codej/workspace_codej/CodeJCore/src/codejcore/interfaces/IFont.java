
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

import codejcore.widgets.GraphicsContext;

/**
 * Representation of a font in a Metaverse Application
 * 
 * @author deck
 *
 */
public interface IFont {

	/**
	 * Gets the name of the font
	 * 
	 * @return The name of the font
	 */
	public String getFontName();

	/**
	 * Gets whether the font has been loaded
	 * 
	 * @param sess The session for which the answer is being gathered
	 * @return Whether the font has been loaded
	 */
	public boolean isFontLoaded(SessionDataApplicationToken sess);

	/**
	 * Gets the string used to import the font into JS
	 * 
	 * @return The string used to import the font into JS
	 */
	public String getDependencyImportString();

	/**
	 * Gets the reference used to apply the font
	 * 
	 * @return The reference used to apply the font
	 */
	public String getFontReference();

	/**
	 * Gets the scaling number of the font. This number is used to make all fonts
	 * have similar sizes
	 * 
	 * @return The scaling number of the font
	 */
	public double getFontScale();

	/**
	 * Initiates a handler upon the loading of the font
	 * 
	 * @param sess The session over which to load the font
	 * @param gc   The graphics context in which to draw
	 * @param hndl The handler to be initiated upon the loading of the font
	 */
	public void handleFontLoading(SessionDataApplicationToken sess, GraphicsContext gc, ICompletionHandler hndl);

	/**
	 * Initiates the loading of the font
	 * 
	 * @param sess The session over which to load the font
	 * @param gc   The graphics context in which to create the font
	 */
	public void initiateFontLoading(SessionDataApplicationToken sess, GraphicsContext gc);

}
