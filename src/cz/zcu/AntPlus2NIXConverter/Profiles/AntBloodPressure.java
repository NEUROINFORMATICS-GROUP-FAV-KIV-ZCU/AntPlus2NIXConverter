package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import com.dsi.ant.plugins.antplus.pcc.AntPlusBloodPressurePcc;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntBloodPressure {

	private static int index = 0 ;
	
	//  private  AntPlusBloodPressurePcc.BloodPressureMeasurement bpr = null;
	  
	private File file;
	private Block block;
	private Source source;
	private DataArray dataSystolic;
	private DataArray dataDistolic;
	private DataArray dataHeartRade;
	private DataArray dataTime;
	  
	private ID id;
	  
	public AntBloodPressure(int systolic, int distolic, int heartRade, GregorianCalendar timeStamp) {
		  
			 /* id = new ID(8);
				 createBlock();
				 createSource();
				 createDataArraySystolic(systolic);
				 createDataArrayDistolic(distolic);
				 createDataArrayHeartRade(heartRade);
				 createDataArrayTimeStamp(timeStamp);*/
				 index ++;
		  
		  }
	  
	  /*
	  public AntBloodPressure(int systolic, int distolic, int heartRade, GregorianCalendar timeStamp, BloodPressureMeasurement bpr) {
		  
		  this( systolic, distolic, heartRade, timeStamp);
		 this.bpr = bpr;
		  
		 
		 
	  
	  }*/
	  
	  public void createFile(){
		  file = File.open("AntBloodPressure.h5", FileMode.Overwrite);
		  
		  block = file.createBlock("recording" + index, "recording");
		  
		  source = block.createSource("BloodPressure" + index, "antMessage");
		  
		  file.close();
	  }
	  
	  /*public void createDataArraySystolic(int systolic){
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
	  }*/
	
}
