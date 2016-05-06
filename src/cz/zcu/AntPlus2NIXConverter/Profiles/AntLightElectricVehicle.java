package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;
import java.util.Arrays;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

public class AntLightElectricVehicle {

private static int index = 0 ;
	
	  
	private File file;
	private Block block;
	private Source source;
	private DataArray dataSpeedDistance;
	private DataArray dataSysGearState;
	private DataArray dataMode;
	private DataArray dataBatStatus;
	private double[] speedDistance;
	private byte[] sysGearStateB;
	private boolean[] sysGearState;
	
	private int[] mode;
	private int[] batStatus;
	private OdMLData metaData; 
	  
	public AntLightElectricVehicle( double[] speedDistance, boolean[] sysGearState, int[] mode, int[] BatStatus, OdMLData metaData ) {
		
		this.speedDistance =speedDistance;
		this.sysGearState = sysGearState;
		this.mode = mode;
		this.batStatus = BatStatus;
		this.metaData = metaData;
		index ++;
	}
	  
	public void prevedSysGearState(){
		
		sysGearStateB = new byte[sysGearState.length];
		
		for (int i = 0; i < sysGearState.length; i++) {
					
				sysGearStateB[i] = Byte.parseByte(String.valueOf(sysGearState[i]));
		}
	}
	public void createFile(){
		
		prevedSysGearState();
		
		file = File.open("AntLightElectricVehicle.h5", FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("lightElectricVehicle" + index, "antMessage");
		
		
		dataBatStatus = block.createDataArray("BatStatus" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,batStatus.length}));
			dataBatStatus.setData(batStatus, new NDSize(new int [] {1,batStatus.length}), new NDSize(2,0));
		
			
		dataMode = block.createDataArray("Mode" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,mode.length}));
			dataMode.setData(mode, new NDSize(new int[] {1,mode.length}), new NDSize(2,0));

			
		dataSpeedDistance = block.createDataArray("SpeedDistance" + index, "antMessage", DataType.Double, 
				new NDSize(new int[] {1,speedDistance.length}));
			dataSpeedDistance.setData(speedDistance, new NDSize(new int[] {1,speedDistance.length}), new NDSize(2,0));

			
			dataSysGearState = block.createDataArray("SysGearState" + index, "antMessage", DataType.Bool, 
					new NDSize(new int[] {1,speedDistance.length}));
				dataSysGearState.setData(sysGearStateB, new NDSize(new int[] {1,sysGearState.length}), new NDSize(2,0));

		
		
		file.close();

	  }

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		AntLightElectricVehicle.index = index;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public DataArray getDataSpeedDistance() {
		return dataSpeedDistance;
	}

	public void setDataSpeedDistance(DataArray dataSpeedDistance) {
		this.dataSpeedDistance = dataSpeedDistance;
	}

	public DataArray getDataSysGearState() {
		return dataSysGearState;
	}

	public void setDataSysGearState(DataArray dataSysGearState) {
		this.dataSysGearState = dataSysGearState;
	}

	public DataArray getDataMode() {
		return dataMode;
	}

	public void setDataMode(DataArray dataMode) {
		this.dataMode = dataMode;
	}

	public DataArray getDataBatStatus() {
		return dataBatStatus;
	}

	public void setDataBatStatus(DataArray dataBatStatus) {
		this.dataBatStatus = dataBatStatus;
	}

	public double[] getSpeedDistance() {
		return speedDistance;
	}

	public void setSpeedDistance(double[] speedDistance) {
		this.speedDistance = speedDistance;
	}

	public byte[] getSysGearStateB() {
		return sysGearStateB;
	}

	public void setSysGearStateB(byte[] sysGearStateB) {
		this.sysGearStateB = sysGearStateB;
	}

	public boolean[] getSysGearState() {
		return sysGearState;
	}

	public void setSysGearState(boolean[] sysGearState) {
		this.sysGearState = sysGearState;
	}

	public int[] getMode() {
		return mode;
	}

	public void setMode(int[] mode) {
		this.mode = mode;
	}

	public int[] getBatStatus() {
		return batStatus;
	}

	public void setBatStatus(int[] batStatus) {
		this.batStatus = batStatus;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}
	  
	 
	
}
