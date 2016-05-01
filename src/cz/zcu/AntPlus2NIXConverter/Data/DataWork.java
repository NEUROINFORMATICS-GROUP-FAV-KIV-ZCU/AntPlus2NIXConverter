package cz.zcu.AntPlus2NIXConverter.Data;

import org.bytedeco.javacpp.tools.Slf4jLogger;
import org.g_node.nix.File;
import org.g_node.nix.FileMode;

public class DataWork {

	HDF5Data hdfData;

	public DataWork() {

	}

	public void loadAntData() {

	}

	public void saveNixData(NixData data) {

		hdfData = new HDF5Data(data);
		hdfData.createHDFFile();
		File file = File.open(data.getFileName(), FileMode.Overwrite);

		file.createBlock(data.getName(), data.getType());

		file.close();

	}

	public static void main(String[] args) {
		DataWork data = new DataWork();
		NixData nixData = new NixData("pokus.h5");
		data.saveNixData(nixData);
	}

}
