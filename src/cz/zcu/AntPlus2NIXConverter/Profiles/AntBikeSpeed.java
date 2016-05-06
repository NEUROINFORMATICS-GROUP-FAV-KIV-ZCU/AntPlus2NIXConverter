package cz.zcu.AntPlus2NIXConverter.Profiles;

import com.dsi.ant.plugins.antplus.pcc.AntPlusBikeSpeedDistancePcc;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

public class AntBikeSpeed {

	private static int index = 0;

	private AntPlusBikeSpeedDistancePcc bikSpeed = null;

	private File file;
	private Block block;
	private Source source;
	private DataArray dataArrayLatSpEvTime;
	private DataArray dataArrayCumWheelRew;
	private ID id;

	public AntBikeSpeed(int cumWheelRew, int latSpEvTime) {

		/*id = new ID(8);
		createBlock();
		createSource();
		createDataArrayLatSpEvTime(latSpEvTime);
		createDataArrayCumWheelRew(cumWheelRew);*/
		index++;

	}

	public AntBikeSpeed(int cumWheelRew, int latSpEvTime, AntPlusBikeSpeedDistancePcc bikSpeed) {

		this(cumWheelRew, latSpEvTime);
		this.bikSpeed = bikSpeed;

	}

	public void createFile() {
		file = File.open("AntBikeSpeed.h5", FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");
		
		source = block.createSource("bikeSpeed" + index, "antMessage");
		
		file.close();

	}

	/*public void createDataArrayLatSpEvTime(int latSpEvTime) {
		dataArrayLatSpEvTime = new DataArray("LatSpEvTime_" + id.nextString(), "antMessage", "LatSpEvTime" + index,
				"N/A", "int");
		dataArrayLatSpEvTime.setData(latSpEvTime);
	}

	public void createDataArrayCumWheelRew(int cumWheelRew) {
		dataArrayCumWheelRew = new DataArray("CumWheelRew_" + id.nextString(), "antMessage", "cumWheelRew" + index,
				"N/A", "int");
		dataArrayCumWheelRew.setData(cumWheelRew);
	}*/

}
