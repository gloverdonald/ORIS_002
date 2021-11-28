package ru.itis.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.dao.FollowRepository;
import ru.itis.dao.PostsRepository;
import ru.itis.dao.impl.FollowRepositoryImpl;
import ru.itis.dao.impl.PostsRepositoryImpl;
import ru.itis.services.*;
import ru.itis.services.impl.*;
import ru.itis.dao.FilesRepository;
import ru.itis.dao.UsersRepository;
import ru.itis.dao.impl.FilesRepositoryImpl;
import ru.itis.dao.impl.UsersRepositoryImpl;

import ru.itis.services.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomContextListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "mstpas123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/LiveJournal";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        FilesService filesService = new FilesServiceImpl(filesRepository, usersRepository);
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        Validator validator = new ValidatorImpl(usersRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder, validator);
        PostsRepository postsRepository = new PostsRepositoryImpl(dataSource);
        PostsService postsService = new PostsServiceImpl(postsRepository);
        ObjectMapper objectMapper = new ObjectMapper();

        UserSearchService userSearchService = new UserSearchServiceImpl(usersRepository);

        FollowRepository followRepository = new FollowRepositoryImpl(dataSource);
        FollowService followService = new FollowServiceImpl(followRepository);

        servletContext.setAttribute("filesService", filesService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("postsService", postsService);
        servletContext.setAttribute("objectMapper", objectMapper);

        servletContext.setAttribute("userSearchService", userSearchService);
        servletContext.setAttribute("followService", followService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
