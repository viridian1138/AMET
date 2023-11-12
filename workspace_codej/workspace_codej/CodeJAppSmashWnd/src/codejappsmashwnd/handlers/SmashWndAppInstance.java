
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
import codejcore.widgets.TetrahedronWidget;
import codejcore.widgets.TetrahedronWidgetInitializer;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.Material;
import codejcore.widgets.MeshPhongMaterial;
import codejcore.widgets.MeshPhongMaterialInitializer;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;
import codejcore.widgets.Widget;

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
	 * The far distance at which items are temporarily placed in meters.
	 */
	static final double TARGET_FAR_DIST = -10;
	
	
	/**
	 * The Y-Axis size of the jump hurdle in meters.
	 */
	static final double JUMP_HURDLE_SIZE_Y = 0.16;
	
	
	/**
	 * The size of the jump hurdle on the X and Z axes in meters.
	 */
	static final double JUMP_HURDLE_SIZE_XZ = 0.46;
	
	
	/**
	 * The axis size of the dumbbels in meters.
	 */
	static final double DUMBBELL_SIZE = 0.16;
	
	
	/**
	 * The axis size of the punch targets in meters.
	 */
	static final double TARGET_PUNCH_SIZE = 0.16;
	
	
	/**
	 * The radius of the kick targets in meters.
	 */
	static final double TARGET_KICK_SIZE = 0.16;
	
	

	/**
	 * Width of the center bucket in meters, which is shared with all buckets
	 */
	static final double BUCKET_L2_WIDTH = 1;
	
	/**
	 * The maximum number of punch/kick targets
	 */
	static final int MAX_TARGETS = 18;
	
	/**
	 * The maximum number of dumbbels
	 */
	static final int MAX_DUMBBELS = 6;
	
	/**
	 * The maximum number of jump hurdles
	 */
	static final int MAX_JUMP_HURDLES = 6;
	
	/**
	 * The maximum number of dodge columns
	 */
	static final int MAX_DODGE_COLUMNS = 2;

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * Widget for displaying the SmashWnd run time
	 */
	protected TextWidget smashWndTimeTextWidget = null;

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
	 * Material for the right targets
	 */
	protected Material targetMaterialA = null;

	/**
	 * Material for the left targets
	 */
	protected Material targetMaterialB = null;

	/**
	 * Material for the dumbbels
	 */
	protected Material dumbbellMaterialA = null;

	/**
	 * Material for the jump hurdles
	 */
	protected Material jumpHurdleMaterialA = null;

	
	/**
	 * The current SmashWnd run time in seconds
	 */
	protected int currentSmashWndTimeSeconds = 0;

	/**
	 * The start button widget
	 */
	protected AbstractPushbuttonWidget startButton = null;

	/**
	 * The exit button widget
	 */
	protected AbstractPushbuttonWidget exitButton = null;

	/**
	 * The keyboard focus rotation for the metaverse application
	 */
	protected HorizontalFocusRotation focus = new HorizontalFocusRotation();
	
	
	/**
	 * An element that is moved by a strategy
	 * 
	 * @author tgreen
	 *
	 * @param <A> The widget moved by the element
	 */
	protected static class MovementElement<A extends Widget>
	{
		/**
		 * The widget moved by the element
		 */
		protected A widget;

		/**
		 * Gets the widget moved by the element
		 * 
		 * @return The widget moved by the element
		 */
		public A getWidget() {
			return widget;
		}

		/**
		 * Sets the widget moved by the element
		 * 
		 * @param widget The widget moved by the element
		 */
		public void setWidget(A widget) {
			this.widget = widget;
		}
		
		
		
	}
	
	
	/**
	 * The right punch targets
	 */
	protected final MovementElement<BoxWidget>[] targetsPunchA = (MovementElement<BoxWidget>[])( new MovementElement[ MAX_TARGETS ] );
	
	
	/**
	 * The left punch targets
	 */
	protected final MovementElement<BoxWidget>[] targetsPunchB = (MovementElement<BoxWidget>[])( new MovementElement[ MAX_TARGETS ] );
	
	
	/**
	 * The right kick targets
	 */
	protected final MovementElement<TetrahedronWidget>[] targetsKickA = (MovementElement<TetrahedronWidget>[])( new MovementElement[ MAX_TARGETS ] );
	
	
	/**
	 * The left kick targets
	 */
	protected final MovementElement<TetrahedronWidget>[] targetsKickB = (MovementElement<TetrahedronWidget>[])( new MovementElement[ MAX_TARGETS ] );
	
	
	/**
	 * The dumbbels
	 */
	protected final MovementElement<BoxWidget>[] dumbbelsA = (MovementElement<BoxWidget>[])( new MovementElement[ MAX_DUMBBELS ] );
	
	
	/**
	 * The jump hurdles
	 */
	protected final MovementElement<BoxWidget>[] jumpHurdlesA = (MovementElement<BoxWidget>[])( new MovementElement[ MAX_JUMP_HURDLES ] );
	
	
	/**
	 * The dodge columns
	 */
	protected final MovementElement<BoxWidget>[] dodgeColumnsA = (MovementElement<BoxWidget>[])( new MovementElement[ MAX_DODGE_COLUMNS ] );
	
	
	
	
	

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

	/**
	 * Generates a human-readable string for the SmashWnd run time
	 * 
	 * @param smashWndTimeSeconds The SmashWnd run time in seconds
	 * @return Human-readable string for the SmashWnd run time
	 */
	public String generateEventString(int smashWndTimeSeconds) {
		int rv = smashWndTimeSeconds % 60;
		String ret = "" + (smashWndTimeSeconds / 60) + ":" + (rv < 10 ? "0" : "") + rv;
		return (ret);
	}

	@Override
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		gc.appendJs("	scene.background = new THREE.Color(0x77b5fe);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		final IFont fontK = theme.getButtonFont();

		currentSmashWndTimeSeconds = 0;

		{
			TextWidgetInitializer twA = new TextWidgetInitializer(theme);
			twA.setDisplayText("Running Smash Wnd");
			twA.setPosition(new Vector3(0.0, 3.5 + 1.0, -7.0));

			titleTextWidget = TextWidget.create(gc, twA);
			titleTextWidget.addToScene(gc);
		}

		{
			TextWidgetInitializer twB = new TextWidgetInitializer(theme);
			twB.setDisplayText(generateEventString(currentSmashWndTimeSeconds));
			twB.setPosition(new Vector3(0.0, 3.5, -7.0));

			smashWndTimeTextWidget = TextWidget.create(gc, twB);
			smashWndTimeTextWidget.addToScene(gc);
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
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xe46a54");
			targetMaterialA = MeshPhongMaterial.create(gc, minit);

			targetMaterialA.setOwningObject(this);
		}

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xb4ecbb");
			targetMaterialB = MeshPhongMaterial.create(gc, minit);

			targetMaterialB.setOwningObject(this);
		}

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0x252729");
			dumbbellMaterialA = MeshPhongMaterial.create(gc, minit);

			dumbbellMaterialA.setOwningObject(this);
		}

		{
			MeshPhongMaterialInitializer minit = new MeshPhongMaterialInitializer(gc.getCurrentTheme(), "0xf1edc2");
			jumpHurdleMaterialA = MeshPhongMaterial.create(gc, minit);

			jumpHurdleMaterialA.setOwningObject(this);
		}
		
		
		{
			for( int cnt = 0 ; cnt < targetsPunchA.length ; cnt++ )
			{
				targetsPunchA[ cnt ] = new MovementElement<BoxWidget>();
				
				BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
				twB.setLength(new Vector3(TARGET_PUNCH_SIZE, TARGET_PUNCH_SIZE, TARGET_PUNCH_SIZE));
				twB.setMaterial(targetMaterialA);
				twB.setPosition(new Vector3(0, 1.5, TARGET_FAR_DIST));

				targetsPunchA[ cnt ].widget = BoxWidget.create(gc, twB);
				targetsPunchA[ cnt ].getWidget().addToScene(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < targetsPunchB.length ; cnt++ )
			{
				targetsPunchB[ cnt ] = new MovementElement<BoxWidget>();
				
				BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
				twB.setLength(new Vector3(TARGET_PUNCH_SIZE, TARGET_PUNCH_SIZE, TARGET_PUNCH_SIZE));
				twB.setMaterial(targetMaterialB);
				twB.setPosition(new Vector3(0, 1.5, TARGET_FAR_DIST));

				targetsPunchB[ cnt ].widget = BoxWidget.create(gc, twB);
				targetsPunchB[ cnt ].getWidget().addToScene(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < targetsKickA.length ; cnt++ )
			{
				targetsKickA[ cnt ] = new MovementElement<TetrahedronWidget>();
				
				TetrahedronWidgetInitializer twB = new TetrahedronWidgetInitializer(theme);
				twB.setRadius(TARGET_KICK_SIZE);
				twB.setMaterial(targetMaterialA);
				twB.setPosition(new Vector3(0, 1.5, TARGET_FAR_DIST));

				targetsKickA[ cnt ].widget = TetrahedronWidget.create(gc, twB);
				targetsKickA[ cnt ].getWidget().addToScene(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < targetsKickB.length ; cnt++ )
			{
				targetsKickB[ cnt ] = new MovementElement<TetrahedronWidget>();
				
				TetrahedronWidgetInitializer twB = new TetrahedronWidgetInitializer(theme);
				twB.setRadius(TARGET_KICK_SIZE);
				twB.setMaterial(targetMaterialB);
				twB.setPosition(new Vector3(0, 1.5, TARGET_FAR_DIST));

				targetsKickB[ cnt ].widget = TetrahedronWidget.create(gc, twB);
				targetsKickB[ cnt ].getWidget().addToScene(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < dumbbelsA.length ; cnt++ )
			{
				dumbbelsA[ cnt ] = new MovementElement<BoxWidget>();
				
				BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
				twB.setLength(new Vector3(DUMBBELL_SIZE, DUMBBELL_SIZE, DUMBBELL_SIZE));
				twB.setMaterial(dumbbellMaterialA);
				twB.setPosition(new Vector3(0, 1.5, TARGET_FAR_DIST));

				dumbbelsA[ cnt ].widget = BoxWidget.create(gc, twB);
				dumbbelsA[ cnt ].getWidget().addToScene(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < jumpHurdlesA.length ; cnt++ )
			{
				jumpHurdlesA[ cnt ] = new MovementElement<BoxWidget>();
				
				BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
				twB.setLength(new Vector3(JUMP_HURDLE_SIZE_XZ, JUMP_HURDLE_SIZE_Y, JUMP_HURDLE_SIZE_XZ));
				twB.setMaterial(jumpHurdleMaterialA);
				twB.setPosition(new Vector3(0, 1.5, TARGET_FAR_DIST));

				jumpHurdlesA[ cnt ].widget = BoxWidget.create(gc, twB);
				jumpHurdlesA[ cnt ].getWidget().addToScene(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < dodgeColumnsA.length ; cnt++ )
			{
				dodgeColumnsA[ cnt ] = new MovementElement<BoxWidget>();
				
				BoxWidgetInitializer twB = new BoxWidgetInitializer(theme);
				twB.setLength(new Vector3(BUCKET_L2_WIDTH, BUCKET_L2_HEIGHT, BUCKET_L2_WIDTH));
				twB.setMaterial(bucketL2Material);
				twB.setPosition(new Vector3(0, 1.5, TARGET_FAR_DIST));

				dodgeColumnsA[ cnt ].widget = BoxWidget.create(gc, twB);
				dodgeColumnsA[ cnt ].getWidget().addToScene(gc);
			}
		}
		
		

		{
			PushbuttonWidgetInitializer bwi = new PushbuttonWidgetInitializer(theme);
			bwi.getLabelText().setDisplayText("Start");
			bwi.setPosition(new Vector3(0.0, 3.5 - 1.0, -7.0));
			bwi.setFocus(true);
			startButton = theme.createPushbutton(gc, bwi);
			startButton.addToScene(gc);

			focus.add("Start Button", startButton, new IEventHandler() {

				@Override
				public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

					System.out.println("Handle Start");

					System.out.println("SmashWnd Attempting SmashWnd Start");

					gc.generateTimeout(1000);

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
	public void dispatchTimeoutEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("SmashWnd App Responding To Timeout Event");

		int smashWndTimeSeconds = currentSmashWndTimeSeconds;

		smashWndTimeSeconds++;

		currentSmashWndTimeSeconds = smashWndTimeSeconds;

		final int currentSmashWndTimeSeconds = smashWndTimeSeconds;

		smashWndTimeTextWidget.setDisplayText(generateEventString(currentSmashWndTimeSeconds), gc);

		gc.generateTimeout(1000);

	}

	@Override
	public void stopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Smash Wnd App Responding To Stop Event");

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		{
			smashWndTimeTextWidget.removeFromScene(gc);

			smashWndTimeTextWidget.dispose(gc);
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
			for( int cnt = 0 ; cnt < targetsPunchA.length ; cnt++ )
			{
				targetsPunchA[ cnt ].getWidget().removeFromScene(gc);
				targetsPunchA[ cnt ].getWidget().dispose(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < targetsPunchB.length ; cnt++ )
			{
				targetsPunchB[ cnt ].getWidget().removeFromScene(gc);
				targetsPunchB[ cnt ].getWidget().dispose(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < targetsKickA.length ; cnt++ )
			{
				targetsKickA[ cnt ].getWidget().removeFromScene(gc);
				targetsKickA[ cnt ].getWidget().dispose(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < targetsKickB.length ; cnt++ )
			{
				targetsKickB[ cnt ].getWidget().removeFromScene(gc);
				targetsKickB[ cnt ].getWidget().dispose(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < dumbbelsA.length ; cnt++ )
			{
				dumbbelsA[ cnt ].getWidget().removeFromScene(gc);
				dumbbelsA[ cnt ].getWidget().dispose(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < jumpHurdlesA.length ; cnt++ )
			{
				jumpHurdlesA[ cnt ].getWidget().removeFromScene(gc);
				jumpHurdlesA[ cnt ].getWidget().dispose(gc);
			}
		}
		
		
		{
			for( int cnt = 0 ; cnt < dodgeColumnsA.length ; cnt++ )
			{
				dodgeColumnsA[ cnt ].getWidget().removeFromScene(gc);
				dodgeColumnsA[ cnt ].getWidget().dispose(gc);
			}
		}
		
		

		{
			bucketL2Material.dispose(gc);
		}

		{
			targetMaterialA.dispose(gc);
		}

		{
			targetMaterialB.dispose(gc);
		}

		{
			dumbbellMaterialA.dispose(gc);
		}

		{
			jumpHurdleMaterialA.dispose(gc);
		}

		{
			startButton.removeFromScene(gc);

			startButton.dispose(gc);
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


