Feature: Validating Place Api's

@AddPlaceApi @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add place payload 
Given Add place payload "<name>" "<language>" "<address>"
When User calls "AddPlaceApi" with "Post" http request
Then the API call is success and response status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:
	| name   | language | address |
	| AAhouse| English  | Test    | 
#	| BBhouse| French   | Main st |
#	| DDhouse| Hindi    | Lower st|
	

@deletePlaceAPI @Regression
Scenario: Verfiy if delete place functionality is working
Given  Delete place payload
When 	User calls "deletePlaceAPI" with "Post" http request
Then the API call is success and response status code 200
And "status" in response body is "OK"
	