package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Resources.APIResources;
import Resources.Utils;
import Resources.testdatabuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefination extends Utils {

	private RequestSpecification res;
	private Response Respose;
	private ResponseSpecification resspec;
	 static String pl_id;
	APIResources resourcesAPI;
	testdatabuild p = new testdatabuild();

	@Given("Add place payload")
	public void add_place_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions

		res = given().log().all().spec(requestSpecfication()).body(p.addPlacePayload());

	}

	

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().log().all().spec(requestSpecfication()).body(p.addPlacePayload1(name, language, address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions

		APIResources resourcesAPI = APIResources.valueOf(resource);

		System.out.println(resourcesAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))

			Respose = res.when().post(resourcesAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			Respose = res.when().get(resourcesAPI.getResource());

	}

	@Then("the API call is success and response status code {int}")
	public void the_api_call_is_success_and_response_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(Respose.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(Respose, key), value);

	}

	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		// Construct request Request spec
		 pl_id = getJsonPath(Respose, "place_id");
		res = given().log().all().spec(requestSpecfication()).queryParam("place_id", pl_id);

		// Hit get place id

		user_calls_with_post_http_request(resource, "GET");

		String actualname = getJsonPath(Respose, "name");

		System.out.println("name from feature file: " + expectedname);
		System.out.println("name from get response: " + actualname);
		assertEquals(expectedname, actualname);

	}
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		res =given().log().all().spec(requestSpecfication().body(p.deletePlacePayload(pl_id)));
				
		
		System.out.println("delete pass");

	}

}
