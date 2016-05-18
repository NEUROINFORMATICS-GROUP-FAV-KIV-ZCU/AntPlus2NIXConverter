package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Power Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Bike Power.Profil pro vytvoreni­ HDF5
 * souboru ze zarizeni Bike Power.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntBikePower implements INixFile {

	/** Staticke aributy tridy **/

	private static int index = 0;

	/** Aributy tridy **/

	private double[] power;

	private OdMLData metaData;
	
	private ID id;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi o vykonu a
	 * metadatech vysilanych Ant plus profilem.
	 * 
	 * @param power
	 *            Vykon
	 * @param metaData
	 *            MetaData
	 */
	public AntBikePower(double[] power, OdMLData metaData) {

		id = new ID();
		this.power = power;
		this.metaData = metaData;
		
	}

	/**
	 * Metoda pro vytvoreni casti  NIX, vcetne dat a metadat.
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */

	@Override
	public void fillNixFile(File nixFile) {
		
		Block block = nixFile.createBlock("recording" + id.randomString(24), "recording");
		block.setDefinition("kiv.zcu.cz");
		block.createSource("bikePower" + id.randomString(24), "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o vykonu */
		DataArray dataArrayBikePower = block.createDataArray("powerOnly" + id.randomString(24), "antMessage", DataType.Double,
				new NDSize(new int[] { 1, power.length }));
		dataArrayBikePower.setData(power, new NDSize(new int[] { 1, power.length }), new NDSize(2, 0));		
		
	}
	
	/***** Getry a Setry *******/
	

	public double[] getPower() {
		return power;
	}


	public void setPower(double[] power) {
		this.power = power;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}
	
}

	
