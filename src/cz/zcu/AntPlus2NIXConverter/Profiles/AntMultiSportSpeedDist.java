package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvorení HDF5 souboru ze zarizeni Multi Sport Speed & Distance.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntMultiSportSpeedDist implements INixFile{

	/** Aributy tridy **/
	private static int index = 0;
	
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
	 * Metoda pro vytvoreni casti  NIX, vcetne dat a metadat.
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("multiSportSpeedDist" + index, "antMessage");
		
		/* Pridani metadat do souboru */
		metaData.createSectionNix(nixFile);
		
		/* Naplneni dataArray daty o case */
		DataArray dataTime = block.createDataArray("dataTime" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, timeStamp.length }));
		dataTime.setData(timeStamp, new NDSize(new int[] { 1, timeStamp.length }), new NDSize(2, 0));
		
		/* Naplneni dataArray daty o vzdalenosti */
		DataArray dataDistance = block.createDataArray("dataDistance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, distance.length }));
		dataDistance.setData(distance, new NDSize(new int[] { 1, distance.length }), new NDSize(2, 0));
		
	}

	/*** Getry a Setry ***/


	public double[] getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(double[] timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double[] getDistance() {
		return distance;
	}

	public void setDistance(double[] distance) {
		this.distance = distance;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

}
