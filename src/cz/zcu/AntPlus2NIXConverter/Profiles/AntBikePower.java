package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

public class AntBikePower {

	private static int index = 0;

	private File file;
	private Block block;
	private Source source;
	private DataArray dataArrayBikePower;

	private double[] power;

	private OdMLData metaData;

	public AntBikePower(double[] power, OdMLData metaData) {

		this.power = power;
		index++;

	}

	public void createFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("bikePower" + index, "antMessage");

		file.close();

		dataArrayBikePower = block.createDataArray("BikePower" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, power.length }));
		dataArrayBikePower.setData(power, new NDSize(new int[] { 1, power.length }), new NDSize(2, 0));

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

}
