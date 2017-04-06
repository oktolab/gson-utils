package br.com.oktolab.gson.adapter;

import java.lang.reflect.Type;
import java.time.LocalTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonLocalTimeTypeAdapter implements JsonDeserializer<LocalTime>, JsonSerializer<LocalTime> {

	@Override
	public LocalTime deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
        String jsonStr = json.getAsJsonPrimitive().getAsString();
        return LocalTime.parse(jsonStr);
	}

	@Override
	public JsonElement serialize(final LocalTime src, final Type typeOfSrc,
			final JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}
	
}
