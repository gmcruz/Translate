package com.lingoli.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TranslationTest {

	Translation translation;
	
	@Before
	public void setup(){
		translation = new Translation(1, 2, 3, new Date(), new Date(), "Translated Text");
	}
	
	@After
	public void tearDown(){
		translation = null;
	}
	
	@Test
	public void testToString() {		
		assertEquals("1, 2, 3, Translated Text", translation.toString());
	}
	
	@Test
	public void testGetId() {
		assertEquals(0, translation.getId());
	}

	@Test
	public void testGetBaseTextId() {
		assertEquals(1, translation.getBaseId());
	}

	@Test
	public void testGetLanguageId() {
		assertEquals(2, translation.getLanguageId());
	}

	@Test
	public void testGetStarRating() {
		assertEquals(3, translation.getStarRating());
	}

	@Test
	public void testGetTranslationText() {
		assertEquals("Translated Text", translation.getTranslationText());
	}

	@Test
	public void testGetClass() {
		assertEquals(Translation.class, translation.getClass());
	}

}
