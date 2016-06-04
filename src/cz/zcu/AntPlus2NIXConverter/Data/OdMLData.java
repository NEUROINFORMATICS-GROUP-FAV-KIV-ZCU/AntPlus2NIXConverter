package cz.zcu.AntPlus2NIXConverter.Data;

import java.util.ArrayList;
import java.util.List;

import org.g_node.nix.File;
import org.g_node.nix.Section;
import org.g_node.nix.Value;

/**
 * Trida pro reprezentaci metadat ve formátu odML.
 * 
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
public class OdMLData {

	/** Atributy tridy **/

	private String deviceName;
	private String deviceType;
	private String[] deviceState;
	private int deviceNumber;
	private int batteryStatus;
	private int signalStrength;
	private int manIdentification;
	private int[] manSpecData;
	private int prodInfo;

	/**
	 * Konstruktor tridy. Naplni jednotlive atributy tridy, pro uchovani
	 * informaci o metadatech z profilu ANT plus
	 * 
	 * @param deviceName
	 *            Jmeno zarizeni
	 * @param deviceType
	 *            Typ zarizeni
	 * @param deviceState
	 *            Status zarizeni
	 * @param deviceNumber
	 *            Cislo zarizeni
	 * @param batteryStatus
	 *            Status baterie
	 * @param signalStenght
	 *            Sila signalu
	 * @param manIdentifacation
	 *            Identifikace vyrobce
	 * @param manSpecData
	 *            Specificka
	 * @param prodInfo
	 *            Info
	 */
	public OdMLData(String deviceName, String deviceType, String[] deviceState, int deviceNumber, int batteryStatus,
			int signalStenght, int manIdentifacation, int[] manSpecData, int prodInfo) {
		setDeviceName(deviceName);
		setDeviceNumber(deviceNumber);
		setDeviceState(deviceState);
		setDeviceType(deviceType);
		setBatteryStatus(batteryStatus);
		setSignalStrength(signalStenght);
		setManIdentification(manIdentifacation);
		setManSpecData(manSpecData);
		setProdInfo(prodInfo);

	}
	
	private List<Value> convertStringArray(String[] deviceState){
		ArrayList<Value> deviceStateList = new ArrayList<>();
		for(int i = 0; i < deviceState.length; i++){
			deviceStateList.add(new Value(deviceState[i]));
		}
		return deviceStateList;
	}
	
	private List<Value> convertIntArray(int[] specificData){
		ArrayList<Value> specificDataList = new ArrayList<>();
		for(int i = 0; i < specificData.length; i++){
			specificDataList.add(new Value(specificData[i]));
		}
		return specificDataList;
	}

	public Section createSectionNix(File file){
		
		Section section = file.createSection("AntMetaData", "metadata");
		section.createProperty("deviceName", new Value(getDeviceName()));
		section.createProperty("deviceType", new Value(getDeviceType()));
		section.createProperty("deviceState", convertStringArray(getDeviceState()));
		section.createProperty("deviceNumber", new Value(getDeviceNumber()));
		section.createProperty("batteryStatus", new Value(getBatteryStatus()));
		section.createProperty("signalStrength", new Value(getSignalStrength()));
		section.createProperty("manufacturerIdentification", new Value(getManIdentification()));
		section.createProperty("manufacturerSpecificData", convertIntArray(getManSpecData()));
		section.createProperty("productInfo", new Value(getProdInfo()));

		return section;
	}
	
	/******* Getry a Setry *********/ 
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
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

	public int[] getManSpecData() {
		return manSpecData;
	}

	public void setManSpecData(int[] manSpecData) {
		this.manSpecData = manSpecData;
	}

	public int getProdInfo() {
		return prodInfo;
	}

	public void setProdInfo(int prodInfo) {
		this.prodInfo = prodInfo;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String[] getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(String[] deviceState) {
		this.deviceState = deviceState;
	}

	public int getBatteryStatus() {
		return batteryStatus;
	}

	public void setBatteryStatus(int batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

}
