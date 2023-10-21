
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
import codejcore.widgets.AbstractScrollbarWidget;
import codejcore.widgets.AbstractScrollbuttonWidget;
import codejcore.widgets.BoxWidget;
import codejcore.widgets.BoxWidgetInitializer;
import codejcore.widgets.Face3;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.PrismWidget;
import codejcore.widgets.PrismWidgetInitializer;
import codejcore.widgets.ScrollbarWidgetInitializer;
import codejcore.widgets.ScrollbuttonWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * The vertical scrollbar widget for the Emerald theme
 * 
 * @author tgreen
 *
 */
public class VerticalScrollbarWidget extends AbstractScrollbarWidget {

	/**
	 * The minimum Y-Coordinate of the scrollbar in the units of the Metaverse scene
	 * (meters above the floor). To be replaced by a configurable setting (future
	 * expansion).
	 */
	protected static final double MIN_HEIGHT_OVERALL = 0.1;

	/**
	 * The maximum Y-Coordinate of the scrollbar in the units of the Metaverse scene
	 * (meters above the floor). To be replaced by a configurable setting (future
	 * expansion).
	 */
	protected static final double MAX_HEIGHT_OVERALL = 5.5;

	/**
	 * The width of the scrollbar in the units of the Metaverse scene (meters). To
	 * be replaced by a configurable setting (future expansion).
	 */
	protected static final double WIDTH = 1.0;

	/**
	 * The minimum Y-Coordinate of the scrollbar track in the units of the Metaverse
	 * scene (meters above the floor). To be replaced by a configurable setting
	 * (future expansion).
	 */
	protected static final double MIN_HEIGHT_TRACK = MIN_HEIGHT_OVERALL + WIDTH;

	/**
	 * The maximum Y-Coordinate of the scrollbar track in the units of the Metaverse
	 * scene (meters above the floor). To be replaced by a configurable setting
	 * (future expansion).
	 */
	protected static final double MAX_HEIGHT_TRACK = MAX_HEIGHT_OVERALL - WIDTH;

	/**
	 * The percentage of the width of the scrollbar to use as the width of one of
	 * the races that defines the exterior of the scrollbar track
	 */
	protected static final double TRACK_WIDTH_PCT = 1.0 / 32.0;

	/**
	 * Box widget representing the outer shell of the scrollbar thumb
	 */
	protected BoxWidget scrollbarThumbOuterShell;

	/**
	 * The up-arrow button of the scrollbar
	 */
	protected AbstractScrollbuttonWidget scrollUpButton;

	/**
	 * The down-arrow button of the scrollbar
	 */
	protected AbstractScrollbuttonWidget scrollDownButton;

	/**
	 * Whether the scrollbar has the keyboard focus
	 */
	protected boolean focus;

	/**
	 * The minimum value of the scrollbar
	 */
	protected double minScroll;

	/**
	 * The maximum value of the scrollbar
	 */
	protected double maxScroll;

	/**
	 * The start value of the scrollbar thumb
	 */
	protected double startScroll;

	/**
	 * The end value of the scrollbar thumb
	 */
	protected double endScroll;

	/**
	 * Narrow box for race defining exterior of scrollbar track
	 */
	protected BoxWidget trackAA;

	/**
	 * Narrow box for race defining exterior of scrollbar track
	 */
	protected BoxWidget trackAB;

	/**
	 * Narrow box for race defining exterior of scrollbar track
	 */
	protected BoxWidget trackAC;

	/**
	 * Narrow box for race defining exterior of scrollbar track
	 */
	protected BoxWidget trackAD;

	/**
	 * Prism that is conditionally displayed to highlight when the scrollbar has the
	 * keyboard focus
	 */
	protected PrismWidget hilightPrism;

