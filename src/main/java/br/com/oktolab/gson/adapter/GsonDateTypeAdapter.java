package br.com.oktolab.gson.adapter;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonDateTypeAdapter implements JsonDeserializer<Date> {
	 
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String DATE_WITH_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
	 
	@Override 
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	    try { 
	        String jsonStr = json.getAsJsonPrimitive().getAsString();
	        return parseDate(jsonStr);
	    } catch (ParseException e) { 
	        throw new JsonParseException(e.getMessage(), e);
	    } 
	} 
	 
	private Date parseDate(String dateString) throws ParseException {
		DateFormat dateFormat = null;
	    if (dateString != null && dateString.trim().length() > 0) {
	    	if (dateString.contains("T")) {
	    		dateFormat = new SimpleDateFormat(DATE_WITH_TIME_PATTERN);
	    	} else {
	    		dateFormat = new SimpleDateFormat(DATE_PATTERN);
	    	}
	    	return dateFormat.parse(dateString);
	    } else { 
	        return null; 
	    } 
	}
}