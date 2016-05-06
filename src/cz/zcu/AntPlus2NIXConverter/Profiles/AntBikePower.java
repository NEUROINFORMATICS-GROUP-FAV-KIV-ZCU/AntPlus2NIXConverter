package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import com.dsi.ant.plugins.antplus.pcc.AntPlusBikePowerPcc;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntBikePower {

	private static int index = 0 ;
	
	  private AntPlusBikePowerPcc bikPower = null;
	  
	  private File file;
	  private Block block;
	  private Source source;
	  private DataArray dataArrayBikePower;
	  private ID id;
	
	  
	  public AntBikePower(double power) {
		
		/*  id = new ID(8);
			createBlock();
			createSource();
			createDataArrayBikePower(power);*/
			index++;
		  
	}
	  
	  public AntBikePower(double power, AntPlusBikePowerPcc bikSpeed) {
			
		 this(power);
		 this.bikPower = bikPower;
		 
		  
	}
	  
	  
	  public void createFile() {
		  	file = File.open("AntBikePower.h5", FileMode.Overwrite);
			
		  	block = file.createBlock("recording" + index, "recording");
			
			source = block.createSource("bikePower" + index, "antMessage");
			
			file.close();

	  }

		/*public void createDataArrayBikePower(double power) {
			dataArrayBikePower = new DataArray("BikePower_" + id.nextString(), "antMessage", "powerOnly" + index, "N/A",
					"double");
			dataArrayBikePower.setData(power);
		}*/

}
