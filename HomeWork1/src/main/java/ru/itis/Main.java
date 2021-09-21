package ru.itis;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Optional;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "mstpas123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hw1";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        CrudRepository<Driver, Long> driversDao = new DriverRepository(dataSource);
        Optional<Driver> optionalDriver = driversDao.findById(3L);

        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            System.out.println("Driver found! Name: " + driver.getFirstName());
        } else {
            System.out.println("No drivers found!");
        }


       Driver driver = Driver.builder()
                .firstName("Ivan")
                .lastName("Antonov")
                .age(27)
                .build();
        Driver savedDriver = driversDao.save(driver);
        System.out.println("Created driver with id = " + savedDriver.getId());



        Driver driver1 = Driver.builder()
                .firstName("Ivan1")
                .lastName("Antonoff")
                .age(30)
                .build();
        Optional<Driver> updatedDriverOptional = driversDao.update(1L, driver1);
        if (updatedDriverOptional.isPresent()) {
            Driver updatedDriver = updatedDriverOptional.get();
            System.out.println("Updated driver with id = " + updatedDriver.getId());
        }

        Long id = 2L;
        driversDao.delete(id);
        System.out.println("Delete driver with id = " + id);
    }
}
