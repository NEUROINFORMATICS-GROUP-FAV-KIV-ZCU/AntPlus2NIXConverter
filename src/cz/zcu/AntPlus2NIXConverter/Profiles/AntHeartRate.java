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
	private DataArray dataComputedHeartRate;
	private DataArray dataTimeOfPreviousHeartBeat;
	private ID id;
	private int[] pole = new int[2];

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
		
		pole[0] = 1;
		
		dataHeartBeatCounter = block.createDataArray("heartBeatCounter" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,heartBeatCounter.length}));
			dataHeartBeatCounter.setData(heartBeatCounter, new NDSize(new int [] {1,heartBeatCounter.length}), new NDSize(2,0));
			
		dataComputedHeartRate = block.createDataArray("computedHeartRate" + index, "antMessage", DataType.Int32,
				new NDSize(new int[] {1,computedHeartRate.length}));
			dataComputedHeartRate.setData(computedHeartRate, new NDSize(new int[] {1,computedHeartRate.length}), new NDSize(2,0));

		dataTimeOfPreviousHeartBeat = block.createDataArray("timeOfPreviousHeartBeat" + index, "antMessage", DataType.Double, 
				new NDSize(new int[] {1,timeOfPreviousHeartBeat.length}));
			dataTimeOfPreviousHeartBeat.setData(timeOfPreviousHeartBeat, new NDSize(new int[] {1,timeOfPreviousHeartBeat.length}), new NDSize(2,0));

		file.close();
	}
	
	public static void main(String[] args) {
		AntHeartRate a = new AntHeartRate();
		a.createFile(new int [] {1,2,3,4,5,6,7,8,9}, new int[] {9,8,7,6,5,4,3,2,1}, new double[] {5.0,6.0,7.0,8.0,9.0,1.0,2.0,3.0});
	}

}
