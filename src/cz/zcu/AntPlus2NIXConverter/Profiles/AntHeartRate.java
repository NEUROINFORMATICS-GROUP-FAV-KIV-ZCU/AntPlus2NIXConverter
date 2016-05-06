package cz.zcu.AntPlus2NIXConverter.Profiles;


import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

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

	private int[] heartBeatCounter = new int[4];
	private int[] computedHeartRate = new int[4];
	private double[] timeOfPreviousHeartBeat = new double[3];

	public AntHeartRate(int[] hearBeatCounter, int[] computedHeartRate, double[] timeOfPreviousHeartBeat, OdMLData metaData) {
		
		this.heartBeatCounter = hearBeatCounter;
		this.computedHeartRate = computedHeartRate;
		this.timeOfPreviousHeartBeat = timeOfPreviousHeartBeat; 
	
	}
	
	public void createNixFile(){
		file = File.open("AntHeartRateRate.h5", FileMode.Overwrite);
		
		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("HeartRate" + index, "antMessage");
				
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
	
	
}
