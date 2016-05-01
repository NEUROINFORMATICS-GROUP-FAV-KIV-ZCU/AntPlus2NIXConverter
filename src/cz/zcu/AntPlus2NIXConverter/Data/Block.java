package cz.zcu.AntPlus2NIXConverter.Data;

import java.time.LocalDate;

public class Block {

	private String ID;
	private String type;
	private String name;
	private LocalDate date;
	
	
	public Block(String ID, String type, String name, LocalDate date) {
		
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
	
	
}
