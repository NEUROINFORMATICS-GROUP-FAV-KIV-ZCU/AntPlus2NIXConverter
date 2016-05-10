package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvorení HDF5 souboru ze zarizeni Multi Sport Speed & Distance.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntMultiSportSpeedDist {

	/** Aributy tridy **/

	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataTime;
	private DataArray dataDistance;

	private double[] timeStamp;
	private double[] distance;
	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param timeStamp
	 *            Casova znamka
	 * @param distance
	 *            Vzdalenost
	 * @param metaData
	 *            MetaData
	 */
	public AntMultiSportSpeedDist(double[] timeStamp, double[] distance, OdMLData metaData) {

		this.timeStamp = timeStamp;
		this.distance = distance;
		this.metaData = metaData;

		index++;
	}

	/**
	 *  Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	public void createNixFile(String fileName) {

		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("multiSportSpeedDist" + index, "antMessage");
		
		/* Pridani metadat do bloku */
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

		/* Naplneni dataArray daty o case */
		dataTime = block.createDataArray("dataTime" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, timeStamp.length }));
		dataTime.setData(timeStamp, new NDSize(new int[] { 1, timeStamp.length }), new NDSize(2, 0));
		dataTime.setUnit("seconds");
	
		/* Naplneni dataArray daty o vzdalenosti */
		dataDistance = block.createDataArray("dataDistance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, distance.length }));
		dataDistance.setData(distance, new NDSize(new int[] { 1, distance.length }), new NDSize(2, 0));
		dataDistance.setUnit("meters");
		file.close();

	}

	/*** Getry a Setry ***/
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
