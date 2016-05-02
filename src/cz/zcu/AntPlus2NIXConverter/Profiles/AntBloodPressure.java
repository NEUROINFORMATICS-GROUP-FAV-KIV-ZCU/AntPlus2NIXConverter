package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import com.dsi.ant.plugins.antplus.pcc.AntPlusBloodPressurePcc;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntBloodPressure {

	private static int index = 0 ;
	
	//  private  AntPlusBloodPressurePcc.BloodPressureMeasurement bpr = null;
	  
	  private Block block;
	  private Source source;
	  private DataArray dataSystolic;
	  private DataArray dataDistolic;
	  private DataArray dataHeartRade;
	  private DataArray dataTime;
	  
	  private ID id;
	  
	  public AntBloodPressure(int systolic, int distolic, int heartRade, GregorianCalendar timeStamp) {
		  
			  id = new ID(8);
				 createBlock();
				 createSource();
				 createDataArraySystolic(systolic);
				 createDataArrayDistolic(distolic);
				 createDataArrayHeartRade(heartRade);
				 createDataArrayTimeStamp(timeStamp);
				 index ++;
		  
		  }
	  
	  /*
	  public AntBloodPressure(int systolic, int distolic, int heartRade, GregorianCalendar timeStamp, BloodPressureMeasurement bpr) {
		  
		  this( systolic, distolic, heartRade, timeStamp);
		 this.bpr = bpr;
		  
		 
		 
	  
	  }*/
	  
	  public void createBlock(){
			 block = new Block("BloodPressure_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

	  }
	
	  public void createSource(){
		  source = new Source("BloodPressure_" + id.nextString(), "antMessage", "BloodPressure" + index);
	  }
	  
	  public void createDataArraySystolic(int systolic){
		  dataSystolic = new DataArray("systolicBloodPress_" + id.nextString(), "antMessage", "distolicBloodPress" + index, "mmHg", "int");
		  dataSystolic.setData(systolic);
	  }
	  
	  public void createDataArrayDistolic(int distolic){
		  dataDistolic = new DataArray("distolicBloodPress_" + id.nextString(), "antMessage", "systolicBloodPress" + index, "mmHg", "int");
		  dataDistolic.setData(distolic);
	  }
	  public void createDataArrayHeartRade(int heartRade){
		  dataHeartRade = new DataArray("HeartRade_" + id.nextString(), "antMessage", "heardRade_" + index, "bpm", "int");
		  dataHeartRade.setData(heartRade);
	  }
	  
	  public void createDataArrayTimeStamp(GregorianCalendar date){
		  dataTime = new DataArray("timeStamp_" + id.nextString(), "antMessage", "timeStamp" + index, "N/A", "GreorgianCalendar");
		  dataTime.setData(date);
	  }
	
}
