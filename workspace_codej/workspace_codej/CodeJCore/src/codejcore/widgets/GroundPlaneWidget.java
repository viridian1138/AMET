


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
import codejcore.interfaces.INormMap;
import codejcore.interfaces.ISound;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;

/**
 * Widget for a planar texture map representing the ground
 * @author tgreen
 *
 */
public class GroundPlaneWidget extends Widget {
	
	/**
	 * Reference to the JS object for the THREE.JS geometry
	 */
	String geometryContext;
	
	/**
	 * Reference to the JS object for the THREE.JS mesh
	 */
	String meshContext;
	
	/**
	 * Normal map defining the texture of the ground plane
	 */
	INormMap norm;
	
	/**
	 * Whether audio has been defined for the ground plane
	 */
	boolean audioDefined = false;

	
	/**
	 * Constructor
	 * @param _geometryContext Reference to the JS object for the THREE.JS geometry
	 * @param _meshContext Reference to the JS object for the THREE.JS mesh
	 * @param _norm Normal map defining the texture of the ground plane
	 */
	protected GroundPlaneWidget( String _geometryContext , String _meshContext , INormMap _norm ) {
		geometryContext = _geometryContext;
		meshContext = _meshContext;
		norm = _norm;
	}
	
	/**
	 * Creates an instance of a ground plane widget
	 * @param gc The graphics context in which to display the ground plane widget
	 * @param pwi Object used to initialize the ground plane widget
	 * @return An instance of a ground plane widget
	 */
	public static GroundPlaneWidget create( GraphicsContext gc , INormMap norm )
	{
		
		System.out.println( "Creating Ground Plane Widget" );
		
		String ac = gc.getApplicationContext();
		
		String geometryWc = gc.generateNewWidgetContext();
		
		String meshWc = gc.generateNewWidgetContext();
		
		String geometryContext = "tmpObj." + ac + "." + geometryWc;
		
		String meshContext = "tmpObj." + ac + "." + meshWc;
		
		
		gc.appendJs( "{\n" );
		gc.appendJs( "	// Create the upright plane\n"
				+ "	const planeWidth = 256;\n"
				+ "	const planeHeight = 128;\n"
				+ geometryContext + " = new THREE.PlaneGeometry(\n"
				+ "		planeWidth,\n"
				+ "		planeHeight\n"
				+ "	);\n" );
		gc.appendJs( "\n" );
		gc.appendJs( "	const planeTextureMap = textureLoader.load( '" + ( norm.getTextureMapReference() ) + "' );\n"
				+ "	planeTextureMap.wrapS = THREE.RepeatWrapping;\n"
				+ "	planeTextureMap.wrapT = THREE.RepeatWrapping;\n"
				+ "	planeTextureMap.repeat.set(16, 16);\n"
				+ "	//planeTextureMap.magFilter = THREE.NearestFilter;\n"
				+ "	planeTextureMap.minFilter = THREE.NearestFilter;\n"
				+ "	planeTextureMap.anisotropy = gl.capabilities.getMaxAnisotropy();\n"
				+ "	const planeNorm = textureLoader.load( '" + ( norm.getNormReference() ) + "' );\n"
				+ "	planeNorm.wrapS = THREE.RepeatWrapping;\n"
				+ "	planeNorm.wrapT = THREE.RepeatWrapping;\n"
				+ "	planeNorm.minFilter = THREE.NearestFilter;\n"
				+ "	planeNorm.repeat.set(16, 16);\n"
				+ "	const planeMaterial = new THREE.MeshStandardMaterial({\n"
				+ "		map: planeTextureMap,\n"
				+ "		side: THREE.DoubleSide,\n"
				+ "		normalMap: planeNorm\n"
				+ "	});\n" );
		gc.appendJs( "\n"
		    + meshContext + " = new THREE.Mesh( " + geometryContext + " , planeMaterial );\n"
			+ "\n"
		    + meshContext + ".rotation.x = Math.PI / 2;\n"
			+ "\n"
			);
		gc.appendJs( "}\n" );
		
		
		return new GroundPlaneWidget( geometryContext , meshContext , norm );
		
	}
	

	@Override
	public void addToScene( GraphicsContext gc )
	{
		
		System.out.println( "Adding To Scene" );
		
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
			    + "	light.target = " + meshContext + ";\n"
				+ "\n"
				);
		gc.appendJs( "\n"
		    + "	scene.add( " + meshContext + " );\n"
			+ "\n"
			);
		gc.appendJs( "}\n" );
	}
	
	
	@Override
	public void dispose( GraphicsContext gc )
	{
		System.out.println( "Disposing... " + this );
		
		
		// !!!!!!!!!!!!!!!!!!!!1 Dispose Material As Well !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111
		
		
		gc.appendJs( "{\n" );
		gc.appendJs( "light.target = initialLightTarget;\n" );
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


