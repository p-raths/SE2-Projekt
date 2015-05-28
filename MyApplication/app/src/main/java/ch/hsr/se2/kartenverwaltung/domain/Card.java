package ch.hsr.se2.kartenverwaltung.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Card extends BaseObject {
	private int id;
	private String cardName;
	private String cardDescription;
	private int cardRevision;
	private CardType cardType;
	private User user;
	private ArrayList<Attribute> attributes;

	public Card() {
		setId(0);
		this.setAttributes(new ArrayList<Attribute>());
	}

	public Card(String name, String description, int revision,
			CardType cardType, User user) {
		this.setId(0);
		this.setName(name);
		this.setDescription(description);
		this.setRevision(revision);
		this.setCardType(cardType);
		this.setUser(user);
		this.setAttributes(new ArrayList<Attribute>());
	}

	public Card(int id, String name, String description, int revision,
			CardType cardType, User user) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setRevision(revision);
		this.setCardType(cardType);
		this.setUser(user);
		this.setAttributes(new ArrayList<Attribute>());
	}

	public Card(ResultSet resultSet) {
		try {
			this.setId(resultSet.getInt("CardId"));
			this.setName(resultSet.getString("CardName"));
			this.setDescription(resultSet.getString("CardDescription"));
			CardType cardType = new CardType();
			cardType.setId(resultSet.getInt("CatId"));
			this.setCardType(cardType);
			User user = new User();
			user.setId(resultSet.getInt("UsrId"));
			this.setUser(user);
			this.setAttributes(new ArrayList<Attribute>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return cardName;
	}

	public void setName(String name) {
		this.cardName = name;
	}

	public String getDescription() {
		return cardDescription;
	}

	public void setDescription(String cardDescription) {
		this.cardDescription = cardDescription;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean equals(Card other) {
		if (this.getId() == 0) {
			if (this.getName().equals(other.getName())
					&& this.getDescription().equals(other.getDescription())
					&& this.getRevision() == other.getRevision()
					&& this.getCardType().getId() == other.getCardType()
							.getId()
					&& this.getUser().getId() == other.getUser().getId()) {
				return true;
			}
		} else if (this.getId() == other.getId()) {
			return true;
		}
		return false;
	}

	public int getRevision() {
		return cardRevision;
	}

	public void setRevision(int cardRevision) {
		this.cardRevision = cardRevision;
	}

	@Override
	public boolean hasId() {
		if (this.getId() == 0)
			return false;
		return true;
	}

	@Override
	public boolean hasAttributes() {
		if ("".equals(this.getName()) && "".equals(this.getDescription()))
			return false;
		return true;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}
}
