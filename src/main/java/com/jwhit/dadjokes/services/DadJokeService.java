package com.jwhit.dadjokes.services;

import com.jwhit.dadjokes.models.DadJoke;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DadJokeService
{
    List<DadJoke> findAll(Pageable pageable);

    DadJoke save(DadJoke dadJoke);

    void delete(long id);
}
