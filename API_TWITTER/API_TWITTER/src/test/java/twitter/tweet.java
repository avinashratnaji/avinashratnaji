package twitter;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class tweet {
	Properties p = new Properties();
	
	private static Logger log = LogManager.getLogger(getTrends.class.getName());
 
	@Test 
	public void tweets() throws IOException {
		
		
		FileInputStream fis = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\API_TWITTER\\API_TWITTER\\src\\main\\java\\resources\\data.properties");
		p.load(fis);
	RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
	Response res =
	given().auth().oauth(p.getProperty("CONSUMER_KEY"),p.getProperty("CONSUMER_SECRET"),p.getProperty("ACCESS_TOKEN"),p.getProperty("TOKEN_KEY")).
	queryParam("status","Qualitest 2020 batch!! Work From Home").
	when().
		post("update.json").
		
	then().assertThat().statusCode(200).extract().response();
	
	String responseString = res.asString() ;
	JsonPath js = new JsonPath(responseString);
	long abc = js.get("id") ;
	System.out.println(abc);
	if (abc != 0) {
		log.info("TWEET CREATED!!");
	}
	
			}
	
}
