package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc.BodyWeightStatus;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntMuscleOxygenMonitor {
	
	
	private static int index = 0 ;
	
	  private int weight;
	  
	  private Block block;
	  private Source source;
	  private DataArray dataHemoglobinConcentrate;
	  private DataArray dataSaturatedHemoglPerc;
	  
	  
	  private ID id;
	  
	  public AntMuscleOxygenMonitor(double saturatedHemoglPerc, double hemoglobinConcentrate) {
		  
		  id = new ID(8);
			createBlock();
			createSource();
			createDataArrayHeartBeat(hemoglobinConcentrate);
			createDataArrayComputedHeartRade(saturatedHemoglPerc);
						index++;
	  
	  }
	  

	  public void createBlock() {
			block = new Block("MuscleOxygenMonitor_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

		}

		public void createSource() {
			source = new Source("MuscleOxygenMonitor_" + id.nextString(), "antMessage", "muscleOxygenMonitor" + index);
		}

		public void createDataArrayHeartBeat(double hemoglobinConcentrate) {
			dataHemoglobinConcentrate = new DataArray("MuscleOxygenMonitor_" + id.nextString(), "antMessage", "hemoglobinConcentr" + index, "g/dl",
					"double");
			dataHemoglobinConcentrate.setData(hemoglobinConcentrate);
		}

		public void createDataArrayComputedHeartRade(double saturatedHemoglPerc) {
			dataSaturatedHemoglPerc = new DataArray("MuscleOxygenMonitor_" + id.nextString(), "antMessage", " saturatedHemoglPerc" + index, "%",
					"double");
			dataSaturatedHemoglPerc.setData(saturatedHemoglPerc);
		}

	

}
