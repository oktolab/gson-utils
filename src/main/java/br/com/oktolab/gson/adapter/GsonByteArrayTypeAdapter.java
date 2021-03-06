package br.com.oktolab.gson.adapter;

import java.lang.reflect.Type;
import java.util.Base64;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonByteArrayTypeAdapter implements JsonDeserializer<byte[]>, JsonSerializer<byte[]> {

	@Override
	public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(Base64.getEncoder().encodeToString(src));
	}

	@Override
	public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Base64.getDecoder().decode(json.getAsString());
	}
	 
	
}
