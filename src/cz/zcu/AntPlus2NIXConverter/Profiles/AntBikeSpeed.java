package cz.zcu.AntPlus2NIXConverter.Profiles;


import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

public class AntBikeSpeed {

	private static int index = 0;

	
	private File file;
	private Block block;
	private Source source;
	private DataArray dataArrayLatSpEvTime;
	private DataArray dataArrayCumWheelRew;
	
	private int[] cumWheelRew;
	private int[] latSpEvTime;
	
	private OdMLData metaData;
	
	public AntBikeSpeed(int[] cumWheelRew, int[] latSpEvTime, OdMLData metaData) {

		this.cumWheelRew = cumWheelRew;
		this.latSpEvTime = latSpEvTime;
		this.metaData = metaData;
		index++;

	}

	public void createFile(String fileName) {
		file = File.open(fileName, FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("bikeSpeed" + index, "antMessage");
		
		file.close();

		dataArrayLatSpEvTime = block.createDataArray("LatSpEvTime" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,latSpEvTime.length}));
			dataArrayLatSpEvTime.setData(latSpEvTime, new NDSize(new int [] {1,latSpEvTime.length}), new NDSize(2,0));
			
		dataArrayCumWheelRew = block.createDataArray("CumWheelRew" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,cumWheelRew.length}));
			dataArrayCumWheelRew.setData(cumWheelRew, new NDSize(new int[] {1,cumWheelRew.length}), new NDSize(2,0));

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

	public DataArray getDataArrayLatSpEvTime() {
		return dataArrayLatSpEvTime;
	}

	public void setDataArrayLatSpEvTime(DataArray dataArrayLatSpEvTime) {
		this.dataArrayLatSpEvTime = dataArrayLatSpEvTime;
	}

	public DataArray getDataArrayCumWheelRew() {
		return dataArrayCumWheelRew;
	}

	public void setDataArrayCumWheelRew(DataArray dataArrayCumWheelRew) {
		this.dataArrayCumWheelRew = dataArrayCumWheelRew;
	}

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
