package cz.zcu.AntPlus2NIXConverter.Data;

public class CreateSource {

	private String ID;
	private String type;
	private String name;
	
	public CreateSource(String ID, String type, String name) {
		
		setID(ID);
		setType(type);
		setName(name);
	
		
		
	}
	
	@Override
	public String toString() {
	
		return "Block: [name " + this.name + "], [type " + this.type + "], [id " + this.ID + "]";
			
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
}
