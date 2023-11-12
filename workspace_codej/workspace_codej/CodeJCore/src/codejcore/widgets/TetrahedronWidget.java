


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



package codejcore.widgets;

import codejcore.interfaces.ICompletionHandler;
import codejcore.interfaces.ISound;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;

/**
 * A widget representing a tetrahedron.
 * @author tgreen
 *
 */
public class TetrahedronWidget extends Widget {
	
	/**
	 * Reference to the JS object for the THREE.JS geometry
	 */
	String geometryContext;
	
	/**
	 * Reference to the JS object for the THREE.JS mesh
	 */
	String meshContext;
	
	/**
	 * The center position for the tetrahedron
	 */
	Vector3 position;
	
	/**
	 * The material for the tetrahedron
	 */
	Material material;
	
	/**
	 * Whether audio has been defined for the tetrahedron
	 */
	boolean audioDefined = false;

	/**
	 * Constructor
	 * @param _geometryContext Reference to the JS object for the THREE.JS geometry
	 * @param _meshContext Reference to the JS object for the THREE.JS mesh
	 * @param gc The graphics context in which to display the tetrahedron
	 * @param bwi Object used to initialize the tetrahedron
	 */
	protected TetrahedronWidget( String _geometryContext , String _meshContext , GraphicsContext gc , TetrahedronWidgetInitializer bwi ) {
		geometryContext = _geometryContext;
		meshContext = _meshContext;
		material = bwi.getMaterial(this, gc);
		position = bwi.getPosition();
	}
	
	/**
	 * Creates an instance of the tetrahedron widget
	 * @param gc The graphics context in which to display the tetrahedron
	 * @param bwi Object used to initialize the tetrahedron
	 * @return An instance of the tetrahedron widget
	 */
	public static TetrahedronWidget create( GraphicsContext gc , TetrahedronWidgetInitializer bwi )
	{
		
		System.out.println( "Creating Tetrahedron Widget" );
		
		String ac = gc.getApplicationContext();
		
		String geometryWc = gc.generateNewWidgetContext();
		
		String meshWc = gc.generateNewWidgetContext();
		
		String geometryContext = "tmpObj." + ac + "." + geometryWc;
		
		String meshContext = "tmpObj." + ac + "." + meshWc;
		
		
		TetrahedronWidget widget = new TetrahedronWidget( geometryContext , meshContext , gc , bwi );
		
		
		Vector3 p = widget.getPosition();
		
		double radius = bwi.getRadius();
		
		int detail = bwi.getDetail();
		
		
		gc.appendJs( "{\n" );
		gc.appendJs( geometryContext + " = new THREE.TetrahedronGeometry(\n"
		    + "		" + radius + ",\n"
			+ "		" + detail + "\n"
			+ "	);\n"
			+ "\n"
		    + meshContext + " = new THREE.Mesh( " + geometryContext + " , " + ( widget.material.getMaterialContext() ) + " );\n"
			+ "\n"
		    + meshContext + ".position.set( " + p.getX() + " , " + p.getY() + " , " + p.getZ() + " );\n"
			+ "\n"
			);
		gc.appendJs( "}\n" );
		
		
		return widget;
		
	}
	
	
	@Override
	public void addToScene( GraphicsContext gc )
	{
		
		System.out.println( "Adding To Scene" );
		
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
		    + "	scene.add( " + meshContext + " );\n"
			+ "\n"
			);
		gc.appendJs( "}\n" );
	}
	
	/**
	 * Gets the center position of the tetrahedron
	 * @return The center position of the tetrahedron
	 */
	public Vector3 getPosition()
	{
		return( position );
	}
	
	/**
	 * Sets the center position of the tetrahedron
	 * @param p The position to which to set the center
	 * @param gc The graphics context in which to set the position
	 */
	public void setPosition( Vector3 p , GraphicsContext gc )
	{
		
		System.out.println( "Setting Position" );
		
		position = p;
		
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
			    + meshContext + ".position.set( " + p.getX() + " , " + p.getY() + " , " + p.getZ() + " );\n"
				+ "\n"
			);
		gc.appendJs( "}\n" );
	}
	
	
	@Override
	public void dispose( GraphicsContext gc )
	{
		System.out.println( "Disposing... " + this );
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
			    + meshContext + ".geometry.dispose( );\n"
				+ "\n"
			);
		gc.appendJs( "\n"
			    + geometryContext + ".dispose( );\n"
				+ "\n"
			);
		gc.appendJs( "\n"
			    + meshContext + " = undefined;\n"
				+ "\n"
			);
		gc.appendJs( "\n"
			    + geometryContext + " = undefined;\n"
				+ "\n"
			);
		
		
		if( material.getOwningObject() == this )
		{
			material.dispose(gc);
		}
		
		
		gc.appendJs( "}\n" );
		
	}
	

	@Override
	public void removeFromScene( GraphicsContext gc )
	{
		System.out.println( "Removing from scene... " + this );
		
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
			    + "scene.remove( " + meshContext + " );\n"
				+ "\n"
			);
		gc.appendJs( "}\n" );
		
	}

	@Override
	public void addPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, final ISound snd) {
		
		snd.handleSoundLoading(sess, gc,
				new ICompletionHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc) {
						

						gc.appendJs( "{\n" );
						gc.appendJs( 
						  "			const audio = new THREE.PositionalAudio(tmpObj.audioListener);\n"
						+ "			audio.setBuffer( " + ( snd.getSoundReference() ) + " );\n"
						+ "			" + meshContext + ".add(audio);\n" );
						gc.appendJs( "}\n" );
						
						audioDefined = true;
						
					}
			
				});
		
	}

	
	
	@Override
	public void playPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, final ISound snd) {
		
		snd.handleSoundLoading(sess, gc,
				new ICompletionHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc) {
						
						if( audioDefined )
						{
						

						gc.appendJs( "{\n" );
						gc.appendJs( 
						  "			var tobj = {};\n"
						+ "			tobj.mesh = " + meshContext + ";\n"
						+ "	        tobj.audioDefined = true;\n"
						+ "\n"
						+ "	        tobj.timeEnabled = true;\n"
						+ "		    const audio = tobj.mesh.children[0];\n"
						+ "		    if (audio.isPlaying) {\n"
						+ "			     audio.stop();\n"
						+ "			}\n"
						+ "	        tobj.audioBuffer = " + ( snd.getSoundReference() ) + ";\n"
						+ "			tmpObj.audioQueue.push( tobj );\n"
						+ "		// audio.isPlaying = false;\n"
						+ "			tobj.audioPlay = true;\n"
						);
						gc.appendJs( "}\n" );
						
						}
						else
						{
							System.out.println( "Audio Not Defined " + this );
						}
						
					}
			
				});
	}

	
	
}


