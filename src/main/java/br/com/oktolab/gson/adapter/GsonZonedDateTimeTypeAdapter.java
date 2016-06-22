package br.com.oktolab.gson.adapter;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonZonedDateTimeTypeAdapter implements JsonDeserializer<ZonedDateTime>, JsonSerializer<ZonedDateTime> {

	@Override
	public ZonedDateTime deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try { 
	        String jsonStr = json.getAsJsonPrimitive().getAsString();
	        return parseLocalDateTime(jsonStr);
	    } catch (ParseException e) { 
	        throw new JsonParseException(e.getMessage(), e);
	    } 
	}
	
	private ZonedDateTime parseLocalDateTime(final String dateString) throws ParseException {
	    if (dateString != null && dateString.trim().length() > 0) {
	    	if (dateString.length() == 19) { // whithout zone
	    		//dateString = dateString + "-03:00";
	    		LocalDateTime localDateTime = LocalDateTime.parse(dateString);
	    		return localDateTime.atZone(ZoneId.of("America/Sao_Paulo"));
	    	} else {
	    		return ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
	    	}
	    } else { 
	        return null; 
	    } 
	}

	@Override
	public JsonElement serialize(final ZonedDateTime src, final Type typeOfSrc,
			final JsonSerializationContext context) {
		final String strDateTime = DateTimeFormatter.ISO_OFFSET_DATE_TIME
									.withZone(ZoneId.of("America/Sao_Paulo"))
									.format(src);
		return new JsonPrimitive(strDateTime);
	}
	
}