	/**
	 * Calculates the height of the scrollbar thumb in the units of the Metaverse
	 * scene (meters above the floor).
	 * 
	 * @param minScroll   The minimum value of the scrollbar
	 * @param maxScroll   The maximum value of the scrollbar
	 * @param startScroll The start value of the scrollbar thumb
	 * @param endScroll   The end value of the scrollbar thumb
	 * @return The height of the scrollbar thumb in the units of the Metaverse scene
	 *         (meters above the floor).
	 */
	protected double calcThumbHeight(double minScroll, double maxScroll, double startScroll, double endScroll) {
		double pct = (endScroll - startScroll) / (maxScroll - minScroll);
		return (pct * (MAX_HEIGHT_TRACK - MIN_HEIGHT_TRACK));
	}

	/**
	 * Calculates the Y-coordinate of the center of the scrollbar thumb in the units
	 * of the Metaverse scene (meters above the floor).
	 * 
	 * @param minScroll   The minimum value of the scrollbar
	 * @param maxScroll   The maximum value of the scrollbar
	 * @param startScroll The start value of the scrollbar thumb
	 * @param endScroll   The end value of the scrollbar thumb
	 * @return The Y-coordinate of the center of the scrollbar thumb in the units of
	 *         the Metaverse scene (meters above the floor).
	 */
	protected double calcThumbCenterY(double minScroll, double maxScroll, double startScroll, double endScroll) {
		double posn = (startScroll + endScroll) / 2.0;
		double pct = (posn - minScroll) / (maxScroll - minScroll);
		return ((1.0 - pct) * (MAX_HEIGHT_TRACK - MIN_HEIGHT_TRACK) + MIN_HEIGHT_TRACK);
	}

