
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

package codejcore.bounds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Properties;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;

import codejcore.arch.Session;
import codejcore.arch.SessionData;
import codejcore.arch.SessionStore;
import codejcore.interfaces.CoreNames;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.SessionDataToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;
import codejcore.widgets.Vector3;

/**
 * Class for instances of a web service responding to the completion of the
 * calculation of the bounds of a block of text
 * 
 * @author tgreen
 *
 */
public class BoundsResponseHandler implements HttpHandler {

	/**
	 * Object used for thread synchronization
	 */
	public static final Integer syncObj = new Integer(-1);

	/**
	 * Property name used to store the calculated bounds in the session
	 */
	public static final String BOUNDS = "Bounds";

	/**
	 * Handles a request to generate the bounds of a block of text
	 * 
	 * @param sess The session for which the bounds is to be calculated
	 * @param gc   The graphics context for which the bounds is to be calculated
	 * @param tid  The ID of the bounds to be calculated
	 * @param hndl The event handler to be invoked upon the calculation of the
	 *             bounds
	 */
	public static void handleBoundsGeneration(SessionDataApplicationToken sess, GraphicsContext gc, String tid,
			IBoundsHandler hndl) {
		SessionDataToken std = sess.getSessionDataToken();
		synchronized (syncObj) {
			SessionBoundsLoadingDesc desc = (SessionBoundsLoadingDesc) (std.objects.get(BOUNDS));
			if (desc == null) {
				desc = new SessionBoundsLoadingDesc();
				std.objects.put(BOUNDS, desc);
			}

			SessionBoundsLoadingIdDesc desc2 = desc.ids.get(tid);
			if (desc2 == null) {
				desc2 = new SessionBoundsLoadingIdDesc();
				desc.ids.put(tid, desc2);
			}

			if (desc2.loadedBounds != null) {
				hndl.dispatchEvent(sess, gc, desc2.loadedBounds);
			} else {
				desc2.events.add(new BoundsConditionalResponse(sess, hndl));
			}
		}

	}

	@Override
	public void handle(HttpExchange t) throws IOException {

		System.out.println((this.getClass().getName()) + " Attempting Response");
		try {
			System.out.println("Request Method : " + (t.getRequestMethod()));
			System.out.println("Request URI : " + (t.getRequestURI().toString()));

			/*
			 * NOTE: jQuery generates DOM errors when text/javascript is used as the content
			 * type. The text/plain type works better.
			 */
			String CONTENT_TYPE = "text/plain";

			System.out.println("Content Type : " + CONTENT_TYPE);
			t.getResponseHeaders().putIfAbsent("Content-Type", Collections.singletonList(CONTENT_TYPE));

			if (t instanceof HttpsExchange) {
				t.getResponseHeaders().putIfAbsent("Access-Control-Allow-Origin", Collections.singletonList("*"));
			}

			StringBuilder jsBuilder = new StringBuilder();

			if (t.getRequestMethod().equals("POST")) {
				System.out.println("Verified Content Type\n");
				InputStream is = t.getRequestBody();
				Properties p = new Properties();
				System.out.println("Loading Properties\n");
				p.load(is);
				System.out.println("Listing Properties:\n");
				p.list(System.out);
				System.out.println("\n");
				String xids = p.getProperty(EventHandlerNames.PROPERTY_ARCH_ID);
				String xtimes = p.getProperty(EventHandlerNames.PROPERTY_ARCH_TIME);
				String tid = p.getProperty(BoundsEventHandlerNames.TID);
				String minxs = p.getProperty(BoundsEventHandlerNames.MIN_X);
				String minys = p.getProperty(BoundsEventHandlerNames.MIN_Y);
				String minzs = p.getProperty(BoundsEventHandlerNames.MIN_Z);
				String maxxs = p.getProperty(BoundsEventHandlerNames.MAX_X);
				String maxys = p.getProperty(BoundsEventHandlerNames.MAX_Y);
				String maxzs = p.getProperty(BoundsEventHandlerNames.MAX_Z);
				long xid = Long.parseLong(xids);
				long xtime = Long.parseLong(xtimes);
				double minx = Double.parseDouble(minxs);
				double miny = Double.parseDouble(minys);
				double minz = Double.parseDouble(minzs);
				double maxx = Double.parseDouble(maxxs);
				double maxy = Double.parseDouble(maxys);
				double maxz = Double.parseDouble(maxzs);
				Session sess = new Session(xtime, xid);
				System.out.println("Created Session\n");
				SessionData sd = SessionStore.sessionTable.get(sess);
				System.out.println("SessionData: " + sd + "\n");
				if (sd != null) {
					MetaverseApplication app = sd.getCurrentApplication();
					if (app != null) {
						UiEvent e = new UiEvent(p);
						GraphicsContext gc = new GraphicsContext(jsBuilder, sd);
						SessionDataApplicationToken token = sd.getToken();
						SessionDataToken std = token.getSessionDataToken();
						final Bounds bounds = new Bounds();
						bounds.min = new Vector3(minx, miny, minz);
						bounds.max = new Vector3(maxx, maxy, maxz);
						synchronized (syncObj) {
							SessionBoundsLoadingDesc desc = (SessionBoundsLoadingDesc) (std.objects.get(BOUNDS));
							if (desc == null) {
								desc = new SessionBoundsLoadingDesc();
								std.objects.put(BOUNDS, desc);
							}

							SessionBoundsLoadingIdDesc desc2 = desc.ids.get(tid);
							if (desc2 == null) {
								desc2 = new SessionBoundsLoadingIdDesc();
								desc.ids.put(tid, desc2);
							}

							desc2.loadedBounds = bounds;
							for (BoundsConditionalResponse cn : desc2.events) {
								if (token == cn.getSess()) {
									gc.insertBkFront();
									cn.getHndl().dispatchEvent(token, gc, bounds);
									gc.insertBkEnd();
								}
							}
							desc2.events.clear();
						}
					}
				}
			}

			String jsResponse = jsBuilder.toString();

			System.out.println("Response " + jsResponse);
			byte[] responseBytes = jsResponse.getBytes();
			final int RESPONSE_OK = 200;
			t.sendResponseHeaders(RESPONSE_OK, responseBytes.length);
			OutputStream os = t.getResponseBody();
			os.write(responseBytes);
			os.close();
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
			if (ex instanceof IOException) {
				throw ((IOException) ex);
			}
		}
		System.out.println((this.getClass().getName()) + " Attempted Response");

	}

