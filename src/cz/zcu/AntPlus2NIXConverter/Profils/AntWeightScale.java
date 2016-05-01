package cz.zcu.AntPlus2NIXConverter.Profils;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc.BodyWeightStatus;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntWeightScale {

	private static int index = 0 ;
	
	  private BodyWeightStatus wsp;
	  private int weight;
	  
	  private Block block;
	  private Source source;
	  private DataArray data;
	  private ID id;
	  
	  
	  
	  public AntWeightScale(BodyWeightStatus wsp, int weight) {
		  
		 this(weight);
		 this.wsp = wsp;
	  
	  }
	  

	  public AntWeightScale(int weight) {
		  
		 this.weight = weight;
		 id = new ID(8);
		 createBlock();
		 createSource();
		 createDataArray(weight);
		 index ++;
	  
	  }
	  
	  
	  private int getWeight(){
		  weight = wsp.getIntValue();
		  return weight = wsp.getIntValue();
		  
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
