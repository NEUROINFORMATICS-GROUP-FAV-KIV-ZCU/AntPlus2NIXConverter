package cz.zcu.AntPlus2NIXConverter.Profiles;

import org.g_node.nix.*;

import cz.zcu.AntPlus2NIXConverter.Convert.ID;
import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;

public class AntMultiSportSpeedDist {

	private static int index = 0;

	private File file;
	private Block block;
	private Source source;
	private DataArray dataTime;
	private DataArray dataDistance;

	private double[] timeStamp;
	private double[] distance;
	private OdMLData metaData;

	public AntMultiSportSpeedDist(double[] timeStamp, double[] distance, OdMLData metaData) {

		this.timeStamp = timeStamp;
		this.distance = distance;
		this.metaData = metaData;
		
		index++;
	}

	public void createFile(String fileName) {
		
		file = File.open(fileName, FileMode.Overwrite);

		block = file.createBlock("recording" + index, "recording");

		source = block.createSource("multiSportSpeedDist" + index, "antMessage");

		dataTime = block.createDataArray("DataTime" + index, "antMessage", DataType.Double,
				new NDSize(new int[] {1,timeStamp.length}));
			dataTime.setData(timeStamp, new NDSize(new int [] {1,timeStamp.length}), new NDSize(2,0));
			
		dataDistance= block.createDataArray("DataDistance" + index, "antMessage", DataType.Double,
				new NDSize(new int[] {1,distance.length}));
			dataDistance.setData(distance, new NDSize(new int[] {1,distance.length}), new NDSize(2,0));

		
		file.close();

	}

}
