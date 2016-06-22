package br.com.oktolab.gson.adapter;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonLocalDateTypeAdapter implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try { 
	        String jsonStr = json.getAsJsonPrimitive().getAsString();
	        return parseLocalDateTime(jsonStr);
	    } catch (ParseException e) { 
	        throw new JsonParseException(e.getMessage(), e);
	    } 
	}
	
	private LocalDate parseLocalDateTime(String dateString) throws ParseException {
	    if (dateString != null && dateString.trim().length() > 0) {
	    	if (dateString.contains("T")) {
	    		dateString = dateString.substring(0, dateString.indexOf("T"));
	    	}
	    	return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
	    } else { 
	        return null; 
	    } 
	}

	@Override
	public JsonElement serialize(final LocalDate src, final Type typeOfSrc,
			final JsonSerializationContext context) {
		return new JsonPrimitive(DateTimeFormatter.ISO_DATE.format(src));
	}

}
