package com.jwhit.dadjokes.controllers;

import com.jwhit.dadjokes.models.DadJoke;
import com.jwhit.dadjokes.models.User;
import com.jwhit.dadjokes.services.DadJokeService;
import com.jwhit.dadjokes.services.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dadjokes")
public class DadJokeController
{
    @Autowired
    private DadJokeService dadJokeService;

    @Autowired
    private UserService userService;

    // GET all public dad jokes
    // http://localhost:2019/dadjokes/public
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page, 0 to N"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                          value = "Sorting criteria in format: property(,asc|desc)." + "Default sort is ascending." + "Multiple sort criteria are supported")})
    @GetMapping(value = "/public", produces = {"application/json"})
    public ResponseEntity<?> getAllPublicDadJokes(@PageableDefault(page = 0, size = 3)Pageable pageable)
    {
        List<DadJoke> rtnList = dadJokeService.findPublicDadJokes(pageable);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    // GET all private dad jokes
    // http://localhost:2019/dadjokes/private
    @ApiImplicitParams({
          @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page, 0 to N"),
          @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
          @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                            value = "Sorting criteria in format: property(,asc|desc)." + "Default sort is ascending." + "Multiple sort criteria are supported")})
    @GetMapping(value = "/private", produces = {"application/json"})
    public ResponseEntity<?> getAllPrivateDadJokes(@PageableDefault(page = 0, size = 3)Pageable pageable, Authentication authentication)
    {
        User thisUser = userService.findByName(authentication.getName());
        List<DadJoke> rtnList = dadJokeService.findPrivateDadJokesByUserId(thisUser.getUserid(), pageable);

        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    // POST new dad joke
    // http://localhost:2019/dadjokes/add
    @PostMapping(value = "/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> postNewDadJoke(@Valid @RequestBody DadJoke newDadJoke, Authentication authentication)
    {
        User thisUser = userService.findByName(authentication.getName());
        newDadJoke.setUser(thisUser);
        dadJokeService.save(newDadJoke);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // DEL private dad joke
    // http://localhost:2019/dadjokes/{dadJokeId}
    @DeleteMapping("/{dadJokeId}")
    public ResponseEntity<?> deleteDadJokeById(@PathVariable long dadJokeId, Authentication authentication)
    {
        User thisUser = userService.findByName(authentication.getName());
        dadJokeService.delete(dadJokeId, thisUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PUT update dad joke
    // http://localhost:2019/dadjokes/{dadJokeId}
    @PutMapping(value = "/{dadJokeId}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateDadJoke(@Valid @RequestBody DadJoke dadJoke, @PathVariable long dadJokeId, Authentication authentication)
    {
        User thisUser = userService.findByName(authentication.getName());
        dadJokeService.update(dadJoke, dadJokeId, thisUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
