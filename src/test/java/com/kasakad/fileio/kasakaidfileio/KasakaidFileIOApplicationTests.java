package com.kasakad.fileio.kasakaidfileio;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class KasakaidFileIOApplicationTests extends AbstractBaseTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Rule
	// The @Rule 'myResource' must be public.
	public MyResource myResource;

	@Test
	public void contextLoads() {
	    assertThat(applicationContext, notNullValue());
		assertThat(myResource, notNullValue());
	}

}
