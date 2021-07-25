package com.ownprojects.unittesting;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void contextLoads() throws JSONException {
    String response = this.restTemplate.getForObject("/serviceAndDB", String.class);
    JSONAssert.assertEquals(
        "[{ id: 10001},{id: 10002},{id:10003}]",
        response, false);
  }
}
