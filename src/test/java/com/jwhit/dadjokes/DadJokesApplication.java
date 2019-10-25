package com.jwhit.dadjokes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableWebMvc
//// @EnableJpaAuditing

@SpringBootApplication
public class DadJokesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DadJokesApplication.class, args);
    }
}
//public class DadJokesTestApplication
//{
//    private static final Logger logger = LoggerFactory.getLogger(DadJokesTestApplication.class);
//    private static boolean stop = false;
//
//    @Autowired
//    private static Environment env;
//
//    private static void checkEnvironmentVariable(String envvar)
//    {
//        if (System.getenv(envvar) == null)
//        {
//            logger.error("Environment Variable " + envvar + " missing");
//            stop = true;
//        }
//    }
//
//    public static void main(String[] args)
//    {
//        checkEnvironmentVariable("OAUTHCLIENTID");
//        checkEnvironmentVariable("OAUTHCLIENTSECRET");
//
//        if (!stop)
//        {
//            ApplicationContext ctx = SpringApplication.run(DadJokesTestApplication.class, args);
//
//            DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
//            dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
//        }
//    }
//
//}

