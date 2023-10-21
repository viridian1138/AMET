
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

package codejthemeemerald.handlers;

import java.util.ArrayList;

import codejcore.interfaces.ISound;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractScrollbuttonWidget;
import codejcore.widgets.BoxWidget;
import codejcore.widgets.BoxWidgetInitializer;
import codejcore.widgets.Face3;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.PrismWidget;
import codejcore.widgets.PrismWidgetInitializer;
import codejcore.widgets.ScrollbuttonWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * The scroll-down button widget for the Emerald theme
 * 
 * @author tgreen
 *
 */
public class ScrollButtonDownWidget extends AbstractScrollbuttonWidget {

	/**
	 * The minimum Y-Coordinate of the scrollbar in the units of the Metaverse scene
	 * (meters above the floor). To be replaced by a configurable setting (future
	 * expansion).
	 */
	protected static final double MIN_HEIGHT = 0.1;

	/**
	 * The maximum Y-Coordinate of the scrollbar in the units of the Metaverse scene
	 * (meters above the floor). To be replaced by a configurable setting (future
	 * expansion).
	 */
	protected static final double MAX_HEIGHT = 5.5;

	/**
	 * The width of the scrollbar in the units of the Metaverse scene (meters). To
	 * be replaced by a configurable setting (future expansion).
	 */
	protected static final double WIDTH = 1.0;

	/**
	 * The percentage of the width of the scrollbar to use as the width of one of
	 * the races that defines the exterior of the scrollbar track
	 */
	protected static final double TRACK_WIDTH_PCT = 1.0 / 32.0;

	/**
	 * Prism for the downward-facing arrow of the button
	 */
	protected PrismWidget buttonArrowPrism;

	/**
	 * Whether the button has the keyboard focus
	 */
	protected boolean focus;

	/**
	 * Narrow box for race defining exterior near top of button
	 */
	protected BoxWidget trackAA;

	/**
	 * Narrow box for race defining exterior near top of button
	 */
	protected BoxWidget trackAB;

	/**
	 * Narrow box for race defining exterior near top of button
	 */
	protected BoxWidget trackAC;

	/**
	 * Narrow box for race defining exterior near top of button
	 */
	protected BoxWidget trackAD;

	/**
	 * Narrow box for race defining exterior near bottom of button
	 */
	protected BoxWidget trackBA;

	/**
	 * Prism that is conditionally displayed to highlight when the button has the
	 * keyboard focus
	 */
	protected PrismWidget hilightPrism;

