package stepdefintion;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class stepDefination {

	
	@Given("^User is on landing page$")
	public void user_is_on_landing_page() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("User is on landing page");
	}
	@When("^User login into application with username and password$")
	public void user_login_into_application_with_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("User is on landing page");
	}
	@Then("^Home page is populated$")
	public void home_page_is_populated() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("User is on landing page");
	}
	@Then("^Dashboard display$")
	public void dashboard_display() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("User is on landing page");
	}
	@When("User login into application with {string} and password {string}")
	public void user_login_into_application_with_and_password(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions

System.out.println("username" + string);
System.out.println("password" + string2);
	}
}
