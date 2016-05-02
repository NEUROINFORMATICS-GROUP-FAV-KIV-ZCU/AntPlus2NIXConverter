package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntLightElectricVehicle {

private static int index = 0 ;
	
	//  private  AntPlusBloodPressurePcc.BloodPressureMeasurement bpr = null;
	  
	  private Block block;
	  private Source source;
	  private DataArray dataSpeedDistance;
	  private DataArray dataSysGearState;
	  private DataArray dataMode;
	  private DataArray dataBatStatus;
	  
	  private ID id;
	  
	  
	  public AntLightElectricVehicle( double[] speedDistance, boolean sysGearState, int mode, int[] BatStatus ) {
		
		  id = new ID(8);
			 createBlock();
			 createSource();
			 createDataArraySpeedDistance(speedDistance);
			 createDataArraySysGearState(sysGearState);
			 createDataArrayMode(mode);
			 createDataArrayBatStatus(BatStatus);
			 index ++;
	}
	  
	  public void createBlock(){
			 block = new Block("LightElectricVehicle_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

	  }
	
	  public void createSource(){
		  source = new Source("LightElectricVehicle_" + id.nextString(), "antMessage", "lightElectricVehicle" + index);
	  }
	  
	  public void  createDataArraySpeedDistance(double[] speedDistance){
		  dataSpeedDistance = new DataArray("systolicBloodPress_" + id.nextString(), "antMessage", "distolicBloodPress" + index, "N/A", "double");
		  dataSpeedDistance.setData(speedDistance);
	  }
	  
	  public void createDataArrayMode(int mode){
		dataMode = new DataArray("mode_" + id.nextString(), "antMessage", "mode" + index, "N/A", "int");
		  dataMode.setData(mode);
	  }
	  public void createDataArraySysGearState(boolean sysGearState){
		  dataSysGearState = new DataArray("sysGearState_" + id.nextString(), "antMessage", "sysGearState_" + index, "N/A", "boolean");
		  dataSysGearState.setData(sysGearState);
	  }
	  
	  public void createDataArrayBatStatus(int[] batStatus){
		  dataBatStatus = new DataArray("BatStatus_" + id.nextString(), "antMessage", "batStatus" + index, "N/A", "int");
		  dataBatStatus.setData(batStatus);
	  }
	
}
