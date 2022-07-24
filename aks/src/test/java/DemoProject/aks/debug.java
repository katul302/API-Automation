package DemoProject.aks;

import static io.restassured.RestAssured.given;

import files.ReusableMethod;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class debug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = ("http://216.10.245.166");
		System.out.println(payload.Addbook("abcd", "1234"));
		String resp = given().header("Content-Type", "application/json").body(payload.Addbook("abcd","1234")).when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		
		
				

		System.out.println("Response:" + resp );
		
		//JsonPath js = ReusableMethod.rawToJson(resp);
		//String ID = js.get("ID");
		//System.out.println();

	
	
	}

}
