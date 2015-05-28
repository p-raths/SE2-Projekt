package domain;

public class Location extends BaseObject {
	private int locId;
	private String locPosition;
	private int locNumberOfVisits;
	private Card card;

	public Location() {
		this.setId(0);
		this.setNumberOfVisits(0);
	}

	public Location(String position, Card id) {
		this.setPosition(position);
		this.setCard(id);
	}

	public Location(int id, String position, Card card) {
		this.setId(id);
		this.setPosition(position);
		this.setCard(card);
	}

	public int getId() {
		return locId;
	}

	public void setId(int id) {
		this.locId = id;
	}

	public String getPosition() {
		return locPosition;
	}

	public void setPosition(String position) {
		this.locPosition = position;
	}

	public int getNumberOfVisits() {
		return locNumberOfVisits;
	}

	public void setNumberOfVisits(int numberOfVisits) {
		this.locNumberOfVisits = numberOfVisits;
	}

	public void incrementNumberOfVisits() {
		this.locNumberOfVisits++;
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
		if ("".equals(this.getPosition()))
			return false;
		return true;
	}
}
