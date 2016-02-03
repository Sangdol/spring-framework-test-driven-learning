package com.sangdol.springSimple;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/integration-testing.html#spring-mvc-test-framework
 *
 * TODO Create integration test for learning configurations
 */
public class HomeControllerTest {

    private HomeController homeController = new HomeController();
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

    /**
     * http://stackoverflow.com/questions/18336277/how-to-check-string-in-response-body-with-mockmvc
     */
    @Test
    public void testMockMvc() throws Exception {
        String contentType = "text/plain;charset=ISO-8859-1";
        String contentString = "Hello sangdol!";
        MvcResult result = this.mockMvc.perform(get("/home").param("name", "sangdol"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string(contentString))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentType(), is(contentType));
        assertThat(response.getContentAsString(), is(contentString));
    }

    /**
     * To convert object to JSON, need to have Jackson Mapper in the classpath
     * @see WebMvcConfigurationSupport#addDefaultHttpMessageConverters
     *
     * jsonPath() need JsonPath library - https://github.com/jayway/JsonPath
     *
     * Normal case (not in unit test)
     * http://stackoverflow.com/questions/3616359/who-sets-response-content-type-in-spring-mvc-responsebody
     *
     * Need to answer this..
     * http://stackoverflow.com/questions/22183178/java-lang-assertionerror-content-type-not-set-while-junit-spring-mvc-controller
     */
    @Test
    public void testMockMvc2() throws Exception {
        this.mockMvc.perform(get("/home/map").param("name", "sangdol"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"Hello\":\"sangdol\"}"))
                .andExpect(jsonPath("$.Hello").value("sangdol"))
                .andReturn();
    }

    /**
     * http://stackoverflow.com/a/31700674/524588
     */
    @Test
    public void testCaseSensitivity() throws Exception {
        this.mockMvc.perform(get("/home/MAP").param("name", "sangdol"))
                .andExpect(status().isNotFound());
    }
}