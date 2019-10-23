package com.jwhit.dadjokes.repository;

import com.jwhit.dadjokes.models.DadJoke;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface DadJokeRepository extends PagingAndSortingRepository<DadJoke, Long>
{
    @Override
    Optional<DadJoke> findById(Long aLong);

    List<DadJoke> findDadJokesByIsprivateAndUser_Userid(boolean Isprivate, long Userid, Pageable pageable);

    List<DadJoke> findDadJokesByIsprivate(boolean Isprivate, Pageable pageable);
}
