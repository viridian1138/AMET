


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
 * Class describing a Phong material.
 * @author tgreen
 *
 */
public class MeshPhongMaterial extends Material {
	
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
	protected MeshPhongMaterial( String _materialContext ) {
		materialContext = _materialContext;
	}
	

	/**
	 * Creates an instance of the MeshPhongMaterial
	 * @param gc The graphics context in which to display the material
	 * @param init Object used to initialize the material
	 * @return An instance of the MeshPhongMaterial
	 */
	public static MeshPhongMaterial create( GraphicsContext gc , MeshPhongMaterialInitializer init )
	{
		
		System.out.println( "Creating Phong Material" );
		
		String color = init.getColor();
		
		String ac = gc.getApplicationContext();
		
		String materialWc = gc.generateNewWidgetContext();
		
		String materialContext = "tmpObj." + ac + "." + materialWc;
		
		gc.appendJs( "{\n" );
		gc.appendJs( "\n"
			+ "\n"
			+ materialContext + " = new THREE.MeshPhongMaterial({\n"
			+ "		color: " + color + ",\n"
			+ "		side: THREE.DoubleSide\n"
			+ "	});\n"
			+ "\n"
			);
		gc.appendJs( "}\n" );
		
		return( new MeshPhongMaterial( materialContext ) );
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

