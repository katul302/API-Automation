package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification rsb;
//	ResponseSpecification resspec;

	public RequestSpecification requestSpecfication() throws IOException {
		
		if(rsb==null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		

		rsb = new RequestSpecBuilder().setBaseUri(getGloablValue("baseURI")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return rsb;
		}
		return rsb;

	}

//	public ResponseSpecification responsespec() {
//
//		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//		return resspec;
//	}
//	
	public static String  getGloablValue(String key) throws IOException
	{
		Properties prop  = new Properties();
		FileInputStream fis  = new FileInputStream("D:\\API Automatiom\\APIFramework\\src\\main\\java\\Resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String resp = response.asString();
		JsonPath	js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
