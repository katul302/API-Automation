package DemoProject.aks;

import io.restassured.RestAssured;
import serialized.googlemappost;
import serialized.location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {

	public static void main(String[] args) {
		
		googlemappost p = new googlemappost();
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName("Frontline house");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().queryParam("key", "qaclick123").body(p).when().post("/maps/api/place/add/json").then()
				.assertThat().statusCode(200).extract().response().asString();
		System.out.println(res);

	}

}
