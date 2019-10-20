package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.DadJoke;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DadJokeRepository extends PagingAndSortingRepository<DadJoke, Long>
{
}
