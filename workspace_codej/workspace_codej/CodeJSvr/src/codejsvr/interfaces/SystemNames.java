
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

package codejsvr.interfaces;

import org.eclipse.jface.preference.IPreferenceStore;

import codejsvr.Activator;
import codejsvr.preferences.PreferenceConstants;

/**
 * Standard naming constants used by the CodeJ web server system
 * 
 * @author tgreen
 *
 */
public class SystemNames {

	/**
	 * Default Constructor
	 */
	public SystemNames() {
	}

	/**
	 * The web application service theme for the core services
	 */
	public static enum ApplicationServiceTheme {
		/**
		 * Application service theme patterned after standard HTML pages
		 */
		HTML {

			@Override
			public String getSuffix() {
				return (".html");
			}

		},
		/**
		 * Application service theme patterned after Active Server pages
		 */
		ASP {

			@Override
			public String getSuffix() {
				return (".asp");
			}

		},
		/**
		 * Application service theme patterned after Java Server pages
		 */
		JSP {

			@Override
			public String getSuffix() {
				return (".jsp");
			}

		};

		/**
		 * Gets the suffix associated with the theme
		 * 
		 * @return The suffix associated with the theme
		 */
		public abstract String getSuffix();
	}

	/**
	 * The path to the metaverse web application.
	 */
	public static final String METAVERSE_WEB_APPLICATION_PATH = "app/";

	/**
	 * Gets the application service theme
	 * 
	 * @return The application service theme
	 */
	public static String getApplicationServiceTheme() {
		final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		final String prefApplicationThemeString = store.getString(PreferenceConstants.P_APPLICATION_SERVICE_THEME);
		return (prefApplicationThemeString);
	}

}
