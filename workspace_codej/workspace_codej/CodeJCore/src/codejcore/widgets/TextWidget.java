


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

import codejcore.bounds.Bounds;
import codejcore.bounds.IBoundsHandler;
import codejcore.interfaces.ICompletionHandler;
import codejcore.interfaces.IFont;
import codejcore.interfaces.ISound;
import codejcore.interfaces.SessionDataApplicationToken;
import codejcore.interfaces.UiEvent;

/**
 * Class for a widget displaying text
 * @author tgreen
 *
 */
public class TextWidget extends Widget {
	
	/**
	 * Reference to the JS object for the THREE.JS geometry
	 */
	String geometryContext;
	
	/**
	 * Reference to the JS object for the THREE.JS mesh
	 */
	String meshContext;
	
	/**
	 * The font used to display the text
	 */
	IFont font;
	
	/**
	 * The text that the widget is to display
	 */
	String displayText;
	
	/**
	 * The material for the front of the text
	 */
	Material material;
	
	/**
	 * The material for the side of the text
	 */
	Material sideMaterial;
	
	/**
	 * The font size for the text
	 */
	double fontSize;
	
	/**
	 * Whether audio has been defined for the prism
	 */
	boolean audioDefined = false;


	/**
	 * The spatial position for the text
	 */
	Vector3 position;
	
	/**
	 * The ID for the text in terms of a JS reference
	 */
	protected volatile String tid;

	
	/**
	 * Constructor
	 * @param _geometryContext Reference to the JS object for the THREE.JS geometry
	 * @param _meshContext Reference to the JS object for the THREE.JS mesh
	 * @param gc The graphics context in which to display the text
	 * @param tw Object used to initialize the text
	 */
	protected TextWidget( String _geometryContext , String _meshContext , GraphicsContext gc , TextWidgetInitializer tw  ) {
		geometryContext = _geometryContext;
		meshContext = _meshContext;
		font = tw.getFont();
		displayText = tw.getDisplayText();
		position = tw.getPosition();
		material = tw.getMaterial(this, gc);
		sideMaterial = tw.getSideMaterial(this, gc);
		fontSize = tw.getFontSize();
	}
	
