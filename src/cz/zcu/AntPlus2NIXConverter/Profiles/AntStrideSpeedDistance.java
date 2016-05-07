package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Profil pro vytvorení HDF5 souboru ze zarizeni Stride Speed & Distance.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntStrideSpeedDistance {

	private static int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataStrideCount;
	private DataArray dataDistance;
	private DataArray dataSpeed;
	
	private long[] strideCount;
	private double[] distance;
	private double[] speed;
	private OdMLData metaData ;

	/**
	 * Konstruktor tridy.
	 * @param strideCount Pocet kroku
	 * @param distance Vzdalenost
	 * @param speed Rychlost
	 * @param metaData MetaData
	 */
	public AntStrideSpeedDistance(long[] strideCount, double[] distance, double[] speed,OdMLData metaData ) {
	
		this.strideCount = strideCount;
		this.distance = distance;
		this.speed = speed;
		this.metaData = metaData;
		
		index++;
	}
	
	/**
	 * Metoda pro vytvoreni HDF5 souboru i s celou jeho strukturou vcetne dat a metadat.
	 * @param fileName Nazev souboru
	 */
	public void createNixFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("strideSpeedDistance" + index, "antMessage");
		
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

		dataStrideCount = block.createDataArray("StrideCount" + index, "antMessage", DataType.Int64,
				new NDSize(new int[] {1,strideCount.length}));
		dataStrideCount.setData(strideCount, new NDSize(new int [] {1,strideCount.length}), new NDSize(2,0));
			
		dataDistance = block.createDataArray("distance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] {1,distance.length}));
			dataDistance.setData(distance, new NDSize(new int[] {1,distance.length}), new NDSize(2,0));

		dataSpeed = block.createDataArray("speed" + index, "antMessage", DataType.Double, 
				new NDSize(new int[] {1,speed.length}));
			dataSpeed.setData(speed, new NDSize(new int[] {1,speed.length}), new NDSize(2,0));

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

	public DataArray getDataStrideCount() {
		return dataStrideCount;
	}

	public void setDataStrideCount(DataArray dataStrideCount) {
		this.dataStrideCount = dataStrideCount;
	}

	public DataArray getDataDistance() {
		return dataDistance;
	}

	public void setDataDistance(DataArray dataDistance) {
		this.dataDistance = dataDistance;
	}

	public DataArray getDataSpeed() {
		return dataSpeed;
	}

	public void setDataSpeed(DataArray dataSpeed) {
		this.dataSpeed = dataSpeed;
	}

	public long[] getStrideCount() {
		return strideCount;
	}

	public void setStrideCount(long[] strideCount) {
		this.strideCount = strideCount;
	}

	public double[] getDistance() {
		return distance;
	}

	public void setDistance(double[] distance) {
		this.distance = distance;
	}

	public double[] getSpeed() {
		return speed;
	}

	public void setSpeed(double[] speed) {
		this.speed = speed;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	
	
}
