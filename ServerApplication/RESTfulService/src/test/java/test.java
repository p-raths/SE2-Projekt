import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

@Path("/serverTest")
public class test {

	private static final String LOCALHOST = "http://localhost:8000/test/";

	private static HttpServer server;

	@GET
	@Produces("text/plain")
	public String testSystem() {
		return "Hello. This is a test";
	}

	@BeforeClass
	public static void setUp() throws Exception {
		// Our server start

		System.out.println("Creating server");
		server = HttpServerFactory.create(LOCALHOST);
	
		System.out.println("Starting server");
		server.start();

		System.out.println("HTTP server started");
		System.out.println("Running tests...");
		
	}

	// Destroy the server
	@AfterClass
	public static void tearDown() throws IOException {
		System.out.println("Stopping server");
		server.stop(0);
		System.out.println("Server stopped");
	}

	private static String testResourceAtUrl(URL url) throws Exception {

		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");
			connection.connect();

			InputStream inputStream = connection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String firstLineOfText = reader.readLine();
			System.out.println("Read: " + firstLineOfText);

			System.out.println("System was initialized correctly. About to run actual tests...");

			connection.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new Exception("could not establish connection to " + url.toExternalForm());
	}

	@Test
	public void testMyMethod() throws Exception {
		String activationText = testResourceAtUrl(new URL(LOCALHOST + "restfulproject/GetFeeds"));
		String testString = "[{\"id\":0,\"name\":\"CardTypeTest\",\"description\":\"CardTypeDescriptionTest\",\"defaultAttributes\":\"DefaultAttributes\"}]";
		assertEquals(testString, activationText);

	}

}