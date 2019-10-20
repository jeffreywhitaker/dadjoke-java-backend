package com.jwhit.dadjokes.services;

import com.jwhit.dadjokes.models.DadJoke;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DadJokeService
{
    List<DadJoke> findAll(Pageable pageable);

    DadJoke save(DadJoke dadJoke);

    void delete(long id);
}
