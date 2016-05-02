package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntStrideSpeedDistance {

	private static int index = 0;

	private Block block;
	private Source source;
	private DataArray dataStrideCount;
	private DataArray dataDistance;
	private DataArray dataSpeed;
	private ID id;

	
	public AntStrideSpeedDistance(long strideCount, double[] distance, double[] speed) {
	
		id = new ID(8);
		createBlock();
		createSource();
		createDataArrayStrideCount(strideCount);
		createDataArrayDistance(distance);
		createDataArraySpeed(speed);
		index++;
	}
	
	public void createBlock() {
		block = new Block("StrideSpeedDistance_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

	}

	public void createSource() {
		source = new Source("StrideSpeedDistance_" + id.nextString(), "antMessage", "strideSpeedDistance" + index);
	}

	public void createDataArrayStrideCount(long strideCount) {
		dataStrideCount = new DataArray("StrideCount_" + id.nextString(), "antMessage", "strideCount" + index, "strides",
				"long");
		dataStrideCount.setData(strideCount);
	}

	public void createDataArrayDistance(double[] distance) {
		dataDistance = new DataArray("Distance_" + id.nextString(), "antMessage", "distance" + index, "metrs",
				"double");
		dataDistance.setData(distance);
	}

	public void createDataArraySpeed(double[] speed) {
		dataSpeed = new DataArray("Speed_" + id.nextString(), "antMessage",
				"speed" + index, "second", "double");
		dataSpeed.setData(speed);
	}
}
