package com.lingoli.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BaseTest {
			
	Base base;		
	
	@Before
	public void setup(){
		Calendar cal = Calendar.getInstance();		    		
		cal.set(cal.YEAR, 1970);
		cal.set(cal.MONTH, cal.JANUARY);
		cal.set(cal.DATE, 1);		    
		cal.set(cal.MILLISECOND, 521);
		//Keep in mind that you will see if the object was created successfully by the string "1970"
		base = new Base(1, "This is the Base Text.", 2, cal.getTime(), cal.getTime(), new Date());			
	}
	
	@After
	public void tearDown(){
		base = null;
	}

	@Test
	public void testGetId() {
		assertEquals(0, base.getId());
	}

	@Test
	public void testGetContentId() {
		assertEquals(1, base.getContentId());
	}

	@Test
	public void testGetLanguageId() {
		assertEquals(2, base.getLanguageId());
	}

	@Test
	public void testGetTimeMiliStart() {
		assertThat(base.getTimeMiliStart().toString(), containsString("1970")); 		
	}

	@Test
	public void testGetTimeMiliEnd() {
		assertThat(base.getTimeMiliEnd().toString(), containsString("1970")); 		
	}

}
