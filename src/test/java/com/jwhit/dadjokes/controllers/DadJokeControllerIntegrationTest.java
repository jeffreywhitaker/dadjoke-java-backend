package com.jwhit.dadjokes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwhit.dadjokes.DadJokesApplication;
import com.jwhit.dadjokes.models.DadJoke;
import com.jwhit.dadjokes.models.Role;
import com.jwhit.dadjokes.models.User;
import com.jwhit.dadjokes.models.UserRoles;
import com.jwhit.dadjokes.repository.DadJokeRepository;
import com.jwhit.dadjokes.repository.UserRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DadJokesApplication.class)
@AutoConfigureMockMvc
public class DadJokeControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DadJokeRepository dadJokeRepository;

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

    // test CRUD operations
    @Test
    @WithMockUser(username = "testuser", password = "", roles = "")
    public void postNewDadJoke() throws Exception
    {
        DadJoke testDJ = new DadJoke("test dad joke part 1", "test dad joke part 2", true, null);

        ObjectMapper mapper = new ObjectMapper();
        String stringTestDJ = mapper.writeValueAsString(testDJ);

        given().contentType("application/json").body(stringTestDJ).when().post("/dadjokes/add").then().statusCode(201);
    }

    @Test
    @WithMockUser(username = "testuser", password = "", roles = "")
    public void updateDadJokeById() throws Exception
    {
        DadJoke joke = dadJokeRepository.findAll().iterator().next();
        long jokeId = joke.getDadjokeid();
        DadJoke testDJ = new DadJoke("test dad joke part 1", "test dad joke part 2", true, null);
        testDJ.setDadjokeid(jokeId);

        ObjectMapper mapper = new ObjectMapper();
        String stringTestDJ = mapper.writeValueAsString(testDJ);

        given().contentType("application/json").body(stringTestDJ).when().put("/dadjokes/" + jokeId).then().statusCode(200);
    }

    @Test
    @WithMockUser(username = "testuser", password = "", roles = "")
    public void deleteDadJokeById() throws Exception
    {
        DadJoke joke = dadJokeRepository.findAll().iterator().next();
        long jokeId = joke.getDadjokeid();

        given().when().delete("dadjokes/" + jokeId).then().statusCode(200);
    }
}
