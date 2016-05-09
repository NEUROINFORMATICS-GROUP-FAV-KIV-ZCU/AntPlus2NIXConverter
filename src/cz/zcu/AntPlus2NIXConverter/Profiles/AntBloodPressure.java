package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.GregorianCalendar;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Profil pro vytvorení HDF5 souboru ze zarizeni Blood Pressure.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntBloodPressure {

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
	 * Konstruktor tridy.
	 * @param systolic Systolicky tlak
	 * @param distolic Distolicky tlak
	 * @param heartRate Srdecni tep
	 * @param timeStamp Casova znamka
	 * @param metaData MetaData
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

	public void prevedTimeStamp() {
		timeStampSt = new byte[timeStamp.length];
		for (int i = 0; i < timeStamp.length; i++) {
			timeStampSt[i] = Byte.parseByte(timeStamp[i].toString());
		}

	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru i s celou jeho strukturou vcetne dat a metadat.
	 * @param fileName Nazev souboru
	 */
	public void createNixFile(String fileName) {

		prevedTimeStamp();
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("bloodPressure" + index, "antMessage");

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

		dataSystolic = block.createDataArray("Systolic" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, systolic.length }));
		dataSystolic.setData(systolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));

		dataDistolic = block.createDataArray("diastolicBloodPress" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, distolic.length }));
		dataDistolic.setData(distolic, new NDSize(new int[] { 1, systolic.length }), new NDSize(2, 0));

		dataHeartRate = block.createDataArray("HeartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, heartRate.length }));
		dataHeartRate.setData(heartRate, new NDSize(new int[] { 1, heartRate.length }), new NDSize(2, 0));

		dataTime = block.createDataArray("TimeStamp" + index, "antMessage", DataType.Int16,
				new NDSize(new int[] { 1, timeStampSt.length }));
		dataTime.setData(timeStampSt, new NDSize(new int[] { 1, timeStampSt.length }), new NDSize(2, 0));

		//file.close();
	}

	public static void main(String[] args) {
	AntBloodPressure b =	new AntBloodPressure(new int[] { 43 }, new int[] { 3, 5, 67, 3 }, new int[] { 4, 5, 6, 4 },
				new GregorianCalendar[] {}, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		b.createNixFile("testovaci.h5");
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
