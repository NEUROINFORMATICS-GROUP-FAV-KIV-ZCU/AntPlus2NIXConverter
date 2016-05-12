package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixStream;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvorení HDF5 souboru ze zarizeni Multi Sport Speed & Distance.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntMultiSportSpeedDist implements INixStream{

	/** Aributy tridy **/

	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataTime;
	private DataArray dataDistance;

	private double[] timeStamp;
	private double[] distance;
	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param timeStamp
	 *            Casova znamka
	 * @param distance
	 *            Vzdalenost
	 * @param metaData
	 *            MetaData
	 */
	public AntMultiSportSpeedDist(double[] timeStamp, double[] distance, OdMLData metaData) {

		this.timeStamp = timeStamp;
		this.distance = distance;
		this.metaData = metaData;

		index++;
	}

	/**
	 *  Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	public Stream<Block> createNixFile(String fileName) {

		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("multiSportSpeedDist" + index, "antMessage");
		
		/* Pridani metadat do bloku */
		section = metaData.createSectionNix(file);
		
		/* Naplneni dataArray daty o case */
		dataTime = block.createDataArray("dataTime" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, timeStamp.length }));
		dataTime.setData(timeStamp, new NDSize(new int[] { 1, timeStamp.length }), new NDSize(2, 0));
		
		/* Naplneni dataArray daty o vzdalenosti */
		dataDistance = block.createDataArray("dataDistance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, distance.length }));
		dataDistance.setData(distance, new NDSize(new int[] { 1, distance.length }), new NDSize(2, 0));
		
		List<Block> blocks = Arrays.asList(block);
		
		file.close();
		
		return blocks.stream();
	}

	/*** Getry a Setry ***/
	public File getFile() {
		return file;
	}


	public Block getBlock() {
		return block;
	}


	public Source getSource() {
		return source;
	}

	public Section getSection() {
		return section;
	}

	public DataArray getDataTime() {
		return dataTime;
	}


	public DataArray getDataDistance() {
		return dataDistance;
	}


	public double[] getTimeStamp() {
		return timeStamp;
	}


	public double[] getDistance() {
		return distance;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

}
