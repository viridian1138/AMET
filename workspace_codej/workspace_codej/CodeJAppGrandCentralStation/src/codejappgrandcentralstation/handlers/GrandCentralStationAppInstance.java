
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

package codejappgrandcentralstation.handlers;

import java.util.ArrayList;
import java.util.TreeMap;

import codejappgrandcentralstationnormmap.handlers.NormMap;
import codejcore.arch.SessionStore;
import codejcore.focus.HorizontalFocusRotation;
import codejcore.interfaces.IEventHandler;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.INormMap;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.AbstractScrollbarWidget;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.GroundPlaneWidget;
import codejcore.widgets.KeyNames;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.ScrollbarWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * Class for instances of a central starting point Metaverse Application
 * 
 * @author tgreen
 *
 */
public class GrandCentralStationAppInstance extends MetaverseApplicationInstance {

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * Widget for displaying the current metaverse application
	 */
	protected TextWidget selectorTextWidget = null;

	/**
	 * Widget for texture map used to display a representation of the ground
	 */
	protected GroundPlaneWidget groundPlaneWidget = null;

	/**
	 * Vertical scroll bar for scrolling through the metaverse applications
	 */
	protected AbstractScrollbarWidget verticalScrollbarWidget = null;

	/**
	 * The current index into the metaverse application list
	 */
	protected int currentIndex = 0;

	/**
	 * The sorted list of metaverse applications
	 */
	ArrayList<MetaverseApplication> sortedApps = null;

	/**
	 * The apply button widget
	 */
	protected AbstractPushbuttonWidget applyButton = null;

	/**
	 * The keyboard focus rotation for the metaverse application
	 */
	protected HorizontalFocusRotation focus = new HorizontalFocusRotation();

	/**
	 * Default Constructor
	 */
	public GrandCentralStationAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return (EventHandlerNames.GRAND_CENTRAL_STATION_APP);
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is A Grand Central Station Response\";\n");
		gc.appendJs("}\n");
	}

	@Override
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		sortedApps = new ArrayList<MetaverseApplication>();

		TreeMap<String, MetaverseApplication> tm = new TreeMap<String, MetaverseApplication>();

		INormMap norm = new NormMap();

		gc.appendJs("	scene.background = new THREE.Color(0x77b5fe);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		for (MetaverseApplication app : SessionStore.applications.values()) {
			tm.put(app.getApplicationName(), app);
		}

		for (MetaverseApplication app : tm.values()) {
			sortedApps.add(app);
		}

		if ((currentIndex >= 0) && (currentIndex < sortedApps.size())) {
			System.out.println("Starting With App " + (sortedApps.get(currentIndex).getApplicationName()));

			final String appName = sortedApps.get(currentIndex).getApplicationName();

			{
				TextWidgetInitializer twA = new TextWidgetInitializer(theme);
				twA.setDisplayText("Grand Central Station");
				twA.setPosition(new Vector3(0.0, 3.5 + 1.0, -7.0));

				titleTextWidget = TextWidget.create(gc, twA);
				titleTextWidget.addToScene(gc);
			}

			{
				TextWidgetInitializer twB = new TextWidgetInitializer(theme);
				twB.setDisplayText(appName);
				twB.setPosition(new Vector3(0.0, 3.5, -7.0));

				selectorTextWidget = TextWidget.create(gc, twB);
				selectorTextWidget.addToScene(gc);
			}

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

					System.out.println("Grand Central Attempting App Start");

					if ((currentIndex >= 0) && (currentIndex < sortedApps.size())) {
						System.out.println("Changing To App " + (sortedApps.get(currentIndex).getApplicationName()));

						gc.changeApplication(sortedApps.get(currentIndex), e);
					}

				}

			});
		}

		{
			final ScrollbarWidgetInitializer pwi = new ScrollbarWidgetInitializer(theme);
			pwi.setMinScroll(0);
			pwi.setMaxScroll(sortedApps.size());
			pwi.setStartScroll(currentIndex);
			pwi.setEndScroll(currentIndex + 1);
			verticalScrollbarWidget = theme.createVerticalScrollbar(gc, pwi);
			verticalScrollbarWidget.addToScene(gc);

			focus.add("Vertical Scrollbar", verticalScrollbarWidget, new IEventHandler() {

				@Override
				public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

					System.out.println("Handle Scroll");

					final IMetaverseTheme theme = gc.getCurrentTheme();

					String keyCode = e.getKeyCode();

					if (keyCode.equals(KeyNames.ARROW_DOWN_KEY)) {
						System.out.println("Grand Central Attempting App Increment");

						currentIndex++;

						if (currentIndex >= sortedApps.size()) {
							currentIndex = 0;
						}

						if ((currentIndex >= 0) && (currentIndex < sortedApps.size())) {
							System.out.println(
									"Incremented To App " + (sortedApps.get(currentIndex).getApplicationName()));

							final String appName = sortedApps.get(currentIndex).getApplicationName();

							final int arrivedCurrentIndex = currentIndex;

							selectorTextWidget.setDisplayText(appName, gc);

							{
								verticalScrollbarWidget.setLocation(0, sortedApps.size(), arrivedCurrentIndex,
										arrivedCurrentIndex + 1, gc);
							}

						}
					}

					if (keyCode.equals(KeyNames.ARROW_UP_KEY)) {
						System.out.println("Grand Central Attempting App Decrement");

						currentIndex--;

						if (currentIndex < 0) {
							currentIndex = sortedApps.size() - 1;
						}

						if ((currentIndex >= 0) && (currentIndex < sortedApps.size())) {
							System.out.println(
									"Decremented To App " + (sortedApps.get(currentIndex).getApplicationName()));

							final String appName = sortedApps.get(currentIndex).getApplicationName();

							final int arrivedCurrentIndex = currentIndex;

							selectorTextWidget.setDisplayText(appName, gc);

							{
								verticalScrollbarWidget.setLocation(0, sortedApps.size(), arrivedCurrentIndex,
										arrivedCurrentIndex + 1, gc);
							}

						}
					}

				}

			});
		}

		groundPlaneWidget = GroundPlaneWidget.create(gc, norm);
		groundPlaneWidget.addToScene(gc);

	}

	@Override
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Grand Central Station App Responding To Keydown Event");

		focus.dispatchEvent(e, sess, gc);

	}

	@Override
	public void stopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		{
			selectorTextWidget.removeFromScene(gc);

			selectorTextWidget.dispose(gc);
		}

		{
			verticalScrollbarWidget.removeFromScene(gc);

			verticalScrollbarWidget.dispose(gc);
		}

		{
			applyButton.removeFromScene(gc);

			applyButton.dispose(gc);
		}

		{
			groundPlaneWidget.removeFromScene(gc);

			groundPlaneWidget.dispose(gc);
		}

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is An Grand Central Station Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
