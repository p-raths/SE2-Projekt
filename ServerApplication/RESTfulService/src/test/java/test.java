import javax.ws.rs.*;
import static org.junit.Assert.assertEquals;
import com.sun.net.httpserver.HttpServer;

@Path("/serverTest")
public class test {

    private static final String LOCALHOST = "http://localhost:8080/";

    private static HttpServer server;

    @GET
    @Produces("text/plain")
    public String testSystem(){
        return "Hello. This is a test";
    }

    @BeforeClass
    public static void setUp() throws Exception{
        System.out.println("Creating server");
        server = HttpServerFactory.create(LOCALHOST);

        System.out.println("Starting server");
        server.start();

        System.out.println("HTTP server started");
        System.out.println("Running tests...");

        testResourceAtUrl(new URL(LOCALHOST + "hellotest"));
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

        assertEquals('{"id":0,"name":"CardTypeTest","description":"CardTypeDescriptionTest","defaultAttributes":"DefaultAttributes"}]",' activationText);

    }
    
    // Destroy the server
    @AfterClass
    public static void tearDown() throws IOException{
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

}