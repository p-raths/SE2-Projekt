package ch.hsr.se2.kartenverwaltung.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Attribute extends BaseObject {
	private int atrId;
	private String atrName;
	private String atrValue;
	private AttributeType attributeType;
	private Card card;

	public Attribute() {
		this.setId(0);
	}

	public Attribute(String name, String value, AttributeType attributeType,
			Card card) {
		this.setId(0);
		this.setName(name);
		this.setValue(value);
		this.setAttributeType(attributeType);
		this.setCard(card);
	}

	public Attribute(int id, String name, String value,
			AttributeType attributeType, Card card) {
		this.setId(id);
		this.setName(name);
		this.setValue(value);
		this.setAttributeType(attributeType);
		this.setCard(card);
	}

	public Attribute(ResultSet resultSet) {
		try {
			if (resultSet.next()) {
				this.setId(resultSet.getInt("AtrId"));
				this.setName(resultSet.getString("AtrName"));
				this.setValue(resultSet.getString("AtrValue"));
				AttributeType attributeType = new AttributeType();
				attributeType.setId(resultSet.getInt("AttId"));
				this.setAttributeType(attributeType);
				Card card = new Card();
				card.setId(resultSet.getInt("CardId"));
				this.setCard(card);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return atrId;
	}

	public void setId(int id) {
		this.atrId = id;
	}

	public String getName() {
		return atrName;
	}

	public void setName(String name) {
		this.atrName = name;
	}

	public String getValue() {
		return atrValue;
	}

	public void setValue(String value) {
		this.atrValue = value;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
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
