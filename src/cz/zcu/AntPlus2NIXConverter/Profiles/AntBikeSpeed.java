package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusBikeSpeedDistancePcc;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntBikeSpeed {

	private static int index = 0;

	private AntPlusBikeSpeedDistancePcc bikSpeed = null;

	private Block block;
	private Source source;
	private DataArray dataArrayLatSpEvTime;
	private DataArray dataArrayCumWheelRew;
	private ID id;

	public AntBikeSpeed(int cumWheelRew, int latSpEvTime) {

		id = new ID(8);
		createBlock();
		createSource();
		createDataArrayLatSpEvTime(latSpEvTime);
		createDataArrayCumWheelRew(cumWheelRew);
		index++;

	}

	public AntBikeSpeed(int cumWheelRew, int latSpEvTime, AntPlusBikeSpeedDistancePcc bikSpeed) {

		this(cumWheelRew, latSpEvTime);
		this.bikSpeed = bikSpeed;

	}

	public void createBlock() {
		block = new Block("BikeSpeed_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

	}

	public void createSource() {
		source = new Source("BikeSpeed_" + id.nextString(), "antMessage", "bikeSpeed" + index);
	}

	public void createDataArrayLatSpEvTime(int latSpEvTime) {
		dataArrayLatSpEvTime = new DataArray("LatSpEvTime_" + id.nextString(), "antMessage", "LatSpEvTime" + index,
				"N/A", "int");
		dataArrayLatSpEvTime.setData(latSpEvTime);
	}

	public void createDataArrayCumWheelRew(int cumWheelRew) {
		dataArrayCumWheelRew = new DataArray("CumWheelRew_" + id.nextString(), "antMessage", "cumWheelRew" + index,
				"N/A", "int");
		dataArrayCumWheelRew.setData(cumWheelRew);
	}

}
