package cz.zcu.AntPlus2NIXConverter.Profiles;

import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc;
import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc.BodyWeightStatus;
import com.dsi.ant.plugins.antplus.pcc.defines.BatteryStatus;
import com.dsi.ant.plugins.antplus.pccbase.AntPlusCommonPcc;
import com.dsi.ant.plugins.antplus.pccbase.AntPlusCommonPcc.IBatteryStatusReceiver;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
public class AntWeightScale {

	private static int index = 0 ;
	
	  private AntPlusWeightScalePcc wsp;
	  private AntPlusCommonPcc com;
	  
	  private int weight;
	  
	  private File file;
	  private Block block;
	  private Source source;
	  private DataArray data;
	  private ID id;
	  
	  
	  
	  public AntWeightScale(AntPlusWeightScalePcc wsp, int weight, AntPlusCommonPcc pcc) {
	
		 this(weight);
		 this.wsp = wsp;
		 wsp.getAntDeviceNumber();
		 wsp.getDeviceName();
		 
	  }
	  

	  public AntWeightScale(int weight) {
		  
		 this.weight = weight;
		 /*id = new ID(8);
		 createBlock();
		 createSource();
		 createDataArray(weight);*/
		 index ++;
	  
	  }
	  
	  
	  
	
	  public void createFile(){
		  file = File.open("AntWeightScale.h5", FileMode.Overwrite);
		  
		  block = file.createBlock("recording" + index, "recording");
		  
		  source = block.createSource("weightScale" + index, "antMessage");
		  
		  file.close();

	  }
	  
	  /*public void createDataArray(int weight){
		  data = new DataArray("weight", "antMessage", "weight" + index, "kg", "int");
		  data.setData(weight);
	  }*/
}
