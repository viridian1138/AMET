


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
import java.util.Random;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsExchange;

import codejcore.arch.Session;
import codejcore.arch.SessionData;
import codejcore.arch.SessionStore;
import codejcore.interfaces.CoreNames;
import codejcore.interfaces.IFont;
import codejcore.interfaces.IMetaverseTheme;
import codejcore.interfaces.IUiDependency;
import codejcore.interfaces.MetaverseApplication;
import codejcore.widgets.EventHandlerNames;

/**
 * Class for instances of the web service to get the JS content (index_xr.js) of the CodeJ metaverse web application.
 * 
 * NOTE: some aspects of this file are still in the process of being transitioned to SmashWnd 
 * 
 * @author tgreen
 *
 */
public class IndexXRHandler implements HttpHandler {
	
	/**
	 * Default Constructor
	 */
	public IndexXRHandler()
	{
		System.out.println( "IndexXRHandler Initializing Session Table" );
		SessionStore.sessionTable.clear();
	}

	
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
		+ "\n"
		+ "\n"
		+ "//$$strtCprt\n"
		+ "/**\n"
		+ "* Another Metaverse Toolkit (AMET) \n"
		+ "* \n"
		+ "* Copyright (C) 2023 Thornton Green\n"
		+ "* \n"
		+ "* This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as\n"
		+ "* published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.\n"
		+ "* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty \n"
		+ "* of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.\n"
		+ "* You should have received a copy of the GNU General Public License along with this program; if not, \n"
		+ "* see <http://www.gnu.org/licenses>.\n"
		+ "* Additional permission under GNU GPL version 3 section 7\n"
		+ "*\n"
		+ "*/\n"
		+ "//$$endCprt\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n" );

{
IExtensionRegistry registry = Platform.getExtensionRegistry();
final String EXTENSION_POINT_NAME = "CodeJCore.UiSystemDependency";
IConfigurationElement[] config = registry.getConfigurationElementsFor( EXTENSION_POINT_NAME );
System.out.println( EXTENSION_POINT_NAME + " Registry Length : " + ( config.length ) );
for( IConfigurationElement e : config )
{
	System.out.println( "Evaluating Extension " + e );
	try
	{
		final Object o = e.createExecutableExtension( "class" );
		System.out.println( "Evaluating Executable Extension " + o );
		if( o instanceof IUiDependency )
		{
			final IUiDependency iuisystemdependency = (IUiDependency)( o );
			final String imprt = iuisystemdependency.getDependencyImportString();
			System.out.println( "Adding Import " + imprt );
			htmlBuilder.append( "\n" + imprt + "\n" );
			System.out.println( "Added Import " + imprt );
		}
	}
	catch( Throwable ex )
	{
		ex.printStackTrace( System.out );
	}
}
}


{
IExtensionRegistry registry = Platform.getExtensionRegistry();
final String EXTENSION_POINT_NAME = "CodeJCore.UiApplicationDependency";
IConfigurationElement[] config = registry.getConfigurationElementsFor( EXTENSION_POINT_NAME );
System.out.println( EXTENSION_POINT_NAME + " Registry Length : " + ( config.length ) );
for( IConfigurationElement e : config )
{
	System.out.println( "Evaluating Extension " + e );
	try
	{
		final Object o = e.createExecutableExtension( "class" );
		System.out.println( "Evaluating Executable Extension " + o );
		if( o instanceof IUiDependency )
		{
			final IUiDependency iuisystemdependency = (IUiDependency)( o );
			final String imprt = iuisystemdependency.getDependencyImportString();
			System.out.println( "Adding Import " + imprt );
			htmlBuilder.append( "\n" + imprt + "\n" );
			System.out.println( "Added Import " + imprt );
		}
	}
	catch( Throwable ex )
	{
		ex.printStackTrace( System.out );
	}
}
}


htmlBuilder.append( "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "var gl, cube, marker1, marker2, light, scene, canvas, glContext, tmpObj;\n"
		+ "init();\n"
		+ "animate();\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// Handles the initialization of the script.\n"
		+ "function init() {\n"
		+ "\n"
		+ "	const dt = new Date();\n"
		+ "	const millis = parseInt(dt.getMilliseconds());\n"
		+ "	const millis2 = parseInt(millis % 777);\n"
		+ "	for (let cnt = 0; cnt < millis2; cnt++) {\n"
		+ "		var vv = Math.random();\n"
		+ "	}\n"
		+ "\n"
		+ "	// create geometryContext\n"
		+ "	gl = new THREE.WebGLRenderer({ antialias: true });\n"
		+ "	gl.setPixelRatio(window.devicePixelRatio);\n"
		+ "	gl.setSize(window.innerWidth, window.innerHeight);\n"
		+ "	gl.outputEncoding = THREE.sRGBEncoding;\n"
		+ "	gl.xr.enabled = true;\n"
		+ "	document.body.appendChild(gl.domElement);\n"
		+ "	document.body.appendChild(VRButton.createButton(gl));\n"
		+ "\n"
		+ "\n"
		+ "	// tmpObj\n"
		+ "	tmpObj = {};\n\n" );



final long time = System.currentTimeMillis();
final long id = SessionStore.currentId;
SessionStore.currentId++;

Random r1 = new Random( time );
for( int cnt = 0 ; cnt < 5 ; cnt++ )
{
	r1.nextLong();
}


