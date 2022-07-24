package DemoProject.aks;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";
		
		// Login into Jira Api to get session id
		
		
		SessionFilter s = new SessionFilter();
		String comment = "Please review the bug";
		
		String response =	given().log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"username\": \"katul302\",\r\n"
				+ "    \"password\": \"1@34567bA\"\r\n"
				+ "}").log().all().filter(s).when().post("rest/auth/1/session").then().log().all().extract().response().asString();

		// Add a comment to existing issue
		
		
		
		 String jsonresponse =given().pathParam("id", "10007").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \""+comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(s).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201)
		 .extract().response().asString();
		//System.out.println("****" + jsonresponse);
		JsonPath js = new JsonPath(jsonresponse);
			 //String comment_id =js.get("id");
		String commentid =js.getString("id");
		System.out.println(commentid);
		// Add an attachment to an existing issue
		
		given().header("X-Atlassian-Token", "no-check").filter(s).header("Content-Type","multipart/form-data")
		.multiPart("file", new File("Jira.txt"))
		.when().post("/rest/api/2/issue/10007/attachments").then().log()
		.all().assertThat().statusCode(200);
		
		// Get issue
		
		String issueDetails = given().filter(s).pathParam("key", "10007").queryParam("fields", "comment")
				.log().all().when().get("/rest/api/2/issue/{key}").then().log().all()
		.extract().response().asString();
		System.out.println("#$#$#$" +issueDetails);
		
		JsonPath js1 = new JsonPath(issueDetails);
		int comment_count = js1.getInt("fields.comment.comments.size()");
		System.out.println(comment_count);
		for(int i =0;i<comment_count;i++) {
			
			
			String commentIdIssue =js1.get("fields.comment.comments["+i+"].id").toString();
			if(commentIdIssue.equalsIgnoreCase(commentid))
			{
				String body =js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(body);
				Assert.assertEquals(body, comment);
			}
			
		}
		
	}

}
