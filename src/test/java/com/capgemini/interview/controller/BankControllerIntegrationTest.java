package com.capgemini.interview.controller;

import com.capgemini.interview.advice.ApiResponseAdvice;
import com.capgemini.interview.exceptions.result.Result;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BankControllerIntegrationTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        BankController bankController = new BankController();
        mockMvc = MockMvcBuilders.standaloneSetup(bankController).setControllerAdvice(new ApiResponseAdvice()).build();
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post("/bank/create?accountName=test")
            .accept(MediaType.ALL))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andExpect(jsonPath("$.responseCode", is(HttpStatus.OK.value())))
            .andExpect(jsonPath("$.responseMessage", is("Account created")))
            .andExpect(jsonPath("$.responseBody", is(notNullValue())))
            .andExpect(jsonPath("$.responseBody.results", is(notNullValue())))
            .andExpect(jsonPath("$.responseBody.successBody", is(notNullValue())))
            .andExpect(jsonPath("$.responseBody.ok", is(true)))
            .andExpect(jsonPath("$.responseBody.results.length()", is(1)))
            .andExpect(jsonPath("$.responseBody.results.results[0].code", is(Result.OK.getCode())))
            .andExpect(jsonPath("$.responseBody.results.results[0].message", is(Result.OK.getMessage())))
            .andExpect(jsonPath("$.responseBody.successBody.accountId", is(1)))
            .andExpect(jsonPath("$.responseBody.successBody.accountName", is("test")))
            .andExpect(jsonPath("$.responseBody.successBody.balance", is(0.0)))
            .andExpect(jsonPath("$.responseBody.successBody.amount", is(0.0)));
    }
}