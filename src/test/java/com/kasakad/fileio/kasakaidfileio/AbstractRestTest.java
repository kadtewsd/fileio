package com.kasakad.fileio.kasakaidfileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class AbstractRestTest extends AbstractBaseTest {

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mvc;

    public void setUp() {
        super.setUp();
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
//                .apply(springSecurity()) // これを apply しないと csrf を設定しても 404 になってしまう。
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMapper);
        MockitoAnnotations.initMocks(this);
    }
}
