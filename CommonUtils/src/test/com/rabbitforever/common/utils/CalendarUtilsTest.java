package com.rabbitforever.common.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rabbitforever.common.factories.UtilsFactory;

public class CalendarUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseDateStringToMinimumOfTheDate() {
		try {
			UtilsFactory utilsFactory = UtilsFactory.getInstance();
			DateUtils dateUtils = utilsFactory.getInstanceOfDateUtils();
			dateUtils.parseDateStringToDate("20180808");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
