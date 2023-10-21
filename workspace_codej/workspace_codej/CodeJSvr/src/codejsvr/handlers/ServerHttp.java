
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

package codejsvr.handlers;

import java.net.InetSocketAddress;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import codejsvr.Activator;
import codejsvr.interfaces.IHttpHandler;
import codejsvr.preferences.PreferenceConstants;

/**
 * Version of CodeJ web server sending unencrypted HTTP to localhost. Note that
 * since the browser requires a secure connection before entering VR, this
 * service likely cannot serve VR outside of localhost.
 * 
 * @author tgreen
 *
 */
public class ServerHttp {

	/**
	 * Singleton instance of the server
	 */
	protected static HttpServer server = null;

	/**
	 * Whether the server has been started
	 */
	protected static boolean serverStarted = false;

	/**
	 * Gets a singleton instance of the server
	 * 
	 * @return A singleton instance of the server
	 * @throws Throwable
	 */
	protected static HttpServer getServer() throws Throwable {
		if (server == null) {
			int parsedPort = 8080;
			try
			{
				final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
				final String prefPortNumberString = store.getString( PreferenceConstants.P_PORT_NUMBER );
				parsedPort = Integer.parseInt( prefPortNumberString );
			}
			catch( Throwable ex )
			{
				ex.printStackTrace( System.out );
			}
			final int PORT_NUMBER = parsedPort;
			System.out.println("Port Number " + PORT_NUMBER);
			server = HttpServer.create(new InetSocketAddress(PORT_NUMBER), 0);
			System.out.println("Added Default Handler for /test");
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			final String EXTENSION_POINT_NAME = "CodeJSvr.HttpHandlers";
			IConfigurationElement[] config = registry.getConfigurationElementsFor(EXTENSION_POINT_NAME);
			System.out.println(EXTENSION_POINT_NAME + " Registry Length : " + (config.length));
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating Extension " + e);
				try {
					final Object o = e.createExecutableExtension("class");
					System.out.println("Evaluating Executable Extension " + o);
					if (o instanceof IHttpHandler) {
						final IHttpHandler ihttphandler = (IHttpHandler) (o);
						final String path = ihttphandler.getPath();
						final HttpHandler httphandler = ihttphandler.getHandler();
						System.out.println("Adding Handler for " + path + " with " + httphandler);
						server.createContext(path, httphandler);
						System.out.println("Added Handler for " + path);
					}
				} catch (Throwable ex) {
					ex.printStackTrace(System.out);
				}
			}
			server.setExecutor(null);
			server.start();
			serverStarted = true;
			System.out.println("CodeJSvr Started Server Without Encryption");
		}
		return (server);
	}

	/**
	 * Starts the web server.
	 * 
	 * @throws Throwable
	 */
	public static void startServer() throws Throwable {
		System.out.println("CodeJSvr Starting Server Without Encryption");
		if (server == null) {
			server = getServer();
			serverStarted = true;
		}
		System.out.println("CodeJSvr Finished Attempted Start Server Without Encryption");
	}

	/**
	 * Stops the web server.
	 */
	public static void stopServer() {
		System.out.println("CodeJSvr Stopping Server Without Encryption");
		if (server != null) {
			final int DELAY = 0;
			server.stop(DELAY);
		}
		server = null;
		serverStarted = false;
		System.out.println("CodeJSvr Stopped Server Without Encryption");
	}

}
