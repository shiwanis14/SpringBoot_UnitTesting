package com.ownprojects.unittesting.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.ownprojects.unittesting.Item;
import com.ownprojects.unittesting.service.BusinessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BusinessService businessService;


  @Test
  public void getItemTest() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
        .accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().json("{\n"
            + "    \"id\": 1,\n"
            + "    \"name\": \"Ball\",\n"
            + "    \"price\": 5,\n"
            + "    \"quantity\": 10\n"
            + "}"))
        .andReturn();
    DocumentContext documentContext = JsonPath.parse("{\n"
        + "    \"id\": 1,\n"
        + "    \"name\": \"Ball\",\n"
        + "    \"price\": 5,\n"
        + "    \"quantity\": 10\n"
        + "}");
    int count = documentContext.read("$.length()");
    Assertions.assertEquals(4,count);
    System.out.println(documentContext.read("$").toString());
    System.out.println(documentContext.read("$.id").toString());
  }


  @Test
  public void getItemTestWithJSONAssert() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
        .accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().json("{ id: 1, name: Ball, price: 5}"))
        .andReturn();
  }

  @Test
  public void getItemFromServiceTest() throws Exception {
    Mockito.when(businessService.getItem()).thenReturn
        (new Item(2, "Item2", 10, 1));
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/service")
        .accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().json("{ id: 2, name: Item2, price: 10}"))
        .andReturn();
  }

  @Test
  public void getItemFromServiceANdDBTest() throws Exception {
    Mockito.when(businessService.getItemFromDatabase()).thenReturn(Arrays.asList(
        (new Item(2, "Item2", 10, 1)),
        (new Item(3, "Item3", 10, 2)),
        (new Item(4, "Item4", 10, 3))));
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/serviceAndDB")
        .accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(content().json(
            "[{ id: 2, name: Item2, price: 10},{ id: 3, name: Item3, price: 10},{ id: 4, name: Item4, price: 10}]"))
        .andReturn();

  }


}
