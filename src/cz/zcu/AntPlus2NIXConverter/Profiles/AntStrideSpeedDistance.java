package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Stride Speed & Distance.
 * Profil pro vytvoreni ­ HDF5 souboru ze zarizeni Stride Speed & Distance.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntStrideSpeedDistance implements INixFile{

	/** Aributy tridy **/
	private static int index = 0;
	
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
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat.
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("strideSpeedDistance" + index, "antMessage");

		/* Pridani metadat do souboru */
		metaData.createSectionNix(nixFile);

		/* Naplneni dataArray daty chuzi */
		DataArray dataStrideCount = block.createDataArray("strideCount" + index, "antMessage", DataType.Int64,
				new NDSize(new int[] { 1, strideCount.length }));
		dataStrideCount.setData(strideCount, new NDSize(new int[] { 1, strideCount.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o vzdalenosti */
		DataArray dataDistance = block.createDataArray("distance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, distance.length }));
		dataDistance.setData(distance, new NDSize(new int[] { 1, distance.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o rychlosti */
		DataArray dataSpeed = block.createDataArray("speed" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, speed.length }));
		dataSpeed.setData(speed, new NDSize(new int[] { 1, speed.length }), new NDSize(2, 0));

	}

	/** Getry a Setry **/

	public long[] getStrideCount() {
		return strideCount;
	}

	public void setStrideCount(long[] strideCount) {
		this.strideCount = strideCount;
	}

	public double[] getDistance() {
		return distance;
	}

	public void setDistance(double[] distance) {
		this.distance = distance;
	}

	public double[] getSpeed() {
		return speed;
	}

	public void setSpeed(double[] speed) {
		this.speed = speed;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

}
