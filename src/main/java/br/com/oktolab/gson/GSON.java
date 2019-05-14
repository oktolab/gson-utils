package br.com.oktolab.gson;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import br.com.oktolab.gson.adapter.GsonByteArrayTypeAdapter;
import br.com.oktolab.gson.adapter.GsonByteBufferTypeAdapter;
import br.com.oktolab.gson.adapter.GsonDateTypeAdapter;
import br.com.oktolab.gson.adapter.GsonLocalDateTimeTypeAdapter;
import br.com.oktolab.gson.adapter.GsonLocalDateTypeAdapter;
import br.com.oktolab.gson.adapter.GsonLocalTimeTypeAdapter;
import br.com.oktolab.gson.adapter.GsonZonedDateTimeTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

public class GSON {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	private static Gson gson;

	public static Gson getGson() {
		if (gson == null) {
			synchronized (GSON.class) {
				if (gson == null) {
					gson = new GsonBuilder()
							.setDateFormat(DATE_FORMAT)
							.registerTypeAdapter(LocalTime.class, new GsonLocalTimeTypeAdapter())
							.registerTypeAdapter(Date.class, new GsonDateTypeAdapter())
							.registerTypeAdapter(LocalDate.class, new GsonLocalDateTypeAdapter())
							.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeTypeAdapter())
							.registerTypeAdapter(ZonedDateTime.class, new GsonZonedDateTimeTypeAdapter())
							.registerTypeAdapter(ByteBuffer.class, new GsonByteBufferTypeAdapter())
							.registerTypeAdapter(byte[].class, new GsonByteArrayTypeAdapter())
							.create();
				}
			}
		}
		return gson;
	}

	public static boolean isValidJson(String content) {
		Object parsedValue = null;
		try {
			parsedValue = getGson().fromJson(content, JsonObject.class);
		} catch (Exception e) {
			try {
				parsedValue = getGson().fromJson(content, new TypeToken<List<JsonObject>>(){}.getType());
			} catch (Exception e2) {
				try {
					parsedValue = getGson().fromJson(content, new TypeToken<List<JsonPrimitive>>(){}.getType());
				} catch (Exception e3) {
					return false;
				}
			}
		}
		return parsedValue != null;
	}
	
	public static String toJsonGZIP(Object value) throws Exception {
		String jsonStr = getGson().toJson(value);
		return compress(jsonStr);
	}
	
	public static <T> T fromJsonGZIP(String json, Class<T> classOfT) throws Exception {
		String decompressedJson = decompress(json);
		return getGson().fromJson(decompressedJson, classOfT);
	}
	
	public static <T> T fromJsonGZIP(String json, Type typeOfT) throws Exception {
		String decompressedJson = decompress(json);
		return getGson().fromJson(decompressedJson, typeOfT);
	}

	private static String compress(String str) throws Exception {
		if (str == null || str.length() == 0) {
			return "";
		}
		ByteArrayOutputStream obj = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(obj);
		gzip.write(str.getBytes(StandardCharsets.ISO_8859_1));
		gzip.close();
		return obj.toString(StandardCharsets.ISO_8859_1.name());
	}

	private static String decompress(String json) throws Exception {
		byte[] bytes = json.getBytes(StandardCharsets.ISO_8859_1);
		if (bytes == null || bytes.length == 0) {
			return "{}";
		}
		GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis, StandardCharsets.ISO_8859_1));
		String outStr = "";
		String line;
		while ((line = bf.readLine()) != null) {
			outStr += line;
		}
		return outStr;
	}
}
