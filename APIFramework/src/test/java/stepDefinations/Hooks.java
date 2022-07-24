package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	// Hooks is nothing but just it set pre and post condition before test execution

	@Before("@deletePlaceAPI")

	public void beforecase() throws IOException {

		stepDefination sd = new stepDefination();

		if (stepDefination.pl_id == null) {
			sd.add_place_payload("Atul", "English", "India");
			sd.user_calls_with_post_http_request("AddPlaceApi", "POST");
			sd.verify_place_id_created_maps_to_using("Atul", "getPlaceAPI");

		}
	}

}
