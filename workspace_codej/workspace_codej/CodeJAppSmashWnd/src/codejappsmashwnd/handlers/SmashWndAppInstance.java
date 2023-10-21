
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

package codejappsmashwnd.handlers;

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
import codejcore.widgets.BoxWidget;
import codejcore.widgets.BoxWidgetInitializer;
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
 * Class for instances of a Metaverse Application with elements resembling Smash
 * Box
 * 
 * @author tgreen
 *
 */
public class SmashWndAppInstance extends MetaverseApplicationInstance {

	/**
	 * The distance along the Z-axis from the center of the VR space to the buckets
	 * in meters.
	 */
	static final double BACK_DIST_BUCKET = -7;

	/**
	 * The X-Offset in meters between the center and extremal buckets
	 */
	static final double BUCKET_X_OFFSET = 3.8;

	/**
	 * Height of the center bucket in meters, which is shared with all buckets
	 */
	static final double BUCKET_L2_HEIGHT = 3;

	/**
	 * Width of the center bucket in meters, which is shared with all buckets
	 */
	static final double BUCKET_L2_WIDTH = 1;

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * Center bucket Widget
	 */
	protected BoxWidget bucketL2Widget = null;

	/**
	 * Material for the center Widget, which is shared with all bucket Widgets
	 */
	protected Material bucketL2Material = null;

	/**
	 * Left-most Bucket Widget
	 */
	protected BoxWidget bucketL1Widget = null;

	/**
	 * Right-most Bucket Widget
	 */
	protected BoxWidget bucketL3Widget = null;

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
	public SmashWndAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return ("Smash Wnd");
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"Smash Wnd Testing Response\";\n");
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
			twA.setDisplayText("Running Smash Wnd");
			twA.setPosition(new Vector3(0.0, 3.5 + 1.0, -7.0));

			titleTextWidget = TextWidget.create(gc, twA);
			titleTextWidget.addToScene(gc);
		}

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xf1edc2");
			bucketL2Material = MeshPhongMaterial.create(gc, minit);

			BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
			twB.setLength(new Vector3(BUCKET_L2_WIDTH, BUCKET_L2_HEIGHT, BUCKET_L2_WIDTH));
			twB.setMaterial(bucketL2Material);
			twB.setPosition(new Vector3(0, BUCKET_L2_HEIGHT - 2, BACK_DIST_BUCKET));

			bucketL2Widget = BoxWidget.create(gc, twB);
			bucketL2Widget.addToScene(gc);

			bucketL2Material.setOwningObject(this);
		}

		{
			BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
			twB.setLength(new Vector3(BUCKET_L2_WIDTH, BUCKET_L2_HEIGHT, BUCKET_L2_WIDTH));
			twB.setMaterial(bucketL2Material);
			twB.setPosition(new Vector3(-BUCKET_X_OFFSET, BUCKET_L2_HEIGHT - 2, BACK_DIST_BUCKET));

			bucketL1Widget = BoxWidget.create(gc, twB);
			bucketL1Widget.addToScene(gc);
		}

		{
			BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
			twB.setLength(new Vector3(BUCKET_L2_WIDTH, BUCKET_L2_HEIGHT, BUCKET_L2_WIDTH));
			twB.setMaterial(bucketL2Material);
			twB.setPosition(new Vector3(BUCKET_X_OFFSET, BUCKET_L2_HEIGHT - 2, BACK_DIST_BUCKET));

			bucketL3Widget = BoxWidget.create(gc, twB);
			bucketL3Widget.addToScene(gc);
		}

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

		System.out.println("Smash Wnd App Responding To Keydown Event");

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

		System.out.println("Smash Wnd App Responding To Stop Event");

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		{
			bucketL2Widget.removeFromScene(gc);

			bucketL2Widget.dispose(gc);
		}

		{
			bucketL1Widget.removeFromScene(gc);

			bucketL1Widget.dispose(gc);
		}

		{
			bucketL3Widget.removeFromScene(gc);

			bucketL3Widget.dispose(gc);
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
		gc.appendJs("tmpObj.zmsg=\"This Is A Smash Wnd Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
