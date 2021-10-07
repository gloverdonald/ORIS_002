package ru.itis.Listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.Repositories.FileRepository;
import ru.itis.Repositories.FileRepositoryImpl;
import ru.itis.Repositories.SignInDB;
import ru.itis.Repositories.SignUpDB;
import ru.itis.Services.FileService;
import ru.itis.Services.FileServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "mstpas123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hw2";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);
        FileRepository filesRepository = new FileRepositoryImpl(dataSource);
        FileService filesService = new FileServiceImpl(filesRepository);
        SignUpDB signUpDB = new SignUpDB(dataSource);
        SignInDB signInDB = new SignInDB(dataSource);

        servletContext.setAttribute("signUp", signUpDB);
        servletContext.setAttribute("signIn",signInDB);
        servletContext.setAttribute("filesRepository", filesRepository);
        servletContext.setAttribute("filesService", filesService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
