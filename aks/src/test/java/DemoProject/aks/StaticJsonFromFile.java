package DemoProject.aks;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJsonFromFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		// Content of the file to string -> content of the file convert into 
		// Byte -> Byte data to String
		
		RestAssured.baseURI = ("http://216.10.245.166");
		
		String resp = given().header("Content-Type", "application/json").body(new String(Files.readAllBytes(Paths.get("D:\\Payload.json")))).when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReusableMethod.rawToJson(resp);
				String ID = js.get("ID");
				System.out.println(ID);

	}

}
