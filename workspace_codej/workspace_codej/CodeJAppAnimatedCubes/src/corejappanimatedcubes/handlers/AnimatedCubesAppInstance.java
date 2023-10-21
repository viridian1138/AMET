
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

package corejappanimatedcubes.handlers;

import java.util.Random;

import codejappanimatedcubesnormmap.handlers.NormMap;
import codejcore.arch.SessionStore;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.INormMap;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.BoxWidget;
import codejcore.widgets.BoxWidgetInitializer;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.GroundPlaneWidget;
import codejcore.widgets.KeyNames;
import codejcore.widgets.Material;
import codejcore.widgets.MeshPhongMaterial;
import codejcore.widgets.MeshPhongMaterialInitializer;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * Class for instances of a Metaverse Application displaying a set of animated
 * cubes
 * 
 * @author tgreen
 *
 */
public class AnimatedCubesAppInstance extends MetaverseApplicationInstance {

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * The exit button widget
	 */
	protected AbstractPushbuttonWidget exitButton = null;

	/**
	 * Widget for the first animated box
	 */
	protected BoxWidget animatedBoxWidgetA = null;

	/**
	 * Material for the first animated box
	 */
	protected Material animatedBoxWidgetAMaterial = null;

	/**
	 * Widget for the second animated box
	 */
	protected BoxWidget animatedBoxWidgetB = null;

	/**
	 * Material for the second animated box
	 */
	protected Material animatedBoxWidgetBMaterial = null;

	/**
	 * Widget for the third animated box
	 */
	protected BoxWidget animatedBoxWidgetC = null;

	/**
	 * Material for the third animated box
	 */
	protected Material animatedBoxWidgetCMaterial = null;

	/**
	 * Widget for texture map used to display a representation of the ground
	 */
	protected GroundPlaneWidget groundPlaneWidget = null;

	/**
	 * Default Constructor
	 */
	public AnimatedCubesAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return ("Animated Cubes");
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is A Animated Cubes Response\";\n");
		gc.appendJs("}\n");
	}

	@Override
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		INormMap norm = new NormMap();

		gc.appendJs("	scene.background = new THREE.Color(0x77fefe);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xff2222");
			animatedBoxWidgetAMaterial = MeshPhongMaterial.create(gc, minit);

			final BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(animatedBoxWidgetAMaterial);
			animatedBoxWidgetA = BoxWidget.create(gc, bwi);
			animatedBoxWidgetA.addToScene(gc);
		}

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0x22ff22");
			animatedBoxWidgetBMaterial = MeshPhongMaterial.create(gc, minit);

			final BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(animatedBoxWidgetBMaterial);
			animatedBoxWidgetB = BoxWidget.create(gc, bwi);
			animatedBoxWidgetB.addToScene(gc);
		}

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0x2222ff");
			animatedBoxWidgetCMaterial = MeshPhongMaterial.create(gc, minit);

			final BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(animatedBoxWidgetCMaterial);
			animatedBoxWidgetC = BoxWidget.create(gc, bwi);
			animatedBoxWidgetC.addToScene(gc);
		}

		{
			TextWidgetInitializer twA = new TextWidgetInitializer(theme);
			twA.setDisplayText("Running Animated Cubes");

			titleTextWidget = TextWidget.create(gc, twA);
			titleTextWidget.addToScene(gc);
		}

		{
			PushbuttonWidgetInitializer bwi = new PushbuttonWidgetInitializer(theme);
			bwi.getLabelText().setDisplayText("Exit");
			bwi.setPosition(new Vector3(2.0 + 3.0, 3.5 - 1.0, -7.0));
			bwi.setFocus(true);
			exitButton = theme.createPushbutton(gc, bwi);

			exitButton.addToScene(gc);
		}

		groundPlaneWidget = GroundPlaneWidget.create(gc, norm);
		groundPlaneWidget.addToScene(gc);

		gc.generateTimeout(500);

		System.out.println("Event Gen: ");
		gc.printJs();

	}

	/**
	 * Random number generator
	 */
	Random rand = new Random(1234);

	@Override
	public void dispatchTimeoutEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Animated Cubes App Responding To Timeout Event");

		Vector3 vectA = new Vector3(0 + 2.0 * (rand.nextDouble()), 3 - 2 + 2.0 * (rand.nextDouble()), -6.5);

		animatedBoxWidgetA.setPosition(vectA, gc);

		Vector3 vectB = new Vector3(0 + 2.0 * (rand.nextDouble()), 3 - 2 + 2.0 * (rand.nextDouble()), -6.5);

		animatedBoxWidgetB.setPosition(vectB, gc);

		Vector3 vectC = new Vector3(0 + 2.0 * (rand.nextDouble()), 3 - 2 + 2.0 * (rand.nextDouble()), -6.5);

		animatedBoxWidgetC.setPosition(vectC, gc);

		gc.generateTimeout(500);

	}

	@Override
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Animated Cubes App Responding To Keydown Event");

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

		System.out.println("Animated Cubes App Responding To Stop Event");

		animatedBoxWidgetA.removeFromScene(gc);

		animatedBoxWidgetB.removeFromScene(gc);

		animatedBoxWidgetC.removeFromScene(gc);

		animatedBoxWidgetA.dispose(gc);

		animatedBoxWidgetB.dispose(gc);

		animatedBoxWidgetC.dispose(gc);

		animatedBoxWidgetAMaterial.dispose(gc);

		animatedBoxWidgetBMaterial.dispose(gc);

		animatedBoxWidgetCMaterial.dispose(gc);

		{
			exitButton.removeFromScene(gc);

			exitButton.dispose(gc);
		}

		{
			groundPlaneWidget.removeFromScene(gc);

			groundPlaneWidget.dispose(gc);
		}

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is An Animated Cubes Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
