package com.jwhit.dadjokes.services;

import com.jwhit.dadjokes.exceptions.ResourceFoundException;
import com.jwhit.dadjokes.exceptions.ResourceNotFoundException;
import com.jwhit.dadjokes.models.DadJoke;
import com.jwhit.dadjokes.models.User;
import com.jwhit.dadjokes.repository.DadJokeRepository;
import com.jwhit.dadjokes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "dadJokeService")
public class DadJokeServiceImpl implements DadJokeService
{
    // constructors
    private final DadJokeRepository dadjokerepos;

    public DadJokeServiceImpl(DadJokeRepository dadjokerepos)
    {
        this.dadjokerepos = dadjokerepos;
    }

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
    public void delete(long dadJokeId, User thisUser)
    {
        DadJoke currentDadJoke = dadjokerepos.findById(dadJokeId)
                .orElseThrow(() -> new ResourceNotFoundException("DadJoke ID " + dadJokeId + " not found!"));

        if (thisUser.getUserid() != currentDadJoke.getUser().getUserid())
        {
            throw new ResourceFoundException("User Id does not match this DadJoke. Please only delete your own DadJokes.");
        }
        else
        {
            dadjokerepos.deleteById(dadJokeId);
        }
    }

    @Transactional
    @Override
    public DadJoke update(DadJoke updatedDadJoke, long dadJokeId, User thisUser)
    {
        DadJoke currentDadJoke = dadjokerepos.findById(dadJokeId)
                .orElseThrow(() -> new ResourceNotFoundException("DadJoke ID " + dadJokeId + " not found!"));

        if (thisUser.getUserid() != currentDadJoke.getUser().getUserid())
        {
            throw new ResourceFoundException("User Id does not match. Please update your own DadJoke.");
        }
        else
            {
                if (updatedDadJoke.getDadjokequestion() != null)
                {
                    currentDadJoke.setDadjokequestion(updatedDadJoke.getDadjokequestion());
                }

                if (updatedDadJoke.getDadjokeanswer() != null)
                {
                    currentDadJoke.setDadjokeanswer(updatedDadJoke.getDadjokeanswer());
                }

                currentDadJoke.setIsprivate(updatedDadJoke.isIsprivate());

                return dadjokerepos.save(currentDadJoke);
            }
    }

    @Override
    public List<DadJoke> findPrivateDadJokesByUserId(long userId, Pageable pageable)
    {
        return dadjokerepos.findDadJokesByIsprivateAndUser_Userid(true, userId, pageable);
    }

    @Override
    public List<DadJoke> findPublicDadJokes(Pageable pageable)
    {
        return dadjokerepos.findDadJokesByIsprivate(false, pageable);
    }
}
