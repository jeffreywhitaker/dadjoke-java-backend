package com.jwhit.dadjokes.repository;

import com.jwhit.dadjokes.models.DadJoke;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DadJokeRepository extends PagingAndSortingRepository<DadJoke, Long>
{
    @Override
    Optional<DadJoke> findById(Long aLong);
}
