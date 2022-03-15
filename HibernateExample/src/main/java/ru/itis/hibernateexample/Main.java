package ru.itis.hibernateexample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.itis.hibernateexample.config.HibernateConfig;
import ru.itis.hibernateexample.model.Car;
import ru.itis.hibernateexample.model.Driver;
import ru.itis.hibernateexample.repository.DriverRepositoryHibernate;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        DriverRepositoryHibernate driverRepositoryHibernate = new DriverRepositoryHibernate();

        Driver driver = Driver.builder().age(1).firstName("FIRST").lastName("SECOND").build();
        Driver driver2 = Driver.builder().age(2).firstName("FIRST").lastName("SECOND").build();
        Driver driver3 = Driver.builder().age(33).firstName("FIRST").lastName("SECOND").build();
        Driver driver4 = Driver.builder().age(4).firstName("FIRST").lastName("SECOND").build();
        Driver driver5 = Driver.builder().age(5).firstName("FIRST").lastName("SECOND").build();

        driverRepositoryHibernate.save(driver);
        driverRepositoryHibernate.save(driver2);
        driverRepositoryHibernate.save(driver3);
        driverRepositoryHibernate.save(driver4);
        driverRepositoryHibernate.save(driver5);


        driverRepositoryHibernate.delete(1L);

        System.out.println(driverRepositoryHibernate.findById(2L));
        System.out.println(driverRepositoryHibernate.findAll());


    }
}
