package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntMultiSportSpeedDist {

	private static int index = 0;

	private File file;
	private Block block;
	private Source source;
	private DataArray dataTime;
	private DataArray dataDistance;
	private ID id;

	public AntMultiSportSpeedDist(double[] timeStamp, double[] distance) {

		/*id = new ID(8);
		createBlock();
		createSource();
		createDataArrayDistance(distance);
		createDataArrayTimeStamp(timeStamp);*/
		index++;
	}

	public void createFile() {
		file = File.open("AntMultiSportSpeedDist.h5", FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("multiSportSpeedDist" + index, "antMessage");
		
		file.close();

	}

	/*public void createDataArrayDistance(double[] distance) {
		dataDistance = new DataArray("Distance_" + id.nextString(), "antMessage", "distance" + index, "metrs",
				"double");
		dataDistance.setData(distance, null, null);
	}

	public void createDataArrayTimeStamp(double[] time) {
		dataTime = new DataArray("TimeStamp_" + id.nextString(), "antMessage", "timeStamp" + index, "N/A",
				"GreorgianCalendar");
		dataTime.setData(time, null, null);
	}*/
}
