package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.FeedObjects;


public class Project {
	
	
	public ArrayList<FeedObjects> GetFeeds(Connection connection) throws Exception
	{
		ArrayList<FeedObjects> feedData = new ArrayList<FeedObjects>();
		try
		{
			//String uname = request.getParameter("uname");
			PreparedStatement ps = connection.prepareStatement("SELECT CatName,CarDescription,CatDefaultAttributes FROM CardType ORDER BY CatId DESC");
			//ps.setString(1,uname);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				FeedObjects feedObject = new FeedObjects();
				feedObject.setTitle(rs.getString("CatName"));
				feedObject.setDescription(rs.getString("CatDescription"));
				feedObject.setUrl(rs.getString("CatDefaultAttributes"));
				feedData.add(feedObject);
			}
			return feedData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
}
