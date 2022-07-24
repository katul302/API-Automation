package DemoProject.aks;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import desearlization.api;
import desearlization.getCourse;
import desearlization.webautomation;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) throws InterruptedException {
		
		String [] courseTitle = {"Selenium Webdriver Java","Cypress","Protractor"};

//		WebDriver driver = WebDriverManager.chromedriver().create();
//
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
//		
//		driver.findElement(By.cssSelector("#identifierId")).sendKeys("katul302@gmail.com	");
//		driver.findElement(By.cssSelector("#identifierId")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		
//		driver.findElement(By.name("password")).sendKeys("1@34567bA");
//		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		String url = driver.getCurrentUrl();

		// Google auth is not possible from automation now
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qj8PO4vJG1h6yCUzb7Xw9jEGAY5h6qJyV9ARpXKvID-vGWUDUBAeKuyaIJm8MmxXw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partailcode = url.split("code=")[1];
		String actual_code = partailcode.split("&scope")[0];
		System.out.println(actual_code);

		String accessToken = given().urlEncodingEnabled(false).queryParams("code", actual_code)
				.queryParams("clinet_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println("Conslole log: " + accessToken);

		JsonPath js = new JsonPath(accessToken);

		String accessToken1 = js.getString("access_token");

//		String response = given().queryParam("access_token", accessToken1).when().log().all()
//				.get("https://rahulshettyacademy.com/getCourse.php").asString();
//
//		System.out.println(response);

		getCourse gc = given().queryParam("access_token", accessToken1).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(getCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());

		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

		// Traversing nested json

		List<api> apicoursecount = gc.getCourses().getApi();

		for (int i = 0; i < apicoursecount.size(); i++) {

			if (apicoursecount.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
				;
			{
				System.out.println(apicoursecount.get(i).getPrice());
				break;
			}
			
			

		}

		//get the course tite for webautomation
		//String course1;
		List<webautomation> webAutomation = gc.getCourses().getWebAutomation();
		ArrayList<String> a = new ArrayList<String>();
		
		for(int j=0;j<webAutomation.size();j++) {
			 a.add(webAutomation.get(j).getCourseTitle());
			
		}
		
		List<String> expectedList = Arrays.asList(courseTitle);
		
		Assert.assertTrue(a.equals(expectedList));
		
	}

}
