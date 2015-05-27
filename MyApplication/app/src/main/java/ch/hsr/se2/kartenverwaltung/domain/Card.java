package ch.hsr.se2.kartenverwaltung.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by roberto on 29.03.15.
 */
public class Card {
	private int cardId;
	private String cardName;
	private String description;
	private Date creationDate;
	private HashMap<Integer, Attribute> cardAttributesMap;
	private HashMap<Integer, Location> locationHashMap;
    private int cardRevision;

	public Card(final int id, final String name, final String description) {
		this.cardId = id;
        this.cardName = name;
		this.description = description;
		this.creationDate = new Date();
		this.cardAttributesMap = new HashMap<>();
		this.locationHashMap = new HashMap<>();
	    this.cardRevision = 1;
    }

	public void setCardId(final int id) {
		this.cardId = id;
	}

    public void setCardName(final String name) {
        this.cardName = name;
    }

    public void setCardDescription(final String description) {
        this.description = description;
    }

	public void setAttribut(final Attribute newAttribut) {
		this.cardAttributesMap.put(newAttribut.getId(), newAttribut);
	}

    public void setCardRevision(final int revision){
        this.cardRevision = revision;
    }

	public Integer getCardId() {
		return this.cardId;
	}

	public Collection<Attribute> getAllAttributes() {

		return this.cardAttributesMap.values();
	}

	public Attribute getAttribut(final int attributeKey) {

		return this.cardAttributesMap.get(attributeKey);
	}

	public Collection<Location> getAllPositions() {
		return this.locationHashMap.values();
	}

	public void addPosition(final Location location) {
		this.locationHashMap.put(location.getLocationId(), location);
	}

	public Location getLocation(final int locationId) {

		return this.locationHashMap.get(locationId);
	}

	public String getCardName() {

		return cardName;
	}

	public String getDescription() {

		return description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

    public int getRevision(){
        return cardRevision;
    }
}
