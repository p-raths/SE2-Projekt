package ch.hsr.se2.kartenverwaltung.domain;

public class Device extends BaseObject {
	private int devId;
	private String devName;
	private boolean devIsSynchronized;
	private DeviceDescription deviceDescription;

	public Device() {
		setId(0);
	}

	public Device(int id, String name, Boolean isSynchronized,
			DeviceDescription deviceDescription) {
		this.setId(id);
		this.setName(name);
		this.setIsSynchronized(isSynchronized);
		this.setDeviceDescription(deviceDescription);
	}

	public int getId() {
		return devId;
	}

	public void setId(int id) {
		this.devId = id;
	}

	public String getName() {
		return devName;
	}

	public void setName(String name) {
		this.devName = name;
	}

	public boolean isSynchronized() {
		return devIsSynchronized;
	}

	public void setIsSynchronized(boolean isSynchronized) {
		this.devIsSynchronized = isSynchronized;
	}

	public DeviceDescription getDeviceDescription() {
		return deviceDescription;
	}

	public void setDeviceDescription(DeviceDescription deviceDescription) {
		this.deviceDescription = deviceDescription;
	}

	@Override
	public boolean hasId() {
		if (this.getId() == 0)
			return false;
		return true;
	}

	@Override
	public boolean hasAttributes() {
		if ("".equals(this.getName()))
			return false;
		return true;
	}
}
