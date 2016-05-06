package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

public class AntMultiSportSpeedDist {

	private static int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataTime;
	private DataArray dataDistance;

	private double[] timeStamp;
	private double[] distance;
	private OdMLData metaData;

	public AntMultiSportSpeedDist(double[] timeStamp, double[] distance, OdMLData metaData) {

		this.timeStamp = timeStamp;
		this.distance = distance;
		this.metaData = metaData;
		
		index++;
	}

	public void createNixFile(String fileName) {
		
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("multiSportSpeedDist" + index, "antMessage");

		section = file.createSection("AntMetaData", "metadata");
		section.createProperty("deviceName", metaData.getDeviceName());
		section.createProperty("deviceType", metaData.getDeviceType());
		section.createProperty("deviceState", metaData.getDeviceState());
		section.createProperty("deviceNumber", metaData.getDeviceNumber());
		section.createProperty("batteryStatus", metaData.getBatteryStatus());
		section.createProperty("signalStrength", metaData.getSignalStrength());
		section.createProperty("manufacturerIdentification", metaData.getManIdentification());
		section.createProperty("manufacturerSpecificData", metaData.getManSpecData());
		section.createProperty("productInfo", metaData.getProdInfo());

		
		dataTime = block.createDataArray("DataTime" + index, "antMessage", DataType.Double,
				new NDSize(new int[] {1,timeStamp.length}));
			dataTime.setData(timeStamp, new NDSize(new int [] {1,timeStamp.length}), new NDSize(2,0));
			
		dataDistance= block.createDataArray("DataDistance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] {1,distance.length}));
			dataDistance.setData(distance, new NDSize(new int[] {1,distance.length}), new NDSize(2,0));

		
		file.close();

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public DataArray getDataTime() {
		return dataTime;
	}

	public void setDataTime(DataArray dataTime) {
		this.dataTime = dataTime;
	}

	public DataArray getDataDistance() {
		return dataDistance;
	}

	public void setDataDistance(DataArray dataDistance) {
		this.dataDistance = dataDistance;
	}

	public double[] getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(double[] timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double[] getDistance() {
		return distance;
	}

	public void setDistance(double[] distance) {
		this.distance = distance;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

	
}
