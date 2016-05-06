package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntLightElectricVehicle {

private static int index = 0 ;
	
	//  private  AntPlusBloodPressurePcc.BloodPressureMeasurement bpr = null;
	  
	private File file;
	private Block block;
	private Source source;
	private DataArray dataSpeedDistance;
	private DataArray dataSysGearState;
	private DataArray dataMode;
	private DataArray dataBatStatus;
	  
	private ID id;
	  
	  
	public AntLightElectricVehicle( double[] speedDistance, boolean sysGearState, int mode, int[] BatStatus ) {
		
		 /* id = new ID(8);
			 createBlock();
			 createSource();
			 createDataArraySpeedDistance(speedDistance);
			 createDataArraySysGearState(sysGearState);
			 createDataArrayMode(mode);
			 createDataArrayBatStatus(BatStatus);*/
			 index ++;
	}
	  
	public void createFile(){
		file = File.open("AntLightElectricVehicle.h5", FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("lightElectricVehicle" + index, "antMessage");
		
		file.close();

	  }
	  
	  /*public void  createDataArraySpeedDistance(double[] speedDistance){
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
	  }*/
	
}
