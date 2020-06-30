# dadjoke-java-backend
by Jeffrey Whitaker (https://github.com/jeffreywhitaker)

 2) swagger documentation: http://jwhit-dadjokes.herokuapp.com/swagger-ui.html
 3) -- Primary Endpoints Below --
 
## Setup
Make a configuration using template "SpringBoot" with MainClass com.jwhit.dadjokes.DadJokesApplication

Set environment variables
* OAUTHCLIENTID
* OAUTHCLIENTSECRET
* MYDBHOST
* MYDBPASSWORD
* MYDBUSER

Starts on port 2019 by default http://localhost:2019/swagger-ui.html

## [POST] registration endpoint
https://jwhit-dadjokes.herokuapp.com/createnewuser

example:
```
{
    "username" : "Mojo",
    "password" : "corgie",
    "primaryemail" : "home@local.house"
}
```

returns:
```
{
     "access_token": "87014332-1dfe-473e-8d2d-bd81e29a42c9",
     "token_type": "bearer",
     "expires_in": 3599,
     "scope": "read trust write"
}
```

## [POST] login endpoint
https://jwhit-dadjokes.herokuapp.com/login
* (The testuser below has built in seed data.)

```
example:
{
     "username" : "testuser",
     "password" : "password"
}
```

```
returns:
{
     "access_token": "c238e4ad-f76a-44a6-aebf-7b7b6e9dc2de",
     "token_type": "bearer",
     "expires_in": 3599,
     "scope": "read trust write"
}
```

## [GET] all PUBLIC dadjokes
https://jwhit-dadjokes.herokuapp.com/dadjokes/public?size=1000&page=0&sort=dadjokeid,desc
## [GET] all PRIVATE dadjokes
https://jwhit-dadjokes.herokuapp.com/dadjokes/private?size=1000&page=0&sort=dadjokeid,desc
* (endpoint is pageable, sortable, and sizeable by using the query params in the URL)

```
no body required; returns an array of dad jokes with this format:
(it's a lot of info, only take what you need)

{
        "dadjokeid": 18,
        "dadjokequestion": "I'm reading a book about anti-gravity",
        "dadjokeanswer": "It's impossible to put down!",
        "isprivate": false,
        "user": {
            "userid": 14,
            "username": "jeff99",
            "primaryemail": "jeff@email.com",
            "userroles": [
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                }
            ],
            "useremails": []
        }
}
```

## [POST] new dadjoke
https://jwhit-dadjokes.herokuapp.com/dadjokes/add
* (uses the token sent in axiosWithAuth to automatically set user)

```
example:
{
        "dadjokequestion": "Dad joke setup",
        "dadjokeanswer": "Dad joke punchline",
        "isprivate": false
}
```


## [PUT] update dad joke
https://jwhit-dadjokes.herokuapp.com/dadjokes/7
* (where 7 is the dadJokeId)

```
example:
{
    "dadjokequestion": "TEST UPDATE",
    "dadjokeanswer": "string",
    "isprivate": false
}
```

## [DELETE] dad joke
https://jwhit-dadjokes.herokuapp.com/dadjokes/7
* (where 7 is the dadJokeId)

no body required; returns HttpStatus.OK (200)