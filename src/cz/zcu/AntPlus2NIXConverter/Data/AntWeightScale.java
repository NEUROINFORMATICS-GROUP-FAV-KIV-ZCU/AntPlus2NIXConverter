package cz.zcu.AntPlus2NIXConverter.Data;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusWeightScalePcc.BodyWeightStatus;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntWeightScale {

	private static int index = 0 ;
	
	  private BodyWeightStatus wsp;
	  private int weight;
	  
	  private Block block;
	  private Source source;
	  private DataArray data;
	  private ID id;
	  
	  
	  
	  public AntWeightScale(BodyWeightStatus wsp) {
		  
		 this.wsp = wsp; 
		 id = new ID();
		 createBlock();
		 createSource();
		 createDataArray();
		 index ++;
	  
	  }
	  
	  
	  private int getWeight(){
		  weight = wsp.getIntValue();
		  return weight = wsp.getIntValue();
		  
	  }
	
	  public void createBlock(){
			 block = new Block("WeightScale_" + id.createID(), "recording", "recording" + index, LocalDate.now());

	  }
	
	  public void createSource(){
		  source = new Source("WeightScale_" + id.createID(), "antMessage", "WeightScale" + index);
	  }
	  
	  public void createDataArray(){
		  data = new DataArray("weight", "antMessage", "weight" + index, "kg");
		  data.setData(getWeight());
	  }
}
