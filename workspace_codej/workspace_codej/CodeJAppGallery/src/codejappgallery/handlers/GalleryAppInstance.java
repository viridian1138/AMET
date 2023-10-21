
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

package codejappgallery.handlers;

import java.util.ArrayList;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import codejcore.focus.HorizontalFocusRotation;
import codejcore.interfaces.IEventHandler;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.AbstractScrollbarWidget;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.KeyNames;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.ScrollbarWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * Class for instances of a Metaverse Application containing a gallery of other
 * spaces
 * 
 * @author tgreen
 *
 */
public class GalleryAppInstance extends MetaverseApplicationInstance {

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * Widget for displaying the current app
	 */
	protected TextWidget selectorTextWidget = null;

	/**
	 * Vertical scroll bar for scrolling through the gallery collection
	 */
	protected AbstractScrollbarWidget verticalScrollbarWidget = null;

	/**
	 * The current index into the gallery collection
	 */
	protected int currentIndex = 0;

	/**
	 * The sorted list of metaverse applications in the gallery collection
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
	public GalleryAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return ("Gallery");
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is A Gallery Response\";\n");
		gc.appendJs("}\n");
	}

	@Override
	public void dispatchStartEvent(UiEvent ev, SessionDataApplicationToken sess, GraphicsContext gc) {

		sortedApps = new ArrayList<MetaverseApplication>();

		TreeMap<String, MetaverseApplication> tm = new TreeMap<String, MetaverseApplication>();

		gc.appendJs("	scene.background = new THREE.Color(0x77b5fe);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		{
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			final String EXTENSION_POINT_NAME = "CodeJGallery.Participant";
			IConfigurationElement[] config = registry.getConfigurationElementsFor(EXTENSION_POINT_NAME);
			System.out.println(EXTENSION_POINT_NAME + " Registry Length : " + (config.length));
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating Extension " + e);
				try {
					final Object o = e.createExecutableExtension("class");
					System.out.println("Evaluating Executable Extension " + o);
					if (o instanceof MetaverseApplication) {
						final MetaverseApplication app = (MetaverseApplication) (o);
						final String name = app.getApplicationName();
						System.out.println("Adding App " + name);
						tm.put(name, app);
						System.out.println("Added App " + name);
					}
				} catch (Throwable ex) {
					ex.printStackTrace(System.out);
				}
			}

		}

		for (MetaverseApplication app : tm.values()) {
			sortedApps.add(app);
		}

		if ((currentIndex >= 0) && (currentIndex < sortedApps.size())) {
			System.out.println("Starting With App " + (sortedApps.get(currentIndex).getApplicationName()));

			final String appName = sortedApps.get(currentIndex).getApplicationName();

			{
				TextWidgetInitializer twA = new TextWidgetInitializer(theme);
				twA.setDisplayText("Gallery");
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

					System.out.println("Gallery Attempting App Start");

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
						System.out.println("Gallery Attempting App Increment");

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
						System.out.println("Gallery Attempting App Decrement");

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

	}

	@Override
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Gallery App Responding To Keydown Event");

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

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is An Gallery Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
