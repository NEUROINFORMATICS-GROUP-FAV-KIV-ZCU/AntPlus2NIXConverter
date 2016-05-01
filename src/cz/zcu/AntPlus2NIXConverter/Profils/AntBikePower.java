package cz.zcu.AntPlus2NIXConverter.Profils;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusBikePowerPcc;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntBikePower {

	private static int index = 0 ;
	
	  private AntPlusBikePowerPcc bikPower = null;
	     
	  private Block block;
	  private Source source;
	  private DataArray dataArrayBikePower;
	  private ID id;
	
	  
	  public AntBikePower(double power) {
		
		  id = new ID(8);
			createBlock();
			createSource();
			createDataArrayBikePower(power);
			index++;
		  
	}
	  
	  public AntBikePower(double power, AntPlusBikePowerPcc bikSpeed) {
			
		 this(power);
		 this.bikPower = bikPower;
		 
		  
	}
	  
	  
	  public void createBlock() {
			block = new Block("BikePower_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

		}

		public void createSource() {
			source = new Source("BikePower_" + id.nextString(), "antMessage", "bikePower" + index);
		}

		public void createDataArrayBikePower(double power) {
			dataArrayBikePower = new DataArray("BikePower_" + id.nextString(), "antMessage", "powerOnly" + index, "N/A",
					"double");
			dataArrayBikePower.setData(power);
		}

}
