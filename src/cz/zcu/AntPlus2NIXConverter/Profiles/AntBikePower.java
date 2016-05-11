package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

import javax.xml.crypto.Data;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixStream;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Power Profil pro
 * vytvoreni≠ HDF5 souboru ze zarizeni Bike Power.Profil pro vytvoren√≠ HDF5
 * souboru ze zarizeni Bike Power.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntBikePower implements INixStream {

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
	public Stream<File> createNixFile(String fileName) {
		Stream<File> stream = null;

		//file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("bikePower" + index, "antMessage");

		
		/* Pridani metadat do bloku */
		section = metaData.createSectionNix(file);
		
		/* Naplneni dataArray daty o vykonu */
		dataArrayBikePower = block.createDataArray("powerOnly" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, power.length }));
		dataArrayBikePower.setData(power, new NDSize(new int[] { 1, power.length }), new NDSize(2, 0));

		file.close();
		
		stream.mapToLong((ToLongFunction<? super File>) file);
		return stream;
	}


	/** Getry a Setry **/

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public File getFile() {
		return file;
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

	public DataArray getDataArrayBikePower() {
		return dataArrayBikePower;
	}

	public void setDataArrayBikePower(DataArray dataArrayBikePower) {
		this.dataArrayBikePower = dataArrayBikePower;
	}

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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
