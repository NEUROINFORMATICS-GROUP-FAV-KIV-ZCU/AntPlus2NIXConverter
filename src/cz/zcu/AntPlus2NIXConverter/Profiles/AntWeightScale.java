package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Weight Scale.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntWeightScale {

	/** Staticky atribut tridy pro identifikaci casti souboru */
	
	private static int index = 0;

	/** Aributy tridy **/
	
	private int[] weight;
	private OdMLData metaData;
	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataWeight;

	/**
	 * Konstruktor tridy. 
	 * Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param weight Vaha
	 * @param metaData MetaData
	 */
	public AntWeightScale(int[] weight, OdMLData metaData) {

		this.weight = weight;
		this.metaData = metaData;
		index++;

	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat.
	 * @param fileName Nazev souboru
	 */
	public void createNixFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("weightScale" + index, "antMessage");

		/* Pridani metadat do bloku */

		section = file.createSection("AntMetaData", "metadata");
		section.createProperty("deviceName", metaData.getDeviceName());
		section.createProperty("deviceType", metaData.getDeviceType());
		section.createProperty("deviceState", metaData.getDeviceState());
		section.createProperty("deviceNumber", metaData.getDeviceNumber());
		section.createProperty("batteryStatus", metaData.getBatteryStatus());
		section.createProperty("signalStrength", metaData.getSignalStrength());
		section.createProperty("manufacturerIdentification", metaData.getManIdentification());
		section.createProperty("manufacturerSpecificData", metaData.getManSpecData());
		section.createProperty("productInfo", metaData.getProdInfo());

		/* Naplneni dataArray daty o vaze */
		
		dataWeight = block.createDataArray("weight" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, weight.length }));
		dataWeight.setData(weight, new NDSize(new int[] { 1, weight.length }), new NDSize(2, 0));
		dataWeight.setUnit("kg");
		file.close();

	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		AntWeightScale.index = index;
	}

	public int[] getWeight() {
		return weight;
	}

	public void setWeight(int[] weight) {
		this.weight = weight;
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

	public void setFile(File file) {
		this.file = file;
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

	public DataArray getDataWeight() {
		return dataWeight;
	}

	public void setDataWeight(DataArray dataWeight) {
		this.dataWeight = dataWeight;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}
