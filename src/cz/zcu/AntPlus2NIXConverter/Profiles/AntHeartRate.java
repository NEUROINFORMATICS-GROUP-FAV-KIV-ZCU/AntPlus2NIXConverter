package cz.zcu.AntPlus2NIXConverter.Profiles;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

import org.g_node.nix.*;

/**
 * Trida pro zpracovani informaci o ANT plus profilu HeartRate Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Heart Rate.* @author Vaclav Janoch, Filip
 * Kupilik, Petr Tobias
 * 
 * @version 1.0
 */

public class AntHeartRate {

	/** Aributy tridy **/
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
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param heartBeatCounter
	 *            Pocet srdecnich tepu
	 * @param computedHeartRate
	 *            Vypocitany tep
	 * @param timeOfPreviousHeartBeat
	 *            Cas predchoziho uderu srdce
	 * @param metaData
	 *            MetaData
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
	 * 
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	public void createNixFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("heartRate" + index, "antMessage");

		/* Pridani metadat do bloku */

		section = metaData.createSectionNix(file);

		/* Naplneni dataArray daty o tlukotu srdce */
		dataHeartBeatCounter = block.createDataArray("heartBeatCount" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartBeatCounter.length }));
		dataHeartBeatCounter.setData(heartBeatCounter, new NDSize(new int[] { 1, heartBeatCounter.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o vypoctech */
		dataComputedHeartRate = block.createDataArray("comluptedHeartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, computedHeartRate.length }));
		dataComputedHeartRate.setData(computedHeartRate, new NDSize(new int[] { 1, computedHeartRate.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o case prechoziho tepu */
		dataTimeOfPreviousHeartBeat = block.createDataArray("timeOfPreviousHeartBeat" + index, "antMessage",
				DataType.Double, new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }));
		dataTimeOfPreviousHeartBeat.setData(timeOfPreviousHeartBeat,
				new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }), new NDSize(2, 0));

		file.close();
	}

	/**** Getry a Setry *****/
	public Block getBlock() {
		return block;
	}

	public Section getSection() {
		return section;
	}

	public Source getSource() {
		return source;
	}

	public DataArray getDataHeartBeatCounter() {
		return dataHeartBeatCounter;
	}

	public DataArray getDataComputedHeartRate() {
		return dataComputedHeartRate;
	}

	public DataArray getDataTimeOfPreviousHeartBeat() {
		return dataTimeOfPreviousHeartBeat;
	}

	public int[] getHeartBeatCounter() {
		return heartBeatCounter;
	}

	public int[] getComputedHeartRate() {
		return computedHeartRate;
	}

	public double[] getTimeOfPreviousHeartBeat() {
		return timeOfPreviousHeartBeat;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

	public File getFile() {
		return file;
	}

}
