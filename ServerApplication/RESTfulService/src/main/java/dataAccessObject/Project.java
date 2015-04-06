package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dataTransferObject.CardType;

public class Project {
	
	
	public ArrayList<CardType> GetFeeds(Connection connection) throws Exception
	{
		ArrayList<CardType> feedData = new ArrayList<CardType>();
		try
		{			
			PreparedStatement ps = connection.prepareStatement("SELECT CatName,CatDescription,CatDefaultAttributes FROM TCardType ORDER BY CatId DESC");		
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CardType cardType = new CardType();
				cardType.setName(rs.getString("CatName"));
				cardType.setDescription(rs.getString("CatDescription"));
				cardType.setDefaultAttributes(rs.getString("CatDefaultAttributes"));
				feedData.add(cardType);
			}
			return feedData;
		}
		catch(Exception e)
		{
			System.out.println("GetFeeds-Project error caused!");
			throw e;
		}
	}
	
	public String PostFeed(CardType cardType, Connection connection) throws Exception
	{
		try
		{
			String action;
			PreparedStatement ps;
			if (cardType.getId() == -1)
			{
				ps = connection.prepareStatement("INSERT INTO TCardType VALUES(NULL, ?, ?, ?)");
				action = "inserted";
			}
			else
			{
				ps = connection.prepareStatement("UPDATE TCardType SET CatName=?, CatDescription=?, CatDefaultAttributes=? WHERE CatId=?");
				ps.setInt(4, cardType.getId());
				action = "updated";
			}
			ps.setString(1, cardType.getName());
			ps.setString(2, cardType.getDescription());
			ps.setString(3, cardType.getDefaultAttributes());
			return "" + ps.executeUpdate() + " rows have been successfully " + action + "!";			
		}
		catch(Exception e)
		{
			System.out.println("PostFeed-Project error caused!");
			throw e;
		}
	}	
}

