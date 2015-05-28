package ch.hsr.se2.kartenverwaltung.domain;

public class DeviceDescription extends BaseObject {
	private int dedId;
	private String dedName;

	public DeviceDescription() {
		setId(0);
	}

	public DeviceDescription(String name) {
		setId(0);
		this.setName(name);
	}

	public DeviceDescription(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public int getId() {
		return dedId;
	}

	public void setId(int dedId) {
		this.dedId = dedId;
	}

	public String getName() {
		return dedName;
	}

	public void setName(String dedName) {
		this.dedName = dedName;
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
