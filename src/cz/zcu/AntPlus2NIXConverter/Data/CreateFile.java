package cz.zcu.AntPlus2NIXConverter.Data;

import java.time.LocalDate;
import org.g_node.nix.*;

public class CreateFile {

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
		
	}
	
	@Override
	public String toString() {
	
		return "Block: [name " + this.name + "], [type " + this.type + "], [id " + this.ID + "], [date " + this.date+ "]";
			
	}
	
	
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
	
	public File createFile(String name){
		File file = File.open(name, FileMode.Overwrite);
		
		return file;
		
	}
	
	public CreateFile createBlock(String ID, String type, String name, LocalDate date){
		CreateFile block = new CreateFile(ID, name, type, date);
		
		return block;
	}
	
	public Source createSource(String ID, String type, String name){
		Source source = new Source(ID, type, name);
		
		return source;
	}
	
	public CreateDataArray createDataArray(String ID, String type, String name, String unit, String dataType){
		CreateDataArray dataArray = new CreateDataArray(ID, type, name, unit, dataType);
		
		return dataArray;
	}
	
	
}
