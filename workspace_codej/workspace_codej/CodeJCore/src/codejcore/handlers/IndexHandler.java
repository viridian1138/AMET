


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
import java.io.OutputStream;
import java.util.Collections;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;

import codejcore.interfaces.CoreNames;
import codejsvr.interfaces.SystemNames;

/**
 * Class for instances of the web service to get the HTML content (index.html) of the CodeJ metaverse web application.
 * @author tgreen
 *
 */
public class IndexHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		
		System.out.println( ( this.getClass().getName() ) + " Attempting Response" );
		try
		{
			System.out.println( "Request Method : " + ( t.getRequestMethod() ) );
			System.out.println( "Request URI : " + ( t.getRequestURI().toString() ) );
			
			String CONTENT_TYPE = "text/html";
			System.out.println( "Content Type : " + CONTENT_TYPE );
			t.getResponseHeaders().putIfAbsent( "Content-Type" , Collections.singletonList( CONTENT_TYPE ) );
			
			if( t instanceof HttpsExchange )
			{
				t.getResponseHeaders().putIfAbsent( "Access-Control-Allow-Origin" , Collections.singletonList( "*" ) );
			}
			
			StringBuilder htmlBuilder = new StringBuilder();

htmlBuilder.append( "\n"
		+ "<!DOCTYPE html>\n"
		+ "<html lang=\"en\">\n"
		+ "<head>\n"
		+ "    <meta charset=\"UTF-8\">\n"
		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
		+ "    <style>\n"
		+ "\n"
		+ "    </style>\n"
		+ "    <title>YET.ANOTHER.METAVERSE</title>\n"
		+ "</head>\n"
		+ "<body>\n"
		+ "    <h1>Entering Another Metaverse Toolkit (AMET)</h1>\n"
		+ "    \n"
		+ "    <p>To learn more about this metaverse <A href=\"" + CoreNames.ABOUT_PAGE_PATH + SystemNames.getApplicationServiceTheme() + "\">Click Here</A>\n"
		+ "    \n"
		+ "</body>\n"
		+ "<script type=\"module\" src=\"./" + CoreNames.VIRTUAL_REALITY_MAIN_JAVASCRIPT_PATH + "\"></script>\n"
		+ "</html>\n" );		
			
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

