package com.jwhit.dadjokes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwhit.dadjokes.models.DadJoke;
import com.jwhit.dadjokes.models.User;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.LessThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DadJokeControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    // test response time is less than 5 seconds
    @Test
    public void getAllPublicDadJokes()
    {
        given().when().get("/dadjokes/public").then().time(lessThan(5000L));
    }

    @Test
    public void getAllPrivateDadJokesByUserId()
    {
        given().when().get("/dadjokes/private").then().time(lessThan(5000L));
    }

    @Test
    public void postNewDadJoke() throws Exception
    {
        DadJoke testDJ = new DadJoke("test dad joke part 1", "test dad joke part 2", true, null);
        User testUser = new User("testuser", "password", "test@email.com", null);
        testDJ.setUser(testUser);

        ObjectMapper mapper = new ObjectMapper();
        String stringTestDJ = mapper.writeValueAsString(testDJ);

        given().contentType("application/json").body(stringTestDJ).when().post("/dadjokes/add").then().statusCode(201);
    }
}
