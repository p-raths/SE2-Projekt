package webService;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import model.ProjectManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dataTransferObject.CardType;

@Path("/WebService")
public class FeedService {
	
	@GET
	@Path("/GetFeeds")
	@Produces("application/json")
	public String feed()
	{
		String feeds  = null;
		try 
		{
			ArrayList<CardType> feedData = null;
			ProjectManager projectManager= new ProjectManager();
			feedData = projectManager.GetFeeds();
			Gson gson = new Gson();
			System.out.println(gson.toJson(feedData));
			feeds = gson.toJson(feedData);

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return feeds;
	}
	
	@POST
	@Path("/PostFeeds*")
	@Consumes("application/json")
	public String seedArray(@QueryParam("input") String input)
	{
		String feeds  = null;
		try 
		{
			ArrayList<CardType> feedData = null;
			Gson gson = new Gson();
			ProjectManager projectManager= new ProjectManager();
			Type arrayType = new TypeToken<ArrayList<CardType>>() {}.getType();
						
			feedData = gson.fromJson(input, arrayType);
			System.out.println(gson.toJson(feedData));
			feeds = gson.toJson(feedData);

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return feeds;
	}
	
	
	@POST
	@Path("/PostFeed")
	@Produces("text/plain")
	public Response seed(@QueryParam("id") String id, 
			@QueryParam("name") String name, 
			@QueryParam("description") String description, 
			@QueryParam("defaultattributes") String defaultAttributes)
	{		
		String seedResult  = "";
		try 
		{
			System.out.println("Input: id=" + id + "; name=" + name + "; desc=" + description + "; attr=" + defaultAttributes);
			CardType seedData = new CardType(Integer.parseInt(id), name, description, defaultAttributes);
			ProjectManager projectManager= new ProjectManager();
			seedResult = projectManager.PostFeed(seedData);

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return Response.status(200).entity(seedResult).build();
	}
	

}
