package br.com.oktolab.gson;

import org.junit.Assert;
import org.junit.Test;

public class GsonTest {

	@Test
	public void idValidJsonTest() {
		Assert.assertTrue(GSON.isValidJson("{}"));
		Assert.assertTrue(GSON.isValidJson("[]"));
		Assert.assertTrue(GSON.isValidJson("{\"foo\":50}"));
		Assert.assertTrue(GSON.isValidJson("[{\"foo\":50}]"));
		Assert.assertFalse(GSON.isValidJson("{error}"));
		Assert.assertFalse(GSON.isValidJson("[error]"));
		Assert.assertFalse(GSON.isValidJson("Internal Server Error"));
	}
}
