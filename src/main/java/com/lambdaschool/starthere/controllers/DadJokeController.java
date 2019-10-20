package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.DadJoke;
import com.lambdaschool.starthere.services.DadJokeService;
import com.lambdaschool.starthere.services.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // GET all dad jokes
    // http://localhost:2019/dadjokes/all
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page, 0 to N"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                          value = "Sorting criteria in format: property(,asc|desc)." + "Default sort is ascending." + "Multiple sort criteria are supported")})
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllDadJokes(@PageableDefault(page = 0, size = 3)Pageable pageable)
    {
        List<DadJoke> rtnList = dadJokeService.findAll(pageable);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    // POST new dad joke
    // http://localhost:2019/dadjokes/add
    @PostMapping(value = "/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> postNewDadJoke(@Valid @RequestBody DadJoke newDadJoke)
    {
        dadJokeService.save(newDadJoke);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // DEL private dad joke
    // http://localhost:2019/dadjokes/{dadjokeid}
    @DeleteMapping("/{dadjokeid}")
    public ResponseEntity<?> deleteDadJokeById(@PathVariable long dadjokeid)
    {
        dadJokeService.delete(dadjokeid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
