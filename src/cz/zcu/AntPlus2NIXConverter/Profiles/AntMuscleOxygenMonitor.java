package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Muscle Oxygen Monitor
 * Profil pro vytvoreni ­ HDF5 souboru ze zarizeni Muscle Oxygen Monitor.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
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
	 * 
	 * @param saturatedHemoglPerc
	 *            Predchozi a soucasny hemoglobin
	 * @param hemoglobinConcentrate
	 *            Koncentrace hemoglobinu
	 * @param metaData
	 *            MetaData
	 */
	public AntMuscleOxygenMonitor(double[] saturatedHemoglPerc, double[] hemoglobinConcentrate, OdMLData metaData) {

		this.saturatedHemoglPerc = saturatedHemoglPerc;
		this.hemoglobinConcentrate = hemoglobinConcentrate;
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

		source = block.createSource("muscleOxygenMonitor" + index, "antMessage");

		/* Pridani metadat do bloku */
		section = metaData.createSectionNix(file);

		/* Naplneni dataArray daty o hemoglobinu v procentech */

		dataSaturatedHemoglPerc = block.createDataArray("saturatedHemoglPerc" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, saturatedHemoglPerc.length }));
		dataSaturatedHemoglPerc.setData(saturatedHemoglPerc, new NDSize(new int[] { 1, saturatedHemoglPerc.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o koncentraci hemoglobinu */
		dataHemoglobinConcentrate = block.createDataArray("hemoglobinConcentr" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }));
		dataHemoglobinConcentrate.setData(hemoglobinConcentrate,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }), new NDSize(2, 0));

		file.close();

	}

	/** Getry a setry **/
	public Section getSection() {
		return section;
	}

	public Block getBlock() {
		return block;
	}

	public Source getSource() {
		return source;
	}

	public DataArray getDataHemoglobinConcentrate() {
		return dataHemoglobinConcentrate;
	}

	public DataArray getDataSaturatedHemoglPerc() {
		return dataSaturatedHemoglPerc;
	}

	public double[] getSaturatedHemoglPerc() {
		return saturatedHemoglPerc;
	}

	public double[] getHemoglobinConcentrate() {
		return hemoglobinConcentrate;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public int getIndex() {
		return index;
	}

	public File getFile() {
		return file;
	}

}
