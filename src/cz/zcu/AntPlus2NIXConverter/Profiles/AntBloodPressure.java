package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.GregorianCalendar;
import java.util.UUID;

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
	private int[] systolic;
	private int[] diastolic;
	private int[] heartRate;
	private GregorianCalendar[] timeStamp;

	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu
	 * spolecne s metadaty
	 * 
	 * @param systolic
	 *            Systolicky tlak
	 * @param diastolic
	 *            Distolicky tlak
	 * @param heartRate
	 *            Srdecni tep
	 * @param timeStamp
	 *            Casova znamka
	 * @param metaData
	 *            MetaData
	 */
	public AntBloodPressure(int[] systolic, int[] diastolic, int[] heartRate, GregorianCalendar[] timeStamp,
			OdMLData metaData) {

		this.systolic = systolic;
		this.diastolic = diastolic;
		this.heartRate = heartRate;
		this.timeStamp = timeStamp;
		this.metaData = metaData;

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
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat
	 * 
	 *@param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		byte[] timeStampConv = convertTimeStamp();

		Block block = nixFile.createBlock("kiv.zcu.cz_block_" + UUID.randomUUID().toString(), "recording");

		block.createSource("kiv.zcu.cz_source_bloodPressure_" + UUID.randomUUID().toString(), "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o systolickem tlaku */
		DataArray dataSystolic = block.createDataArray("kiv.zcu.cz_data_array_systolicBloodPress_" + UUID.randomUUID().toString(), "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, systolic.length }));
		dataSystolic.setData(systolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o diastolickem tlaku */
		DataArray dataDiastolic = block.createDataArray("kiv.zcu.cz_data_array_diastolicBloodPress_" + UUID.randomUUID().toString(), "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, diastolic.length }));
		dataDiastolic.setData(diastolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o srdecni cinnosti */
		DataArray dataHeartRate = block.createDataArray("kiv.zcu.cz_data_array_heartRate_" + UUID.randomUUID().toString(), "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartRate.length }));
		dataHeartRate.setData(heartRate, new NDSize(new int[] { 1, heartRate.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o case */
		DataArray dataTime = block.createDataArray("kiv.zcu.cz_data_array_timeStamp_" + UUID.randomUUID().toString(), "antMessage", DataType.Int16,
				new NDSize(new int[] { 1, timeStampConv.length }));
		dataTime.setData(timeStampConv, new NDSize(new int[] { 1, timeStampConv.length }), new NDSize(2, 0));

	}

	/** Getry a Setry **/
	
	
	public int[] getSystolic() {
		return systolic;
	}


	public void setSystolic(int[] systolic) {
		this.systolic = systolic;
	}

	public int[] getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(int[] distolic) {
		this.diastolic = distolic;
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
