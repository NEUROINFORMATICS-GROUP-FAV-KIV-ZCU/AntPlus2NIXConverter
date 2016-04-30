package cz.zcu.AntPlus2NIXConverter.Data;

public class DataArray {

	private String ID;
	private String type;
	private String name;
	private int dataInt;
	private int[] dataArrayInt;
	private double dataDouble;
	private double[] dataArrayDouble;
	private String unit;
	
	
	
	public DataArray(String ID, String type, String name, String unit) {
		
		setID(ID);
		setType(type);
		setName(name);
		setUnit(unit);
		
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
	public double getDataArrayDouble() {
		return dataArrayDouble;
	}
	public void setDataArrayDouble(double dataArrayDouble) {
		this.dataArrayDouble = dataArrayDouble;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
