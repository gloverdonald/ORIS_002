package ru.itis.hibernateexample.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ru.itis.hibernateexample.config.HibernateConfig;
import ru.itis.hibernateexample.model.Driver;

import java.util.List;
import java.util.Optional;


public class DriverRepositoryHibernate implements CrudRepository<Driver, Long> {
    private final SessionFactory factory = HibernateConfig.getSessionFactory();
    private final Session session = factory.openSession();
        
    @Override
    public Optional<Driver> findById(Long id) {
        try {
            session.getTransaction().begin();
            Optional<Driver> driver = session.byId(Driver.class).loadOptional(id);
            session.getTransaction().commit();
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return Optional.empty();
        }
    }

    @Override
    public List<Driver> findAll() {
        try {
            session.getTransaction().begin();
            String s = "from Driver";
            Query<Driver> query = session.createQuery(s);
            List<Driver> drivers = query.getResultList();
            session.getTransaction().commit();
            return drivers;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Driver save(Driver item) {
        try {
            session.getTransaction().begin();
            Long id = (Long) session.save(item);
            Driver driver = session.get(Driver.class, id);
            session.getTransaction().commit();
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            session.getTransaction().begin();
            Driver driver = session.get(Driver.class, id);
            session.delete(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
