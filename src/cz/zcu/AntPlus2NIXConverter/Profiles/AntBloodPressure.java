package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.GregorianCalendar;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Trida pro zpracovani informaci o ANT plus profilu BloodPressure Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Blood Pressure.@author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntBloodPressure {

	/** Aributy tridy **/
	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataSystolic;
	private DataArray dataDistolic;
	private DataArray dataHeartRate;
	private DataArray dataTime;

	private int[] systolic;
	private int[] distolic;
	private int[] heartRate;
	private GregorianCalendar[] timeStamp;
	private byte[] timeStampSt;

	private OdMLData metaData;

	/**
	  Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu
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
	public void prevedTimeStamp() {
		timeStampSt = new byte[timeStamp.length];
		for (int i = 0; i < timeStamp.length; i++) {
			timeStampSt[i] = Byte.parseByte(timeStamp[i].toString());
		}

	}

	/**
	 *Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat 
	 * @param fileName
	 *            Nazev souboru 
	 */
	public void createNixFile(String fileName) {

		prevedTimeStamp();
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("bloodPressure" + index, "antMessage");

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

		/* Naplneni dataArray daty o systolickem tlaku */
 		
		dataSystolic = block.createDataArray("systolicBloodPress" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, systolic.length }));
		dataSystolic.setData(systolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));
		dataSystolic.setUnit("mmHg");
		/* Naplneni dataArray daty o distolickem tlaku */		
		dataDistolic = block.createDataArray("diastolicBloodPress" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, distolic.length }));
		dataDistolic.setData(distolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));
		dataDistolic.setUnit("mmHg");
		/* Naplneni dataArray daty o srdecni cinnosti */

		dataHeartRate = block.createDataArray("heartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartRate.length }));
		dataHeartRate.setData(heartRate, new NDSize(new int[] { 1, heartRate.length }), new NDSize(2, 0));
		dataHeartRate.setUnit("bpm");
		/* Naplneni dataArray daty o case */
		dataTime = block.createDataArray("timeStamp" + index, "antMessage", DataType.Int16,
				new NDSize(new int[] { 1, timeStampSt.length }));
		dataTime.setData(timeStampSt, new NDSize(new int[] { 1, timeStampSt.length }), new NDSize(2, 0));
		dataTime.setUnit("N/A");
		file.close();
	}

	/** Getry a Setry **/
	
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

	public DataArray getDataSystolic() {
		return dataSystolic;
	}

	public void setDataSystolic(DataArray dataSystolic) {
		this.dataSystolic = dataSystolic;
	}

	public DataArray getDataDistolic() {
		return dataDistolic;
	}

	public void setDataDistolic(DataArray dataDistolic) {
		this.dataDistolic = dataDistolic;
	}

	public DataArray getDataHeartRate() {
		return dataHeartRate;
	}

	public void setDataHeartRate(DataArray dataHeartRate) {
		this.dataHeartRate = dataHeartRate;
	}

	public DataArray getDataTime() {
		return dataTime;
	}

	public void setDataTime(DataArray dataTime) {
		this.dataTime = dataTime;
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

	public byte[] getTimeStampSt() {
		return timeStampSt;
	}

	public void setTimeStampSt(byte[] timeStampSt) {
		this.timeStampSt = timeStampSt;
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
