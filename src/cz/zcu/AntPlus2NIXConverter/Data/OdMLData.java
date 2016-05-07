package cz.zcu.AntPlus2NIXConverter.Data;

/**
 * Trida pro reprezentaci metadat ve formátu odML.
 * @author Vaclav Janoch, Filip Kupilik, Petr Tobias
 * @version 1.0
 */
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

	/**
	 * Konstruktor tridy.
	 * @param deviceName Jmeno zarizeni
	 * @param deviceType Typ zarizeni
	 * @param deviceState Status zarizeni
	 * @param deviceNumber Cislo zarizeni
	 * @param batteryStatus Status baterie
	 * @param signalStenght Sila signalu
	 * @param manIdentifacation Identifikace vyrobce
	 * @param manSpecData Specificka
	 * @param prodInfo Info
	 */
	public OdMLData(int deviceName, int deviceType, int deviceState, int deviceNumber, int batteryStatus,
			int signalStenght, int manIdentifacation, int manSpecData, int prodInfo) {

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