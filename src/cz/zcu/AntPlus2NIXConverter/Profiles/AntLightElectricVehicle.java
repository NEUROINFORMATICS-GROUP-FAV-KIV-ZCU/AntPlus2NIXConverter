package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Interface.INixStream;

/**
 * Trida pro zpracovani informaci o ANT plus profilu Light Electic Vehicle
 * Profil pro vytvoreni ­ HDF5 souboru ze zarizeni Light Electric Vehicle.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class AntLightElectricVehicle implements INixStream{

	/** Aributy tridy **/

	private int index = 0;

	private File file;
	private Block block;
	private Source source;
	private Section section;
	private DataArray dataSpeedDistance;
	private DataArray dataSysGearState;
	private DataArray dataMode;
	private DataArray dataBatStatus;
	private double[] speedDistance;
	private byte[] sysGearStateB;
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

	private void convertSysGearState() {

		sysGearStateB = new byte[sysGearState.length];

		for (int i = 0; i < sysGearState.length; i++) {

			sysGearStateB[i] = Byte.parseByte(String.valueOf(sysGearState[i]));
		}
	}

	/**
	 * Metoda pro vytvoreni HDF5 souboru s NIX formatem vcetne dat a metadat
	 * 
	 * @param fileName
	 *            Nazev souboru
	 */
	public Stream<Block> createNixFile(String fileName) {

		convertSysGearState();

		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("lightElVeh" + index, "antMessage");

		/* Pridani metadat do bloku */

		section = metaData.createSectionNix(file);

		/* Naplneni dataArray daty o baterii */

		dataBatStatus = block.createDataArray("batStatus" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, batStatus.length }));
		dataBatStatus.setData(batStatus, new NDSize(new int[] { 1, batStatus.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o modu */
		dataMode = block.createDataArray("mode" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] { 1, mode.length }));
		dataMode.setData(mode, new NDSize(new int[] { 1, mode.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty o rychlosti */
		dataSpeedDistance = block.createDataArray("speedDistance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, speedDistance.length }));
		dataSpeedDistance.setData(speedDistance, new NDSize(new int[] { 1, speedDistance.length }), new NDSize(2, 0));

		/* Naplneni dataArray daty systrmu a zarizeni */
		dataSysGearState = block.createDataArray("sysGearState" + index, "antMessage", DataType.Int16,
				new NDSize(new int[] { 1, speedDistance.length }));
		dataSysGearState.setData(sysGearStateB, new NDSize(new int[] { 1, sysGearState.length }), new NDSize(2, 0));

		List<Block> blocks = Arrays.asList(block);
		
		file.close();
		
		return blocks.stream();
	}

	/** Getry a Setry ***/

	public Section getSection() {
		return section;
	}

	public File getFile() {
		return file;
	}

	public int getIndex() {
		return index;
	}

	public Block getBlock() {
		return block;
	}

	public Source getSource() {
		return source;
	}

	public DataArray getDataSpeedDistance() {
		return dataSpeedDistance;
	}

	public DataArray getDataSysGearState() {
		return dataSysGearState;
	}

	public DataArray getDataMode() {
		return dataMode;
	}

	public DataArray getDataBatStatus() {
		return dataBatStatus;
	}

	public double[] getSpeedDistance() {
		return speedDistance;
	}

	public byte[] getSysGearStateB() {
		return sysGearStateB;
	}

	public boolean[] getSysGearState() {
		return sysGearState;
	}

	public int[] getMode() {
		return mode;
	}

	public int[] getBatStatus() {
		return batStatus;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

}
