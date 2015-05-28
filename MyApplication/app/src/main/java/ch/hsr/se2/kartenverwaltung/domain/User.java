package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends BaseObject {
	private int usrId;
	private String usrName;
	private String usrPassword;

	public User() {
		this.setId(0);
	}

	public User(String name, String password) {
		this.setId(0);
		this.setName(name);
		this.setPassword(password);
	}

	public User(int id, String name, String password) {
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
	}

	public User(ResultSet resultSet) {
		try {
			if (resultSet.next()) {
				this.setId(resultSet.getInt("UsrId"));
				this.setName(resultSet.getString("UsrName"));
				this.setPassword(resultSet.getString("UsrPassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return usrId;
	}

	public void setId(int id) {
		this.usrId = id;
	}

	public String getName() {
		return usrName;
	}

	public void setName(String name) {
		this.usrName = name;
	}

	public String getPassword() {
		return usrPassword;
	}

	public void setPassword(String password) {
		this.usrPassword = password;
	}

	@Override
	public boolean hasId() {
		if (this.getId() == 0)
			return false;
		return true;
	}

	@Override
	public boolean hasAttributes() {
		if ("".equals(this.getName()) && "".equals(this.getPassword()))
			return false;
		return true;
	}
}
