package br.com.oktolab.gson.adapter;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonDateTypeAdapter implements JsonDeserializer<Date> {
	 
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String START_OF_DAY = "T00:00:00";
	 
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
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}
		if (dateString.length() < 19) {
			if (dateString.length() == 10) {
				dateString = dateString + START_OF_DAY;
			} else {
				dateString = dateString + START_OF_DAY.substring(START_OF_DAY.length() + dateString.length() - 19);
			}
    	}
    	return new SimpleDateFormat(DATE_PATTERN).parse(dateString);
	}
	
}