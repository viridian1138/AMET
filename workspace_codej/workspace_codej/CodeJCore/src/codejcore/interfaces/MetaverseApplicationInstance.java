
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

import java.util.Hashtable;

import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;

/**
 * Abstract class for instances of a Metaverse Application
 * 
 * @author tgreen
 *
 */
public abstract class MetaverseApplicationInstance {

	/**
	 * Table of event handlers indexed by event type
	 */
	protected final Hashtable<String, IEventHandler> iEventHandlers = new Hashtable<String, IEventHandler>();

	/**
	 * Default Constructor
	 */
	public MetaverseApplicationInstance() {

		iEventHandlers.put(EventHandlerNames.START_EVENT_TYPE, new IEventHandler() {

			@Override
			public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
				MetaverseApplicationInstance.this.systemDispatchStartEvent(e, sess, gc);
			}

		});

		iEventHandlers.put(EventHandlerNames.KEYDOWN_EVENT_TYPE, new IEventHandler() {

			@Override
			public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
				MetaverseApplicationInstance.this.systemDispatchKeydownEvent(e, sess, gc);
			}

		});

		iEventHandlers.put(EventHandlerNames.TIMEOUT_EVENT_TYPE, new IEventHandler() {

			@Override
			public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
				MetaverseApplicationInstance.this.systemDispatchTimeoutEvent(e, sess, gc);
			}

		});

	}

	/**
	 * Gets the name of the application
	 * 
	 * @return The name of the application
	 */
	public abstract String getApplicationName();

	/**
	 * Handles a system request to process an event
	 * 
	 * @param e    The event to be processed
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the event
	 */
	public void systemDispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		String eventType = e.getEventType();
		IEventHandler hndl = iEventHandlers.get(eventType);
		if (hndl != null) {
			hndl.dispatchEvent(e, sess, gc);
		} else {
			dispatchEvent(e, sess, gc);
		}
	}

	/**
	 * Override this method in a subclass to handle otherwise unrecognized events
	 * 
	 * @param e    The event associated with the event
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the event
	 */
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

	}

	/**
	 * Handles a system request to start the application
	 * 
	 * @param e    The event associated with the application start
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the application start
	 */
	public void systemDispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		final String ac = gc.getApplicationContext();
		System.out.println("ac: " + ac);
		System.out.println("Creating Application Context : " + this);

		gc.appendJs("{\n");
		gc.appendJs("tmpObj." + ac + " = {};\n");
		gc.appendJs("}\n");

		dispatchStartEvent(e, sess, gc);

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is An Application Start Response " + this + "\";\n");
		gc.appendJs("}\n");
	}

	/**
	 * Override this method in a subclass to handle requests to start the
	 * application
	 * 
	 * @param e    The event associated with the application start
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the application start
	 */
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
	}

	/**
	 * Handles a system request to process a key down event
	 * 
	 * @param e    The event associated with the key down
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the key down
	 */
	public void systemDispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		final String ac = gc.getApplicationContext();
		System.out.println("ac: " + ac);
		System.out.println("Dispatching Keydown : " + this);

		dispatchKeydownEvent(e, sess, gc);

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is An Application Keydown Response " + this + "\";\n");
		gc.appendJs("}\n");
	}

	/**
	 * Override this method in a subclass to handle key down events
	 * 
	 * @param e    The event associated with the key down
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the key down
	 */
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
	}

	/**
	 * Handles a system request to process a timeout event
	 * 
	 * @param e    The event associated with the timeout
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the timeout
	 */
	public void systemDispatchTimeoutEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		final String ac = gc.getApplicationContext();
		System.out.println("ac: " + ac);
		System.out.println("Dispatching Timeout : " + this);

		dispatchTimeoutEvent(e, sess, gc);

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is An Application Timeout Response " + this + "\";\n");
		gc.appendJs("}\n");
	}

	/**
	 * Override this method in a subclass to handle timeout events
	 * 
	 * @param e    The event associated with the timeout
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the timeout
	 */
	public void dispatchTimeoutEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
	}

	/**
	 * Handles a system request to stop the application
	 * 
	 * @param e    The event associated with the application stop
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the application stop
	 */
	public void systemStopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		stopApplication(e, sess, gc);
	}

	/**
	 * Override this method in a subclass to handle requests to stop the application
	 * 
	 * @param e    The event associated with the application stop
	 * @param sess The session data associated with the application instance
	 * @param gc   The graphics context in which to render the application stop
	 */
	public void stopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
	}

}
