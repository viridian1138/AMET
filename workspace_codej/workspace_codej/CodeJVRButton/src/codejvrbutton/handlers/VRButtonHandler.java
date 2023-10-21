


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



package codejvrbutton.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;

/**
 * Class for instances of a web service to get the JS definition for the button for entering Virtual Reality
 * @author tgreen
 *
 */
public class VRButtonHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		
		System.out.println( ( this.getClass().getName() ) + " Attempting Response" );
		try
		{
			System.out.println( "Request Method : " + ( t.getRequestMethod() ) );
			System.out.println( "Request URI : " + ( t.getRequestURI().toString() ) );
			
			String CONTENT_TYPE = "text/javascript";
			System.out.println( "Content Type : " + CONTENT_TYPE );
			t.getResponseHeaders().putIfAbsent( "Content-Type" , Collections.singletonList( CONTENT_TYPE ) );
			
			if( t instanceof HttpsExchange )
			{
				t.getResponseHeaders().putIfAbsent( "Access-Control-Allow-Origin" , Collections.singletonList( "*" ) );
			}
			
			StringBuilder htmlBuilder = new StringBuilder();

htmlBuilder.append( "\n"
		+ "var VRButton = {\n"
		+ "    createButton: function(gl, options) {\n"
		+ "        if (options && options.referenceSpaceType) {\n"
		+ "            gl.xr.setReferenceSpaceType(options.referenceSpaceType);\n"
		+ "        }\n"
		+ "\n"
		+ "        function EnterVR() {\n"
		+ "            // label button\n"
		+ "            button.innerHTML = 'Enter XR';\n"
		+ "            var currentSession = null;\n"
		+ "            function onSessionStarted(session) {\n"
		+ "                session.addEventListener('end', onSessionEnded);\n"
		+ "                \n"
		+ "                button.textContent = 'Exit XR';\n"
		+ "                currentSession = session;\n"
		+ "                setupWebGLLayer()\n"
		+ "                    .then(() => {\n"
		+ "                        gl.xr.setSession(currentSession);\n"
		+ "                    });\n"
		+ "            }\n"
		+ "            \n"
		+ "            function onSessionEnded(/*event*/) {\n"
		+ "                currentSession.removeEventListener('end', onSessionEnded);\n"
		+ "                button.textContent = 'Enter XR';\n"
		+ "                currentSession = null;\n"
		+ "            }\n"
		+ "\n"
		+ "            function setupWebGLLayer() {\n"
		+ "                var glContext = gl.getContext();\n"
		+ "                return glContext.makeXRCompatible().then(() => {\n"
		+ "                    currentSession.updateRenderState( {baseLayer: new XRWebGLLayer(currentSession, glContext) });\n"
		+ "                });\n"
		+ "            }\n"
		+ "            \n"
		+ "            button.onclick = () => {\n"
		+ "                if (currentSession === null) {\n"
		+ "                    let sessionInit = {\n"
		+ "                        optionalFeatures: [\"local-floor\", \"bounded-floor\"]\n"
		+ "                    };\n"
		+ "                navigator.xr\n"
		+ "                         .requestSession('immersive-vr', sessionInit)\n"
		+ "                         .then(onSessionStarted);\n"
		+ "                }\n"
		+ "                else {\n"
		+ "                    currentSession.end();\n"
		+ "                }\n"
		+ "            }\n"
		+ "        }             \n"
		+ "\n"
		+ "        function NotFound() {\n"
		+ "            console.log('immersive-vr mode not found');\n"
		+ "        }\n"
		+ "\n"
		+ "        if (navigator.xr) {\n"
		+ "            var button = document.createElement(\"button\");\n"
		+ "            navigator.xr.isSessionSupported('immersive-vr')\n"
		+ "                        .then(function(supported) {\n"
		+ "                           if (supported) { EnterVR() }\n"
		+ "                           else { NotFound(); }\n"
		+ "                        })\n"
		+ "            button.setAttribute(\"id\", \"btn\");\n"
		+ "            return button;\n"
		+ "        } else {\n"
		+ "            if (window.isSecureContext === false) {\n"
		+ "                console.log('WebXR needs HTTPS');\n"
		+ "            } else {\n"
		+ "                console.log('WebXR not available');\n"
		+ "            }\n"
		+ "            return;\n"
		+ "        }\n"
		+ "    }\n"
		+ "}\n"
		+ "\n"
		+ "export {VRButton};\n" );		
			
			String htmlResponse = htmlBuilder.toString();
			
			System.out.println( "Response " + htmlResponse );
			byte[] responseBytes = htmlResponse.getBytes();
			final int RESPONSE_OK = 200;
			t.sendResponseHeaders( RESPONSE_OK , responseBytes.length );
			OutputStream os = t.getResponseBody();
			os.write( responseBytes );
			os.close();
		}
		catch( Throwable ex )
		{
			ex.printStackTrace( System.out );
			if( ex instanceof IOException )
			{
				throw( (IOException) ex );
			}
		}
		System.out.println( ( this.getClass().getName() ) + " Attempted Response" );
		
	}

}

