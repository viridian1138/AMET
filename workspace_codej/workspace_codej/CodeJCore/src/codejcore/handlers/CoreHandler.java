
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

package codejcore.handlers;

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
import codejcore.interfaces.MetaverseApplicationInstance;
import codejcore.interfaces.UiEvent;
import codejcore.widgets.EventHandlerNames;
import codejcore.widgets.GraphicsContext;

/**
 * Class for instances of the core web service used by CodeJ to respond to
 * events generated by the metaverse application JS on the web client.
 * 
 * @author tgreen
 *
 */
public class CoreHandler implements HttpHandler {

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
					MetaverseApplicationInstance app = sd.getCurrentApplicationInstance();
					if (app != null) {
						UiEvent e = new UiEvent(p);
						GraphicsContext gc = new GraphicsContext(jsBuilder, sd);
						gc.insertBkFront();
						app.systemDispatchEvent(e, sd.getToken(), gc);
						gc.insertBkEnd();
					}
				}
				sess.print();
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

}
