package br.com.oktolab.gson;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonPrimitive;

import br.com.oktolab.gson.adapter.GsonDateTypeAdapter;
import br.com.oktolab.gson.adapter.GsonLocalDateTimeTypeAdapter;
import br.com.oktolab.gson.adapter.GsonLocalDateTypeAdapter;
import br.com.oktolab.gson.adapter.GsonZonedDateTimeTypeAdapter;

public class GsonTypeAdapterTest {
	
	@Test
	public void gsonDateTypeAdapter() {
		GsonDateTypeAdapter adapter = new GsonDateTypeAdapter();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Assert.assertEquals("2017-10-01T18:12:00", format.format(adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00-03:00"), null, null)));
		Assert.assertEquals("2017-10-01T18:12:00", format.format(adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00Z"), null, null)));
		Assert.assertEquals("2017-10-01T18:12:00", format.format(adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00"), null, null)));
		Assert.assertEquals("2017-10-01T18:12:00", format.format(adapter.deserialize(new JsonPrimitive("2017-10-01T18:12"), null, null)));
		Assert.assertEquals("2017-10-01T18:10:00", format.format(adapter.deserialize(new JsonPrimitive("2017-10-01T18:1"), null, null)));
		Assert.assertEquals("2017-10-01T18:00:00", format.format(adapter.deserialize(new JsonPrimitive("2017-10-01T18"), null, null)));
		Assert.assertEquals("2017-10-01T00:00:00", format.format(adapter.deserialize(new JsonPrimitive("2017-10-01"), null, null)));
	}
	
	@Test
	public void gsonLocalDateTimeTypeAdapter() {
		GsonLocalDateTimeTypeAdapter adapter = new GsonLocalDateTimeTypeAdapter();
		Assert.assertEquals("2017-10-01T18:12", LocalDateTime.parse("2017-10-01T18:12:00", DateTimeFormatter.ISO_DATE_TIME).toString());
		Assert.assertEquals("2017-10-01T18:12", LocalDateTime.parse("2017-10-01T18:12:00-03:00", DateTimeFormatter.ISO_DATE_TIME).toString());
		Assert.assertEquals("2017-10-01T18:12", LocalDateTime.parse("2017-10-01T18:12:00-03:00", DateTimeFormatter.ISO_DATE_TIME).toString());
		
		Assert.assertEquals("2017-10-01T18:12", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00-03:00"), null, null).toString());
		Assert.assertEquals("2017-10-01T18:12", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00-03:00"), null, null).toString());
		Assert.assertEquals("2017-10-01T18:12", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00Z"), null, null).toString());
		Assert.assertEquals("2017-10-01T18:12", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00"), null, null).toString());
		Assert.assertEquals("2017-10-01T18:12", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12"), null, null).toString());
		Assert.assertEquals("2017-10-01T18:10", adapter.deserialize(new JsonPrimitive("2017-10-01T18:10"), null, null).toString());
		Assert.assertEquals("2017-10-01T00:00", adapter.deserialize(new JsonPrimitive("2017-10-01"), null, null).toString());
	}
	
	@Test
	public void gsonLocalDateTypeAdapter() {
		GsonLocalDateTypeAdapter adapter = new GsonLocalDateTypeAdapter();
		Assert.assertEquals("2017-10-01", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00-03:00"), null, null).toString());
		Assert.assertEquals("2017-10-01", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00Z"), null, null).toString());
		Assert.assertEquals("2017-10-01", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12:00"), null, null).toString());
		Assert.assertEquals("2017-10-01", adapter.deserialize(new JsonPrimitive("2017-10-01T18:12"), null, null).toString());
		Assert.assertEquals("2017-10-01", adapter.deserialize(new JsonPrimitive("2017-10-01T18:1"), null, null).toString());
		Assert.assertEquals("2017-10-01", adapter.deserialize(new JsonPrimitive("2017-10-01T18"), null, null).toString());
		Assert.assertEquals("2017-10-01", adapter.deserialize(new JsonPrimitive("2017-10-01"), null, null).toString());
	}
	
	@Test
	public void testZonedDateTime() {
		GsonZonedDateTimeTypeAdapter adapter = new GsonZonedDateTimeTypeAdapter();
		ZonedDateTime zonedDateTime = adapter.deserialize(new JsonPrimitive("2017-10-26T17:25:00Z"), null, null);
		Assert.assertEquals(17, zonedDateTime.getHour());
	}
	
	@Test
	public void testLocalToZonedDateTime() {
		System.out.println(LocalDateTime.parse("2017-08-14T18:35:00").atZone(ZoneId.of("America/Sao_Paulo")));
//		GsonZonedDateTimeTypeAdapter adapter = new GsonZonedDateTimeTypeAdapter();
//		ZonedDateTime zonedDateTime = adapter.deserialize(new JsonPrimitive("2017-08-14T18:35:00"), null, null);
//		Assert.assertEquals(17, zonedDateTime.getHour());
	}
	
	@Test
	public void todoTestsLocalDateTime() {
		System.out.println(LocalDateTime.parse("16/01 11h30", new DateTimeFormatterBuilder().appendPattern("dd/MM HH'h'mm").parseDefaulting(ChronoField.YEAR, 2017).toFormatter()));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30"));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30Z", DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30z", DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30.000", DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30.000Z", DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30.000-02:00", DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30z", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30.000", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30.000Z", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//		System.out.println(LocalDateTime.parse("2017-12-03T10:15:30.000-02:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		System.out.println(ZonedDateTime.now());
		System.out.println(ZonedDateTime.now().toLocalDateTime());
		
		System.out.println(Date.from(LocalDate.parse("2017-12-03").atStartOfDay(ZoneOffset.UTC).toInstant()));
		
	}
}
