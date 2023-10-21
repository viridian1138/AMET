
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

package codejfonthelvetikerbold.handlers;

import codejcore.interfaces.CoreNames;
import codejcore.interfaces.ICompletionHandler;
import codejcore.interfaces.IFont;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.SessionDataToken;
import codejcore.widgets.GraphicsContext;

/**
 * Font plug-in for for the Helvetiker bold font
 * 
 * @author tgreen
 *
 */
public class FontHelvetikerBold implements IFont {

	@Override
	public String getDependencyImportString() {
		return ("./" + CoreNames.FONT_RESOURCE_PATH + FontHandler.FONT_PATH);
	}

	@Override
	public String getFontReference() {
		return (FontResponseHandler.FONT_REFERENCE);
	}

	@Override
	public String getFontName() {
		return ("Helvetiker Bold");
	}

	@Override
	public boolean isFontLoaded(SessionDataApplicationToken sess) {
		SessionDataToken std = sess.getSessionDataToken();
		synchronized (FontResponseHandler.syncObj) {
			SessionFontLoadingDesc desc = (SessionFontLoadingDesc) (std.objects
					.get(FontResponseHandler.HELVETIKER_BOLD_FONT));
			if (desc == null) {
				desc = new SessionFontLoadingDesc();
				std.objects.put(FontResponseHandler.HELVETIKER_BOLD_FONT, desc);
			}

			return (desc.fontLoaded);
		}
	}

	@Override
	public void handleFontLoading(SessionDataApplicationToken sess, GraphicsContext gc, ICompletionHandler hndl) {
		initiateFontLoading(sess, gc);
		FontResponseHandler.handleFontLoading(sess, gc, hndl);

	}

	@Override
	public void initiateFontLoading(SessionDataApplicationToken sess, GraphicsContext gc) {
		FontResponseHandler.initiateFontLoading(sess, gc);
	}

	@Override
	public double getFontScale() {
		return (1.0);
	}

}
