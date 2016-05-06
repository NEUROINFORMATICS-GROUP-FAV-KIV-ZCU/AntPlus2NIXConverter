package cz.zcu.AntPlus2NIXConverter.Data;

import java.time.LocalDate;
import org.g_node.nix.*;

public class CreateFile {

	private File file;
	private Block block;
	private Source source;
	private DataArray dataArray;
	private int index;
	private String ID;
	private String type;
	private String name;
	private int mode;
	private LocalDate date;
	
	
	public CreateFile(String ID, String type, String name, LocalDate date) {
		
		setID(ID);
		setType(type);
		setName(name);
		setDate(date);
		index++;
		
	}
	
	/*@Override
	public String toString() {
	
		return "Block: [name " + this.name + "], [type " + this.type + "], [id " + this.ID + "], [date " + this.date+ "]";
			
	}*/
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public File createFile(String fileName, String blockName, String sourceName,
			String arrayName, int dataType, NDSize shape, int count){
		file = File.open(fileName, FileMode.Overwrite);
		
		block = file.createBlock(blockName + index, "recording");
		
		source = block.createSource(sourceName, "antMessage");
		
		for (int i = 0; i <= count;i++){
		dataArray = block.createDataArray(arrayName, "antMessage", dataType, shape);
		}
		
		return file;
		
	}
	
	
	
	
}
