
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

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import codejsvr.Activator;
import codejsvr.interfaces.IHttpHandler;
import codejsvr.preferences.PreferenceConstants;

/**
 * Version of CodeJ web server using HTTPS to serve VR over the larger web.
 * Future expansion.
 * 
 * @author tgreen
 *
 */
public class ServerHttps {

	/**
	 * Singleton instance of the server
	 */
	protected static HttpsServer server = null;

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
	protected static HttpsServer getServer() throws Throwable {
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
			server = HttpsServer.create(new InetSocketAddress(PORT_NUMBER), 0);
			SSLContext sslContext = SSLContext.getInstance("TLS");

			// Initialize the keystore
			char[] password = "password".toCharArray();
			KeyStore keyStore = KeyStore.getInstance("JKS");
			String ppath = Platform.getLocation().toPortableString();
			System.out.println("Platform Path " + ppath);
			String keyPath = ppath + "/testkey.jks";
			System.out.println("Attempting to load key file at : " + keyPath);
			FileInputStream inputStream = new FileInputStream(keyPath);
			keyStore.load(inputStream, password);

			// Set up the key manager factory
			KeyManagerFactory keyManager = KeyManagerFactory.getInstance("SunX509");
			keyManager.init(keyStore, password);

			TrustManagerFactory trustManager = TrustManagerFactory.getInstance("SunX509");
			trustManager.init(keyStore);

			sslContext.init(keyManager.getKeyManagers(), trustManager.getTrustManagers(), null);

			server.setHttpsConfigurator(

					new HttpsConfigurator(sslContext) {
						@Override
						public void configure(HttpsParameters params) {
							try {
								System.out.println("Attempting to configure HTTPS");

								// Initialize the SSL Context
								SSLContext sslContext = getSSLContext();
								SSLEngine sslEngine = sslContext.createSSLEngine();
								params.setNeedClientAuth(false);
								params.setCipherSuites(sslEngine.getEnabledCipherSuites());

								// Set the SSL parameters
								SSLParameters sslParameters = sslContext.getSupportedSSLParameters();
								params.setSSLParameters(sslParameters);

								System.out.println("HTTPS has been configured");
							} catch (Throwable ex) {
								ex.printStackTrace(System.out);
							}
						}

					}

			);

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
			System.out.println("CodeJSvr Started Encrypted Server");
		}
		return (server);
	}

	/**
	 * Starts the web server.
	 * 
	 * @throws Throwable
	 */
	public static void startServer() throws Throwable {
		System.out.println("CodeJSvr Starting Encrypted Server");
		if (server == null) {
			server = getServer();
		}
		System.out.println("CodeJSvr Finished Attempted Start Encrypted Server");
	}

	/**
	 * Stops the web server.
	 */
	public static void stopServer() {
		System.out.println("CodeJSvr Stopping Encrypted Server");
		if (server != null) {
			final int DELAY = 0;
			server.stop(DELAY);
		}
		server = null;
		serverStarted = false;
		System.out.println("CodeJSvr Stopped Encrypted Server");
	}

}