	/**
	 * Creates an instance of the text widget
	 * @param gc The graphics context in which to display the text
	 * @param tw Object used to initialize the text
	 * @return An instance of the text widget
	 */
	public static TextWidget create( GraphicsContext gc , TextWidgetInitializer tw )
	{
		
		System.out.println( "Creating Text Widget" );
		
		String ac = gc.getApplicationContext();
		
		String geometryWc = gc.generateNewWidgetContext();
		
		String meshWc = gc.generateNewWidgetContext();
		
		String geometryContext = "tmpObj." + ac + "." + geometryWc;
		
		String meshContext = "tmpObj." + ac + "." + meshWc;
		
		final String displayString = tw.getDisplayText();
		
		IFont font = tw.getFont();
		
		double fontSize = tw.getFontSize();
		
		
		
		TextWidget widget = new TextWidget( geometryContext , meshContext , gc , tw );
		
		final String tid = gc.generateNewWidgetContext();
		
		widget.tid = tid;
		
		font.handleFontLoading(gc.getToken(), gc, new ICompletionHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc) {
						


						gc.appendJs( "{\n" );
						gc.appendJs( 
								          "\n"
										+ "		{\n"
										+ "\n"
										+ "			const fntMultiplier = tmpObj.masterFontSizeMultiplier;\n"
										+ "			const fntSz = " + fontSize + " * fntMultiplier;\n"
										+ "			const fntHt = 10 * fntMultiplier;\n"
										+ "			const bvlThk = 1 * fntMultiplier;\n"
										+ "			const bvlSz = 1 * fntMultiplier;\n"
										+ "\n"
										+ "			" + geometryContext + " = new THREE.TextGeometry( '" + displayString + "' , {\n"
										+ "\n"
										+ "				font: " + font.getFontReference() + " ,\n"
										+ "\n"
										+ "				size: fntSz,\n"
										+ "				height: fntHt,\n"
										+ "				curveSegments: 12,\n"
										+ "\n"
										+ "				bevelThickness: bvlThk,\n"
										+ "				bevelSize: bvlSz,\n"
										+ "				bevelEnabled: true\n"
										+ "\n"
										+ "			});\n"
										+ "\n"
										+ "		}\n"
										+ "\n"
										+ "\n" );
									
						gc.initiateBoundsGeneration(geometryContext, tid);
						
						/* gc.appendJs( 
										"		const centerOffsetX = - 0.5 * ( " + geometryContext + ".boundingBox.max.x + " + geometryContext + ".boundingBox.min.x );\n"
										+ "\n"
										+ "		" + meshContext + " = new THREE.Mesh( " + geometryContext + 
											" , [ " + ( widget.material.getMaterialContext() ) + " , " + ( widget.sideMaterial.getMaterialContext() ) + " ] );\n"
										+ "		" + meshContext + ".position.x = " + tw.getPosition().x + " + centerOffsetX;\n"
										+ "		" + meshContext + ".position.y = " + tw.getPosition().y + ";\n"
										+ "		" + meshContext + ".position.z = " + tw.getPosition().z + ";\n"
										+ "\n"
										+ "		" + meshContext + ".castShadow = true;\n"
										+ "		" + meshContext + ".receiveShadow = true;\n"
										+ "\n" ); */
						
						gc.handleBoundsGeneration(
								tid, new IBoundsHandler()
								{

									@Override
									public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
										
										gc.appendJs( "{\n" );
										
										final double centerOffsetX = - 0.5 * ( bnd.getMin().getX() + bnd.getMax().getX() );
										
										gc.appendJs( 
												"		const centerOffsetX = " + centerOffsetX + ";\n"
												+ "\n"
												+ "		" + meshContext + " = new THREE.Mesh( " + geometryContext + 
													" , [ " + ( widget.material.getMaterialContext() ) + " , " + ( widget.sideMaterial.getMaterialContext() ) + " ] );\n"
												+ "		" + meshContext + ".position.x = " + tw.getPosition().x + " + centerOffsetX;\n"
												+ "		" + meshContext + ".position.y = " + tw.getPosition().y + ";\n"
												+ "		" + meshContext + ".position.z = " + tw.getPosition().z + ";\n"
												+ "\n"
												+ "		" + meshContext + ".castShadow = true;\n"
												+ "		" + meshContext + ".receiveShadow = true;\n"
												+ "\n" );
										
										gc.appendJs( "}\n" );
										
									}
									
								});
						
						
						
						gc.appendJs( "}\n" );
						
						
					}
			
				});
		
		return widget;
		
	}
	

	@Override
	public void addToScene( GraphicsContext gc )
	{

		System.out.println( "Adding To SceneA" );
		
		
		font.handleFontLoading(gc.getToken(), gc, new ICompletionHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc) {
						
						gc.handleBoundsGeneration(
								tid, new IBoundsHandler()
								{

									@Override
									public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
										
										System.out.println( "Adding To SceneB" );
						
						
										gc.appendJs( "{\n" );
										gc.appendJs( "\n"
													+ "	scene.add( " + meshContext + " );\n"
													+ "\n"
												);
										gc.appendJs( "}\n" );
									}
								} );
						
					}
			
				});

	}
	
	
	/**
	 * Gets the spatial position of the text
	 * @return The spatial position of the text
	 */
	public Vector3 getPosition()
	{
		return( position );
	}
	
	
	/**
	 * Sets the spatial position of the text
	 * @param p The position to be set
	 * @param gc The graphics context in which to set the position
	 */
	public void setPosition( Vector3 p , GraphicsContext gc )
	{
		
		System.out.println( "Setting PositionA" );

		
		gc.handleBoundsGeneration(
				tid, new IBoundsHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
						
						System.out.println( "Setting PositionB" );
						
						position = p;
						
						
						gc.appendJs( "{\n" );
						gc.appendJs( "\n"
							    + meshContext + ".position.set( " + p.getX() + " , " + p.getY() + " , " + p.getZ() + " );\n"
								+ "\n"
							);
						gc.appendJs( "}\n" );
					}
				} );
	}
	
	
	/**
	 * Gets the text that the widget is to display
	 * @return The text that the widget is to display
	 */
	public String getDisplayText() {
		return displayText;
	}
	
	
	/**
	 * Sets the text that the widget is to display
	 * @param _displayString The text that the widget is to display
	 * @param gc The graphics context in which to display the text
	 */
	public void setDisplayText( String _displayString , GraphicsContext gc )
	{
		
		System.out.println( "Setting Display StringA" );
		
		
		font.handleFontLoading(gc.getToken(), gc, new ICompletionHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc) {
						
						gc.handleBoundsGeneration(
								tid, new IBoundsHandler()
								{

									@Override
									public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
										
										System.out.println( "Setting Display StringB" );
										
										displayText = _displayString;
										

										gc.appendJs( "{\n" );
										gc.appendJs( 
												          "\n"
														+ "\n"
														+ "\n"
														+ "		const fntMultiplier = tmpObj.masterFontSizeMultiplier;\n"
														+ "		const fntSz = " + fontSize + " * fntMultiplier;\n"
														+ "		const fntHt = 10 * fntMultiplier;\n"
														+ "		const bvlThk = 1 * fntMultiplier;\n"
														+ "		const bvlSz = 1 * fntMultiplier;\n"
														+ "\n"
														+ "		const prevTextGeo = " + geometryContext + ";\n"
														+ "\n"
														+ "		" + geometryContext + " = new THREE.TextGeometry( '" + displayText + "' , {\n"
														+ "\n"
														+ "			font: " + font.getFontReference() + " ,\n"
														+ "\n"
														+ "			size: fntSz,\n"
														+ "			height: fntHt,\n"
														+ "			curveSegments: 12,\n"
														+ "\n"
														+ "			bevelThickness: bvlThk,\n"
														+ "			bevelSize: bvlSz,\n"
														+ "			bevelEnabled: true\n"
														+ "\n"
														+ "		});\n"
														+ "\n" );
										
										final String tid = gc.generateNewWidgetContext();
										
										TextWidget.this.tid = tid;
													
										gc.initiateBoundsGeneration(geometryContext, tid);
										
										/* gc.appendJs( 
														"		const centerOffsetX = - 0.5 * (" + geometryContext + ".boundingBox.max.x + " + geometryContext + ".boundingBox.min.x);\n"
														+ "\n"
														+ "		const bucketL2Height = 3;\n"
														+ "\n"
														+ "		const prevTextMesh = " + meshContext + ";\n"
														+ "\n"
														+ "		scene.remove( prevTextMesh );\n"
														+ "\n"
														+ "		" + meshContext + " = new THREE.Mesh( " + geometryContext + 
															" , "
															+ "[ " + ( material.getMaterialContext() ) + " , " + ( sideMaterial.getMaterialContext() ) + " ] );\n"
														+ "		" + meshContext + ".position.x = " + getPosition().x + " + centerOffsetX;\n"
														+ "		" + meshContext + ".position.y = " + getPosition().y + ";\n"
														+ "		" + meshContext + ".position.z = " + getPosition().z + ";\n"
														+ "\n"
														+ "		" + meshContext + ".castShadow = true;\n"
														+ "		" + meshContext + ".receiveShadow = true;\n"
														+ "\n"
														+ "		scene.add( " + meshContext + " );\n"
														+ "\n"
														+ "		prevTextMesh.geometry.dispose();\n"
														+ "		prevTextGeo.dispose();\n"
														+ "\n"
											); */
										
										gc.handleBoundsGeneration(
												tid, new IBoundsHandler()
												{

													@Override
													public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
														
														gc.appendJs( "{\n" );
														
														final double centerOffsetX = - 0.5 * ( bnd.getMin().getX() + bnd.getMax().getX() );
														
														gc.appendJs( 
																          "		const centerOffsetX = " + centerOffsetX + ";\n"
																		+ "\n"
																		+ "		const bucketL2Height = 3;\n"
																		+ "\n"
																		+ "		const prevTextMesh = " + meshContext + ";\n"
																		+ "\n"
																		+ "		scene.remove( prevTextMesh );\n"
																		+ "\n"
																		+ "		" + meshContext + " = new THREE.Mesh( " + geometryContext + 
																			" , "
																			+ "[ " + ( material.getMaterialContext() ) + " , " + ( sideMaterial.getMaterialContext() ) + " ] );\n"
																		+ "		" + meshContext + ".position.x = " + getPosition().x + " + centerOffsetX;\n"
																		+ "		" + meshContext + ".position.y = " + getPosition().y + ";\n"
																		+ "		" + meshContext + ".position.z = " + getPosition().z + ";\n"
																		+ "\n"
																		+ "		" + meshContext + ".castShadow = true;\n"
																		+ "		" + meshContext + ".receiveShadow = true;\n"
																		+ "\n"
																		+ "		scene.add( " + meshContext + " );\n"
																		+ "\n"
																		+ "		prevTextMesh.geometry.dispose();\n"
																		+ "		prevTextGeo.dispose();\n"
																		+ "\n" );
														
														gc.appendJs( "}\n" );
														
													}
													
												});
										
										
										
										
										gc.appendJs( "}\n" );
									}
								} );
						
					}
			
				});

	}
	
	
	/**
	 * Gets the ID for the text in terms of a JS reference
	 * @return The ID for the text in terms of a JS reference
	 */
	public String getTid() {
		return tid;
	}

	@Override
	public void dispose( GraphicsContext gc )
	{
		System.out.println( "DisposingA... " + this );

		font.handleFontLoading(gc.getToken(), gc, 
				new ICompletionHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc) {
						
						gc.handleBoundsGeneration(
								tid, new IBoundsHandler()
								{

									@Override
									public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
										
										System.out.println( "DisposingB... " + TextWidget.this );
										
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
										
										
										if( sideMaterial.getOwningObject() == this )
										{
											sideMaterial.dispose(gc);
										}
										
										
										gc.appendJs( "}\n" );
									}
								} );
						
					}
			
				});
		
		
		
	}
	

	@Override
	public void removeFromScene( GraphicsContext gc )
	{
		System.out.println( "Removing from sceneA... " + this );

		font.handleFontLoading(gc.getToken(), gc, 
				new ICompletionHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc) {
						
						gc.handleBoundsGeneration(
								tid, new IBoundsHandler()
								{

									@Override
									public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
										
										System.out.println( "Removing from sceneB... " + TextWidget.this );
										
										
										gc.appendJs( "{\n" );
										gc.appendJs( "\n"
											    + "scene.remove( " + meshContext + " );\n"
												+ "\n"
											);
										gc.appendJs( "}\n" );
									}
								} );
						
					}
			
				});
		
		
		
	}

	@Override
	public void addPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, final ISound snd) {
		
		gc.handleBoundsGeneration(
				tid, new IBoundsHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
						
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
				} );
		
	}

	
	
	@Override
	public void playPositionalAudio(UiEvent e, SessionDataApplicationToken sess, GraphicsContext gc, final ISound snd) {

		
		gc.handleBoundsGeneration(
				tid, new IBoundsHandler()
				{

					@Override
					public void dispatchEvent(SessionDataApplicationToken sess, GraphicsContext gc, Bounds bnd) {
						
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
				} );
	}

	
	
}


