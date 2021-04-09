package twitter;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;
import java.util.Properties;


import org.apache.logging.log4j.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.base;
public class getTrends extends base{
	String INDIA="23424848";
	String US="23424977";
String		UK="23424975";
	String ISRAEL="23424852";
	Integer statusCode = 200; 
	Properties p = new Properties();

	private static Logger log = LogManager.getLogger(getTrends.class.getName());

	@Test (dataProvider="getData")
	public void API_test(String location) throws IOException {
		
		
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Qualitest\\API_TWITTER\\src\\main\\java\\resources\\data.properties");
		p.load(fis);
	RestAssured.baseURI="https://api.twitter.com/1.1/trends/";
	Response res =
	given().auth().oauth(p.getProperty("CONSUMER_KEY"),p.getProperty("CONSUMER_SECRET"),p.getProperty("ACCESS_TOKEN"),p.getProperty("TOKEN_KEY")).
		queryParam("id",location).
	when().
		get("place.json").
	then().assertThat().statusCode(200).extract().response();
	
	
	String responseString = res.asString() ;
	JsonPath js = new JsonPath(responseString);
	List<String> abc = js.get("trends.name") ;
	Listtoelement(abc);
	
	
	}
	
	@DataProvider
	public String[] getData() {
		String[] abcd = {INDIA,US,UK,ISRAEL};
		return abcd;
	}
	
	@AfterTest
	public void print() {
	if(statusCode == 200) {
		log.info("INDIAN TRENDS ARE DISPLAYED");
		log.info("TRENDS IN US:");
		log.info("TRENDS IN UK:");
		log.info("TRENDS IN Israel:");
		
	}
}
}




