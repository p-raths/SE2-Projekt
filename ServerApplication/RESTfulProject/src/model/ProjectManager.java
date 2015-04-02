package model;

import java.sql.Connection;
import java.util.ArrayList;

import dao.Database;
import dao.Project;
import dto.CardType;

public class ProjectManager {
	
	
	public ArrayList<CardType> GetFeeds()throws Exception {
		ArrayList<CardType> feeds = null;
		try 
		{
			Database database = new Database();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			feeds = project.GetFeeds(connection);
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	public String PostFeed(CardType cardType) throws Exception {
		try 
		{
			Database database = new Database();
			Connection connection = database.Get_Connection();
			Project project = new Project();
			return project.PostFeed(cardType, connection);
		} catch (Exception e) {
			throw e;
		}
	}
}
