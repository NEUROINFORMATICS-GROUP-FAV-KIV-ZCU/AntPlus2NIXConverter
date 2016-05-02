package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc;
import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc.BodyWeightStatus;
import com.dsi.ant.plugins.antplus.pcc.defines.BatteryStatus;
import com.dsi.ant.plugins.antplus.pccbase.AntPlusCommonPcc;
import com.dsi.ant.plugins.antplus.pccbase.AntPlusCommonPcc.IBatteryStatusReceiver;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntWeightScale {

	private static int index = 0 ;
	
	  private AntPlusWeightScalePcc wsp;
	  private AntPlusCommonPcc com;
	  
	  private int weight;
	  
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
		 id = new ID(8);
		 createBlock();
		 createSource();
		 createDataArray(weight);
		 index ++;
	  
	  }
	  
	  
	  
	
	  public void createBlock(){
			 block = new Block("WeightScale_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

	  }
	
	  public void createSource(){
		  source = new Source("WeightScale_" + id.nextString(), "antMessage", "WeightScale" + index);
	  }
	  
	  public void createDataArray(int weight){
		  data = new DataArray("weight", "antMessage", "weight" + index, "kg", "int");
		  data.setData(weight);
	  }
}
