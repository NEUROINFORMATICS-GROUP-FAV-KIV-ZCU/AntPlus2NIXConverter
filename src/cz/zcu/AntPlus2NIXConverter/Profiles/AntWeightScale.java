package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Weight Scale.* @author Vaclav Janoch,
 * Filip Kupilik, Petr Tobias
 * 
 * @version 1.0
 */
public class AntWeightScale {

	/** Aributy tridy **/

	private int index = 0;

	private int[] weight;
	private OdMLData metaData;
	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataWeight;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param weight
	 *            Vaha
	 * @param metaData
	 *            MetaData
	 */
	public AntWeightScale(int[] weight, OdMLData metaData) {

		this.weight = weight;
		this.metaData = metaData;
		index++;

	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat.
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	public void createNixFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("weightScale" + index, "antMessage");

		/* Pridani metadat do bloku */
		section = metaData.createSectionNix(file);

		/* Naplneni dataArray daty o vaze */
		dataWeight = block.createDataArray("weight" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, weight.length }));
		dataWeight.setData(weight, new NDSize(new int[] { 1, weight.length }), new NDSize(2, 0));

		file.close();

	}

	/** Getry a Setry **/

	public int[] getWeight() {
		return weight;
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

	public Block getBlock() {
		return block;
	}

	public Source getSource() {
		return source;
	}

	public DataArray getDataWeight() {
		return dataWeight;
	}

	public Section getSection() {
		return section;
	}

}
