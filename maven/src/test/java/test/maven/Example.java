package test.maven;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Example extends TestBase{
	
	@Test
	public void testLogin()
	{
		Assert.assertTrue(true);
	}
	
	@Test
	public void testLogout()
	{
		Assert.assertTrue(false);
	}

}
