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
		Assert.assertTrue(GSON.isValidJson("[ok]"));
		Assert.assertTrue(GSON.isValidJson("[ok, multiple, values]"));
		Assert.assertTrue(GSON.isValidJson("[1, 2, 3]"));
		Assert.assertTrue(GSON.isValidJson("[1,2,3]"));
		Assert.assertFalse(GSON.isValidJson("{error}"));
		Assert.assertFalse(GSON.isValidJson("Internal Server Error"));
	}
	
	@Test
	public void testGZIP() throws Exception {
		Foo foo = new Foo();
		foo.name = "Arthur Rocha";
		foo.number = 123456789;
		
		String jsonGZIP = GSON.toJsonGZIP(foo);
		Foo fromJsonGZIP = GSON.fromJsonGZIP(jsonGZIP, Foo.class);
		
		Assert.assertEquals(foo, fromJsonGZIP);
	}
	
	class Foo {
		String name;
		Integer number;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((number == null) ? 0 : number.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Foo other = (Foo) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (number == null) {
				if (other.number != null)
					return false;
			} else if (!number.equals(other.number))
				return false;
			return true;
		}
		
		
	}
}