	/**
	 * Constructor
	 * 
	 * @param gc  The graphics context in which to display the scrollbar
	 * @param pwi Object used to initialize the scrollbar
	 */
	private VerticalScrollbarWidget(GraphicsContext gc, ScrollbarWidgetInitializer pwi) {

		focus = pwi.isFocus();

		this.minScroll = pwi.getMinScroll();

		this.maxScroll = pwi.getMaxScroll();

		this.startScroll = pwi.getStartScroll();

		this.endScroll = pwi.getEndScroll();

		final Theme theme = (Theme) (gc.getCurrentTheme());

		double thumbHeight = this.calcThumbHeight(pwi.getMinScroll(), pwi.getMaxScroll(), pwi.getStartScroll(),
				pwi.getEndScroll());

		double thumbCenterY = this.calcThumbCenterY(pwi.getMinScroll(), pwi.getMaxScroll(), pwi.getStartScroll(),
				pwi.getEndScroll());

		double thumbOuterWidth = WIDTH * (1.0 - 2 * TRACK_WIDTH_PCT);

		double atrackHeight = MAX_HEIGHT_TRACK - MIN_HEIGHT_TRACK;

		double overallHeight = MAX_HEIGHT_OVERALL - MIN_HEIGHT_OVERALL;

		double trackRaceWidth = WIDTH * (TRACK_WIDTH_PCT);

		double atrackCenterY = (MAX_HEIGHT_TRACK + MIN_HEIGHT_TRACK) / 2.0;

		double overallCenterY = (MAX_HEIGHT_OVERALL + MIN_HEIGHT_OVERALL) / 2.0;

		double av = (WIDTH - trackRaceWidth) / 2.0;

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoMaterial(gc));
			bwi.setPosition(new Vector3(6.0, thumbCenterY, -6.5));
			bwi.setLength(new Vector3(thumbOuterWidth, thumbHeight, thumbOuterWidth));
			scrollbarThumbOuterShell = BoxWidget.create(gc, bwi);
		}

		{
			ScrollbuttonWidgetInitializer lwi = new ScrollbuttonWidgetInitializer(theme);
			lwi.setFocus(focus);
			scrollUpButton = theme.createVerticalScrollbuttonUp(gc, lwi);
		}

		{
			ScrollbuttonWidgetInitializer lwi = new ScrollbuttonWidgetInitializer(theme);
			lwi.setFocus(focus);
			scrollDownButton = theme.createVerticalScrollbuttonDown(gc, lwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0 + av, overallCenterY, -6.5 - av));
			bwi.setLength(new Vector3(trackRaceWidth, overallHeight, trackRaceWidth));
			trackAA = BoxWidget.create(gc, bwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0 - av, atrackCenterY, -6.5 + av));
			bwi.setLength(new Vector3(trackRaceWidth, atrackHeight, trackRaceWidth));
			trackAB = BoxWidget.create(gc, bwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0 + av, atrackCenterY, -6.5 + av));
			bwi.setLength(new Vector3(trackRaceWidth, atrackHeight, trackRaceWidth));
			trackAC = BoxWidget.create(gc, bwi);
		}

		{
			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoAltMaterial(gc));
			bwi.setPosition(new Vector3(6.0 - av, overallCenterY, -6.5 - av));
			bwi.setLength(new Vector3(trackRaceWidth, overallHeight, trackRaceWidth));
			trackAD = BoxWidget.create(gc, bwi);
		}

		internalSetFocus(focus, gc);

	}

	@Override
	public void addPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, ISound snd) {
		scrollbarThumbOuterShell.addPositionalAudio(e, sess, gc, snd);

	}

	@Override
	public void playPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, ISound snd) {
		scrollbarThumbOuterShell.playPositionalAudio(e, sess, gc, snd);

	}

	/**
	 * Creates an instance of a scrollbar widget
	 * 
	 * @param gc  The graphics context in which to display the scrollbar
	 * @param pwi Object used to initialize the scrollbar
	 * @return An instance of a scrollbar widget
	 */
	public static AbstractScrollbarWidget create(GraphicsContext gc, ScrollbarWidgetInitializer pwi) {

		System.out.println("Creating Scrollbar Widget");

		return (new VerticalScrollbarWidget(gc, pwi));
	}

	@Override
	public void addToScene(GraphicsContext gc) {

		System.out.println("Adding To Scene");

		scrollbarThumbOuterShell.addToScene(gc);

		scrollUpButton.addToScene(gc);

		scrollDownButton.addToScene(gc);

		trackAA.addToScene(gc);

		trackAB.addToScene(gc);

		trackAC.addToScene(gc);

		trackAD.addToScene(gc);

		if (focus) {
			hilightPrism.addToScene(gc);
		}
	}

	@Override
	public void dispose(GraphicsContext gc) {
		System.out.println("Disposing... " + this);

		scrollbarThumbOuterShell.dispose(gc);

		scrollUpButton.dispose(gc);

		scrollDownButton.dispose(gc);

		trackAA.dispose(gc);

		trackAB.dispose(gc);

		trackAC.dispose(gc);

		trackAD.dispose(gc);

		if (focus) {
			hilightPrism.dispose(gc);
		}

	}

	@Override
	public void removeFromScene(GraphicsContext gc) {
		System.out.println("Removing from scene... " + this);

		scrollbarThumbOuterShell.removeFromScene(gc);

		scrollUpButton.removeFromScene(gc);

		scrollDownButton.removeFromScene(gc);

		trackAA.removeFromScene(gc);

		trackAB.removeFromScene(gc);

		trackAC.removeFromScene(gc);

		trackAD.removeFromScene(gc);

		if (focus) {
			hilightPrism.removeFromScene(gc);
		}

	}

	@Override
	public void setLocation(double minScroll, double maxScroll, double startScroll, double endScroll,
			GraphicsContext gc) {

		this.minScroll = minScroll;

		this.maxScroll = maxScroll;

		this.startScroll = startScroll;

		this.endScroll = endScroll;

		final Theme theme = (Theme) (gc.getCurrentTheme());

		double thumbHeight = this.calcThumbHeight(minScroll, maxScroll, startScroll, endScroll);

		double thumbCenterY = this.calcThumbCenterY(minScroll, maxScroll, startScroll, endScroll);

		{
			BoxWidget prevScrollbarThumbOuterShell = scrollbarThumbOuterShell;

			BoxWidgetInitializer bwi = new BoxWidgetInitializer(theme);
			bwi.setMaterial(theme.getProtoMaterial(gc));
			bwi.setPosition(new Vector3(6.0, thumbCenterY, -6.5));
			bwi.setLength(new Vector3(1.0, thumbHeight, 1.0));
			scrollbarThumbOuterShell = BoxWidget.create(gc, bwi);

			scrollbarThumbOuterShell.addToScene(gc);

			prevScrollbarThumbOuterShell.removeFromScene(gc);

			prevScrollbarThumbOuterShell.dispose(gc);
		}

		if (focus) {
			hilightPrism.removeFromScene(gc);
			hilightPrism.dispose(gc);
			hilightPrism = null;
		}

		double trackRaceWidth = WIDTH * (TRACK_WIDTH_PCT);

		if (focus) {
			PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
			pwi.setMaterial(theme.getHilightMaterial(gc));

			double thumbOuterWidth = WIDTH * (1.0 - 2 * TRACK_WIDTH_PCT);

			final double thumbMinXtHeight = thumbCenterY - thumbHeight / 2.0;

			final double thumbMaxXtHeight = thumbCenterY + thumbHeight / 2.0;

			final double thumbMinXtXOffset = 6.0 - 0.5 * thumbOuterWidth;

			final double thumbMaxXtXOffset = 6.0 + 0.5 * thumbOuterWidth;

			final double thumbMidXtDepth = -6.5 + 0.5 * thumbOuterWidth + trackRaceWidth;

			ArrayList<Vector3> ar = new ArrayList<Vector3>();
			ar.add(new Vector3(thumbMaxXtXOffset - trackRaceWidth, thumbMinXtHeight + trackRaceWidth, thumbMidXtDepth));
			ar.add(new Vector3(thumbMinXtXOffset + trackRaceWidth, thumbMinXtHeight + trackRaceWidth, thumbMidXtDepth));
			ar.add(new Vector3(thumbMinXtXOffset + trackRaceWidth, thumbMaxXtHeight - trackRaceWidth, thumbMidXtDepth));
			ar.add(new Vector3(thumbMaxXtXOffset - trackRaceWidth, thumbMaxXtHeight - trackRaceWidth, thumbMidXtDepth));

			pwi.setVertices(ar);

			ArrayList<Face3> fa = new ArrayList<Face3>();
			fa.add(new Face3(0, 1, 2));
			fa.add(new Face3(2, 3, 0));

			pwi.setFaces(fa);

			pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

			hilightPrism = PrismWidget.create(gc, pwi);

			hilightPrism.addToScene(gc);
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

				double trackRaceWidth = WIDTH * (TRACK_WIDTH_PCT);

				final Theme theme = (Theme) (gc.getCurrentTheme());

				double thumbHeight = this.calcThumbHeight(minScroll, maxScroll, startScroll, endScroll);

				double thumbCenterY = this.calcThumbCenterY(minScroll, maxScroll, startScroll, endScroll);

				double thumbOuterWidth = WIDTH * (1.0 - 2 * TRACK_WIDTH_PCT);

				final double thumbMinXtXOffset = 6.0 - 0.5 * thumbOuterWidth;

				final double thumbMaxXtXOffset = 6.0 + 0.5 * thumbOuterWidth;

				final double thumbMinXtHeight = thumbCenterY - thumbHeight / 2.0;

				final double thumbMaxXtHeight = thumbCenterY + thumbHeight / 2.0;

				final double thumbMidXtDepth = -6.5 + 0.5 * thumbOuterWidth + trackRaceWidth;

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getHilightMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(thumbMaxXtXOffset - trackRaceWidth, thumbMinXtHeight + trackRaceWidth,
							thumbMidXtDepth));
					ar.add(new Vector3(thumbMinXtXOffset + trackRaceWidth, thumbMinXtHeight + trackRaceWidth,
							thumbMidXtDepth));
					ar.add(new Vector3(thumbMinXtXOffset + trackRaceWidth, thumbMaxXtHeight - trackRaceWidth,
							thumbMidXtDepth));
					ar.add(new Vector3(thumbMaxXtXOffset - trackRaceWidth, thumbMaxXtHeight - trackRaceWidth,
							thumbMidXtDepth));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));
					fa.add(new Face3(2, 3, 0));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

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

		scrollUpButton.setFocus(in, gc);

		scrollDownButton.setFocus(in, gc);

		final boolean changed = (focus != in);

		internalSetFocus(in, gc);

		if (changed && in) {
			hilightPrism.addToScene(gc);
		}

	}

}
