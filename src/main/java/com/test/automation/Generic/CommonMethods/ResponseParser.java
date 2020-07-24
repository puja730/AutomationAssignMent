package com.test.automation.Generic.CommonMethods;

import com.google.gson.Gson;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 

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
	
}
