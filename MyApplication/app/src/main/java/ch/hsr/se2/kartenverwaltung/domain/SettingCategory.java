package ch.hsr.se2.kartenverwaltung.domain;

public class SettingCategory extends BaseObject {
	private int secId;
	private String secName;

	public SettingCategory() {
		setId(0);
	}

	public SettingCategory(String name) {
		setId(0);
		this.setName(name);
	}

	public SettingCategory(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public int getId() {
		return secId;
	}

	public void setId(int id) {
		this.secId = id;
	}

	public String getName() {
		return secName;
	}

	public void setName(String name) {
		this.secName = name;
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
