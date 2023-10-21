
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

package codejappprototypetesting.handlers;

import codejcore.arch.SessionStore;
import codejcore.focus.HorizontalFocusRotation;
import codejcore.interfaces.IEventHandler;
import codejcore.interfaces.IFont;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.Material;
import codejcore.widgets.MeshPhongMaterial;
import codejcore.widgets.MeshPhongMaterialInitializer;
import codejcore.widgets.PrismWidget;
import codejcore.widgets.PrismWidgetInitializer;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * Class for instances of a Metaverse Application testing prototype code
 * 
 * @author tgreen
 *
 */
public class PrototypeTestingAppInstance extends MetaverseApplicationInstance {

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * Prototype test text widget
	 */
	protected TextWidget prototypeTestingMessageWidget = null;

	/**
	 * Prototype test text widget material
	 */
	protected Material prototypeTestingMessageWidgetMaterial = null;

	// protected TextWidget prototypeTestingMessageWidget2 = null;

	// protected Material prototypeTestingMessageWidgetMaterial2 = null;

	/**
	 * Prototype test prism widget
	 */
	protected PrismWidget prismWidget = null;

	/**
	 * Prototype test prism widget material
	 */
	protected Material prismWidgetMaterial = null;

	/**
	 * Prototype test apply button widget
	 */
	protected AbstractPushbuttonWidget applyButton = null;

	/**
	 * The exit button widget
	 */
	protected AbstractPushbuttonWidget exitButton = null;

	/**
	 * The keyboard focus rotation for the metaverse application
	 */
	protected HorizontalFocusRotation focus = new HorizontalFocusRotation();

	/**
	 * Default Constructor
	 */
	public PrototypeTestingAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return ("Prototype Testing");
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"Prototype Testing Response\";\n");
		gc.appendJs("}\n");
	}

	@Override
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		gc.appendJs("	scene.background = new THREE.Color(0x77fefe);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		final IFont fontK = theme.getButtonFont();

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xff8000");
			prismWidgetMaterial = MeshPhongMaterial.create(gc, minit);

			final PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
			pwi.setMaterial(prismWidgetMaterial);
			prismWidget = PrismWidget.create(gc, pwi);
			prismWidget.addToScene(gc);
		}

		{
			TextWidgetInitializer twA = new TextWidgetInitializer(theme);
			twA.setDisplayText("Running Prototype Testing");
			twA.setPosition(new Vector3(0.0, 3.5 + 1.0, -7.0));

			titleTextWidget = TextWidget.create(gc, twA);
			titleTextWidget.addToScene(gc);
		}

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0x00ff00");
			prototypeTestingMessageWidgetMaterial = MeshPhongMaterial.create(gc, minit);

			TextWidgetInitializer twB = new TextWidgetInitializer(theme);
			twB.setFont(fontK);
			twB.setMaterial(prototypeTestingMessageWidgetMaterial);
			twB.setDisplayText("Protoy :_{}<>#$%^&*");
			twB.setPosition(new Vector3(0.0, 3.5, -7.0));

			prototypeTestingMessageWidget = TextWidget.create(gc, twB);
			prototypeTestingMessageWidget.addToScene(gc);

			prototypeTestingMessageWidgetMaterial.setOwningObject(prototypeTestingMessageWidget);
		}

		/*
		 * { MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(
		 * gc.getCurrentTheme(), "0x000000"); prototypeTestingMessageWidgetMaterial2 =
		 * MeshPhongMaterial.create(gc, minit);
		 * 
		 * TextWidgetInitializer twB = new TextWidgetInitializer( theme ); twB.setFont(
		 * fontK ); twB.setMaterial(prototypeTestingMessageWidgetMaterial2);
		 * twB.setDisplayText( "Protoy :_{}<>#$%^&*" ); twB.setPosition( new Vector3(
		 * 0.0 , 3.5 - 1.0 , -7.0 )); twB.setFontSize( 25.0 );
		 * 
		 * prototypeTestingMessageWidget2 = TextWidget.create( gc , twB );
		 * prototypeTestingMessageWidget2.addToScene( gc ); }
		 */

		{
			PushbuttonWidgetInitializer bwi = new PushbuttonWidgetInitializer(theme);
			bwi.getLabelText().setDisplayText("Apply");
			bwi.setPosition(new Vector3(0.0, 3.5 - 1.0, -7.0));
			bwi.setFocus(true);
			applyButton = theme.createPushbutton(gc, bwi);
			applyButton.addToScene(gc);

			focus.add("Apply Button", applyButton, new IEventHandler() {

				@Override
				public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

					System.out.println("Handle Apply");

				}

			});
		}

		{
			PushbuttonWidgetInitializer bwi = new PushbuttonWidgetInitializer(theme);
			bwi.getLabelText().setDisplayText("Exit");
			bwi.setPosition(new Vector3(2.0, 3.5 - 1.0, -7.0));
			bwi.setFocus(false);
			exitButton = theme.createPushbutton(gc, bwi);
			exitButton.addToScene(gc);

			focus.add("Exit Button", exitButton, new IEventHandler() {

				@Override
				public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

					System.out.println("Handle Exit");

					MetaverseApplication grandCentral = SessionStore.applications
							.get(EventHandlerNames.GRAND_CENTRAL_STATION_APP);

					System.out.println("Changing To App " + (grandCentral.getApplicationName()));

					gc.changeApplication(grandCentral, e);

				}

			});
		}

	}

	@Override
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Prototype Testing App Responding To Keydown Event");

		focus.dispatchEvent(e, sess, gc);

		/*
		 * String keyCode = e.getKeyCode();
		 * 
		 * if( keyCode.equals( KeyNames.SPACE_KEY ) ) { MetaverseApplication
		 * grandCentral = SessionStore.applications.get(
		 * EventHandlerNames.GRAND_CENTRAL_STATION_APP );
		 * 
		 * System.out.println( "Changing To App " + ( grandCentral.getApplicationName()
		 * ) );
		 * 
		 * gc.changeApplication( grandCentral , e); }
		 */

	}

	@Override
	public void stopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Prototype Testing App Responding To Stop Event");

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		{
			prototypeTestingMessageWidget.removeFromScene(gc);

			prototypeTestingMessageWidget.dispose(gc);
		}

		{
			prismWidget.removeFromScene(gc);

			prismWidget.dispose(gc);

			prismWidgetMaterial.dispose(gc);
		}

		/*
		 * { prototypeTestingMessageWidget2.removeFromScene(gc);
		 * 
		 * prototypeTestingMessageWidget2.dispose(gc);
		 * 
		 * 
		 * prototypeTestingMessageWidgetMaterial2.dispose(gc); }
		 */

		{
			applyButton.removeFromScene(gc);

			applyButton.dispose(gc);
		}

		{
			exitButton.removeFromScene(gc);

			exitButton.dispose(gc);
		}

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is A Prototype Testing Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
