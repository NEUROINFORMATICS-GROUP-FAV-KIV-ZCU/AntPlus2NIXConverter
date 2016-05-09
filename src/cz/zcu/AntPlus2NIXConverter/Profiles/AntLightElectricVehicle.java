package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;
import java.util.Arrays;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Profil pro vytvorení HDF5 souboru ze zarizeni Light Electric Vehicle.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntLightElectricVehicle {

private int index = 0 ;
	
	  
	private File file;
	private Block block;
	private Source source;
	private Section section;
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
	
	/**
	 * Konstruktor tridy.
	 * @param speedDistance Vzdalenost
	 * @param sysGearState Status systemu a zarizeni
	 * @param mode Mod
	 * @param BatStatus Status baterie
	 * @param metaData MetaData
	 */
	public AntLightElectricVehicle(double[] speedDistance, boolean[] sysGearState, int[] mode, int[] BatStatus, OdMLData metaData ) {
		
		this.speedDistance =speedDistance;
		this.sysGearState = sysGearState;
		this.mode = mode;
		this.batStatus = BatStatus;
		this.metaData = metaData;
		index ++;
	}
	
	/**
	 * Prevod statusu systemu a zarizeni z pole Stringu na pole bytu.
	 */
	public void prevedSysGearState(){
		
		sysGearStateB = new byte[sysGearState.length];
		
		for (int i = 0; i < sysGearState.length; i++) {
					
				sysGearStateB[i] = Byte.parseByte(String.valueOf(sysGearState[i]));
		}
	}
	
	/**
	 * Metoda pro vytvoreni HDF5 souboru i s celou jeho strukturou vcetne dat a metadat.
	 * @param fileName Nazev souboru
	 */
	public void createNixFile(String fileName){
		
		prevedSysGearState();
		
		file = File.open(fileName, FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("lightElVeh" + index, "antMessage");
		
		section = file.createSection("AntMetaData", "metadata");
		section.createProperty("deviceName", new Value(metaData.getDeviceName()));
		section.createProperty("deviceType", new Value(metaData.getDeviceType()));
		section.createProperty("deviceState", new Value(metaData.getDeviceState()));
		section.createProperty("deviceNumber", new Value(metaData.getDeviceNumber()));
		section.createProperty("batteryStatus", new Value(metaData.getBatteryStatus()));
		section.createProperty("signalStrength", new Value(metaData.getSignalStrength()));
		section.createProperty("manufacturerIdentification", new Value(metaData.getManIdentification()));
		section.createProperty("manufacturerSpecificData", new Value(metaData.getManSpecData()));
		section.createProperty("productInfo", new Value(metaData.getProdInfo()));

		
		dataBatStatus = block.createDataArray("batStatus" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,batStatus.length}));
			dataBatStatus.setData(batStatus, new NDSize(new int [] {1,batStatus.length}), new NDSize(2,0));
		
			
		dataMode = block.createDataArray("mode" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,mode.length}));
			dataMode.setData(mode, new NDSize(new int[] {1,mode.length}), new NDSize(2,0));

			
		dataSpeedDistance = block.createDataArray("speedDistance" + index, "antMessage", DataType.Double, 
				new NDSize(new int[] {1,speedDistance.length}));
			dataSpeedDistance.setData(speedDistance, new NDSize(new int[] {1,speedDistance.length}), new NDSize(2,0));

			
			dataSysGearState = block.createDataArray("sysGearState" + index, "antMessage", DataType.Int16, 
					new NDSize(new int[] {1,speedDistance.length}));
				dataSysGearState.setData(sysGearStateB, new NDSize(new int[] {1,sysGearState.length}), new NDSize(2,0));

		
		
		//file.close();

	  }

	
	/*public static void main(String[] args) {
	AntLightElectricVehicle	vehicle = new AntLightElectricVehicle(new double[]{5.0,3.0,12.0,32.0,66.0}, new boolean[] {}, new int[] {}, new int[]{},
				new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		vehicle.createNixFile("testovaci.h5");

	}*/
	
	public Section getSection() {
		return section;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
