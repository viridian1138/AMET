
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

/**
 * Interface for the CodeJ web server
 * 
 * @author tgreen
 *
 */
public class Server {

	/**
	 * Whether an encrypted server is to be used. Future expansion.
	 */
	protected static boolean useEncryptedServer = false;

	/**
	 * Starts the web server.
	 * 
	 * @throws Throwable
	 */
	public static void startServer() throws Throwable {
		System.out.println("CodeJSvr Starting");
		if (useEncryptedServer) {
			ServerHttps.startServer();
		} else {
			ServerHttp.startServer();
		}
		System.out.println("CodeJSvr Finished Attempted Start");
	}

	/**
	 * Stops the web server.
	 */
	public static void stopServer() {
		System.out.println("CodeJSvr Stopping");
		if (useEncryptedServer) {
			ServerHttps.stopServer();
		} else {
			ServerHttp.stopServer();
		}
		System.out.println("CodeJSvr Stopped");
	}

}