final long xtime = Math.abs( r1.nextLong() );


Random r2 = new Random( id );
for( int cnt = 0 ; cnt < 5 ; cnt++ )
{
	r2.nextLong();
}


final long xid = Math.abs( r2.nextLong() );


Session session = new Session(xtime, xid);



if( SessionStore.applications.isEmpty() )
{
	IExtensionRegistry registry = Platform.getExtensionRegistry();
	final String EXTENSION_POINT_NAME = "CodeJCore.MetaverseApplication";
	IConfigurationElement[] config = registry.getConfigurationElementsFor( EXTENSION_POINT_NAME );
	System.out.println( EXTENSION_POINT_NAME + " Registry Length : " + ( config.length ) );
	for( IConfigurationElement e : config )
	{
		System.out.println( "Evaluating Extension " + e );
		try
		{
			final Object o = e.createExecutableExtension( "class" );
			System.out.println( "Evaluating Executable Extension " + o );
			if( o instanceof MetaverseApplication )
			{
				final MetaverseApplication app = (MetaverseApplication)( o );
				final String name = app.getApplicationName();
				System.out.println( "Adding App " + name );
				SessionStore.applications.put(name, app);
				System.out.println( "Added App " + name );
			}
		}
		catch( Throwable ex )
		{
			ex.printStackTrace( System.out );
		}
	}
}






if( SessionStore.fonts.isEmpty() )
{
	IExtensionRegistry registry = Platform.getExtensionRegistry();
	final String EXTENSION_POINT_NAME = "CodeJCore.Font";
	IConfigurationElement[] config = registry.getConfigurationElementsFor( EXTENSION_POINT_NAME );
	System.out.println( EXTENSION_POINT_NAME + " Registry Length : " + ( config.length ) );
	for( IConfigurationElement e : config )
	{
		System.out.println( "Evaluating Extension " + e );
		try
		{
			final Object o = e.createExecutableExtension( "class" );
			System.out.println( "Evaluating Executable Extension " + o );
			if( o instanceof IFont )
			{
				final IFont font = (IFont)( o );
				final String name = font.getFontName();
				System.out.println( "Adding Font " + name );
				SessionStore.fonts.put(name, font);
				System.out.println( "Added Font " + name );
			}
		}
		catch( Throwable ex )
		{
			ex.printStackTrace( System.out );
		}
	}
}






if( SessionStore.themes.isEmpty() )
{
	IExtensionRegistry registry = Platform.getExtensionRegistry();
	final String EXTENSION_POINT_NAME = "CodeJCore.Theme";
	IConfigurationElement[] config = registry.getConfigurationElementsFor( EXTENSION_POINT_NAME );
	System.out.println( EXTENSION_POINT_NAME + " Registry Length : " + ( config.length ) );
	for( IConfigurationElement e : config )
	{
		System.out.println( "Evaluating Extension " + e );
		try
		{
			final Object o = e.createExecutableExtension( "class" );
			System.out.println( "Evaluating Executable Extension " + o );
			if( o instanceof IMetaverseTheme )
			{
				final IMetaverseTheme theme = (IMetaverseTheme)( o );
				final String name = theme.getThemeName();
				System.out.println( "Adding Theme " + name );
				SessionStore.themes.put(name, theme);
				System.out.println( "Added Theme " + name );
			}
		}
		catch( Throwable ex )
		{
			ex.printStackTrace( System.out );
		}
	}
}



MetaverseApplication initialApplication = SessionStore.applications.get( EventHandlerNames.GRAND_CENTRAL_STATION_APP  );

System.out.println( "Initial Application " + initialApplication );



IMetaverseTheme initialTheme = SessionStore.themes.get( EventHandlerNames.EMERALD_THEME );

System.out.println( "Initial Theme " + initialTheme );



SessionData data = new SessionData(xtime, xid, initialApplication, initialTheme);


SessionStore.sessionTable.put(session, data);


htmlBuilder.append( "tmpObj." + EventHandlerNames.PROPERTY_ARCH_TIME + " = \"" + xtime + "\";\n\n" );

htmlBuilder.append( "tmpObj." + EventHandlerNames.PROPERTY_ARCH_ID + " = \"" + xid + "\";\n\n" );


