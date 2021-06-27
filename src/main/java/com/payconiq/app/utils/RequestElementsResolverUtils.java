package com.payconiq.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestElementsResolverUtils {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object parseJSON(Class mapperClass, String json) throws JsonProcessingException {
		return mapper.readValue(json, mapperClass);
	}

}