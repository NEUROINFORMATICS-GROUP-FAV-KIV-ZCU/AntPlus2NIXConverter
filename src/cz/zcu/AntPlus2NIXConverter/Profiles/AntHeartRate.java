package cz.zcu.AntPlus2NIXConverter.Profiles;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

import java.util.UUID;

import org.g_node.nix.*;

/**
 * @Trida pro zpracovani informaci o ANT plus profilu HeartRate Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Heart Rate.
 * @author Vaclav Janoch, Filip
 * Kupilik, Petr Tobias
 * 
 * @version 1.0
 */

public class AntHeartRate implements INixFile{

	/** Aributy tridy **/
	private int[] heartBeatCounter;
	private int[] computedHeartRate;
	private double[] timeOfPreviousHeartBeat;

	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu spolecne s metadaty
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

	}

	/**
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat.
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile){
		
		Block block = nixFile.createBlock("kiv.zcu.cz_block_" + UUID.randomUUID().toString(), "recording");

		block.createSource("kiv.zcu.cz_source_heartRate_" + UUID.randomUUID().toString(), "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o tlukotu srdce */
		DataArray dataHeartBeatCounter = block.createDataArray("kiv.zcu.cz_data_array_heartBeatCount_" + UUID.randomUUID().toString(), "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartBeatCounter.length }));
		dataHeartBeatCounter.setData(heartBeatCounter, new NDSize(new int[] { 1, heartBeatCounter.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o vypoctech */
		DataArray dataComputedHeartRate = block.createDataArray("kiv.zcu.cz_data_array_computedHeartRate_" + UUID.randomUUID().toString(), "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, computedHeartRate.length }));
		dataComputedHeartRate.setData(computedHeartRate, new NDSize(new int[] { 1, computedHeartRate.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o case prechoziho tepu */
		DataArray dataTimeOfPreviousHeartBeat = block.createDataArray("kiv.zcu.cz_data_array_timeOfPreviousHeartBeat_" + UUID.randomUUID().toString(), "antMessage",
				DataType.Double, new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }));
		dataTimeOfPreviousHeartBeat.setData(timeOfPreviousHeartBeat,
				new NDSize(new int[] { 1, timeOfPreviousHeartBeat.length }), new NDSize(2, 0));
	}

	
	/***** Getry a Setry ****/
	
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
