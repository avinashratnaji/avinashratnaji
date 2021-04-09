package twitter;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Weather {
	Properties p = new Properties();
	@Test
    public void weather_update() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Qualitest\\API_TWITTER\\src\\main\\java\\resources\\data.properties");
		p.load(fis);
        RestAssured.baseURI="https://api.twitter.com/1.1/search/";
        Response res=given().auth().oauth(p.getProperty("CONSUMER_KEY"),p.getProperty("CONSUMER_SECRET"),p.getProperty("ACCESS_TOKEN"),p.getProperty("TOKEN_KEY")).
                queryParam("q", "#Bangalore #Weather").when().
                get("tweets.json").then().extract().response();
        String response=res.asString();
        JsonPath jp=new JsonPath(response);
        ArrayList<Object> arr=new ArrayList<>();
        arr.addAll(jp.getList("statuses.text"));
        Iterator itr=arr.iterator();
        while(itr.hasNext())
        {       
            System.out.println(itr.next());
        }
       
       
    }

}
