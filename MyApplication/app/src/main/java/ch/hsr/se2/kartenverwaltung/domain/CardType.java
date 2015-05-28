package domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardType extends BaseObject {
	private int catId;
	private String catName;
	private String catDescription;
	private String catDefaultAttributes;

	public CardType() {
	}

	public CardType(int id, String name, String description,
			String defaultAttributes) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setDefaultAttributes(defaultAttributes);
	}

	public CardType(String name, String description, String defaultAttributes) {
		this.setId(0);
		this.setName(name);
		this.setDescription(description);
		this.setDefaultAttributes(defaultAttributes);
	}

	public CardType(ResultSet resultSet) {
		try {
			if (resultSet.next()) {
				this.setId(resultSet.getInt("CatId"));
				this.setName(resultSet.getString("CatName"));
				this.setDescription(resultSet.getString("CatDescription"));
				this.setDefaultAttributes(resultSet
						.getString("CatDefaultAttributes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return catId;
	}

	public void setId(int id) {
		this.catId = id;
	}

	public String getName() {
		return catName;
	}

	public void setName(String name) {
		this.catName = name;
	}

	public String getDescription() {
		return catDescription;
	}

	public void setDescription(String description) {
		this.catDescription = description;
	}

	public String getDefaultAttributes() {
		return catDefaultAttributes;
	}

	public void setDefaultAttributes(String defaultAttributes) {
		this.catDefaultAttributes = defaultAttributes;
	}

	@Override
	public boolean hasId() {
		if (this.getId() == 0)
			return false;
		return true;
	}

	@Override
	public boolean hasAttributes() {
		if ("".equals(this.getName()) && "".equals(this.getDescription())
				&& "".equals(this.getDefaultAttributes()))
			return false;
		return true;
	}

}
