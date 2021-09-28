package com.OAuth2.OAuth2Impl.Utils;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class JavaObjectToJson {
	
	public String convert(Object javaObject) {
		
		String json = "";
		Gson gson = new GsonBuilder().create();
		json = gson.toJson(javaObject);
		return json;
				
	}
	
}
