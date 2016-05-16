package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixFile;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Light Electic Vehicle
 * Profil pro vytvoreni ­ HDF5 souboru ze zarizeni Light Electric Vehicle.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntLightElectricVehicle implements INixFile {

	/** Staticke atributy tridy **/
	private static int index = 0;

	/** Aributy tridy **/
	private double[] speedDistance;
	private boolean[] sysGearState;
	private int[] mode;
	private int[] batStatus;
	private OdMLData metaData;

	/**
	 * Naplni atributy tridy informacemi z ANT plus profilu s polecne s metadaty
	 * 
	 * @param speedDistance
	 *            Vzdalenost
	 * @param sysGearState
	 *            Status systemu a zarizeni
	 * @param mode
	 *            Mod
	 * @param BatStatus
	 *            Status baterie
	 * @param metaData
	 *            MetaData
	 */
	public AntLightElectricVehicle(double[] speedDistance, boolean[] sysGearState, int[] mode, int[] BatStatus,
			OdMLData metaData) {

		this.speedDistance = speedDistance;
		this.sysGearState = sysGearState;
		this.mode = mode;
		this.batStatus = BatStatus;
		this.metaData = metaData;
		index++;
	}

	/**
	 * Pomocna metoda pro prevod statusu systemu a zarizeni z boolean hodnot na
	 * Byty pro ulozeni do formatu NIX
	 **/

	private byte[] convertSysGearState() {

		byte[] sysGearStateB = new byte[sysGearState.length];

		for (int i = 0; i < sysGearState.length; i++) {

			sysGearStateB[i] = Byte.parseByte(String.valueOf(sysGearState[i]));
		}
		return sysGearStateB;
	}

	/**
	 * Metoda pro vytvoreni casti NIX, vcetne dat a metadat.
	 * 
	 * @param nixFile
	 *            soubor HDF5 pro upraveni na Nix format
	 */
	@Override
	public void fillNixFile(File nixFile) {

		byte[] sysGearStateB = convertSysGearState();

		Block block = nixFile.createBlock("recording" + index, "recording");

		block.createSource("lightElVeh" + index, "antMessage");

		/* Pridani metadat do bloku */

		block.setMetadata(metaData.createSectionNix(nixFile));

		/* Naplneni dataArray daty o baterii */

		DataArray dataBatStatus = block.createDataArray("batStatus" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, batStatus.length }));
		dataBatStatus.setData(batStatus, new NDSize(new int[] { 1, batStatus.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o modu */
		DataArray dataMode = block.createDataArray("mode" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, mode.length }));
		dataMode.setData(mode, new NDSize(new int[] { 1, mode.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o rychlosti */
		DataArray dataSpeedDistance = block.createDataArray("speedDistance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, speedDistance.length }));
		dataSpeedDistance.setData(speedDistance, new NDSize(new int[] { 1, speedDistance.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty systrmu a zarizeni */
		DataArray dataSysGearState = block.createDataArray("sysGearState" + index, "antMessage", DataType.Int16,
				new NDSize(new int[] { 1, speedDistance.length }));
		dataSysGearState.setData(sysGearStateB, new NDSize(new int[] { 1, sysGearState.length }), new NDSize(2, 0));

	}

	/** Getry a Setry ***/

	public double[] getSpeedDistance() {
		return speedDistance;
	}

	public void setSpeedDistance(double[] speedDistance) {
		this.speedDistance = speedDistance;
	}

	public boolean[] getSysGearState() {
		return sysGearState;
	}

	public void setSysGearState(boolean[] sysGearState) {
		this.sysGearState = sysGearState;
	}

	public int[] getMode() {
		return mode;
	}

	public void setMode(int[] mode) {
		this.mode = mode;
	}

	public int[] getBatStatus() {
		return batStatus;
	}

	public void setBatStatus(int[] batStatus) {
		this.batStatus = batStatus;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

}
