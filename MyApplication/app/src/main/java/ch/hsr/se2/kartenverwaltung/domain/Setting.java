package ch.hsr.se2.kartenverwaltung.domain;

public class Setting extends BaseObject {
	private int setId;
	private String setName;
	private String setValue;
	private SettingCategory settingCategory;
	private User user;

	public Setting() {
		setId(0);
	}

	public Setting(String name, String value, User user,
			SettingCategory settingCategory) {
		this.setName(name);
		this.setValue(value);
		this.setUser(user);
		this.setSettingCategory(settingCategory);
	}

	public Setting(int id, String name, String value, User user,
			SettingCategory settingCategory) {
		this.setId(id);
		this.setName(name);
		this.setValue(value);
		this.setUser(user);
		this.setSettingCategory(settingCategory);
	}

	public int getId() {
		return setId;
	}

	public void setId(int id) {
		this.setId = id;
	}

	public String getName() {
		return setName;
	}

	public void setName(String name) {
		this.setName = name;
	}

	public String getValue() {
		return setValue;
	}

	public void setValue(String value) {
		this.setValue = value;
	}

	public SettingCategory getSettingCategory() {
		return settingCategory;
	}

	public void setSettingCategory(SettingCategory settingCategory) {
		this.settingCategory = settingCategory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean hasId() {
		if (this.getId() == 0)
			return false;
		return true;
	}

	@Override
	public boolean hasAttributes() {
		if ("".equals(this.getName()) && "".equals(this.getValue()))
			return false;
		return true;
	}
}
