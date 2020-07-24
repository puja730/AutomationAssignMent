package com.test.automation.Generic.CommonMethods;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response; 

public class ResponseParser {	
	
	public static JSONArray array;
	
	public static Object GetObject(Response response, Class Responsepojoclass) {
		Gson gson = new Gson();		
		Object responseobject = gson.fromJson(response.asString(), Responsepojoclass);
		
		return responseobject;
		
	}

// To Read Json File 	
public static JSONArray JsonParser(String FileName) throws IOException, ParseException {
	
	JSONParser jsonParser = new JSONParser();
    
    FileReader reader = new FileReader(System.getProperty("user.dir")+Constants.JSON_FilePath+FileName+Constants.JSON_FileExtension);
    
    Object obj = jsonParser.parse(reader);
    
     array= new JSONArray();
     
     array.add(obj);
    
      return array;
	}
	
/*postRequest = new HttpPost("https://qa-aresauth.emersonecologics.com/1.0/connect/token");
			postRequest.addHeader("content-type", "application/x-www-form-urlencoded");
			List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
			urlParameters.add(new BasicNameValuePair("grant_type", "password"));
			urlParameters.add(new BasicNameValuePair("username", userName));
			urlParameters.add(new BasicNameValuePair("password", password));
			urlParameters.add(new BasicNameValuePair("scope", "aresApi"));
			urlParameters.add(new BasicNameValuePair("client_id", "AresApiTests"));
			urlParameters.add(new BasicNameValuePair("client_secret", "I4akw7gYW5rlfgXU4wfm9J0pjkF8TOY002Dr/w7krLY="));

			postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
			response = client.execute(postRequest);
			responseAsString = EntityUtils.toString(response.getEntity());
			responseAsJsonObject = new JSONObject(responseAsString);
			AUTH_TOKEN = (String) responseAsJsonObject.get("access_token");
*/

}
