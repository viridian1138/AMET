
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

package codejappmeditationchamber.handlers;

import codejappmeditationchambersoundgong.handlers.SoundMeditationGong;
import codejcore.arch.SessionStore;
import codejcore.focus.HorizontalFocusRotation;
import codejcore.interfaces.IEventHandler;
import codejcore.interfaces.IFont;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.ISound;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.AbstractPushbuttonWidget;
import codejcore.widgets.AbstractScrollbarWidget;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.KeyNames;
import codejcore.widgets.PushbuttonWidgetInitializer;
import codejcore.widgets.ScrollbarWidgetInitializer;
import codejcore.widgets.TextWidget;
import codejcore.widgets.TextWidgetInitializer;
import codejcore.widgets.Vector3;

/**
 * Class for instances of a meditation chamber Metaverse Application
 * 
 * @author tgreen
 *
 */
public class MeditationChamberAppInstance extends MetaverseApplicationInstance {

	/**
	 * Sound for the gong to be played at the end of the meditation
	 */
	protected final ISound snd = new SoundMeditationGong();

	/**
	 * Widget for displaying the title text
	 */
	protected TextWidget titleTextWidget = null;

	/**
	 * Widget for displaying the countdown of the remaining meditation time
	 */
	protected TextWidget countdownTextWidget = null;

	/**
	 * The vertical scroll bar for changing the length of the meditation
	 */
	protected AbstractScrollbarWidget verticalScrollbarWidget = null;

	/**
	 * The initial meditation time upon starting in seconds
	 */
	protected int startMeditationTimeSeconds = 0;

	/**
	 * The current meditation time in seconds
	 */
	protected int currentMeditationTimeSeconds = 0;

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
	 * Default Constructor
	 */
	public MeditationChamberAppInstance() {
	}

	@Override
	public String getApplicationName() {
		return ("Meditation Chamber");
	}

	@Override
	public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {
		System.out.println("Dispatching Event : " + this);
		e.print();
		System.out.println("ac: " + (gc.getApplicationContext()));

		gc.appendJs("{\n");
		gc.appendJs("tmpObj.zmsg=\"Meditation Chamber Response\";\n");
		gc.appendJs("}\n");
	}

	/**
	 * Generates a human-readable string for the remaining meditation time
	 * 
	 * @param meditationTimeSeconds The remaining meditation time in seconds
	 * @return Human-readable string for the remaining meditation time
	 */
	public String generateEventString(int meditationTimeSeconds) {
		int rv = meditationTimeSeconds % 60;
		String ret = "Meditation Time: " + (meditationTimeSeconds / 60) + ":" + (rv < 10 ? "0" : "") + rv;
		return (ret);
	}

	@Override
	public void dispatchStartEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		final int meditationTimeSeconds = 60 * 15;

		startMeditationTimeSeconds = meditationTimeSeconds;

		currentMeditationTimeSeconds = meditationTimeSeconds;

		gc.appendJs("	scene.background = new THREE.Color(0x19337f);\n");

		final IMetaverseTheme theme = gc.getCurrentTheme();

		System.out.println("Meditation Chamber Starting With Initial Time");

		{
			TextWidgetInitializer twA = new TextWidgetInitializer(theme);
			twA.setDisplayText("Running Meditation Chamber");
			twA.setPosition(new Vector3(0.0, 3.5 + 1.0, -7.0));

			titleTextWidget = TextWidget.create(gc, twA);
			titleTextWidget.addToScene(gc);

			titleTextWidget.addPositionalAudio(e, sess, gc, snd);
		}

