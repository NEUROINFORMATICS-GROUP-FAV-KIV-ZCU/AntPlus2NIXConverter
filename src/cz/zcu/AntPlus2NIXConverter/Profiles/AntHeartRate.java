package cz.zcu.AntPlus2NIXConverter.Profiles;

import java.time.LocalDate;

import com.dsi.ant.plugins.antplus.pcc.AntPlusHeartRatePcc;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.CreateFile;
import cz.zcu.AntPlus2NIXConverter.Data.CreateDataArray;
//import cz.zcu.AntPlus2NIXConverter.Data.Source;

import org.g_node.nix.*;

public class AntHeartRate {

	private static int index = 0;
	
	//private Block block;
	private DataArray dataHeartBeatCounter;
	private DataArray dataComputedHeartRade;
	private DataArray dataTimeOfPreviousHeartBeat;
	private ID id;
	private File file;

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
		file = File.open("AntHeartRate.h5", FileMode.Overwrite);
		
		Block block = file.createBlock("recording" + index, "recording");

		Source source = block.createSource("HeartRate" + index, "antMessage");
		
		System.out.println("Block ID: " + block.getId());
		System.out.println("Source ID: " + source.getId());
		
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
