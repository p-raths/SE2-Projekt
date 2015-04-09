package model;

import java.sql.Connection;
import java.util.ArrayList;

import dataAccessObject.Database;
import dataAccessObject.Project;
import dataTransferObject.CardType;

public class ProjectManager {
	public ArrayList<CardType> GetFeeds()throws Exception {
		ArrayList<CardType> feeds = null;
		try 
		{
			System.out.println("GetFeed entered");
			Database database = new Database();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			feeds = project.GetFeeds(connection);
		} catch (Exception e) {
			System.out.println("GetFeeds error caused!");
			throw e;
		}
		return feeds;
	}
	
	public String PostFeed(CardType cardType) throws Exception {
		try 
		{
			System.out.println("PostFeed-ProjectManager entered");
			Database database = new Database();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			return project.PostFeed(cardType, connection);
		} catch (Exception e) {
			System.out.println("PostFeed-ProjectManager error caused!");
			throw e;
		}
	}
}
