package cz.zcu.AntPlus2NIXConverter.Profiles;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

import java.io.IOException;

import org.g_node.nix.*;

/**
 * Trida pro zpracovani informaci o ANT plus profilu HeartRate Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Heart Rate.* @author Vaclav Janoch, Filip
 * Kupilik, Petr Tobias
 * 
 * @version 1.0
 */

public class AntHeartRate implements INixFile{

	/** Aributy tridy **/
	private static int index = 0;

	private int[] heartBeatCounter;
	private int[] computedHeartRate;
	private double[] timeOfPreviousHeartBeat;

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
	 * @throws IOException 
	 */
	@Override
	public void createNixFile(File nixFile){
		
		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("heartRate" + index, "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o tlukotu srdce */
		DataArray dataHeartBeatCounter = block.createDataArray("heartBeatCount" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartBeatCounter.length }));
		dataHeartBeatCounter.setData(heartBeatCounter, new NDSize(new int[] { 1, heartBeatCounter.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o vypoctech */
		DataArray dataComputedHeartRate = block.createDataArray("comluptedHeartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, computedHeartRate.length }));
		dataComputedHeartRate.setData(computedHeartRate, new NDSize(new int[] { 1, computedHeartRate.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o case prechoziho tepu */
		DataArray dataTimeOfPreviousHeartBeat = block.createDataArray("timeOfPreviousHeartBeat" + index, "antMessage",
				DataType.Double, new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }));
		dataTimeOfPreviousHeartBeat.setData(timeOfPreviousHeartBeat,
				new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }), new NDSize(2, 0));
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		AntHeartRate.index = index;
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
