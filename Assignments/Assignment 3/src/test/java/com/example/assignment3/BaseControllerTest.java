package com.example.assignment3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public abstract class BaseControllerTest {
    protected MockMvc mockMvc;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    protected ResultMatcher jsonContentToBe(Object expectedJsonContent) throws JsonProcessingException {
        return content().json(new ObjectMapper().writeValueAsString(expectedJsonContent), true);
    }

    protected ResultActions performPostWithRequestBody(String path, Object body) throws Exception {
        return mockMvc.perform(post(path)
                .content(asJsonString(body))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performPutWithRequestBody(String path, Object body) throws Exception {
        return mockMvc.perform(put(path)
                .content(asJsonString(body))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
    protected ResultActions performGetWithRequestBody(String path, Object body, Object body2, Object body3) throws Exception {
        return mockMvc.perform(get(path)
                .content(asJsonString(body))
                .content(asJsonString(body2))
                .content(asJsonString(body3))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performPostWithRequestBodyPathVariable(String path, String variable, Object body) throws Exception{
        return mockMvc.perform(post(path, variable)
                      .content(asJsonString(body))
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performPatchWithRequestBodyPathVariable(String path,String variable, Object body) throws Exception {
        return mockMvc.perform(patch(path,variable)
                .content(asJsonString(body))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performDeleteWithPathVariable(String path, String variable) throws Exception{
        return mockMvc.perform(delete(path,variable)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

    }
    protected String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
