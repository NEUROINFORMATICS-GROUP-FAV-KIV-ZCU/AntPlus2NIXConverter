package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.util.UUID;

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

	/** Aributy tridy **/

	private int[] cumWheelRew;
	private int[] lastSpEvTime;

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
		this.lastSpEvTime = latSpEvTime;
		this.metaData = metaData;

	}

	/**
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		Block block = nixFile.createBlock("kiv.zcu.cz_block_" + UUID.randomUUID().toString(), "recording");

		block.createSource("kiv.zcu.cz_source_bikeSpeed_" + UUID.randomUUID().toString(), "antMessage");

		/* Pridani metadat do bloku */
		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o case */
		DataArray dataArrayLatSpEvTime = block.createDataArray("kiv.zcu.cz_data_array_lastSpEvTime_" + UUID.randomUUID().toString(), "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, lastSpEvTime.length }));
		dataArrayLatSpEvTime.setData(lastSpEvTime, new NDSize(new int[] { 1, lastSpEvTime.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o otaceni kola */
		DataArray dataArrayCumWheelRew = block.createDataArray("kiv.zcu.cz_data_array_cumWheelRew_" + UUID.randomUUID().toString(), "antMessage", DataType.Int32,
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
		return lastSpEvTime;
	}

	public void setLatSpEvTime(int[] latSpEvTime) {
		this.lastSpEvTime = latSpEvTime;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

}
