package com.jwhit.dadjokes.services;

import com.jwhit.dadjokes.models.DadJoke;
import com.jwhit.dadjokes.models.User;
import com.jwhit.dadjokes.repository.DadJokeRepository;
import com.jwhit.dadjokes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "dadJokeService")
public class DadJokeServiceImpl implements DadJokeService
{
    // constructors
    @Autowired
    private DadJokeRepository dadjokerepos;

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private UserService userService;

    // override methods
    @Override
    public List<DadJoke> findAll(Pageable pageable)
    {
        ArrayList<DadJoke> djList = new ArrayList<>();
        dadjokerepos.findAll(pageable).iterator().forEachRemaining(djList::add);
        return djList;
    }

    @Transactional
    @Override
    public DadJoke save(DadJoke dadJoke)
    {
        return dadjokerepos.save(dadJoke);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        dadjokerepos.deleteById(id);
    }
}