	/**
	 * Constructor
	 * 
	 * @param gc  The graphics context in which to display the button
	 * @param lwi Object used to initialize the button
	 */
	private ScrollButtonDownWidget(GraphicsContext gc, ScrollbuttonWidgetInitializer lwi) {

		focus = lwi.isFocus();

		final Theme theme = (Theme) (gc.getCurrentTheme());

		double buttonHeight = WIDTH;

		double buttonOuterWidth = WIDTH * (1.0 - 2 * TRACK_WIDTH_PCT);

		double buttonOuterPrismWidth = 0.80 * buttonOuterWidth;

		double prismHalf = 0.5 * buttonOuterPrismWidth;

		double buttonRaceWidth = WIDTH * (TRACK_WIDTH_PCT);

		double buttonMinHeight = MIN_HEIGHT;

		double buttonMaxHeight = MIN_HEIGHT + buttonHeight;

		double buttonCenterY = (buttonMaxHeight + buttonMinHeight) / 2.0;

		double av = (WIDTH - buttonRaceWidth) / 2.0;

		{
			PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
			pwi.setMaterial(theme.getProtoMaterial(gc));

			ArrayList<Vector3> ar = new ArrayList<Vector3>();
			ar.add(new Vector3(6.0 - prismHalf, buttonCenterY + prismHalf, 0.0 - prismHalf));
			ar.add(new Vector3(6.0 + prismHalf, buttonCenterY + prismHalf, 0.0 - prismHalf));
			ar.add(new Vector3(6.0, buttonCenterY - prismHalf, 0.0 - prismHalf));
			ar.add(new Vector3(6.0 - prismHalf, buttonCenterY + prismHalf, 0.0 + prismHalf));
			ar.add(new Vector3(6.0 + prismHalf, buttonCenterY + prismHalf, 0.0 + prismHalf));
			ar.add(new Vector3(6.0, buttonCenterY - prismHalf, 0.0 + prismHalf));

			pwi.setVertices(ar);

			pwi.setPosition(new Vector3(0.0, 0.0, -6.5));

			buttonArrowPrism = PrismWidget.create(gc, pwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0, buttonCenterY + av, -6.5 - av));
			bwi.setLength(new Vector3(WIDTH, buttonRaceWidth, buttonRaceWidth));
			trackAA = BoxWidget.create(gc, bwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0, buttonCenterY + av, -6.5 + av));
			bwi.setLength(new Vector3(WIDTH, buttonRaceWidth, buttonRaceWidth));
			trackAB = BoxWidget.create(gc, bwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0 + av, buttonCenterY + av, -6.5));
			bwi.setLength(new Vector3(buttonRaceWidth, buttonRaceWidth, WIDTH));
			trackAC = BoxWidget.create(gc, bwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0 - av, buttonCenterY + av, -6.5));
			bwi.setLength(new Vector3(buttonRaceWidth, buttonRaceWidth, WIDTH));
			trackAD = BoxWidget.create(gc, bwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0, buttonCenterY - av, -6.5 - av));
			bwi.setLength(new Vector3(WIDTH, buttonRaceWidth, buttonRaceWidth));
			trackBA = BoxWidget.create(gc, bwi);
		}

		internalSetFocus(focus, gc);

	}

	@Override
	public void addPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, ISound snd) {
		buttonArrowPrism.addPositionalAudio(e, sess, gc, snd);

	}

	@Override
	public void playPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, ISound snd) {
		buttonArrowPrism.playPositionalAudio(e, sess, gc, snd);

	}

	/**
	 * Creates an instance of a button widget
	 * 
	 * @param gc  The graphics context in which to display the button
	 * @param pwi Object used to initialize the button
	 * @return An instance of a button widget
	 */
	public static AbstractScrollbuttonWidget create(GraphicsContext gc, ScrollbuttonWidgetInitializer pwi) {

		System.out.println("Creating Scroll Button Up Widget");

		return (new ScrollButtonDownWidget(gc, pwi));
	}

	@Override
	public void addToScene(GraphicsContext gc) {

		System.out.println("Adding To Scene");

		buttonArrowPrism.addToScene(gc);

		trackAA.addToScene(gc);

		trackAB.addToScene(gc);

		trackAC.addToScene(gc);

		trackAD.addToScene(gc);

		trackBA.addToScene(gc);

		if (focus) {
			hilightPrism.addToScene(gc);
		}
	}

	@Override
	public void dispose(GraphicsContext gc) {
		System.out.println("Disposing... " + this);

		buttonArrowPrism.dispose(gc);

		trackAA.dispose(gc);

		trackAB.dispose(gc);

		trackAC.dispose(gc);

		trackAD.dispose(gc);

		trackBA.dispose(gc);

		if (focus) {
			hilightPrism.dispose(gc);
		}

	}

	@Override
	public void removeFromScene(GraphicsContext gc) {
		System.out.println("Removing from scene... " + this);

		buttonArrowPrism.removeFromScene(gc);

		trackAA.removeFromScene(gc);

		trackAB.removeFromScene(gc);

		trackAC.removeFromScene(gc);

		trackAD.removeFromScene(gc);

		trackBA.removeFromScene(gc);

		if (focus) {
			hilightPrism.removeFromScene(gc);
		}

	}

	@Override
	public boolean getFocus() {
		return (focus);
	}

	/**
	 * Internal method to set whether the widget has the keyboard focus
	 * 
	 * @param in The focus state to set
	 * @param gc The graphics context in which to render setting the focus
	 */
	protected void internalSetFocus(boolean in, GraphicsContext gc) {

		final boolean changed = (focus != in);

		focus = in;

		if (changed) {
			if (in) {

				final Theme theme = (Theme) (gc.getCurrentTheme());

				double buttonHeight = WIDTH;

				double buttonOuterWidth = WIDTH * (1.0 - 2 * TRACK_WIDTH_PCT);

				double buttonOuterPrismWidth = 0.80 * buttonOuterWidth;

				double prismHalf = 0.5 * buttonOuterPrismWidth;

				double buttonRaceWidth = WIDTH * (TRACK_WIDTH_PCT);

				double buttonMinHeight = MIN_HEIGHT;

				double buttonMaxHeight = MIN_HEIGHT + buttonHeight;

				double buttonCenterY = (buttonMaxHeight + buttonMinHeight) / 2.0;

				double av = (WIDTH - buttonRaceWidth) / 2.0;

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getHilightMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(6.0 - prismHalf, buttonCenterY + prismHalf, 0.0 + prismHalf));
					ar.add(new Vector3(6.0 + prismHalf, buttonCenterY + prismHalf, 0.0 + prismHalf));
					ar.add(new Vector3(6.0, buttonCenterY - prismHalf, 0.0 + prismHalf));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, -6.5));

					hilightPrism = PrismWidget.create(gc, pwi);
				}

			} else {
				hilightPrism.removeFromScene(gc);
				hilightPrism.dispose(gc);
				hilightPrism = null;
			}
		}

	}

	@Override
	public void setFocus(boolean in, GraphicsContext gc) {

		final boolean changed = (focus != in);

		internalSetFocus(in, gc);

		if (changed && in) {
			hilightPrism.addToScene(gc);
		}

	}

}
