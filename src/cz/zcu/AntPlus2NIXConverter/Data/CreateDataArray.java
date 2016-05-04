package cz.zcu.AntPlus2NIXConverter.Data;

public class CreateDataArray {

	private String ID;
	private String type;
	private String name;
	private String dataType;
	
	private int dataInt;
	private int[] dataArrayInt;
	private double dataDouble;
	private double[] dataArrayDouble;
	private boolean dataArrayBoolean;
	private String unit;
	
	
	
	public CreateDataArray(String ID, String type, String name, String unit, String dataType) {
		
		setID(ID);
		setType(type);
		setName(name);
		setUnit(unit);
		setDataType(dataType);
		
		}
	
	@Override
	public String toString() {
	
		return "Block: [name " + this.name + "], [type " + this.type + "], [id " + this.ID + "], [unit " + this.unit+ "]";
			
	}
	
	
	
	public void setData(int data){
		this.dataInt = data;
	}
	
	public void setData(int[] dataIntArray){
		this.dataArrayInt = dataIntArray;
	}
	
	public void setData(double dataDouble){
		this.dataDouble = dataDouble;
	}
	
	public void setData(double[] dataArrayDouble){
		this.dataArrayDouble = dataArrayDouble;
	}
	
	public void setData(boolean dataArrayDouble){
		this.dataArrayBoolean = dataArrayDouble;
	}
	
	
	/************** Getrs and Setrs ************/
	
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
	public int getDataInt() {
		return dataInt;
	}
	public void setDataInt(int dataInt) {
		this.dataInt = dataInt;
	}
	public int[] getDataArrayInt() {
		return dataArrayInt;
	}
	public void setDataArrayInt(int[] dataArrayInt) {
		this.dataArrayInt = dataArrayInt;
	}
	public double getDataDouble() {
		return dataDouble;
	}
	public void setDataDouble(double dataDouble) {
		this.dataDouble = dataDouble;
	}
	public double[] getDataArrayDouble() {
		return dataArrayDouble;
	}
	public void setDataArrayDouble(double[] dataArrayDouble) {
		this.dataArrayDouble = dataArrayDouble;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isDataArrayBoolean() {
		return dataArrayBoolean;
	}

	public void setDataArrayBoolean(boolean dataArrayBoolean) {
		this.dataArrayBoolean = dataArrayBoolean;
	}
	
	
}
