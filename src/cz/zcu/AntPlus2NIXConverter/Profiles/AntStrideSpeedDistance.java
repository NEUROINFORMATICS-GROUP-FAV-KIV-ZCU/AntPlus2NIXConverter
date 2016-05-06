package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntStrideSpeedDistance {

	private static int index = 0;

	private File file;
	private Block block;
	private Source source;
	private DataArray dataStrideCount;
	private DataArray dataDistance;
	private DataArray dataSpeed;
	private ID id;

	
	public AntStrideSpeedDistance(long strideCount, double[] distance, double[] speed) {
	
		/*id = new ID(8);
		createBlock();
		createSource();
		createDataArrayStrideCount(strideCount);
		createDataArrayDistance(distance);
		createDataArraySpeed(speed);*/
		index++;
	}
	
	public void createFile() {
		file = File.open("AntStrideSpeedDistance.h5", FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("strideSpeedDistance" + index, "antMessage");
		
		file.close();

	}

	/*public void createSource() {
		source = new CreateSource("StrideSpeedDistance_" + id.nextString(), "antMessage", "strideSpeedDistance" + index);
	}

	public void createDataArrayStrideCount(long strideCount) {
		dataStrideCount = new DataArray("StrideCount_" + id.nextString(), "antMessage", "strideCount" + index, "strides",
				"long");
		dataStrideCount.setData(strideCount);
	}

	public void createDataArrayDistance(double[] distance) {
		dataDistance = new DataArray("Distance_" + id.nextString(), "antMessage", "distance" + index, "metrs",
				"double");
		dataDistance.setData(distance, null, null);
	}

	public void createDataArraySpeed(double[] speed) {
		dataSpeed = new DataArray("Speed_" + id.nextString(), "antMessage",
				"speed" + index, "second", "double");
		dataSpeed.setData(speed, null, null);
	}*/
}
