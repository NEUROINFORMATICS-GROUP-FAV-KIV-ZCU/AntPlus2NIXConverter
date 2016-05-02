package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusHeartRatePcc;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.Block;
import cz.zcu.AntPlus2NIXConverter.Data.DataArray;
import cz.zcu.AntPlus2NIXConverter.Data.Source;

public class AntHeartRade {

	private static int index = 0;

	private Block block;
	private Source source;
	private DataArray dataHeartBeatCounter;
	private DataArray dataComputedHeartRade;
	private DataArray dataTimeOfPreviousHeartBeat;
	private ID id;

	private AntPlusHeartRatePcc hr = null;

	public AntHeartRade(int[] hearBeatCounter, int computedHeartRade, double[] timeOfPreviousHeartBeat) {

		id = new ID(8);
		createBlock();
		createSource();
		createDataArrayHeartBeat(hearBeatCounter);
		createDataArrayComputedHeartRade(computedHeartRade);
		createDataArrayTimeOfPrevious(timeOfPreviousHeartBeat);
		index++;

	}

	public AntHeartRade(AntPlusHeartRatePcc hr, int[] hearBeatCounter, int computedHeartRade,
			double[] timeOfPreviousHeartBeat) {

		this(hearBeatCounter, computedHeartRade, timeOfPreviousHeartBeat);
		this.hr = hr;

	}

	public void createBlock() {
		block = new Block("HeardRade_" + id.nextString(), "recording", "recording" + index, LocalDate.now());

	}

	public void createSource() {
		source = new Source("HeardRade_" + id.nextString(), "antMessage", "HeardRade" + index);
	}

	public void createDataArrayHeartBeat(int[] hearBeatCounter) {
		dataHeartBeatCounter = new DataArray("HeartBeatCounter_" + id.nextString(), "antMessage", "heartBeatCounter" + index, "bpm",
				"int");
		dataHeartBeatCounter.setData(hearBeatCounter);
	}

	public void createDataArrayComputedHeartRade(int computedHeartRade) {
		dataComputedHeartRade = new DataArray("ComputedHeartRade_" + id.nextString(), "antMessage", "computedHeartRade" + index, "N/A",
				"int");
		dataHeartBeatCounter.setData(computedHeartRade);
	}

	public void createDataArrayTimeOfPrevious(double[] timeOfPreviousHeartBeat) {
		dataTimeOfPreviousHeartBeat = new DataArray("TimeOfPreviousHeartBeat_" + id.nextString(), "antMessage",
				"timeOfPreviousHeartBeat" + index, "second", "double");
		dataHeartBeatCounter.setData(timeOfPreviousHeartBeat);
	}

}
