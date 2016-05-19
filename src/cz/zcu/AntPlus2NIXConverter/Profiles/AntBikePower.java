package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.UUID;

import org.g_node.nix.*;

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

	/** Aributy tridy **/

	private double[] power;

	private OdMLData metaData;

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
		
		Block block = nixFile.createBlock("kiv.zcu.cz_block_" + UUID.randomUUID().toString(), "recording");
		block.createSource("kiv.zcu.cz_source_bikePower_" + UUID.randomUUID().toString(), "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o vykonu */
		DataArray dataArrayBikePower = block.createDataArray("kiv.zcu.cz_data_array_powerOnly_" + UUID.randomUUID().toString(), "antMessage", DataType.Double,
				new NDSize(new int[] { 1, power.length }));
		dataArrayBikePower.setData(power, new NDSize(new int[] { 1, power.length }), new NDSize(2, 0));		
		
	}
	
	public static void main(String[] args) {
		AntBikePower bikePower = new AntBikePower(new double[] { 1.0, 3.2, 5.6, 6.8 }, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		File file = File.open("test_Block_" + UUID.randomUUID().toString() + ".h5", FileMode.Overwrite);
		bikePower.fillNixFile(file);
		System.out.println(file.getBlock(0).getName());
		System.out.println(file.getBlock(0).getId());
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

	
