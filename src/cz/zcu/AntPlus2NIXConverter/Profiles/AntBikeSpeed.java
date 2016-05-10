package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Bike Speed.@author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntBikeSpeed {

	/** Aributy tridy **/
	
	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataArrayLatSpEvTime;
	private DataArray dataArrayCumWheelRew;

	private int[] cumWheelRew;
	private int[] latSpEvTime;

	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param cumWheelRew
	 *            Otacky kola
	 * @param latSpEvTime
	 *            Cas
	 * @param metaData
	 *            MetaData
	 */
	public AntBikeSpeed(int[] cumWheelRew, int[] latSpEvTime, OdMLData metaData) {

		this.cumWheelRew = cumWheelRew;
		this.latSpEvTime = latSpEvTime;
		this.metaData = metaData;
		index++;

	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	public void createNixFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("bikeSpeed" + index, "antMessage");

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
		
		dataArrayLatSpEvTime = block.createDataArray("LatSpEvTime" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, latSpEvTime.length }));
		dataArrayLatSpEvTime.setData(latSpEvTime, new NDSize(new int[] { 1, latSpEvTime.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o otaceni kola */
		
		dataArrayCumWheelRew = block.createDataArray("CumWheelRew" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, cumWheelRew.length }));
		dataArrayCumWheelRew.setData(cumWheelRew, new NDSize(new int[] { 1, cumWheelRew.length }), new NDSize(2, 0));

		file.close();
	}
	
	/***** Getry a Setry *******/

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

	public DataArray getDataArrayLatSpEvTime() {
		return dataArrayLatSpEvTime;
	}

	public void setDataArrayLatSpEvTime(DataArray dataArrayLatSpEvTime) {
		this.dataArrayLatSpEvTime = dataArrayLatSpEvTime;
	}

	public DataArray getDataArrayCumWheelRew() {
		return dataArrayCumWheelRew;
	}

	public void setDataArrayCumWheelRew(DataArray dataArrayCumWheelRew) {
		this.dataArrayCumWheelRew = dataArrayCumWheelRew;
	}

	public int[] getCumWheelRew() {
		return cumWheelRew;
	}

	public void setCumWheelRew(int[] cumWheelRew) {
		this.cumWheelRew = cumWheelRew;
	}

	public int[] getLatSpEvTime() {
		return latSpEvTime;
	}

	public void setLatSpEvTime(int[] latSpEvTime) {
		this.latSpEvTime = latSpEvTime;
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
