package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

import javax.xml.crypto.Data;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Power Profil pro
 * vytvoreni≠ HDF5 souboru ze zarizeni Bike Power.Profil pro vytvoren√≠ HDF5
 * souboru ze zarizeni Bike Power.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntBikePower implements INixFile {

	/** Aributy tridy **/

	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataArrayBikePower;

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
		index++;

	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat.
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */

	@Override
	public void createNixFile(File nixFile) {
		
		block = nixFile.createBlock("recording" + index, "recording");

		source = block.createSource("bikePower" + index, "antMessage");

		/* Pridani metadat do bloku */
		section = metaData.createSectionNix(file);

		/* Naplneni dataArray daty o vykonu */
		dataArrayBikePower = block.createDataArray("powerOnly" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, power.length }));
		dataArrayBikePower.setData(power, new NDSize(new int[] { 1, power.length }), new NDSize(2, 0));		
		
	}
	
	
	public static void main(String[] args) {
		AntBikePower abp = new AntBikePower(new double[]{2,3,4,5,6,4}, new OdMLData(9, 9, 9, 9, 9, 9, 9, 9, 9));
	
		File f = new File();
		f.open("Testovaci.h5", FileMode.Overwrite);
		f.createBlock("Testovaci", "Test");
		abp.createNixFile(f);
		
		System.out.println(f.getBlocks().size());
	
	}

	
}

	
