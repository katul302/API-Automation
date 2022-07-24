package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test(dataProvider="Books data")

	public void addBook(String isbn, String aisle )  
	{

		RestAssured.baseURI = ("http://216.10.245.166");
		String resp = given().header("Content-Type", "application/json").body(payload.Addbook(isbn,aisle)).when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = ReusableMethod.rawToJson(resp);
		String ID = js.get("ID");
		System.out.println(ID);

	}
	
	@DataProvider(name ="Books data")
	public Object[][] getData()
	
	{
		return new Object[][]  {{"etete","3244"},{"ghgj","65656"},{"etdsdf","434344"}};
		
	}
}
