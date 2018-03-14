package br.com.oktolab.gson.adapter;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		try { 
	        String jsonStr = json.getAsJsonPrimitive().getAsString();
	        return parseLocalDateTime(jsonStr);
	    } catch (ParseException e) {
	        throw new JsonParseException(e.getMessage(), e);
	    } 
	}
	
	private LocalDateTime parseLocalDateTime(final String dateString) throws ParseException {
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}
		if (dateString.length() == 10) {
			return LocalDate.parse(dateString).atStartOfDay();
		}
	    return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
	}

	@Override
	public JsonElement serialize(final LocalDateTime src, final Type typeOfSrc, final JsonSerializationContext context) {
		String strDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(src);
		return new JsonPrimitive(strDateTime);
	}

}
