package dto;

public class CardType {
	private int id;
	private String name;
	private String description;
	private String defaultAttributes;
	
	public CardType()	{		
	}
	public CardType(int id, String name, String description, String defaultAttributes)	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.defaultAttributes = defaultAttributes;
	}
	
	public CardType(String name, String description, String defaultAttributes)	{
		this.id = -1;
		this.name = name;
		this.description = description;
		this.defaultAttributes = defaultAttributes;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the defaultAttributes
	 */
	public String getDefaultAttributes() {
		return defaultAttributes;
	}
	/**
	 * @param defaultAttributes the defaultAttributes to set
	 */
	public void setDefaultAttributes(String defaultAttributes) {
		this.defaultAttributes = defaultAttributes;
	}
	
	
	
}
