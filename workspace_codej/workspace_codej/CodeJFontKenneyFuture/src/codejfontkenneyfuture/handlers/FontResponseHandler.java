
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

package codejfontkenneyfuture.handlers;

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
import codejcore.interfaces.ConditionalResponse;
import codejcore.interfaces.CoreNames;
import codejcore.interfaces.ICompletionHandler;
import codejcore.interfaces.MetaverseApplication;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.SessionDataToken;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;

/**
 * Class for instances of a web service responding to the completion of the
 * loading of the Kenney Future font
 * 
 * @author tgreen
 *
 */
public class FontResponseHandler implements HttpHandler {

	/**
	 * Object used for thread synchronization
	 */
	public static final Integer syncObj = new Integer(-1);

	/**
	 * The name of the font
	 */
	public static final String KENNEY_FUTURE_FONT = "KenneyFutureFont";

	/**
	 * The reference to the font in JS
	 */
	public static final String FONT_REFERENCE = "tmpObj.fontKenneyFuture";

	/**
	 * Handles a request to load the font
	 * 
	 * @param sess The session for which to load the font
	 * @param gc   The graphics context for which to load the font
	 * @param hndl The event handler to be invoked upon the loading of the font
	 */
	public static void handleFontLoading(SessionDataApplicationToken sess, GraphicsContext gc,
			ICompletionHandler hndl) {
		SessionDataToken std = sess.getSessionDataToken();
		synchronized (syncObj) {
			SessionFontLoadingDesc desc = (SessionFontLoadingDesc) (std.objects.get(KENNEY_FUTURE_FONT));
			if (desc == null) {
				desc = new SessionFontLoadingDesc();
				std.objects.put(KENNEY_FUTURE_FONT, desc);
			}

			if (desc.fontLoaded) {
				hndl.dispatchEvent(sess, gc);
			} else {
				desc.events.add(new ConditionalResponse(sess, hndl));
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
				long xid = Long.parseLong(xids);
				long xtime = Long.parseLong(xtimes);
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
						synchronized (syncObj) {
							SessionFontLoadingDesc desc = (SessionFontLoadingDesc) (std.objects
									.get(KENNEY_FUTURE_FONT));
							if (desc == null) {
								desc = new SessionFontLoadingDesc();
								std.objects.put(KENNEY_FUTURE_FONT, desc);
							}

							desc.fontLoaded = true;
							for (ConditionalResponse cn : desc.events) {
								if (token == cn.getSess()) {
									gc.insertBkFront();
									cn.getHndl().dispatchEvent(token, gc);
									gc.insertBkEnd();
								}
							}
							desc.events.clear();
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
	 * Initiates the loading of the font
	 * 
	 * @param sess The session for which to load the font
	 * @param gc   The graphics context for which to load the font
	 */
	public static void initiateFontLoading(SessionDataApplicationToken sess, GraphicsContext gc) {
		SessionDataToken std = sess.getSessionDataToken();
		synchronized (syncObj) {
			SessionFontLoadingDesc desc = (SessionFontLoadingDesc) (std.objects.get(KENNEY_FUTURE_FONT));
			if (desc == null) {
				desc = new SessionFontLoadingDesc();
				std.objects.put(KENNEY_FUTURE_FONT, desc);
			}

			if (!(desc.fontLoaded) && !(desc.fontLoading)) {
				desc.fontLoading = true;
				gc.appendJs("\n");
				gc.insertBkFront();
				gc.appendJs("\n" + "	fontLoader.load('./" + CoreNames.FONT_RESOURCE_PATH + FontHandler.FONT_PATH
						+ "' , function(afont) {\n" + "\n" + "		" + FontResponseHandler.FONT_REFERENCE
						+ " = afont;\n" + "\n" + "		{\n" + "\n" + "\n" + "\n" + "{\n" + "var dstr = \"\\n\";\n"
						+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_TIME + "=\" + tmpObj."
						+ EventHandlerNames.PROPERTY_ARCH_TIME + " + \"\\n\";\n" + "dstr = dstr + \""
						+ EventHandlerNames.PROPERTY_ARCH_ID + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_ID
						+ " + \"\\n\";\n"

						+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_EVENT_TYPE + "="
						+ EventHandlerNames.FONT_LOADED_EVENT_TYPE + "\\n\";\n"

						+ "var aaa = $.post( \"" + CoreNames.FONT_LOADING_RESPONSE_PATH + FontHandler.FONT_PATH
						+ "\" , dstr , function(data, status) {\n"
						+ "console.log( \"data: \" + data + \" status: \" + status );\n");

				gc.insertBkFront();

				gc.appendJs("\n" + "console.log( \"data2: \" + data + \" status: \" + status );\n" + "eval( data );\n"
						+ "console.log( \"zmsg2: \" + tmpObj.zmsg );\n");

				gc.insertBkEnd();

				gc.appendJs("\n" + "console.log( \"zmsg: \" + tmpObj.zmsg );\n" + "   });\n" // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						+ "}\n" + "\n" + "\n" + "		}\n" + "\n" + "	});\n" + "\n" + "\n" + "\n");
				gc.insertBkEnd();
				gc.appendJs("\n");
			}
		}

	}

}