	/**
	 * Initiates the calculation of the bounds of a block of text
	 * 
	 * @param sess            The session for which to compute the bounds
	 * @param geometryContext Reference to the string for which to compute the
	 *                        bounds
	 * @param tid             The ID of the bounds to be calculated
	 * @param gc              The graphics context for which to compute the bounds
	 */
	public static void initiateBoundsGeneration(SessionDataApplicationToken sess, final String geometryContext,
			final String tid, GraphicsContext gc) {
		SessionDataToken std = sess.getSessionDataToken();
		synchronized (syncObj) {
			SessionBoundsLoadingDesc desc = (SessionBoundsLoadingDesc) (std.objects.get(BOUNDS));
			if (desc == null) {
				desc = new SessionBoundsLoadingDesc();
				std.objects.put(BOUNDS, desc);
			}

			SessionBoundsLoadingIdDesc desc2 = desc.ids.get(tid);
			if (desc2 == null) {
				desc2 = new SessionBoundsLoadingIdDesc();
				desc.ids.put(tid, desc2);
			}

			if (!(desc2.loadedBounds != null) && !(desc2.boundsLoading)) {
				desc2.boundsLoading = true;
				gc.appendJs("\n");

				gc.insertBkFront();

				gc.appendJs("\n" + "		" + geometryContext + ".computeBoundingBox();\n" + "\n" + "\n" + "		{\n"
						+ "\n" + "\n" + "\n" + "{\n" + "var dstr = \"\\n\";\n" + "dstr = dstr + \""
						+ EventHandlerNames.PROPERTY_ARCH_TIME + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_TIME
						+ " + \"\\n\";\n" + "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_ID + "=\" + tmpObj."
						+ EventHandlerNames.PROPERTY_ARCH_ID + " + \"\\n\";\n"

						+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_EVENT_TYPE + "="
						+ EventHandlerNames.BOUNDS_GENERATED_EVENT_TYPE + "\\n\";\n"

						+ "dstr = dstr + \"" + BoundsEventHandlerNames.TID + "=" + tid + "\\n\";\n"

						+ "dstr = dstr + \"" + BoundsEventHandlerNames.MIN_X + "=\" + " + geometryContext
						+ ".boundingBox.min.x + \"\\n\";\n"

						+ "dstr = dstr + \"" + BoundsEventHandlerNames.MIN_Y + "=\" + " + geometryContext
						+ ".boundingBox.min.y + \"\\n\";\n"

						+ "dstr = dstr + \"" + BoundsEventHandlerNames.MIN_Z + "=\" + " + geometryContext
						+ ".boundingBox.min.z + \"\\n\";\n"

						+ "dstr = dstr + \"" + BoundsEventHandlerNames.MAX_X + "=\" + " + geometryContext
						+ ".boundingBox.max.x + \"\\n\";\n"

						+ "dstr = dstr + \"" + BoundsEventHandlerNames.MAX_Y + "=\" + " + geometryContext
						+ ".boundingBox.max.y + \"\\n\";\n"

						+ "dstr = dstr + \"" + BoundsEventHandlerNames.MAX_Z + "=\" + " + geometryContext
						+ ".boundingBox.max.z + \"\\n\";\n"

						+ "var aaa = $.post( \"" + CoreNames.BOUNDS_LOADING_RESPONSE_PATH
						+ "\" , dstr , function(data, status) {\n"
						+ "console.log( \"data: \" + data + \" status: \" + status );\n");

				gc.insertBkFront();

				gc.appendJs("\n" + "console.log( \"data2: \" + data + \" status: \" + status );\n" + "eval( data );\n"
						+ "console.log( \"zmsg2: \" + tmpObj.zmsg );\n");

				gc.insertBkEnd();

				gc.appendJs("\n" + "console.log( \"zmsg: \" + tmpObj.zmsg );\n" + "   });\n" // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						+ "}\n" + "\n" + "\n" + "		}\n" + "\n");

				gc.insertBkEnd();

				gc.appendJs("\n");
			}
		}

	}

}
