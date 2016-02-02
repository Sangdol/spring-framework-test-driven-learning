package com.sangdol.springSimple;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class ValidationControllerTest {

    private ValidationController validationController = new ValidationController();
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(validationController).build();

    @Test
    public void testNoBindingResult() throws Exception {
        mockMvc.perform(
                post("/validation")
                        .param("name", "SD")
                        .param("age", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("SD 10"));

        // No @Valid, no validation.
        mockMvc.perform(
                post("/validation"))
                .andExpect(status().isOk())
                .andExpect(content().string("null 0"));
    }

    @Test
    public void testValidNoBindingResult() throws Exception {
        mockMvc.perform(
                post("/validation/valid"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(
                post("/validation/valid").param("age", "160"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(
                post("/validation/valid").param("email", "abc"))
                .andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testBindingResult() throws Exception {
        mockMvc.perform(
                post("/validation/binding-result"))
                .andExpect(status().isOk())
                .andExpect(content().string("true 1"));
    }
}