		{
			TextWidgetInitializer twB = new TextWidgetInitializer(theme);
			twB.setDisplayText(generateEventString(meditationTimeSeconds));
			twB.setPosition(new Vector3(0.0, 3.5, -7.0));

			countdownTextWidget = TextWidget.create(gc, twB);
			countdownTextWidget.addToScene(gc);
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

					System.out.println("Meditation Chamber Attempting Meditation Start");

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

		{
			final ScrollbarWidgetInitializer pwi = new ScrollbarWidgetInitializer(theme);
			pwi.setMinScroll(0);
			pwi.setMaxScroll(meditationTimeSeconds);
			pwi.setStartScroll(0);
			pwi.setEndScroll(meditationTimeSeconds);
			verticalScrollbarWidget = theme.createVerticalScrollbar(gc, pwi);
			verticalScrollbarWidget.addToScene(gc);

			focus.add("Vertical Scrollbar", verticalScrollbarWidget, new IEventHandler() {

				@Override
				public void dispatchEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

					System.out.println("Handle Scroll");

					final IMetaverseTheme theme = gc.getCurrentTheme();

					final IFont font = theme.getTitleFont();

					String keyCode = e.getKeyCode();

					if (keyCode.equals(KeyNames.ARROW_DOWN_KEY)) {
						System.out.println("Meditation Chamber Attempting Time Reduction");

						int meditationTimeSeconds = startMeditationTimeSeconds;

						meditationTimeSeconds -= 60;

						if (meditationTimeSeconds >= 0) {

							startMeditationTimeSeconds = meditationTimeSeconds;

							currentMeditationTimeSeconds = meditationTimeSeconds;

							final int currentMeditationTime = meditationTimeSeconds;

							countdownTextWidget.setDisplayText(generateEventString(currentMeditationTime), gc);

							{
								verticalScrollbarWidget.setLocation(0, currentMeditationTime, 0, currentMeditationTime,
										gc);
							}

						}
					}

					if (keyCode.equals(KeyNames.ARROW_UP_KEY)) {
						System.out.println("Meditation Chamber Attempting Time Increase");

						int meditationTimeSeconds = startMeditationTimeSeconds;

						meditationTimeSeconds += 60;

						if (meditationTimeSeconds >= 0) {

							startMeditationTimeSeconds = meditationTimeSeconds;

							currentMeditationTimeSeconds = meditationTimeSeconds;

							final int currentMeditationTime = meditationTimeSeconds;

							countdownTextWidget.setDisplayText(generateEventString(currentMeditationTime), gc);

							{
								verticalScrollbarWidget.setLocation(0, currentMeditationTime, 0, currentMeditationTime,
										gc);
							}

						}
					}

				}

			});

		}

	}

	@Override
	public void dispatchKeydownEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Meditation Chamber App Responding To Keydown Event");

		focus.dispatchEvent(e, sess, gc);

	}

	@Override
	public void dispatchTimeoutEvent(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Meditation Chamber App Responding To Timeout Event");

		int meditationTimeSeconds = currentMeditationTimeSeconds;

		meditationTimeSeconds--;

		if (meditationTimeSeconds < 0) {
			meditationTimeSeconds = 0;
		}

		currentMeditationTimeSeconds = meditationTimeSeconds;

		final int currentMeditationTime = meditationTimeSeconds;

		countdownTextWidget.setDisplayText(generateEventString(currentMeditationTime), gc);

		{
			verticalScrollbarWidget.setLocation(0, startMeditationTimeSeconds,
					startMeditationTimeSeconds - currentMeditationTime, startMeditationTimeSeconds, gc);
		}

		if (currentMeditationTime < 1) {
			gc.appendJs("	scene.background = new THREE.Color(0xffffff);\n");

			titleTextWidget.playPositionalAudio(e, sess, gc, snd);
		}

		gc.generateTimeout(1000);

	}

	@Override
	public void stopApplication(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc) {

		System.out.println("Meditation Chamber App Responding To Stop Event");

		{
			titleTextWidget.removeFromScene(gc);

			titleTextWidget.dispose(gc);
		}

		{
			countdownTextWidget.removeFromScene(gc);

			countdownTextWidget.dispose(gc);
		}

		{
			verticalScrollbarWidget.removeFromScene(gc);

			verticalScrollbarWidget.dispose(gc);
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
		gc.appendJs("tmpObj.zmsg=\"This Is A Meditation Chamber Stop Resonse\";\n");
		gc.appendJs("}\n");

	}

}
