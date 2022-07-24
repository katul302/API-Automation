package Resources;

import java.util.ArrayList;
import java.util.List;

import serialized.googlemappost;
import serialized.location;

public class testdatabuild {
	
	public googlemappost addPlacePayload()
	{
		googlemappost p = new googlemappost();
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName("Frontline house");
		p.setAccuracy(50);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public googlemappost addPlacePayload1(String name, String language, String address)
	{
		googlemappost p = new googlemappost();
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName(name);
		p.setAccuracy(50);

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}

	
	public String  deletePlacePayload(String place_id)
	{
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
	}
}
