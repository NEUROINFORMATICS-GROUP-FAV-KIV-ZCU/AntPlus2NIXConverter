package cz.zcu.AntPlus2NIXConverter.Profiles;

import com.dsi.ant.plugins.antplus.pcc.AntPlusHeartRatePcc;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;

import org.g_node.nix.*;

public class AntHeartRate {

	private static int index = 0;
	
	private File file;
	private Block block;
	private Source source;
	private DataArray dataHeartBeatCounter;
	private DataArray dataComputedHeartRade;
	private DataArray dataTimeOfPreviousHeartBeat;
	private ID id;

	private AntPlusHeartRatePcc hr = null;

	/*public AntHeartRate(int[] hearBeatCounter, int computedHeartRade, double[] timeOfPreviousHeartBeat) {

		id = new ID();
		/*createFile();
		createBlock();
		createSource();
		createDataArrayHeartBeat(hearBeatCounter);
		createDataArrayComputedHeartRate(computedHeartRade);
		createDataArrayTimeOfPrevious(timeOfPreviousHeartBeat);*/
		/*index++;
	}

	public AntHeartRate(AntPlusHeartRatePcc hr, int[] hearBeatCounter, int computedHeartRade,
			double[] timeOfPreviousHeartBeat) {

		this(hearBeatCounter, computedHeartRade, timeOfPreviousHeartBeat);
		this.hr = hr;

	}*/
	
	public void createFile(int[] heartBeatCounter, int[] computedHeartRate, double[] timeOfPreviousHeartBeat){
		file = File.open("AntHeartRateRate.h5", FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("HeartRate" + index, "antMessage");
		
		System.out.println("Block ID: " + block.getId());
		System.out.println("Source ID: " + source.getId());
		System.out.println("Created at: " + block.getCreatedAt());
		
		/*dataHeartBeatCounter = block.createDataArray("heartBeatCounter" + index, "antMessage", DataType.Int32,
				null);
			dataHeartBeatCounter.setData(heartBeatCounter, null, null);
			
		dataComputedHeartRade = block.createDataArray("computedHeartRate" + index, "antMessage", DataType.Int32,
				null);
			dataHeartBeatCounter.setData(computedHeartRate, null, null);

		dataTimeOfPreviousHeartBeat = block.createDataArray("timeOfPreviousHeartBeat" + index, "antMessage", DataType.Double, 
				null);
			dataHeartBeatCounter.setData(timeOfPreviousHeartBeat, null, null);
*/
		file.close();
	}
	
	public static void main(String[] args) {
		AntHeartRate a = new AntHeartRate();
		a.createFile(null, null, null);
	}

}
