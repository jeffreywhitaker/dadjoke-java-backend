package com.jwhit.dadjokes.repository;

import com.jwhit.dadjokes.models.DadJoke;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DadJokeRepository extends PagingAndSortingRepository<DadJoke, Long>
{
}
