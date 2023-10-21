


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

/**
 * Class describing a Standard material
 * @author deck
 *
 */
public class MeshStandardMaterial extends Material {
	
	/**
	 * Reference to the JS object for the THREE.JS material
	 */
	String materialContext;

	@Override
	public String getMaterialContext() {
		return materialContext;
	}

	/**
	 * Constructor
	 * @param _materialContext Reference to the JS object for the THREE.JS material
	 */
	protected MeshStandardMaterial( String _materialContext ) {
		materialContext = _materialContext;
	}
	

	/**
	 * Creates an instance of the MeshStandardMaterial
	 * @param gc The graphics context in which to display the material
	 * @param init Object used to initialize the material
	 * @return An instance of the MeshStandardMaterial
	 */
	public static MeshStandardMaterial create( GraphicsContext gc , MeshStandardMaterialInitializer init )
	{
		
		System.out.println( "Creating Standard Material" );
		
		
		String color = init.getColor();
		
		double opacity = init.getOpacity();
		
		String emissive = init.getEmissive();
		
		boolean transparent = init.isTransparent();
		
		double roughness = init.getRoughness();
		
		double metalness = init.getMetalness();
		
		
		String ac = gc.getApplicationContext();
		
		String materialWc = gc.generateNewWidgetContext();
		
		String materialContext = "tmpObj." + ac + "." + materialWc;
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
			+ "\n"
			+ materialContext + " = new THREE.MeshStandardMaterial({\n"
			+ "		color: " + color + ",\n"
			+ "		side: THREE.DoubleSide,\n"
			+ "		transparent: " + transparent + ",\n"
			+ "		opacity: " + opacity + ",\n"
			+ "		emissive: " + emissive + ",\n"
			+ "		roughness: " + roughness + ",\n"
			+ "		metalness: " + metalness + "\n"
			+ "	});\n"
			+ "\n"
			);
		gc.appendJs( "}\n" );
		
		return( new MeshStandardMaterial( materialContext ) );
	}
	
	
	@Override
	public void dispose( GraphicsContext gc )
	{
		System.out.println( "Disposing... " + this );
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
			    + materialContext + ".dispose( );\n"
				+ "\n"
			);
		gc.appendJs( "}\n" );
	}

}

