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

public class BlockUser {
	private static Logger log = LogManager.getLogger(getTrends.class.getName());
	Properties p = new Properties(); 
	@Test 
	public void Block() throws IOException {
	FileInputStream fis = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\API_TWITTER\\API_TWITTER\\src\\main\\java\\resources\\data.properties");
	p.load(fis);
RestAssured.baseURI="https://api.twitter.com/1.1/blocks/";
Response res =
		given().auth().oauth(p.getProperty("CONSUMER_KEY"),p.getProperty("CONSUMER_SECRET"),p.getProperty("ACCESS_TOKEN"),p.getProperty("TOKEN_KEY")).
		when().
		queryParam("screen_name", "NiloorC").
		post("create.json").

		then().assertThat().statusCode(200).extract().response();

	String responseString = res.asString() ;
	JsonPath js = new JsonPath(responseString);
	String abc = js.get("name") ;
	System.out.println(abc);
	log.info("done");
	}
}
