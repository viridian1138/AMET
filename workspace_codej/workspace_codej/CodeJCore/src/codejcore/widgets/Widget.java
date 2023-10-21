
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

import codejcore.interfaces.ISound;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;

/**
 * Abstract base class for metaverse application widgets
 * 
 * @author tgreen
 *
 */
public abstract class Widget {

	/**
	 * Default Constructor
	 */
	public Widget() {
		// Empty
	}

	/**
	 * Entry point used by the system to dispatch an event associated with the
	 * widget
	 * 
	 * @param e    The event that is being dispatched
	 * @param sess The session in which to dispatch the event
	 * @param gc   The graphics context in which to dispatch the event
	 */
	public void systemDispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"Widget Response\";\n");
		gc.appendJs("}\n");
	}

	/**
	 * Disposes the resources associated with the widget
	 * 
	 * @param gc The graphics context in which to dispose the resources
	 */
	public void dispose(GraphicsContext gc) {
		// Empty
	}

	/**
	 * Removes the widget from the scene
	 * 
	 * @param gc The graphics context from which to remove the widget
	 */
	public void removeFromScene(GraphicsContext gc) {
		// Empty
	}

	/**
	 * Adds the widget to the scene
	 * 
	 * @param gc The graphics context to which to add the widget
	 */
	public void addToScene(GraphicsContext gc) {
		// Empty
	}

	/**
	 * Adds the ability to play positional audio to the widget
	 * 
	 * @param e    The user interface event that caused the adding of the audio
	 * @param sess The session in which to add the audio
	 * @param gc   The graphics context in which to add the audio
	 * @param snd  The sound to be played
	 */
	public abstract void addPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc,
			ISound snd);

	/**
	 * Plays positional audio that has been added to the widget
	 * 
	 * @param e    The user interface event that caused the playing of the audio
	 * @param sess The session in which to play the audio
	 * @param gc   The graphics context in which to play the audio
	 * @param snd  The sound to be played
	 */
	public abstract void playPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc,
			ISound snd);

}
