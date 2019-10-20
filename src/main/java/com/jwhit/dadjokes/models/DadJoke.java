package com.jwhit.dadjokes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "dadjokes")
public class DadJoke extends Auditable
{
    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dadjokeid;

    @Column(nullable = false)
    private String dadjokequestion;

    private String dadjokeanswer;

    @Column(nullable = false)
    private boolean isprivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties({"dadjokes", "hibernateLazyInitializer"})
    private User user;

    // constructors
    public DadJoke()
    {
    }

    public DadJoke(String dadjokequestion, String dadjokeanswer, boolean isprivate, User user)
    {
        this.dadjokequestion = dadjokequestion;
        this.dadjokeanswer = dadjokeanswer;
        this.isprivate = isprivate;
        this.user = user;
    }

    // getters and setters
    public long getDadjokeid()
    {
        return dadjokeid;
    }

    public void setDadjokeid(long dadjokeid)
    {
        this.dadjokeid = dadjokeid;
    }

    public String getDadjokequestion()
    {
        return dadjokequestion;
    }

    public void setDadjokequestion(String dadjokequestion)
    {
        this.dadjokequestion = dadjokequestion;
    }

    public String getDadjokeanswer()
    {
        return dadjokeanswer;
    }

    public void setDadjokeanswer(String dadjokeanswer)
    {
        this.dadjokeanswer = dadjokeanswer;
    }

    public boolean isIsprivate()
    {
        return isprivate;
    }

    public void setIsprivate(boolean isprivate)
    {
        this.isprivate = isprivate;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    // methods
}
