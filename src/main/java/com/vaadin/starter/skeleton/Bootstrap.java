package com.vaadin.starter.skeleton;

import com.github.vokorm.DBKt;
import com.github.vokorm.VokOrm;

import org.flywaydb.core.Flyway;
import org.h2.Driver;

import java.time.Instant;
import java.time.LocalDate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Bootstraps the VokOrm so that the SQLDataProvider has access to the database.
 * @author mavi
 */
@WebListener
public class Bootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // this configures the database + HikariCP connection pool, in order for the SQLDataProvider to be able to access the database
        // here we simply configure an in-memory H2 database
        VokOrm.INSTANCE.getDataSourceConfig().setDriverClassName(Driver.class.getName());
        VokOrm.INSTANCE.getDataSourceConfig().setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        VokOrm.INSTANCE.getDataSourceConfig().setUsername("sa");
        VokOrm.INSTANCE.getDataSourceConfig().setPassword("");
        VokOrm.INSTANCE.init();

        // create the 'Person' table
        final Flyway flyway = new Flyway();
        flyway.setDataSource(VokOrm.INSTANCE.getDataSource());
        flyway.migrate();

        // create some testing data
        DBKt.db(ctx -> {
            for (int i = 0; i < 100; i++) {
                final Person person = new Person();
                person.setName("Person " + i);
                person.setAge(i + 10);
                person.setAlive(i % 2 == 0);
                person.setCreated(Instant.now());
                person.setDateOfBirth(LocalDate.of(1990, 1, i % 28 + 1));
                ctx.getCon().createQuery("insert into Person (name, age, alive, created, dateOfBirth) values (:name, :age, :alive, :created, :dateOfBirth)")
                        .bind(person)
                        .executeUpdate();
            }
            return null;
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        VokOrm.INSTANCE.destroy();
    }
}
