package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntMultiSportSpeedDist {

	private static int index = 0;

	private Block block;
	private Source source;
	private DataArray dataTime;
	private DataArray dataDistance;
	private ID id;

	public AntMultiSportSpeedDist(double[] timeStamp, double[] distance) {

		id = new ID(8);
		createBlock();
		createSource();
		createDataArrayDistance(distance);
		createDataArrayTimeStamp(timeStamp);
		index++;
	}

	public void createBlock() {
		block = new Block("MultiSportSpeedDist_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

	}

	public void createSource() {
		source = new Source("MultiSportSpeedDist_" + id.nextString(), "antMessage", "multiSportSpeedDist" + index);
	}
	
	
	public void createDataArrayDistance(double[] distance) {
		dataDistance = new DataArray("Distance_" + id.nextString(), "antMessage", "distance" + index, "metrs",
				"double");
		dataDistance.setData(distance);
	}

	public void createDataArrayTimeStamp(double[] time) {
		dataTime = new DataArray("TimeStamp_" + id.nextString(), "antMessage", "timeStamp" + index, "N/A",
				"GreorgianCalendar");
		dataTime.setData(time);
	}
}
