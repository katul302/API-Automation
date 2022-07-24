package DemoProject.aks;
//import static io.restassured.RestAssured.*;
import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		
		//String resp = given().header("Content-Type", "application/json").body(payload.CoursePrice()).toString();
		//System.out.println(resp);
		JsonPath js = new JsonPath(payload.CoursePrice());

		System.out.println("1. Print No of courses returned by API");

		int count = js.getInt("courses.size()");

		System.out.println(count);

		System.out.println("2.Print Purchase Amount");

		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);

		System.out.println("3. Print Title of the first course");

		String titleFirstCourse = js.get("courses[0].title");

		System.out.println(titleFirstCourse);
		String titleThirdCourse = js.get("courses[2].title");

		System.out.println(titleThirdCourse);

		System.out.println("4. Print All course titles and their respective Prices");

		for (int i = 0; i < count; i++) {

			String allTitle = js.get("courses[" + i + "].title");
			System.out.println(allTitle);

			// int totalPrice = js.getInt("courses["+i+"].price");
			System.out.println(js.get("courses[" + i + "].price").toString());

			// System.out.println(totalPrice);
		}
//
		System.out.println("5. Print no of copies sold by RPA Course");

		for (int j = 0; j < count; j++) {

			String allTitle = js.get("courses[" + j + "].title");
			System.out.println(allTitle);

			if (allTitle.equalsIgnoreCase("RPA")) {

				System.out.println(js.get("courses[" + j + "].copies").toString());
				break;

			}

		}

//
	System.out.println("6. Verify if Sum of all Course prices matches with Purchase Amount");
	
	
		System.out.println(purchaseAmt);
		int sum =0;
		for (int i = 0; i < count; i++) {

			int price = js.get("courses["+i+"].price");
			
			int copies= js.get("courses["+i+"].copies");
			int amount = price*copies;
			sum = sum+amount;
			
			
			

			
		}
		System.out.println(sum);
		
		Assert.assertEquals(sum, purchaseAmt);
		
	}

}
