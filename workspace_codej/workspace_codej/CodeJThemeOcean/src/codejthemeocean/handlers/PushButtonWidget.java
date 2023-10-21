
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

package codejthemeocean.handlers;

import java.util.ArrayList;

import codejcore.bounds.Bounds;
import codejcore.bounds.IBoundsHandler;
import codejcore.interfaces.ISound;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.Face3;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.PrismWidget;
import codejcore.widgets.PrismWidgetInitializer;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * The push button widget for the Ocean theme
 * 
 * @author tgreen
 *
 */
public class PushButtonWidget extends AbstractPushbuttonWidget {

	/**
	 * The width of the button for determining border sizes. The actual button width
	 * is determined by the bounds of the label text. The width is in the units of
	 * the Metaverse scene (meters). To be replaced by a configurable setting
	 * (future expansion).
	 */
	protected static final double WIDTH = 1.0;

	/**
	 * The percentage of the width of the button to use as the width of the button
	 * border
	 */
	protected static final double TRACK_WIDTH_PCT = 2.0 / 32.0;

	/**
	 * Prism for one of the side borders of the button
	 */
	protected PrismWidget caPrism;

	/**
	 * Prism for one of the side borders of the button
	 */
	protected PrismWidget cbPrism;

	/**
	 * Prism for one of the side borders of the button
	 */
	protected PrismWidget ccPrism;

	/**
	 * Prism for one of the side borders of the button
	 */
	protected PrismWidget cdPrism;

	/**
	 * Prism for the front face of the button
	 */
	protected PrismWidget cePrism;

	/**
	 * Prism for the back face of the button
	 */
	protected PrismWidget cfPrism;

	/**
	 * Prism that is conditionally displayed to highlight when the button has the
	 * keyboard focus
	 */
	protected PrismWidget hilightPrism;

	/**
	 * Text widget displayed as the label for the button
	 */
	protected TextWidget labelTextWidget = null;

	/**
	 * Whether the button has the keyboard focus
	 */
	protected boolean focus = false;

	/**
	 * The spatial position for the button
	 */
	protected Vector3 position = null;

	/**
	 * Constructor
	 * 
	 * @param gc  The graphics context in which to display the button
	 * @param lwi Object used to initialize the button
	 */
	private PushButtonWidget(GraphicsContext gc, PushbuttonWidgetInitializer lwi) {

		final boolean foc = lwi.isFocus();

		final Theme theme = (Theme) (gc.getCurrentTheme());

		final double buttonRaceWidth = WIDTH * (TRACK_WIDTH_PCT);

		double buttonOuterWidth = WIDTH * (1.0 - 2 * TRACK_WIDTH_PCT);

		double buttonOuterPrismWidth = buttonOuterWidth;

		double prismHalf = 0.5 * buttonOuterPrismWidth;

		position = lwi.getPosition();

		double av = (WIDTH - buttonRaceWidth) / 2.0;

		{
			TextWidgetInitializer twB = lwi.getLabelText();
			twB.setPosition(position);

			labelTextWidget = TextWidget.create(gc, twB);
			labelTextWidget.addToScene(gc);
		}

		gc.handleBoundsGeneration(labelTextWidget.getTid(), new IBoundsHandler() {

			@Override
			public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, final Bounds bnd) {

				final double centerOffsetX = -0.5 * (bnd.getMin().getX() + bnd.getMax().getX());

				final double buttonMaxStXOffset = bnd.getMax().getX() + centerOffsetX;

				final double buttonMinStXOffset = bnd.getMin().getX() + centerOffsetX;

				final double buttonMaxStHeight = bnd.getMax().getY();

				final double buttonMinStHeight = bnd.getMin().getY();

				final double buttonMinStDepth = bnd.getMin().getZ();

				double buttonMinXtXOffset = position.getX() + buttonMinStXOffset - 3.0 * buttonRaceWidth;

				double buttonMaxXtXOffset = position.getX() + buttonMaxStXOffset + 3.0 * buttonRaceWidth;

				double buttonMinXtHeight = position.getY() + buttonMinStHeight - 3.0 * buttonRaceWidth;

				double buttonMaxXtHeight = position.getY() + buttonMaxStHeight + 3.0 * buttonRaceWidth;

				double buttonMaxXtDepth = position.getZ() + buttonMinStDepth - 2.0 * buttonRaceWidth;

				double buttonMinXtDepth = buttonMaxXtDepth - buttonRaceWidth;

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getProtoAltMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(buttonMaxXtXOffset, buttonMinXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset, buttonMinXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset + buttonRaceWidth, buttonMinXtHeight - buttonRaceWidth,
							buttonMinXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMinXtHeight - buttonRaceWidth,
							buttonMinXtDepth));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));
					fa.add(new Face3(2, 3, 0));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

