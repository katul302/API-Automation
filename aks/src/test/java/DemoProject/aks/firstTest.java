package DemoProject.aks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jdk.internal.net.http.common.Log;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethod;
import files.payload;

public class firstTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		// Validate if add place api is working

		// given, when ,THEN

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract()
				.response().asString();

		System.out.println("Variable response :" + response);

		//JsonPath js = new JsonPath(response);
		JsonPath js=	ReusableMethod.rawToJson(response);

		String place = js.getString("place_id");
		System.out.println(place);

		// Update place

		String new_add = "70 Summer walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place + "\",\r\n" + "\"address\":\"" + new_add + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// get place

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200)
				// .body("address", equalTo(new_add));
				.extract().response().asString();

	JsonPath js1=	ReusableMethod.rawToJson(getPlaceResponse);

		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);

		Assert.assertEquals(actualAddress, new_add);

	}

}
