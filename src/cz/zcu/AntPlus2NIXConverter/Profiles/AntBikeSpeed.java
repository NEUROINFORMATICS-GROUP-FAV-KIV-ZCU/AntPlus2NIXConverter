package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Bike Speed Profil pro
 * vytvoreni­ HDF5 souboru ze zarizeni Bike Speed.
 * @author Vaclav Janoch, Filip
 * Kupilik, Petr Tobias
 * 
 * @version 1.0
 */
public class AntBikeSpeed implements INixFile{

	/** Staticky aribut tridy **/

	private static int index = 0;

	/** Aributy tridy **/

	private int[] cumWheelRew;
	private int[] latSpEvTime;

	private OdMLData metaData;

	/**
	 * Konstruktor tridy. Naplni atributy tridy informacemi z ANT plus profilu s
	 * polecne s metadaty
	 * 
	 * @param cumWheelRew
	 *            Otacky kola
	 * @param latSpEvTime
	 *            Cas
	 * @param metaData
	 *            MetaData
	 */
	public AntBikeSpeed(int[] cumWheelRew, int[] latSpEvTime, OdMLData metaData) {

		this.cumWheelRew = cumWheelRew;
		this.latSpEvTime = latSpEvTime;
		this.metaData = metaData;
		index++;

	}

	/**
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("bikeSpeed" + index, "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o case */
		DataArray dataArrayLatSpEvTime = block.createDataArray("LatSpEvTime" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, latSpEvTime.length }));
		dataArrayLatSpEvTime.setData(latSpEvTime, new NDSize(new int[] { 1, latSpEvTime.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o otaceni kola */
		DataArray dataArrayCumWheelRew = block.createDataArray("CumWheelRew" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, cumWheelRew.length }));
		dataArrayCumWheelRew.setData(cumWheelRew, new NDSize(new int[] { 1, cumWheelRew.length }), new NDSize(2, 0));
		
	}

	/***** Getry a Setry *******/
	
	public int[] getCumWheelRew() {
		return cumWheelRew;
	}


	public void setCumWheelRew(int[] cumWheelRew) {
		this.cumWheelRew = cumWheelRew;
	}

	public int[] getLatSpEvTime() {
		return latSpEvTime;
	}

	public void setLatSpEvTime(int[] latSpEvTime) {
		this.latSpEvTime = latSpEvTime;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

}
