
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

package codejquery.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;

/**
 * Class for instances of a web service to get the JS definition for the JQuery
 * API
 * 
 * @author tgreen
 *
 */
public class JQueryHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {

		System.out.println((this.getClass().getName()) + " Attempting Response");
		try {
			System.out.println("Request Method : " + (t.getRequestMethod()));
			System.out.println("Request URI : " + (t.getRequestURI().toString()));

			String CONTENT_TYPE = "text/javascript";
			System.out.println("Content Type : " + CONTENT_TYPE);
			t.getResponseHeaders().putIfAbsent("Content-Type", Collections.singletonList(CONTENT_TYPE));

			if (t instanceof HttpsExchange) {
				t.getResponseHeaders().putIfAbsent("Access-Control-Allow-Origin", Collections.singletonList("*"));
			}

			InputStream is = this.getClass()
					.getResourceAsStream(JQueryImport.RUN_AS_DEBUG ? "jquery-3.7.0.js" : "jquery-3.7.0.min.js");

			String htmlResponse = (new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))).lines()
					.collect(Collectors.joining("\n"));

			// System.out.println( "Response " + htmlResponse );
			byte[] responseBytes = htmlResponse.getBytes();
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
