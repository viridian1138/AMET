
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

package codejcore.interfaces;

/**
 * Common naming constants used by the CodeJ core system
 * 
 * @author tgreen
 *
 */
public class CoreNames {

	/**
	 * Default Constructor
	 */
	public CoreNames() {
	}

	/**
	 * Path to metaverse core system web applications
	 */
	public static final String METAVERSE_CORE_SYSTEM_PATH = "core/";

	/**
	 * Path to JS modules used by metaverse applications
	 */
	public static final String JAVASCRIPT_MODULE_PATH = "modules/";

	/**
	 * Path to fonts used by the metaverse applications
	 */
	public static final String FONT_RESOURCE_PATH = "fonts/";

	/**
	 * Path to web services responding to the completion of the loading of fonts
	 */
	public static final String FONT_LOADING_RESPONSE_PATH = "fontresponse/";

	/**
	 * Path to web services responding to the completion of the calculation of the
	 * bounds of text
	 */
	public static final String BOUNDS_LOADING_RESPONSE_PATH = "boundsresponse/";

	/**
	 * Path to sounds used by the metaverse applications
	 */
	public static final String SOUND_RESOURCE_PATH = "sounds/";

	/**
	 * Path to web services responding to the completion of the loading of sounds
	 */
	public static final String SOUND_LOADING_RESPONSE_PATH = "soundresponse/";

	/**
	 * Path to textures used by the metaverse applications
	 */
	public static final String TEXTURE_RESOURCE_PATH = "textures/";

	/**
	 * Path for the browser favorite icon file
	 */
	public static final String BROWSER_FAVORITE_ICON_PATH = "favicon.ico";

	/**
	 * Path for the JS file defining the virtual reality environment
	 */
	public static final String VIRTUAL_REALITY_MAIN_JAVASCRIPT_PATH = "index_xr.js";

	/**
	 * Path for the JS file defining the button for entering virtual reality
	 */
	public static final String VIRTUAL_REALITY_ENTRY_BUTTON_PATH = "VRButton.js";

	/**
	 * Path for the HTML page providing a description
	 */
	public static final String ABOUT_PAGE_PATH = "about.html";

}
