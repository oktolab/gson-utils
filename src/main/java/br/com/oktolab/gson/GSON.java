package br.com.oktolab.gson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import br.com.oktolab.gson.adapter.GsonDateTypeAdapter;
import br.com.oktolab.gson.adapter.GsonLocalDateTimeTypeAdapter;
import br.com.oktolab.gson.adapter.GsonLocalDateTypeAdapter;
import br.com.oktolab.gson.adapter.GsonZonedDateTimeTypeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSON {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	private static Gson gson;

	public static Gson getGson() {
		if (gson == null) {
			synchronized (GSON.class) {
				if (gson == null) {
					gson = new GsonBuilder()
							.setDateFormat(DATE_FORMAT)
							.registerTypeAdapter(Date.class, new GsonDateTypeAdapter())
							.registerTypeAdapter(LocalDate.class, new GsonLocalDateTypeAdapter())
							.registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeTypeAdapter())
							.registerTypeAdapter(ZonedDateTime.class, new GsonZonedDateTimeTypeAdapter())
							.create();
				}
			}
		}
		return gson;
	}
}
