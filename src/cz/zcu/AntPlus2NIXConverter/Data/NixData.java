package cz.zcu.AntPlus2NIXConverter.Data;

public class NixData {

	private String fileName;
	private String name;
	private String type;
	
	public NixData(String fileName) {
		
		this.setFileName(fileName);
	
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
