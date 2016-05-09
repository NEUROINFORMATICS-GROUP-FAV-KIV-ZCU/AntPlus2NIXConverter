package cz.zcu.AntPlus2NIXConverter.Profiles;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

import org.g_node.nix.*;

/**
 * Profil pro vytvorení HDF5 souboru ze zarizeni Heart Rate.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */

public class AntHeartRate {

	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataHeartBeatCounter;
	private DataArray dataComputedHeartRate;
	private DataArray dataTimeOfPreviousHeartBeat;

	private int[] heartBeatCounter = new int[4];
	private int[] computedHeartRate = new int[4];
	private double[] timeOfPreviousHeartBeat = new double[3];

	private OdMLData metaData;
	
	
	/**
	 * Konstruktor tridy.
	 * @param heartBeatCounter Pocet srdecnich tepu
	 * @param computedHeartRate Vypocitany tep
	 * @param timeOfPreviousHeartBeat Cas predchoziho uderu srdce
	 * @param metaData MetaData
	 */
	public AntHeartRate(int[] heartBeatCounter, int[] computedHeartRate, double[] timeOfPreviousHeartBeat,
			OdMLData metaData) {

		this.heartBeatCounter = heartBeatCounter;
		this.computedHeartRate = computedHeartRate;
		this.timeOfPreviousHeartBeat = timeOfPreviousHeartBeat;
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

		source = block.createSource("HeartRate" + index, "antMessage");
				
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

		dataHeartBeatCounter = block.createDataArray("heartBeatCount" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartBeatCounter.length }));
		dataHeartBeatCounter.setData(heartBeatCounter, new NDSize(new int[] { 1, heartBeatCounter.length }),
				new NDSize(2, 0));

		dataComputedHeartRate = block.createDataArray("comluptedHeartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, computedHeartRate.length }));
		dataComputedHeartRate.setData(computedHeartRate, new NDSize(new int[] { 1, computedHeartRate.length }),
				new NDSize(2, 0));

		dataTimeOfPreviousHeartBeat = block.createDataArray("timeOfPreviousHeartBeat" + index, "antMessage",
				DataType.Double, new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }));
		dataTimeOfPreviousHeartBeat.setData(timeOfPreviousHeartBeat,
				new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }), new NDSize(2, 0));

		//file.close();
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public DataArray getDataHeartBeatCounter() {
		return dataHeartBeatCounter;
	}

	public void setDataHeartBeatCounter(DataArray dataHeartBeatCounter) {
		this.dataHeartBeatCounter = dataHeartBeatCounter;
	}

	public DataArray getDataComputedHeartRate() {
		return dataComputedHeartRate;
	}

	public void setDataComputedHeartRate(DataArray dataComputedHeartRate) {
		this.dataComputedHeartRate = dataComputedHeartRate;
	}

	public DataArray getDataTimeOfPreviousHeartBeat() {
		return dataTimeOfPreviousHeartBeat;
	}

	public void setDataTimeOfPreviousHeartBeat(DataArray dataTimeOfPreviousHeartBeat) {
		this.dataTimeOfPreviousHeartBeat = dataTimeOfPreviousHeartBeat;
	}

	public int[] getHeartBeatCounter() {
		return heartBeatCounter;
	}

	public void setHeartBeatCounter(int[] heartBeatCounter) {
		this.heartBeatCounter = heartBeatCounter;
	}

	public int[] getComputedHeartRate() {
		return computedHeartRate;
	}

	public void setComputedHeartRate(int[] computedHeartRate) {
		this.computedHeartRate = computedHeartRate;
	}

	public double[] getTimeOfPreviousHeartBeat() {
		return timeOfPreviousHeartBeat;
	}

	public void setTimeOfPreviousHeartBeat(double[] timeOfPreviousHeartBeat) {
		this.timeOfPreviousHeartBeat = timeOfPreviousHeartBeat;
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	

}
