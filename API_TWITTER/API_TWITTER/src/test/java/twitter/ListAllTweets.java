package twitter;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class ListAllTweets {
	private static Logger log = LogManager.getLogger(getTrends.class.getName());

	Properties p = new Properties();
	
	
	@Test
	public void getUserTweets() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Qualitest\\API_TWITTER\\src\\main\\java\\resources\\data.properties");
		p.load(fis);
	RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
	Response res =
			given().auth().oauth(p.getProperty("CONSUMER_KEY"),p.getProperty("CONSUMER_SECRET"),p.getProperty("ACCESS_TOKEN"),p.getProperty("TOKEN_KEY")).
			when().
			queryParam("exclude_replies", "true").queryParam("include_rts", "false").
			get("user_timeline.json").
	
			then().assertThat().statusCode(200).extract().response();

		String responseString = res.asString() ;
		JsonPath js = new JsonPath(responseString);
		List<String> abc = js.get("text") ;
		System.out.println(abc);
		log.info("done");
		}

}
