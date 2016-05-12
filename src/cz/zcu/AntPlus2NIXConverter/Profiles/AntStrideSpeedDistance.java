package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixStream;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Stride Speed & Distance.
 * Profil pro vytvoreni ­ HDF5 souboru ze zarizeni Stride Speed & Distance.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntStrideSpeedDistance implements INixStream{

	/** Aributy tridy **/
	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataStrideCount;
	private DataArray dataDistance;
	private DataArray dataSpeed;

	private long[] strideCount;
	private double[] distance;
	private double[] speed;
	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param strideCount
	 *            Pocet kroku
	 * @param distance
	 *            Vzdalenost
	 * @param speed
	 *            Rychlost
	 * @param metaData
	 *            MetaData
	 */
	public AntStrideSpeedDistance(long[] strideCount, double[] distance, double[] speed, OdMLData metaData) {

		this.strideCount = strideCount;
		this.distance = distance;
		this.speed = speed;
		this.metaData = metaData;

		index++;
	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	public Stream<Block> createNixFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("strideSpeedDistance" + index, "antMessage");

		/* Pridani metadat do bloku */

		section = metaData.createSectionNix(file);

		/* Naplneni dataArray daty chuzi */
		dataStrideCount = block.createDataArray("strideCount" + index, "antMessage", DataType.Int64,
				new NDSize(new int[] { 1, strideCount.length }));
		dataStrideCount.setData(strideCount, new NDSize(new int[] { 1, strideCount.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o vzdalenosti */
		dataDistance = block.createDataArray("distance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, distance.length }));
		dataDistance.setData(distance, new NDSize(new int[] { 1, distance.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o rychlosti */
		dataSpeed = block.createDataArray("speed" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, speed.length }));
		dataSpeed.setData(speed, new NDSize(new int[] { 1, speed.length }), new NDSize(2, 0));

		List<Block> blocks = Arrays.asList(block);
		
		file.close();

		return blocks.stream();
	}

	/** Getry a Setry **/
	public File getFile() {
		return file;
	}

	public Block getBlock() {
		return block;
	}

	public Source getSource() {
		return source;
	}

	public DataArray getDataStrideCount() {
		return dataStrideCount;
	}

	public DataArray getDataDistance() {
		return dataDistance;
	}

	public DataArray getDataSpeed() {
		return dataSpeed;
	}

	public long[] getStrideCount() {
		return strideCount;
	}

	public double[] getDistance() {
		return distance;
	}

	public double[] getSpeed() {
		return speed;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

	public Section getSection() {
		return section;
	}

}
