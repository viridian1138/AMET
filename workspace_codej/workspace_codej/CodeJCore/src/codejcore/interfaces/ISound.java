
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
 * An abstract representation of a sound in a metaverse application
 * 
 * @author tgreen
 *
 */
public interface ISound {

	/**
	 * Gets the name of the sound
	 * 
	 * @return The name of the sound
	 */
	public String getSoundName();

	/**
	 * Gets whether the sound has been loaded
	 * 
	 * @param sess The session for which the answer is being gathered
	 * @return Whether the sound has been loaded
	 */
	public boolean isSoundLoaded(SessionDataApplicationToken sess);

	/**
	 * Gets the string used to import the sound into JS
	 * 
	 * @return The string used to import the sound into JS
	 */
	public String getDependencyImportString();

	/**
	 * Gets the reference used to play the sound
	 * 
	 * @return The reference used to play the sound
	 */
	public String getSoundReference();

	/**
	 * Initiates a handler upon the loading of the sound
	 * 
	 * @param sess The session over which to load the sound
	 * @param gc   The graphics context in which to draw
	 * @param hndl The handler to be initiated upon the loading of the sound
	 */
	public void handleSoundLoading(SessionDataApplicationToken sess, GraphicsContext gc, ICompletionHandler hndl);

	/**
	 * Initiates the loading of the sound.
	 * 
	 * @param sess The session over which to load the sound
	 * @param gc   The graphics context in which to create the sound
	 */
	public void initiateSoundLoading(SessionDataApplicationToken sess, GraphicsContext gc);

}
