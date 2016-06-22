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

public class GsonLocalDateTimeTypeAdapter implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		try { 
	        String jsonStr = json.getAsJsonPrimitive().getAsString();
	        return parseLocalDateTime(jsonStr);
	    } catch (ParseException e) { 
	        throw new JsonParseException(e.getMessage(), e);
	    } 
	}
	
	private LocalDateTime parseLocalDateTime(final String dateString) throws ParseException {
	    if (dateString != null && dateString.trim().length() > 0) {
	    	if (dateString.contains("T") && dateString.length() > 19) {
	    		return ZonedDateTime.parse(dateString).toLocalDateTime();
	    	}
    		return LocalDateTime.parse(dateString);
	    } else { 
	        return null; 
	    } 
	}

	@Override
	public JsonElement serialize(final LocalDateTime src, final Type typeOfSrc,
			final JsonSerializationContext context) {
		final ZonedDateTime zonedDateTime = ZonedDateTime.of(src, ZoneId.systemDefault());
		final String strDateTime = DateTimeFormatter.ISO_OFFSET_DATE_TIME
									.withZone(ZoneId.of("America/Sao_Paulo"))
									.format(zonedDateTime);
		return new JsonPrimitive(strDateTime);
	}

}
