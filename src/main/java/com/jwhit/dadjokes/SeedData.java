package com.jwhit.dadjokes;

import com.jwhit.dadjokes.models.*;
import com.jwhit.dadjokes.services.DadJokeService;
import com.jwhit.dadjokes.services.RoleService;
import com.jwhit.dadjokes.services.UserService;
import com.jwhit.dadjokes.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    DadJokeService dadJokeService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                                 r1));
        admins.add(new UserRoles(new User(),
                                 r2));
        admins.add(new UserRoles(new User(),
                                 r3));
        User u1 = new User("admin",
                           "password",
                           "admin@lambdaschool.local",
                           admins);
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@mymail.local"));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(),
                                r3));
        datas.add(new UserRoles(new User(),
                                r2));
        User u2 = new User("cinnamon",
                           "1234567",
                           "cinnamon@lambdaschool.local",
                           datas);
        u2.getUseremails()
          .add(new Useremail(u2,
                             "cinnamon@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2,
                             "hops@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2,
                             "bunny@email.local"));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                                r2));
        User u3 = new User("barnbarn",
                           "ILuvM4th!",
                           "barnbarn@lambdaschool.local",
                           users);
        u3.getUseremails()
          .add(new Useremail(u3,
                             "barnbarn@email.local"));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u4 = new User("testuser", "password", "test@email.com", users);

        User u4Saved = userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(),
                r2));
        User u5 = new User("jeff99", "password", "jeff@email.com", users);

        User u5Saved = userService.save(u5);

        // dad joke seeds

        DadJoke dj1 = new DadJoke("My wife is really mad at the fact that I have no sense of direction.", "So I packed up my stuff and right!", false, u4Saved);
        DadJoke dj2 = new DadJoke("Did you know the first French fries weren't actually cooked in France?", "They were cooked in Greece!", false, u4Saved);
        DadJoke dj3 = new DadJoke("How do you make holy water?", "You boil the hell out of it!", false, u4Saved);

        DadJoke dj4 = new DadJoke("What do you call a fake noodle?", "An Impasta!", true, u4Saved);
        DadJoke dj5 = new DadJoke("Did you hear about the restaurant on the moon?", "Great food, no atmosphere!", true, u4Saved);
        DadJoke dj6 = new DadJoke("How many apples grow on a tree?", "All of them!", true, u4Saved);

        DadJoke dj7 = new DadJoke("I'm reading a book about anti-gravity", "It's impossible to put down!", false, u5Saved);
        DadJoke dj8 = new DadJoke("Why did the scarecrow win an award?", "Because he was outstanding in his field!", true, u5Saved);

        dadJokeService.save(dj1);
        dadJokeService.save(dj2);
        dadJokeService.save(dj3);
        dadJokeService.save(dj4);
        dadJokeService.save(dj5);
        dadJokeService.save(dj6);
        dadJokeService.save(dj7);
        dadJokeService.save(dj8);


        //
//        // using JavaFaker create a bunch of regular users
//        // https://www.baeldung.com/java-faker
//        // https://www.baeldung.com/regular-expressions-java
//
//        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                                                                    new RandomService());
//        Faker nameFaker = new Faker(new Locale("en-US"));
//
//        for (int i = 0; i < 100; i++)
//        {
//            new User();
//            User fakeUser;
//
//            users = new ArrayList<>();
//            users.add(new UserRoles(new User(),
//                                    r2));
//            fakeUser = new User(nameFaker.name()
//                                         .username(),
//                                "password",
//                                nameFaker.internet()
//                                         .emailAddress(),
//                                users);
//            fakeUser.getUseremails()
//                    .add(new Useremail(fakeUser,
//                                       fakeValuesService.bothify("????##@gmail.com")));
//            userService.save(fakeUser);
//        }
    }
}