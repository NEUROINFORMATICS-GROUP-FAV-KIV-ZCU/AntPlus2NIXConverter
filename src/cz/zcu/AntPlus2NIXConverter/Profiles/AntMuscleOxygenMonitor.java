package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

public class AntMuscleOxygenMonitor {

	private static int index = 0;

	private File file;
	private Block block;
	private Source source;
	private DataArray dataHemoglobinConcentrate;
	private DataArray dataSaturatedHemoglPerc;

	private double[] saturatedHemoglPerc;
	private double[] hemoglobinConcentrate;
	private OdMLData metaData;

	public AntMuscleOxygenMonitor(double[] saturatedHemoglPerc, double[] hemoglobinConcentrate, OdMLData metaData) {

		this.saturatedHemoglPerc = saturatedHemoglPerc;
		this.hemoglobinConcentrate = hemoglobinConcentrate;
		this.metaData = metaData;
		index++;

	}

	public void createFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("muscleOxygenMonitor" + index, "antMessage");

		dataSaturatedHemoglPerc = block.createDataArray("SaturatedHemoglobin" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, saturatedHemoglPerc.length }));
		dataSaturatedHemoglPerc.setData(saturatedHemoglPerc, new NDSize(new int[] { 1, saturatedHemoglPerc.length }),
				new NDSize(2, 0));

		dataHemoglobinConcentrate = block.createDataArray("HemoglobinConcentrate" + index, "antMessage", DataType.Double,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }));
		dataHemoglobinConcentrate.setData(hemoglobinConcentrate,
				new NDSize(new int[] { 1, hemoglobinConcentrate.length }), new NDSize(2, 0));

		file.close();

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

	public DataArray getDataHemoglobinConcentrate() {
		return dataHemoglobinConcentrate;
	}

	public void setDataHemoglobinConcentrate(DataArray dataHemoglobinConcentrate) {
		this.dataHemoglobinConcentrate = dataHemoglobinConcentrate;
	}

	public DataArray getDataSaturatedHemoglPerc() {
		return dataSaturatedHemoglPerc;
	}

	public void setDataSaturatedHemoglPerc(DataArray dataSaturatedHemoglPerc) {
		this.dataSaturatedHemoglPerc = dataSaturatedHemoglPerc;
	}

	public double[] getSaturatedHemoglPerc() {
		return saturatedHemoglPerc;
	}

	public void setSaturatedHemoglPerc(double[] saturatedHemoglPerc) {
		this.saturatedHemoglPerc = saturatedHemoglPerc;
	}

	public double[] getHemoglobinConcentrate() {
		return hemoglobinConcentrate;
	}

	public void setHemoglobinConcentrate(double[] hemoglobinConcentrate) {
		this.hemoglobinConcentrate = hemoglobinConcentrate;
	}

	public OdMLData getMetaData() {
		return metaData;
	}

	public void setMetaData(OdMLData metaData) {
		this.metaData = metaData;
	}

	

}
