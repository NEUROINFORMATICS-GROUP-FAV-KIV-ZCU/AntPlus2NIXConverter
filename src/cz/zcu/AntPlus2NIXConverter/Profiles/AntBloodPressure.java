package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.GregorianCalendar;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * @Trida pro zpracovani informaci o ANT plus profilu BloodPressure Profil pro
 *        vytvoreni­ HDF5 souboru ze zarizeni Blood Pressure.
 * @author Vaclav Janoch,Filip Kupilik, Petr Tobias
 * 
 * @version 1.0
 */
public class AntBloodPressure implements INixFile{

	/** Aributy tridy **/
	private static int index = 0;

	private int[] systolic;
	private int[] distolic;
	private int[] heartRate;
	private GregorianCalendar[] timeStamp;

	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu
	 * spolecne s metadaty
	 * 
	 * @param systolic
	 *            Systolicky tlak
	 * @param distolic
	 *            Distolicky tlak
	 * @param heartRate
	 *            Srdecni tep
	 * @param timeStamp
	 *            Casova znamka
	 * @param metaData
	 *            MetaData
	 */
	public AntBloodPressure(int[] systolic, int[] distolic, int[] heartRate, GregorianCalendar[] timeStamp,
			OdMLData metaData) {

		this.systolic = systolic;
		this.distolic = distolic;
		this.heartRate = heartRate;
		this.timeStamp = timeStamp;
		this.metaData = metaData;

		index++;

	}

	/**
	 * Pomocna metoda pro prevedeni dat ulozenych ve formatu GregorianCalendar
	 * do datoveho typu Byte pro ulozeni do NIX
	 */
	private byte[] convertTimeStamp() {
		byte[] timeStampSt = new byte[timeStamp.length];
		for (int i = 0; i < timeStamp.length; i++) {
			timeStampSt[i] = Byte.parseByte(timeStamp[i].toString());
		}
		return timeStampSt;
	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	@Override
	public void createNixFile(File nixFile) {

		byte[] timeStampConv = convertTimeStamp();

		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("bloodPressure" + index, "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o systolickem tlaku */
		DataArray dataSystolic = block.createDataArray("systolicBloodPress" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, systolic.length }));
		dataSystolic.setData(systolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o distolickem tlaku */
		DataArray dataDistolic = block.createDataArray("diastolicBloodPress" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, distolic.length }));
		dataDistolic.setData(distolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o srdecni cinnosti */
		DataArray dataHeartRate = block.createDataArray("heartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartRate.length }));
		dataHeartRate.setData(heartRate, new NDSize(new int[] { 1, heartRate.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o case */
		DataArray dataTime = block.createDataArray("timeStamp" + index, "antMessage", DataType.Int16,
				new NDSize(new int[] { 1, timeStampConv.length }));
		dataTime.setData(timeStampConv, new NDSize(new int[] { 1, timeStampConv.length }), new NDSize(2, 0));

	}

	/** Getry a Setry **/
	
	public static int getIndex() {
		return index;
	}
	
	public static void setIndex(int index) {
		AntBloodPressure.index = index;
	}
	
	public int[] getSystolic() {
		return systolic;
	}


	public void setSystolic(int[] systolic) {
		this.systolic = systolic;
	}

	public int[] getDistolic() {
		return distolic;
	}

	public void setDistolic(int[] distolic) {
		this.distolic = distolic;
	}

	public int[] getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int[] heartRate) {
		this.heartRate = heartRate;
	}

	public GregorianCalendar[] getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(GregorianCalendar[] timeStamp) {
		this.timeStamp = timeStamp;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

}
