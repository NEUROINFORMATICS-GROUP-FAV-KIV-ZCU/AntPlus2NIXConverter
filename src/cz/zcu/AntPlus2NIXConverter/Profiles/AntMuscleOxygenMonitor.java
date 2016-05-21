package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Muscle Oxygen Monitor
 * Profil pro vytvoreni ­ HDF5 souboru ze zarizeni Muscle Oxygen Monitor.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntMuscleOxygenMonitor implements INixFile{

	/** Aributy tridy **/
	private static int index = 0;
	
	private double[] saturatedHemoglPerc;
	private double[] hemoglobinConcentrate;
	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu spolecne s metadaty
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
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat.
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("muscleOxygenMonitor" + index, "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o hemoglobinu v procentech */
		DataArray dataSaturatedHemoglPerc = block.createDataArray("saturatedHemoglPerc" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, saturatedHemoglPerc.length }));
		dataSaturatedHemoglPerc.setData(saturatedHemoglPerc, new NDSize(new int[] { 1, saturatedHemoglPerc.length }),
				new NDSize(2, 0));

		/* Naplneni dataArray daty o koncentraci hemoglobinu */
		DataArray dataHemoglobinConcentrate = block.createDataArray("hemoglobinConcentr" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }));
		dataHemoglobinConcentrate.setData(hemoglobinConcentrate,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }), new NDSize(2, 0));
	}

	/** Getry a setry **/
	
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

}