					caPrism = PrismWidget.create(gc, pwi);
				}

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getProtoAltMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(buttonMaxXtXOffset, buttonMinXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset, buttonMaxXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMinXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMinXtHeight + buttonRaceWidth,
							buttonMinXtDepth));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));
					fa.add(new Face3(2, 3, 0));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

					cbPrism = PrismWidget.create(gc, pwi);
				}

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getProtoAltMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(buttonMaxXtXOffset, buttonMaxXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset, buttonMaxXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset + buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMinXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMinXtDepth));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));
					fa.add(new Face3(2, 3, 0));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

					ccPrism = PrismWidget.create(gc, pwi);
				}

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getProtoAltMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(buttonMinXtXOffset, buttonMinXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset, buttonMaxXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset - buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMinXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset - buttonRaceWidth, buttonMinXtHeight + buttonRaceWidth,
							buttonMinXtDepth));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));
					fa.add(new Face3(2, 3, 0));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

					cdPrism = PrismWidget.create(gc, pwi);
				}

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getProtoMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(buttonMaxXtXOffset, buttonMinXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset, buttonMinXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset, buttonMaxXtHeight, buttonMaxXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset, buttonMaxXtHeight, buttonMaxXtDepth));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));
					fa.add(new Face3(2, 3, 0));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

					cePrism = PrismWidget.create(gc, pwi);
				}

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getProtoMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMinXtHeight + buttonRaceWidth,
							buttonMinXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset + buttonRaceWidth, buttonMinXtHeight + buttonRaceWidth,
							buttonMinXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset + buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMinXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMinXtDepth));

					pwi.setVertices(ar);

					ArrayList<Face3> fa = new ArrayList<Face3>();
					fa.add(new Face3(0, 1, 2));
					fa.add(new Face3(2, 3, 0));

					pwi.setFaces(fa);

					pwi.setPosition(new Vector3(0.0, 0.0, 0.0));

					cfPrism = PrismWidget.create(gc, pwi);
				}

				internalSetFocus(foc, bnd, gc);

			}
		});

	}

	@Override
	public void addPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, ISound snd) {

		gc.handleBoundsGeneration(labelTextWidget.getTid(), new IBoundsHandler() {

			@Override
			public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {

				caPrism.addPositionalAudio(e, sess, gc, snd);
			}
		});

	}

	@Override
	public void playPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, ISound snd) {

		gc.handleBoundsGeneration(labelTextWidget.getTid(), new IBoundsHandler() {

			@Override
			public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {

				caPrism.playPositionalAudio(e, sess, gc, snd);
			}
		});

	}

	/**
	 * Creates an instance of a button widget
	 * 
	 * @param gc  The graphics context in which to display the button
	 * @param pwi Object used to initialize the button
	 * @return An instance of a button widget
	 */
	public static AbstractPushbuttonWidget create(GraphicsContext gc, PushbuttonWidgetInitializer pwi) {

		System.out.println("Creating Push Button Up Widget");

		return (new PushButtonWidget(gc, pwi));
	}

	@Override
	public void addToScene(GraphicsContext gc) {

		System.out.println("Adding To Scene");

		gc.handleBoundsGeneration(labelTextWidget.getTid(), new IBoundsHandler() {

			@Override
			public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {

				System.out.println("Adding To SceneB... " + PushButtonWidget.this);

				caPrism.addToScene(gc);
				cbPrism.addToScene(gc);
				ccPrism.addToScene(gc);
				cdPrism.addToScene(gc);
				cePrism.addToScene(gc);
				cfPrism.addToScene(gc);

				if (focus) {
					hilightPrism.addToScene(gc);
				}

				labelTextWidget.addToScene(gc);
			}
		});
	}

	@Override
	public void dispose(GraphicsContext gc) {
		System.out.println("Disposing... " + this);

		gc.handleBoundsGeneration(labelTextWidget.getTid(), new IBoundsHandler() {

			@Override
			public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {

				System.out.println("DisposingB... " + PushButtonWidget.this);

				caPrism.dispose(gc);
				cbPrism.dispose(gc);
				ccPrism.dispose(gc);
				cdPrism.dispose(gc);
				cePrism.dispose(gc);
				cfPrism.dispose(gc);

				if (focus) {
					hilightPrism.dispose(gc);
				}

				labelTextWidget.dispose(gc);
			}
		});

	}

	@Override
	public void removeFromScene(GraphicsContext gc) {
		System.out.println("Removing from scene... " + this);

		gc.handleBoundsGeneration(labelTextWidget.getTid(), new IBoundsHandler() {

			@Override
			public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {

				System.out.println("Removing from sceneB... " + PushButtonWidget.this);

				caPrism.removeFromScene(gc);
				cbPrism.removeFromScene(gc);
				ccPrism.removeFromScene(gc);
				cdPrism.removeFromScene(gc);
				cePrism.removeFromScene(gc);
				cfPrism.removeFromScene(gc);

				if (focus) {
					hilightPrism.removeFromScene(gc);
				}

				labelTextWidget.removeFromScene(gc);
			}
		});

	}

	@Override
	public boolean getFocus() {
		return (focus);
	}

	/**
	 * Internal method to set whether the widget has the keyboard focus
	 * 
	 * @param in  The focus state to set
	 * @param bnd The calculated bounds of the button label text
	 * @param gc  The graphics context in which to render setting the focus
	 */
	protected void internalSetFocus(boolean in, Bounds bnd, GraphicsContext gc) {

		final boolean changed = (focus != in);

		focus = in;

		if (changed) {
			if (in) {

				final double buttonRaceWidth = WIDTH * (TRACK_WIDTH_PCT);

				final Theme theme = (Theme) (gc.getCurrentTheme());

				final double centerOffsetX = -0.5 * (bnd.getMin().getX() + bnd.getMax().getX());

				final double buttonMaxStXOffset = bnd.getMax().getX() + centerOffsetX;

				final double buttonMinStXOffset = bnd.getMin().getX() + centerOffsetX;

				final double buttonMaxStHeight = bnd.getMax().getY();

				final double buttonMinStHeight = bnd.getMin().getY();

				final double buttonMinStDepth = bnd.getMin().getZ();

				double buttonMinXtXOffset = position.getX() + buttonMinStXOffset - 3.0 * buttonRaceWidth;

				double buttonMaxXtXOffset = position.getX() + buttonMaxStXOffset + 3.0 * buttonRaceWidth;

				double buttonMinXtHeight = position.getY() + buttonMinStHeight - 3.0 * buttonRaceWidth;

				double buttonMaxXtHeight = position.getY() + buttonMaxStHeight + 3.0 * buttonRaceWidth;

				final double buttonMaxXtDepth = position.getZ() + buttonMinStDepth - 2.0 * buttonRaceWidth;

				final double buttonMinXtDepth = buttonMaxXtDepth - buttonRaceWidth;

				final double buttonMidXtDepth = buttonMaxXtDepth + 0.5 * buttonRaceWidth;

				{
					PrismWidgetInitializer pwi = new PrismWidgetInitializer(theme);
					pwi.setMaterial(theme.getHilightMaterial(gc));

					ArrayList<Vector3> ar = new ArrayList<Vector3>();
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMinXtHeight + buttonRaceWidth,
							buttonMidXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset + buttonRaceWidth, buttonMinXtHeight + buttonRaceWidth,
							buttonMidXtDepth));
					ar.add(new Vector3(buttonMinXtXOffset + buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMidXtDepth));
					ar.add(new Vector3(buttonMaxXtXOffset - buttonRaceWidth, buttonMaxXtHeight - buttonRaceWidth,
							buttonMidXtDepth));

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

		gc.handleBoundsGeneration(labelTextWidget.getTid(), new IBoundsHandler() {

			@Override
			public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {

				final boolean changed = (focus != in);

				internalSetFocus(in, bnd, gc);

				if (changed && in) {
					hilightPrism.addToScene(gc);
				}
			}
		});

	}

}
