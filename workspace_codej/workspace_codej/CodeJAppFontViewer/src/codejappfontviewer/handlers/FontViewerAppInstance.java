
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

package codejappfontviewer.handlers;

import java.util.ArrayList;
import java.util.TreeMap;

import codejcore.arch.SessionStore;
import codejcore.focus.HorizontalFocusRotation;
import codejcore.interfaces.IEventHandler;
import codejcore.interfaces.IFont;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
// import codejappgrandcentralstationnormmap.handlers.*;
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
 * Class for instances of a font viewer Metaverse Application
 * 
 * @author tgreen
 *
 */
public class FontViewerAppInstance extends MetaverseApplicationInstance {

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * Widget for displaying the current font
	 */
	protected TextWidget fontTextWidget = null;

	/**
	 * Widget for texture map used to display a representation of the ground
	 */
	protected GroundPlaneWidget groundPlaneWidget = null;

	/**
	 * Vertical scroll bar for scrolling through the fonts
	 */
	protected AbstractScrollbarWidget verticalScrollbarWidget = null;

	/**
	 * The current index into the font list
	 */
	protected int currentIndex = 0;

	/**
	 * The sorted list of fonts
	 */
	protected ArrayList<IFont> sortedFonts = null;

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
	public FontViewerAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return ("Font Viewer");
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is A Font Viewer Response\";\n");
		gc.appendJs("}\n");
	}

	@Override
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		sortedFonts = new ArrayList<IFont>();

		TreeMap<String, IFont> tm = new TreeMap<String, IFont>();

		// INormMap norm = new NormMap();

		gc.appendJs("	scene.background = new THREE.Color(0x77fefe);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		for (IFont fnt : SessionStore.fonts.values()) {
			tm.put(fnt.getFontName(), fnt);
		}

		for (IFont fnt : tm.values()) {
			sortedFonts.add(fnt);
		}

		if ((currentIndex >= 0) && (currentIndex < sortedFonts.size())) {
			System.out.println("Starting With Font " + (sortedFonts.get(currentIndex).getFontName()));

			final String fontName = sortedFonts.get(currentIndex).getFontName();

			final IFont fnt = sortedFonts.get(currentIndex);

			{
				TextWidgetInitializer twA = new TextWidgetInitializer(theme);
				twA.setDisplayText("Running Font Viewer");
				twA.setPosition(new Vector3(0.0, 3.5 + 1.0, -7.0));

				titleTextWidget = TextWidget.create(gc, twA);
				titleTextWidget.addToScene(gc);
			}

			{
				TextWidgetInitializer twB = new TextWidgetInitializer(theme);
				twB.setFont(fnt);
				twB.setDisplayText(fontName);
				twB.setPosition(new Vector3(0.0, 3.5, -7.0));

				fontTextWidget = TextWidget.create(gc, twB);
				fontTextWidget.addToScene(gc);
			}

		}

		{
			PushbuttonWidgetInitializer bwi = new PushbuttonWidgetInitializer(theme);
			bwi.getLabelText().setDisplayText("Exit");
			bwi.setPosition(new Vector3(2.0, 3.5 - 1.0, -7.0));
			bwi.setFocus(true);
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

		{
			final ScrollbarWidgetInitializer pwi = new ScrollbarWidgetInitializer(theme);
			pwi.setMinScroll(0);
			pwi.setMaxScroll(sortedFonts.size());
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
						System.out.println("Font Viewer Attempting Font Increment");

						final IFont prevFnt = sortedFonts.get(currentIndex);

						currentIndex++;

						if (currentIndex >= sortedFonts.size()) {
							currentIndex = 0;
						}

						if ((currentIndex >= 0) && (currentIndex < sortedFonts.size())) {
							System.out.println("Incremented To Font " + (sortedFonts.get(currentIndex).getFontName()));

							final String fontName = sortedFonts.get(currentIndex).getFontName();

							final IFont fnt = sortedFonts.get(currentIndex);

							final int arrivedCurrentIndex = currentIndex;

							fontTextWidget.removeFromScene(gc);

							fontTextWidget.dispose(gc);

							{
								TextWidgetInitializer twB = new TextWidgetInitializer(theme);
								twB.setFont(fnt);
								twB.setDisplayText(fontName);
								twB.setPosition(new Vector3(0.0, 3.5, -7.0));

								fontTextWidget = TextWidget.create(gc, twB);
								fontTextWidget.addToScene(gc);
							}

							{
								verticalScrollbarWidget.setLocation(0, sortedFonts.size(), arrivedCurrentIndex,
										arrivedCurrentIndex + 1, gc);
							}

						}
					}

					if (keyCode.equals(KeyNames.ARROW_UP_KEY)) {
						System.out.println("Font Viewer Attempting Font Decrement");

						final IFont prevFnt = sortedFonts.get(currentIndex);

						currentIndex--;

						if (currentIndex < 0) {
							currentIndex = sortedFonts.size() - 1;
						}

						if ((currentIndex >= 0) && (currentIndex < sortedFonts.size())) {
							System.out.println("Decremented To Font " + (sortedFonts.get(currentIndex).getFontName()));

							final String fontName = sortedFonts.get(currentIndex).getFontName();

							final IFont fnt = sortedFonts.get(currentIndex);

							final int arrivedCurrentIndex = currentIndex;

							fontTextWidget.removeFromScene(gc);

							fontTextWidget.dispose(gc);

							{
								TextWidgetInitializer twB = new TextWidgetInitializer(theme);
								twB.setFont(fnt);
								twB.setDisplayText(fontName);
								twB.setPosition(new Vector3(0.0, 3.5, -7.0));

								fontTextWidget = TextWidget.create(gc, twB);
								fontTextWidget.addToScene(gc);
							}

							{
								verticalScrollbarWidget.setLocation(0, sortedFonts.size(), arrivedCurrentIndex,
										arrivedCurrentIndex + 1, gc);
							}

						}
					}

				}

			});
		}

		// groundPlaneWidget = GroundPlaneWidget.create( gc , norm );
		// groundPlaneWidget.addToScene( gc );

	}

	@Override
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Font Viewer App Responding To Keydown Event");

		focus.dispatchEvent(e, sess, gc);

	}

	@Override
	public void stopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		{
			verticalScrollbarWidget.removeFromScene(gc);

			verticalScrollbarWidget.dispose(gc);
		}

		{
			exitButton.removeFromScene(gc);

			exitButton.dispose(gc);
		}

		{
			fontTextWidget.removeFromScene(gc);

			fontTextWidget.dispose(gc);
		}

		/*
		 * { groundPlaneWidget.removeFromScene(gc);
		 * 
		 * groundPlaneWidget.dispose(gc); }
		 */

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"This Is A Font Viewer Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
