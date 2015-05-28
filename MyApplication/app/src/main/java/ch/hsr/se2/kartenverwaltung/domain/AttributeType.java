package ch.hsr.se2.kartenverwaltung.domain;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AttributeType extends BaseObject {
	private int attId;
	private String attName;
	private int attMaxLength;
	private int attMaxPrecision;

	public AttributeType() {
		this.setId(0);
	}

	public AttributeType(String name, int maxLength, int maxPrecision) {
		this.setId(0);
		this.setName(name);
		this.setMaxLength(maxLength);
		this.setMaxPrecision(maxPrecision);
	}

	public AttributeType(int id, String name, int maxLength, int maxPrecision) {
		this.setId(id);
		this.setName(name);
		this.setMaxLength(maxLength);
		this.setMaxPrecision(maxPrecision);
	}

	public AttributeType(ResultSet resultSet) {
		try {
			if (resultSet.next()) {
				this.setId(resultSet.getInt("AttId"));
				this.setName(resultSet.getString("AttName"));
				this.setMaxLength(resultSet.getInt("AttMaxLength"));
				this.setMaxPrecision(resultSet.getInt("AttMaxPrecision"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return attId;
	}

	public void setId(int id) {
		this.attId = id;
	}

	public String getName() {
		return attName;
	}

	public void setName(String name) {
		this.attName = name;
	}

	public int getMaxPrecision() {
		return attMaxPrecision;
	}

	public void setMaxPrecision(int maxPrecision) {
		this.attMaxPrecision = maxPrecision;
	}

	public int getMaxLength() {
		return attMaxLength;
	}

	public void setMaxLength(int maxLength) {
		this.attMaxLength = maxLength;
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