htmlBuilder.append( "\n"
		+ "\n"
		+ "\n"
		+ "	// create camera\n"
		+ "	const angleOfView = 55;\n"
		+ "	const aspectRatio = window.innerWidth / window.innerHeight;\n"
		+ "	const nearPlane = 0.1;\n"
		+ "	const farPlane = 1000;\n"
		+ "	tmpObj.camera = new THREE.PerspectiveCamera(\n"
		+ "		angleOfView,\n"
		+ "		aspectRatio,\n"
		+ "		nearPlane,\n"
		+ "		farPlane\n"
		+ "	);\n"
		+ "	const initialCameraY = 2; /* 8 */\n"
		+ "	const initialCameraZ = 2; /* 30 */\n"
		+ "	tmpObj.camera.position.set(0, initialCameraY, initialCameraZ);\n"
		+ "	tmpObj.realCamera = tmpObj.camera;\n"
		+ "\n"
		+ "	// create the scene\n"
		+ "	scene = new THREE.Scene();\n"
		+ "	scene.background = new THREE.Color(0x77b5fe);\n"
		+ "	const fog = new THREE.Fog(\"grey\", 1, 90);\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	// audio\n"
		+ "	tmpObj.audioLoader = new THREE.AudioLoader();\n"
		+ "	tmpObj.audioListener = new THREE.AudioListener();\n"
		+ "	tmpObj.audioQueue = [];\n"
		+ "	tmpObj.camera.add(tmpObj.audioListener);\n"
		+ "	processAudioCtx(tmpObj);\n"
		+ "	tmpObj.dumbbellHeight = 5.75;\n"
		+ "	tmpObj.pushKickTopHeight = 2.0;\n"
		+ "	tmpObj.pushKickMiddleHeight = 1.25;\n"
		+ "	tmpObj.pushKickBottomHeight = 0.5;\n"
		+ "	tmpObj.legHeight = 2.5;\n"
		+ "	tmpObj.uppercutHeight = 7.0;\n"
		+ "	tmpObj.hookHeadHeight = 7.666;\n"
		+ "	tmpObj.solarplexHeight = 6.0;\n"
		+ "	tmpObj.abdomenHeight = 5.0;\n"
		+ "	tmpObj.uppercutJumpHeight = 8.0;\n"
		+ "	tmpObj.hammerStrikeHeight = 8.0;\n"
		+ "	tmpObj.idealHumanHeadHeights = 8.0;\n"
		+ "	tmpObj.dumbbellHits = parseInt(0);\n"
		+ "	tmpObj.prevDumbbellHits = parseInt(-1);\n"
		+ "	tmpObj.rightPunchHits = parseInt(0);\n"
		+ "	tmpObj.prevRightPunchHits = parseInt(-1);\n"
		+ "	tmpObj.leftPunchHits = parseInt(0);\n"
		+ "	tmpObj.prevLeftPunchHits = parseInt(-1);\n"
		+ "	tmpObj.masterFontSizeMultiplier = 0.0125;\n"
		+ "	tmpObj.fontSizeMultiplierScoring = 0.75;\n"
		+ "	tmpObj.jumpHurdleHeight = 0.05;\n"
		+ "	tmpObj.updateDumbbellHits = true;\n"
		+ "	tmpObj.updateLeftPunchHits = true;\n"
		+ "	tmpObj.updateRightPunchHits = true;\n"
		+ "	tmpObj.backDistBucket = -7;\n"
		+ "	tmpObj.bucketXOffset = 3.8;\n"
		+ "\n"
		+ "	{\n"
		+ "	    var difficulty = 10;\n"
		+ "	    tmpObj.referenceTimeSplit = parseInt( ( difficulty / 50.0 ) * 250 / 2.1);\n"
		+ "	    tmpObj.randomStrategyTimeSplit = parseInt( ( difficulty / 50.0 ) * 500 / 2);\n"
		+ "		const split2 = tmpObj.referenceTimeSplit;\n"
		+ "		tmpObj.flightTime = parseInt(split2 * 5 * 1.6);\n"
		+ "		tmpObj.flightTimeKick = parseInt(split2 * 5 * 1.6 * 1.2);\n"
		+ "		tmpObj.flightTimeStraightPunch = parseInt(split2 * 5 * 1.6 * 0.8);\n"
		+ "		tmpObj.flightTimeDumbbell = parseInt(split2 * 5 * 1.6 * 0.8);\n"
		+ "		tmpObj.jumpingKickDelay = 1.5 * split2;\n"
		+ "	}\n"
		+ "	\n"
		+ "\n"
		+ "\n"
		+ "	// GEOMETRY\n"
		+ "	// create the cube\n"
		+ "	const cubeSize = 4;\n"
		+ "	const cubeGeometry = new THREE.BoxGeometry(\n"
		+ "		cubeSize,\n"
		+ "		cubeSize,\n"
		+ "		cubeSize\n"
		+ "	);\n"
		+ "\n"
		+ "	// create the cube\n"
		+ "	const bucketL2Height = 3;\n"
		+ "	const bucketL2Width = 1.0;\n"
		+ "	\n"
		+ "	const markerHeight = 0.02;\n"
		+ "	const markerDepth = 0.04;\n"
		+ "	const markerLength = 2.0 * tmpObj.bucketXOffset;\n"
		+ "	const marker1Geometry = new THREE.BoxGeometry(\n"
		+ "		markerLength,\n"
		+ "		markerHeight,\n"
		+ "		markerDepth /* 10.0 */\n"
		+ "	);\n"
		+ "	const marker2Geometry = new THREE.BoxGeometry(\n"
		+ "		markerDepth,\n"
		+ "		markerHeight,\n"
		+ "		/* markerLength */ 20.0\n"
		+ "	);\n"
		+ "	\n"
		+ "\n"
		+ "	// create the target geometry\n"
		+ "	tmpObj.targetPunchSize = 0.16;\n"
		+ "	const targetPunchGeometry = new THREE.BoxGeometry(\n"
		+ "		tmpObj.targetPunchSize,\n"
		+ "		tmpObj.targetPunchSize,\n"
		+ "		tmpObj.targetPunchSize\n"
		+ "	);\n"
		+ "\n"
		+ "	// create the target geometry\n"
		+ "	const targetKickSize = 0.16;\n"
		+ "	const targetKickGeometry = new THREE.TetrahedronGeometry(\n"
		+ "		targetKickSize,\n"
		+ "		0\n"
		+ "	);\n"
		+ "\n"
		+ "	// create the jump hurdle geometry\n"
		+ "	const jumpHurdleSizeY = 0.16;\n"
		+ "	const jumpHurdleSizeXZ = 0.46;\n"
		+ "	const jumpHurdleGeometry = new THREE.BoxGeometry(\n"
		+ "		jumpHurdleSizeXZ,\n"
		+ "		jumpHurdleSizeY,\n"
		+ "		jumpHurdleSizeXZ\n"
		+ "	);\n"
		+ "\n"
	//	+ "	// Create the upright plane\n"
	//	+ "	const planeWidth = 256;\n"
	//	+ "	const planeHeight = 128;\n"
	//	+ "	const planeGeometry = new THREE.PlaneGeometry(\n"
	//	+ "		planeWidth,\n"
	//	+ "		planeHeight\n"
	//	+ "	);\n"
	//	+ "\n"
		+ "	// MATERIALS\n"
		+ "	const textureLoader = new THREE.TextureLoader();\n"
		+ "\n"
		+ "	const cubeMaterial = new THREE.MeshPhongMaterial({\n"
		+ "		color: 'pink'\n"
		+ "	});\n"
		+ "\n"
		+ "	const markerMaterial = new THREE.MeshPhongMaterial({\n"
		+ "		color: 0x5500bb\n"
		+ "	});\n"
		+ "\n"
		+ "	const targetMaterialA = new THREE.MeshPhongMaterial({\n"
		+ "		color: 0xe46a54\n"
		+ "	});\n"
		+ "\n"
		+ "	const targetMaterialB = new THREE.MeshPhongMaterial({\n"
		+ "		color: 0xb4ecbb\n"
		+ "	});\n"
		+ "\n"
		+ "	const jumpHurdleMaterialA = new THREE.MeshPhongMaterial({\n"
		+ "		color: 0xf1edc2\n"
		+ "	});\n"
		+ "\n"
		+ "	tmpObj.textMaterial = new THREE.MeshPhongMaterial({\n"
		+ "		color: 0xffc000\n"
		+ "	});\n"
		+ "\n"
		+ "	tmpObj.endTime = parseInt(-5000);\n"
		+ "	tmpObj.nextSequence = null;\n"
		+ "\n"
		+ "\n"
	//	+ "	const planeTextureMap = textureLoader.load('textures/177.jpg');\n"
	//	+ "	planeTextureMap.wrapS = THREE.RepeatWrapping;\n"
	//	+ "	planeTextureMap.wrapT = THREE.RepeatWrapping;\n"
	//	+ "	planeTextureMap.repeat.set(16, 16);\n"
	//	+ "	//planeTextureMap.magFilter = THREE.NearestFilter;\n"
	//	+ "	planeTextureMap.minFilter = THREE.NearestFilter;\n"
	//	+ "	planeTextureMap.anisotropy = gl.capabilities.getMaxAnisotropy();\n"
	//	+ "	const planeNorm = textureLoader.load('textures/177_norm.jpg');\n"
	//	+ "	planeNorm.wrapS = THREE.RepeatWrapping;\n"
	//	+ "	planeNorm.wrapT = THREE.RepeatWrapping;\n"
	//	+ "	planeNorm.minFilter = THREE.NearestFilter;\n"
	//	+ "	planeNorm.repeat.set(16, 16);\n"
	//	+ "	const planeMaterial = new THREE.MeshStandardMaterial({\n"
	//	+ "		map: planeTextureMap,\n"
	//	+ "		side: THREE.DoubleSide,\n"
	//	+ "		normalMap: planeNorm\n"
	//	+ "	});\n"
		+ "	scene.fog = fog;\n"
		+ "\n"
		+ "	const fontLoader = new THREE.FontLoader();\n"
		+ "\n"
		+ "	tmpObj.bucketHypotenuse = Math.sqrt(\n"
		+ "		tmpObj.backDistBucket * tmpObj.backDistBucket +\n"
		+ "		tmpObj.bucketXOffset * tmpObj.bucketXOffset\n"
		+ "	);\n"
		+ "\n"
		+ "	tmpObj.bucketCosine = - tmpObj.backDistBucket / tmpObj.bucketHypotenuse;\n"
		+ "\n"
		+ "	tmpObj.bucketSine = tmpObj.bucketXOffset / tmpObj.bucketHypotenuse;\n"
		+ "\n"
		+ "	tmpObj.bucketDistRatio = - tmpObj.bucketHypotenuse / tmpObj.backDistBucket;\n"
		+ "\n"
		+ "	// MESHES\n"
		+ "	cube = new THREE.Mesh(cubeGeometry, cubeMaterial);\n"
		+ "	cube.position.set(-(cubeSize + 1), cubeSize + 1, 0);\n"
		+ "	scene.add(cube);\n"
		+ "\n"
		+ "	marker1 = new THREE.Mesh(marker1Geometry, markerMaterial);\n"
		+ "	marker1.position.set(-0, 0, 0);\n"
		+ "	scene.add(marker1);\n"
		+ "\n"
		+ "	marker2 = new THREE.Mesh(marker2Geometry, markerMaterial);\n"
		+ "	marker2.position.set(-0, 0, 0);\n"
		+ "	scene.add(marker2);\n"
		+ "\n"
		+ "{\n"
		+ "var dstr = \"\\n\";"
		+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_TIME + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_TIME + " + \"\\n\";\n"
		+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_ID + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_ID + " + \"\\n\";\n"
	
		+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_EVENT_TYPE + "=" + EventHandlerNames.START_EVENT_TYPE + "\\n\";\n"
		
		+ "var aaa = $.post( \"" + CoreNames.METAVERSE_CORE_SYSTEM_PATH + "\" , dstr , function(data, status) {\n"
		+ "console.log( \"data: \" + data + \" status: \" + status );\n"
		+ "eval( data );\n"
		+ "console.log( \"zmsg: \" + tmpObj.zmsg );\n"
		+ "   });\n" // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		+ "}\n"
		+ "\n"
		+ "\n"
		+ "document.addEventListener( 'keydown', (e) => {\n"
		+ "\n"
		+ "		requestIdleCallback( function() { \n"
		+ "var dstr = \"\\n\";"
		+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_TIME + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_TIME + " + \"\\n\";\n"
		+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_ARCH_ID + "=\" + tmpObj." + EventHandlerNames.PROPERTY_ARCH_ID + " + \"\\n\";\n"
	
		+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_EVENT_TYPE + "=" + EventHandlerNames.KEYDOWN_EVENT_TYPE + "\\n\";\n"
		
		+ "dstr = dstr + \"" + EventHandlerNames.PROPERTY_EVENT_KEY_CODE + "=\" + e.code + \"\\n\";\n"
		
		+ "dstr = dstr + \"shiftKey=\" + e.shiftKey + \"\\n\";\n"
		+ "var aaa = $.post( \"" + CoreNames.METAVERSE_CORE_SYSTEM_PATH + "\" , dstr , function(data, status) {\n"
		+ "console.log( \"data: \" + data + \" status: \" + status );\n"
		+ "eval( data );\n"
		+ "console.log( \"zmsg: \" + tmpObj.zmsg );\n"
		+ "   });\n" // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		+ "\n"
		+ "\n"
		+ " } \n"
			+ " , {timeout: 30000} ); \n"
		+ "\n"
		+ "\n"
		+ "});\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	tmpObj.maxTargets = parseInt(18 /* 15 */ /* 10 */);\n"
		+ "	tmpObj.maxDumbbels = parseInt(6);\n"
		+ "	tmpObj.maxJumpHurdles = parseInt(6);\n"
		+ "	tmpObj.utimeStrt = parseInt(-5500);\n"
		+ "	tmpObj.utimeEnd = parseInt(-5000);\n"
		+ "	tmpObj.targetFarDist = -10;\n"
		+ "\n"
		+ "\n"
		+ "	tmpObj.stTimeA = parseInt(-5);\n"
		+ "\n"
		+ "	tmpObj.stTimeB = parseInt(-5);\n"
		+ "\n"
		+ "\n"
		+ "	// const controller0 = gl.xr.getController(0);\n"
		+ "	const controllerGrip0 = gl.xr.getControllerGrip(0);\n"
		+ "\n"
		+ "	// const controller1 = gl.xr.getController(1);\n"
		+ "	const controllerGrip1 = gl.xr.getControllerGrip(1);\n"
		+ "\n"
		+ "\n"
		+ "	tmpObj.controllerSize = 0.1;\n"
		+ "\n"
		+ "	const controllerGeometry = new THREE.BoxGeometry(\n"
		+ "		tmpObj.controllerSize,\n"
		+ "		tmpObj.controllerSize,\n"
		+ "		tmpObj.controllerSize\n"
		+ "	);\n"
		+ "\n"
		+ "	const controllerMaterial0 = new THREE.MeshPhongMaterial({\n"
		+ "		color: 0xff0000\n"
		+ "	});\n"
		+ "\n"
		+ "	const controllerMaterial1 = new THREE.MeshPhongMaterial({\n"
		+ "		color: 0x00ff00\n"
		+ "	});\n"
		+ "\n"
		+ "	tmpObj.controllerMesh0 = new THREE.Mesh(controllerGeometry, controllerMaterial0);\n"
		+ "	tmpObj.controllerMesh0.position.set(0, 0, 0.1);\n"
		+ "\n"
		+ "	tmpObj.controllerMesh1 = new THREE.Mesh(controllerGeometry, controllerMaterial1);\n"
		+ "	tmpObj.controllerMesh1.position.set(0, 0, 0.1);\n"
		+ "\n"
		+ "\n"
		+ "	controllerGrip0.add(tmpObj.controllerMesh0);\n"
		+ "	scene.add(controllerGrip0);\n"
		+ "\n"
		+ "	controllerGrip1.add(tmpObj.controllerMesh1);\n"
		+ "	scene.add(controllerGrip1);\n"
		+ "\n"
		+ "\n"
		+ "\n"
	//	+ "	const plane = new THREE.Mesh(planeGeometry, planeMaterial);\n"
	//	+ "	plane.rotation.x = Math.PI / 2;\n"
	//	+ "	//scene.add(plane);\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	// create objects when audio buffer is loaded\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	//LIGHTS\n"
		+ "	const color = 0xffffff;\n"
		+ "	const intensity = .7;\n"
		+ "	light = new THREE.DirectionalLight(color, intensity);\n"
		+ "	const initialLightTarget = light.target;\n"
	//	+ "	light.target = plane;\n"
		+ "	light.position.set(0, 30, 30);\n"
		+ "	scene.add(light);\n"
	//	+ "	scene.add(light.target);\n"
		+ "\n"
		+ "	const ambientColor = 0xffffff;\n"
		+ "	const ambientIntensity = 0.2;\n"
		+ "	const ambientLight = new THREE.AmbientLight(ambientColor, ambientIntensity);\n"
		+ "	scene.add(ambientLight);\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	tmpObj.now = performance.now();\n"
		+ "	tmpObj.curSecs = -5;\n"
		+ "\n"
		+ "} // init\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// Handles the processing of the current audio geometryContext.\n"
		+ "function processAudioCtx(tmpObj) {\n"
		+ "	if (tmpObj.audioCtx == undefined) {\n"
		+ "		tmpObj.audioCtx = new (window.AudioContext || window.webkitAudioContext)();\n"
		+ "	}\n"
		+ "	else {\n"
		+ "		const astate = tmpObj.audioCtx.state;\n"
		+ "		if (astate != \"running\") {\n"
		+ "			tmpObj.audioCtx = new (window.AudioContext || window.webkitAudioContext)();\n"
		+ "		}\n"
		+ "		else {\n"
		+ "			// console.log( \"Skippy Skip!!!!!!!!!!!\" );\n"
		+ "		}\n"
		+ "	}\n"
		+ "} // processAudioCtx\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// Enables the positional audio on a tobj indicating a collision.\n"
		+ "function enableColisionSound(tmpObj, tobj) {\n"
		+ "	tobj.timeEnabled = true;\n"
		+ "	if (tobj.audioDefined) {\n"
		+ "		const audio = tobj.mesh.children[0];\n"
		+ "		if (audio.isPlaying) {\n"
		+ "			audio.stop();\n"
		+ "		}\n"
		+ "		// audio.isPlaying = false;\n"
		+ "		tobj.audioPlay = true;\n"
		+ "	}\n"
		+ "} // enableColisionSound\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// Performs the playing of positional audio on a tobj indicating a collision.\n"
		+ "function processTobjAudioPlay(tmpObj, tobj, audioBuffer) {\n"
		+ "	if (tobj.audioPlay) {\n"
		+ "		processAudioCtx(tmpObj);\n"
		+ "		const audio = tobj.mesh.children[0];\n"
		+ "		audio.context = tmpObj.audioCtx;\n"
		+ "		audio.panner = audio.context.createPanner();\n"
		+ "		audio.panner.panningModel = 'HRTF';\n"
		+ "		//		audio.play(); // play audio upon leaving bucket\n"
		+ "		const audioCtx = tmpObj.audioCtx;\n"
		+ "		if (audioCtx != undefined) {\n"
		+ "			const source = audioCtx.createBufferSource();\n"
		+ "			source.buffer = audioBuffer;\n"
		+ "			audio.setBuffer(audioBuffer);\n"
		+ "			audio.panner.connect(audioCtx.destination);\n"
		+ "			// audio.setNodeSource( source );\n"
		+ "			console.log( audioBuffer );\n"
		+ "			// source.connect(audioCtx.destination);\n"
		+ "			source.connect(audio.panner);\n"
		+ "			source.start();\n"
		+ " console.log( source ); \n"
		+ "		}\n"
		+ "	}\n"
		+ "	tobj.audioPlay = false;\n"
		+ "} // processTobjAudioPlay\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// Checks whether a dumbbell has collided with a cylinder under the player view.\n"
		+ "// NOTE: The algorithm used for collision checking is fairly dumb.\n"
		+ "function checkDumbbellCollision(tmpObj, tobj) {\n"
		+ "	// if tmpObj.realCamera != tmpObj.camera then VR is being processed.\n"
		+ "	if (!(tobj.collided) /* && ( tmpObj.realCamera != tmpObj.camera ) */) {\n"
		+ "		const cameraPos = new THREE.Vector3();\n"
		+ "		tmpObj.realCamera.getWorldPosition(cameraPos);\n"
		+ "		const dumbbellPos = tobj.mesh.position;\n"
		+ "		// Dumbbell size plus viewer head size\n"
		+ "		const sizeConstraint = tmpObj.dumbbellSize * 0.5 + (0.15 * 0.5);\n"
		+ "\n"
		+ "		const collidedX = Math.abs(dumbbellPos.x - cameraPos.x) <= sizeConstraint;\n"
		+ "\n"
		+ "		const collidedY = dumbbellPos.y <= (cameraPos.y + 0.03);\n"
		+ "\n"
		+ "		const collidedZ = Math.abs(dumbbellPos.z - cameraPos.z) <= sizeConstraint;\n"
		+ "\n"
		+ "		const collided = collidedX && collidedY && collidedZ;\n"
		+ "		if (collided) {\n"
		+ "			enableColisionSound(tmpObj, tobj);\n"
		+ "			tmpObj.dumbbellHits = parseInt(tmpObj.dumbbellHits + 1);\n"
		+ "			tobj.collided = true;\n"
		+ "		}\n"
		+ "	}\n"
		+ "} // checkDumbbellCollision\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// Checks whether a controller has punched the correct target.\n"
		+ "// NOTE: The algorithm used for collision checking is fairly dumb.\n"
		+ "function checkControllerCollision(tmpObj, controllerMesh, tobj) {\n"
		+ "	var collided = false;\n"
		+ "	// if tmpObj.realCamera != tmpObj.camera then VR is being processed.\n"
		+ "	if (!(tobj.collided) /* && ( tmpObj.realCamera != tmpObj.camera ) */) {\n"
		+ "		const controllerPos = new THREE.Vector3();\n"
		+ "		controllerMesh.getWorldPosition(controllerPos);\n"
		+ "		const targetPos = tobj.mesh.position;\n"
		+ "\n"
		+ "		const sizeConstraint = (tmpObj.controllerSize) * 0.5 + (tmpObj.targetPunchSize) * 0.5;\n"
		+ "\n"
		+ "		const collidedX = Math.abs(targetPos.x - controllerPos.x) <= sizeConstraint;\n"
		+ "\n"
		+ "		const collidedY = Math.abs(targetPos.y - controllerPos.y) <= sizeConstraint;\n"
		+ "\n"
		+ "		const collidedZ = Math.abs(targetPos.z - controllerPos.z) <= sizeConstraint;\n"
		+ "\n"
		+ "		collided = collidedX && collidedY && collidedZ;\n"
		+ "		if (collided) {\n"
		+ "			enableColisionSound(tmpObj, tobj);\n"
		+ "			tobj.collided = true;\n"
		+ "		}\n"
		+ "	}\n"
		+ "	return collided;\n"
		+ "} // checkControllerCollision\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// Sets the animation of the display.\n"
		+ "function animate() {\n"
		+ "	gl.setAnimationLoop(draw);\n"
		+ "} // animate\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "// \"Draws\" the contents of the display.\n"
		+ "function draw(time) {\n"
		+ "	time *= 0.001;\n"
		+ "\n"
		+ "	if (resizeDisplay) {\n"
		+ "		tmpObj.camera.aspect = window.innerWidth / window.innerHeight;\n"
		+ "		tmpObj.camera.updateProjectionMatrix();\n"
		+ "	}\n"
		+ "\n"
		+ "	cube.rotation.x += 0.01;\n"
		+ "	cube.rotation.y += 0.01;\n"
		+ "	cube.rotation.z += 0.01;\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	const now = parseInt(performance.now());\n"
		+ "	const curSecs = parseInt(now / 1000);\n"
		+ "\n"
		+ "\n"
		+ "	if (now > tmpObj.endTime) {\n"
		+ "		if (tmpObj.endTime < -3000) {\n"
		+ "			tmpObj.endTime = now;\n"
		+ "		}\n"
		+ "		\n"
		+ "\n"
		+ "	    {\n"
		+ "	        var difficulty = 10;\n"
		+ "	        tmpObj.referenceTimeSplit = parseInt( ( difficulty / 50.0 ) * 250 / 2.1);\n"
		+ "	        tmpObj.randomStrategyTimeSplit = parseInt( ( difficulty / 50.0 ) * 500 / 2);\n"
		+ "		    const split2 = tmpObj.referenceTimeSplit;\n"
		+ "		    tmpObj.flightTime = parseInt(split2 * 5 * 1.6);\n"
		+ "		    tmpObj.flightTimeKick = parseInt(split2 * 5 * 1.6 * 1.2);\n"
		+ "		    tmpObj.flightTimeStraightPunch = parseInt(split2 * 5 * 1.6 * 0.8);\n"
		+ "		    tmpObj.flightTimeDumbbell = parseInt(split2 * 5 * 1.6 * 0.8);\n"
		+ "		    tmpObj.jumpingKickDelay = 1.5 * split2;\n"
		+ "	    }\n"
		+ "\n"
		+ "\n"
		+ "		const INCHES_TO_CENTIMETERS = 2.54;\n"
		+ "		const CENTIMETERS_TO_METERS = 0.01;\n"
		+ "		const FEET_TO_INCHES = 12;\n"
		+ "		\n"
		+ "		var heightFeet = 6;\n"
		+ "		\n"
		+ "		var heightInches = 2;\n"
		+ "\n"
		+ "		tmpObj.defenseHeight = (heightFeet * FEET_TO_INCHES + heightInches) * INCHES_TO_CENTIMETERS * CENTIMETERS_TO_METERS;\n"
		+ "\n"
		+ "		const deltaHeight = 6 * INCHES_TO_CENTIMETERS * CENTIMETERS_TO_METERS * Math.random();\n"
		+ "\n"
		+ "		tmpObj.offenseHeight = tmpObj.defenseHeight + (Math.random() < 0.5 ? -deltaHeight : deltaHeight);\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	} // if (now > tmpObj.endTime) \n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	\n"
		+ "	for( var i = 0 ; i < tmpObj.audioQueue.length ; i++ )\n"
		+ "	{\n"
		+ "    try {\n"
		+ "		      processTobjAudioPlay(tmpObj,  tmpObj.audioQueue[i], tmpObj.audioQueue[i].audioBuffer );\n"
		+ "        }\n"
		+ "        catch( e )\n"
		+ "        {\n"
		+ "               console.error( e );"
		+ "        }\n"
		+ "	}\n"
		+ "	tmpObj.audioQueue = [];\n"
		+ "	\n"
		+ "	\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	light.position.x = 20 * Math.cos(time);\n"
		+ "	light.position.y = 20 * Math.sin(time);\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "	var secsChanged = ((curSecs - tmpObj.curSecs) > 0);\n"
		+ "	var updateDumbbellHits = tmpObj.updateDumbbellHits;\n"
		+ "	var updateLeftPunchHits = tmpObj.updateLeftPunchHits;\n"
		+ "	var updateRightPunchHits = tmpObj.updateRightPunchHits;\n"
		+ "\n"
		+ "\n"
		+ "	// console.log( tmpObj );\n"
		+ "	if (secsChanged ) {\n"
		+ "\n"
		+ "		// console.log( tmpObj );\n"
		+ "		// console.log( tmpObj.fontHelvetiker );\n"
		+ "\n"
		+ "\n"
		+ "\n"
		+ "		const fntMultiplier = tmpObj.masterFontSizeMultiplier;\n"
		+ "		const fntSz = 50 * fntMultiplier;\n"
		+ "		const fntHt = 10 * fntMultiplier;\n"
		+ "		const bvlThk = 1 * fntMultiplier;\n"
		+ "		const bvlSz = 1 * fntMultiplier;\n"
		+ "\n"
		+ "		const stringSeconds = String(parseInt(curSecs % 60)).padStart(2, '0');\n"
		+ "		const stringMinutes = String(parseInt(curSecs / 60)).padStart(3, '0');\n"
		+ "		const displayString = stringMinutes + ':' + stringSeconds;\n"
		+ "\n"
		+ "		const centerOffsetX = - 0.5 * 0.0;\n"
		+ "\n"
		+ "		const bucketL2Height = 3;\n"
		+ "\n"
		+ "\n"
		+ "		tmpObj.updateDumbbellHits = true;\n"
		+ "		tmpObj.updateLeftPunchHits = true;\n"
		+ "		tmpObj.updateRightPunchHits = true;\n"
		+ "		updateDumbbellHits = false;\n"
		+ "		updateLeftPunchHits = false;\n"
		+ "		updateRightPunchHits = false;\n"
		+ "\n"
		+ "	}\n"
		+ "\n"
		+ "\n"
		+ "	if ((tmpObj.dumbbellHits != tmpObj.prevDumbbellHits) && updateDumbbellHits) {\n"
		+ "\n"
		+ "		const displayString = '' + (tmpObj.dumbbellHits);\n"
		+ "\n"
		+ "		tmpObj.prevDumbbellHits = tmpObj.dumbbellHits;\n"
		+ "\n"
		+ "\n"
		+ "		updateDumbbellHits = false;\n"
		+ "		updateLeftPunchHits = false;\n"
		+ "		updateRightPunchHits = false;\n"
		+ "		tmpObj.updateDumbbellHits = false;\n"
		+ "\n"
		+ "	}\n"
		+ "\n"
		+ "\n"
		+ "	if ((tmpObj.rightPunchHits != tmpObj.prevRightPunchHits) && updateRightPunchHits) {\n"
		+ "\n"
		+ "		const displayString = '' + (tmpObj.rightPunchHits);\n"
		+ "		\n"
		+ "        tmpObj.prevRightPunchHits = tmpObj.rightPunchHits;\n"
		+ "\n"
		+ "		updateDumbbellHits = false;\n"
		+ "		updateLeftPunchHits = false;\n"
		+ "		updateRightPunchHits = false;\n"
		+ "		tmpObj.updateRightPunchHits = false;\n"
		+ "\n"
		+ "	}\n"
		+ "\n"
		+ "\n"
		+ "	if ((tmpObj.leftPunchHits != tmpObj.prevLeftPunchHits) && updateLeftPunchHits) {\n"
		+ "\n"
		+ "		const displayString = '' + (tmpObj.leftPunchHits);\n"
		+ "\n"
		+ "		tmpObj.prevLeftPunchHits = tmpObj.leftPunchHits;\n"
		+ "\n"
		+ "		updateDumbbellHits = false;\n"
		+ "		updateLeftPunchHits = false;\n"
		+ "		updateRightPunchHits = false;\n"
		+ "		tmpObj.updateLeftPunchHits = false;\n"
		+ "\n"
		+ "	}\n"
		+ "\n"
		+ "\n"
		+ "	tmpObj.now = now;\n"
		+ "	tmpObj.curSecs = curSecs;\n"
		+ "\n"
		+ "	gl.render(scene, tmpObj.camera);\n"
		+ "\n"
		+ "	if (gl.xr.isPresenting) {\n"
		+ "		tmpObj.realCamera = gl.xr.getCamera(tmpObj.camera);\n"
		+ "	}\n"
		+ "	else {\n"
		+ "		tmpObj.realCamera = tmpObj.camera;\n"
		+ "	}\n"
		+ "} // draw\n"
		+ "\n"
		+ "\n"
		+ "// UPDATE RESIZE\n"
		+ "function resizeDisplay() {\n"
		+ "	const canvas = gl.domElement;\n"
		+ "	const width = canvas.clientWidth;\n"
		+ "	const height = canvas.clientHeight;\n"
		+ "	const needResize = canvas.width != width || canvas.height != height;\n"
		+ "	if (needResize) {\n"
		+ "		gl.setSize(width, height, false);\n"
		+ "	}\n"
		+ "	return needResize;\n"
		+ "} // resizeDisplay\n" );		
			
			String htmlResponse = htmlBuilder.toString();
			
			// System.out.println( "Response " + htmlResponse );
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

