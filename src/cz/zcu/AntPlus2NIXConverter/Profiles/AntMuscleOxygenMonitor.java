package cz.zcu.AntPlus2NIXConverter.Profiles;

import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc.BodyWeightStatus;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntMuscleOxygenMonitor {
	
	
	private static int index = 0 ;
	
	  private int weight;
	  
	  private File file;
	  private Block block;
	  private Source source;
	  private DataArray dataHemoglobinConcentrate;
	  private DataArray dataSaturatedHemoglPerc;
	  
	  
	  private ID id;
	  
	  public AntMuscleOxygenMonitor(double saturatedHemoglPerc, double hemoglobinConcentrate) {
		  
		  /*id = new ID(8);
			createBlock();
			createSource();
			createDataArrayHeartBeat(hemoglobinConcentrate);
			createDataArrayComputedHeartRade(saturatedHemoglPerc);*/
			index++;
	  
	  }
	  

	  public void createFile() {
		  file = File.open("AntMuscleOxygenMonitor", FileMode.Overwrite);
		  
		  block = file.createBlock("recording" + index, "recording");
		  
		  source = block.createSource("muscleOxygenMonitor" + index, "antMessage");
		  
		  file.close();

		}

		/*public void createDataArrayHeartBeat(double hemoglobinConcentrate) {
			dataHemoglobinConcentrate = new DataArray("MuscleOxygenMonitor_" + id.nextString(), "antMessage", "hemoglobinConcentr" + index, "g/dl",
					"double");
			dataHemoglobinConcentrate.setData(hemoglobinConcentrate);
		}

		public void createDataArrayComputedHeartRade(double saturatedHemoglPerc) {
			dataSaturatedHemoglPerc = new DataArray("MuscleOxygenMonitor_" + id.nextString(), "antMessage", " saturatedHemoglPerc" + index, "%",
					"double");
			dataSaturatedHemoglPerc.setData(saturatedHemoglPerc);
		}*/

	

}
