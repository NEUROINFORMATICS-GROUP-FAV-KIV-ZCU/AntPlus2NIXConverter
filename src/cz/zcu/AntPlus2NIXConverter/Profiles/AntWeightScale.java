package cz.zcu.AntPlus2NIXConverter.Profiles;


import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Weight Scale.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * 
 * @version 1.0
 */
public class AntWeightScale implements INixFile{

	/** Aributy tridy **/
	private static int index = 0;
	
	private int[] weight;
	private OdMLData metaData;

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
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat.
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("weightScale" + index, "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o vaze */
		DataArray dataWeight = block.createDataArray("weight" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, weight.length }));
		dataWeight.setData(weight, new NDSize(new int[] { 1, weight.length }), new NDSize(2, 0));

	}

	/** Getry a Setry **/
	

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
}
