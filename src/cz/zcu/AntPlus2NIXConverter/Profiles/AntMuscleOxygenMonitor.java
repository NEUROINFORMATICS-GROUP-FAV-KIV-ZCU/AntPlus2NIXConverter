package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 *Trida pro zpracovani informaci o ANT plus profilu Muscle Oxygen Monitor
 * Profil pro vytvoreni ­ HDF5 souboru ze zarizeni Muscle Oxygen Monitor.
 *@author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntMuscleOxygenMonitor {

	/** Aributy tridy **/
	
	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataHemoglobinConcentrate;
	private DataArray dataSaturatedHemoglPerc;

	private double[] saturatedHemoglPerc;
	private double[] hemoglobinConcentrate;
	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * @param saturatedHemoglPerc Predchozi a soucasny hemoglobin
	 * @param hemoglobinConcentrate Koncentrace hemoglobinu
	 * @param metaData MetaData
	 */
	public AntMuscleOxygenMonitor(double[] saturatedHemoglPerc, double[] hemoglobinConcentrate, OdMLData metaData) {

		this.saturatedHemoglPerc = saturatedHemoglPerc;
		this.hemoglobinConcentrate = hemoglobinConcentrate;
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

		source = block.createSource("muscleOxygenMonitor" + index, "antMessage");

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

		/* Naplneni dataArray daty o hemoglobinu v procentech */

		dataSaturatedHemoglPerc = block.createDataArray("saturatedHemoglPerc" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, saturatedHemoglPerc.length }));
		dataSaturatedHemoglPerc.setData(saturatedHemoglPerc, new NDSize(new int[] { 1, saturatedHemoglPerc.length }),
				new NDSize(2, 0));

		dataSaturatedHemoglPerc.setUnit("%");
		/* Naplneni dataArray daty o koncentraci hemoglobinu */

		dataHemoglobinConcentrate = block.createDataArray("hemoglobinConcentr" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }));
		dataHemoglobinConcentrate.setData(hemoglobinConcentrate,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }), new NDSize(2, 0));
		dataSaturatedHemoglPerc.setUnit("g/dl");
		file.close();

	}

	
	/** Getry a setry **/
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
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

	public DataArray getDataHemoglobinConcentrate() {
		return dataHemoglobinConcentrate;
	}

	public void setDataHemoglobinConcentrate(DataArray dataHemoglobinConcentrate) {
		this.dataHemoglobinConcentrate = dataHemoglobinConcentrate;
	}

	public DataArray getDataSaturatedHemoglPerc() {
		return dataSaturatedHemoglPerc;
	}

	public void setDataSaturatedHemoglPerc(DataArray dataSaturatedHemoglPerc) {
		this.dataSaturatedHemoglPerc = dataSaturatedHemoglPerc;
	}

	public double[] getSaturatedHemoglPerc() {
		return saturatedHemoglPerc;
	}

	public void setSaturatedHemoglPerc(double[] saturatedHemoglPerc) {
		this.saturatedHemoglPerc = saturatedHemoglPerc;
	}

	public double[] getHemoglobinConcentrate() {
		return hemoglobinConcentrate;
	}

	public void setHemoglobinConcentrate(double[] hemoglobinConcentrate) {
		this.hemoglobinConcentrate = hemoglobinConcentrate;
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
