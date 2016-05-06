package cz.zcu.AntPlus2NIXConverter.Data;

public class OdMLData {

	 private int deviceName;	
	 private int deviceType;
	 private int deviceState;
	 private int deviceNumber;
	 private int batteryStatus;
	 private int signalStrength;
	 private int manIdentification;
	 private int manSpecData;
	 private int prodInfo;
	 
	 
	public int getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(int deviceName) {
		this.deviceName = deviceName;
	}
	public int getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(int deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public int getSignalStrength() {
		return signalStrength;
	}
	public void setSignalStrength(int signalStrength) {
		this.signalStrength = signalStrength;
	}
	public int getManIdentification() {
		return manIdentification;
	}
	public void setManIdentification(int manIdentification) {
		this.manIdentification = manIdentification;
	}
	public int getManSpecData() {
		return manSpecData;
	}
	public void setManSpecData(int manSpecData) {
		this.manSpecData = manSpecData;
	}
	public int getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(int prodInfo) {
		this.prodInfo = prodInfo;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public int getDeviceState() {
		return deviceState;
	}
	public void setDeviceState(int deviceState) {
		this.deviceState = deviceState;
	}
	public int getBatteryStatus() {
		return batteryStatus;
	}
	public void setBatteryStatus(int batteryStatus) {
		this.batteryStatus = batteryStatus;
	}
	
}