
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

package codejapphelloworld.handlers;

import codejcore.arch.SessionStore;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.KeyNames;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * Class for instances of a "Hello World" Metaverse Application
 * 
 * @author tgreen
 *
 */
public class HelloWorldAppInstance extends MetaverseApplicationInstance {

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * The exit button widget
	 */
	protected AbstractPushbuttonWidget exitButton = null;

	/**
	 * The widget for displaying "Hrllo World"
	 */
	protected TextWidget helloWorldTextWidget = null;

	/**
	 * Default Constructor
	 */
	public HelloWorldAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return ("Hello World");
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"Hello World Response\";\n");
		gc.appendJs("}\n");
	}

	@Override
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		gc.appendJs("	scene.background = new THREE.Color(0x77fefe);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		{
			TextWidgetInitializer twA = new TextWidgetInitializer(theme);
			twA.setDisplayText("Running Hello World");
			twA.setPosition(new Vector3(0.0, 3.5 + 1.0, -7.0));

			titleTextWidget = TextWidget.create(gc, twA);
			titleTextWidget.addToScene(gc);
		}

		{
			TextWidgetInitializer twB = new TextWidgetInitializer(theme);
			twB.setDisplayText("Hello World");
			twB.setPosition(new Vector3(0.0, 3.5, -7.0));

			helloWorldTextWidget = TextWidget.create(gc, twB);
			helloWorldTextWidget.addToScene(gc);
		}

		{
			PushbuttonWidgetInitializer bwi = new PushbuttonWidgetInitializer(theme);
			bwi.getLabelText().setDisplayText("Exit");
			bwi.setPosition(new Vector3(2.0, 3.5 - 1.0, -7.0));
			bwi.setFocus(true);
			exitButton = theme.createPushbutton(gc, bwi);

			exitButton.addToScene(gc);
		}

	}

	@Override
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Hello World App Responding To Keydown Event");

		String keyCode = e.getKeyCode();

		if (keyCode.equals(KeyNames.SPACE_KEY)) {
			MetaverseApplication grandCentral = SessionStore.applications
					.get(EventHandlerNames.GRAND_CENTRAL_STATION_APP);

			System.out.println("Changing To App " + (grandCentral.getApplicationName()));

			gc.changeApplication(grandCentral, e);
		}

	}

	@Override
	public void stopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Hello World App Responding To Stop Event");

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		{
			helloWorldTextWidget.removeFromScene(gc);

			helloWorldTextWidget.dispose(gc);
		}

		{
			exitButton.removeFromScene(gc);

			exitButton.dispose(gc);
		}

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is A Hello World Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
