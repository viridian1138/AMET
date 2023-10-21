
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

package codejappmeditationchambersoundgong.handlers;

import codejcore.interfaces.CoreNames;
import codejcore.interfaces.ICompletionHandler;
import codejcore.interfaces.ISound;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.SessionDataToken;
import codejcore.widgets.GraphicsContext;

/**
 * Widget-level class interface for the Meditation Chamber gong sound
 * 
 * @author tgreen
 *
 */
public class SoundMeditationGong implements ISound {

	@Override
	public String getDependencyImportString() {
		return ("./" + CoreNames.SOUND_RESOURCE_PATH + SoundHandler.SOUND_PATH);
	}

	@Override
	public String getSoundReference() {
		return (SoundResponseHandler.SOUND_REFERENCE);
	}

	@Override
	public String getSoundName() {
		return ("Meditation Gong");
	}

	@Override
	public boolean isSoundLoaded(SessionDataApplicationToken sess) {
		SessionDataToken std = sess.getSessionDataToken();
		synchronized (SoundResponseHandler.syncObj) {
			SessionSoundLoadingDesc desc = (SessionSoundLoadingDesc) (std.objects
					.get(SoundResponseHandler.MEDITATION_GONG_SOUND));
			if (desc == null) {
				desc = new SessionSoundLoadingDesc();
				std.objects.put(SoundResponseHandler.MEDITATION_GONG_SOUND, desc);
			}

			return (desc.soundLoaded);
		}
	}

	@Override
	public void handleSoundLoading(SessionDataApplicationToken sess, GraphicsContext gc, ICompletionHandler hndl) {
		initiateSoundLoading(sess, gc);
		SoundResponseHandler.handleSoundLoading(sess, gc, hndl);

	}

	@Override
	public void initiateSoundLoading(SessionDataApplicationToken sess, GraphicsContext gc) {
		SoundResponseHandler.initiateSoundLoading(sess, gc);

	}

}
