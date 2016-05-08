package cz.zcu.AntPlus2NIXConverter.Profiles;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

import org.g_node.nix.*;

/**
 * Trida pro zpracovani informaci o ANT plus profilu HeartRate
 * Profil pro vytvoreni­ HDF5 souboru ze zarizeni Heart Rate.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */

public class AntHeartRate {

	/** Staticky atribut tridy pro identifikaci souboru */
	private static int index = 0;

	/** Aributy tridy **/
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
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
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
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * @param fileName Nazev souboru
	 */
	public void createNixFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("HeartRate" + index, "antMessage");
		/* Pridani metadat do bloku */

		section = file.createSection("metadata", "metadata");
		section.createProperty("deviceName", metaData.getDeviceName());
		section.createProperty("deviceType", metaData.getDeviceType());
		section.createProperty("deviceState", metaData.getDeviceState());
		section.createProperty("deviceNumber", metaData.getDeviceNumber());
		section.createProperty("batteryStatus", metaData.getBatteryStatus());
		section.createProperty("signalStrength", metaData.getSignalStrength());
		section.createProperty("manufacturerIdentification", metaData.getManIdentification());
		section.createProperty("manufacturerSpecificData", metaData.getManSpecData());
		section.createProperty("productInfo", metaData.getProdInfo());

		/* Naplneni dataArray daty o tlukotu srdce */
		dataHeartBeatCounter = block.createDataArray("heartBeatCount" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartBeatCounter.length }));
		dataHeartBeatCounter.setData(heartBeatCounter, new NDSize(new int[] { 1, heartBeatCounter.length }),
				new NDSize(2, 0));
		dataHeartBeatCounter.setUnit("N/A");
		/* Naplneni dataArray daty o vypoctech */
		dataComputedHeartRate = block.createDataArray("comluptedHeartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, computedHeartRate.length }));
		dataComputedHeartRate.setData(computedHeartRate, new NDSize(new int[] { 1, computedHeartRate.length }),
				new NDSize(2, 0));
		dataComputedHeartRate.setUnit("bpm");
		/* Naplneni dataArray daty o case prechoziho tepu */
		dataTimeOfPreviousHeartBeat = block.createDataArray("timeOfPreviousHeartBeat" + index, "antMessage",
				DataType.Double, new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }));
		dataTimeOfPreviousHeartBeat.setData(timeOfPreviousHeartBeat,
				new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }), new NDSize(2, 0));
		dataTimeOfPreviousHeartBeat.setUnit("seconds");
		file.close();
	}

	
	/**** Getry a Setry *****/
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

}
