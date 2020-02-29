package com.vaadin.starter.skeleton;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import eu.vaadinonkotlin.VaadinOnKotlin;
import eu.vaadinonkotlin.vokdb.VokOrmPluginKt;
import org.flywaydb.core.Flyway;
import org.h2.Driver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Instant;
import java.time.LocalDate;

import static com.gitlab.mvysny.jdbiorm.JdbiOrm.jdbi;

/**
 * Bootstraps the <a href="https://github.com/mvysny/vok-orm">Vok-ORM</a> which is used by the <code>SQLDataProvider</code> to access the database.
 * @author mavi
 */
@WebListener
public class Bootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // this configures the database + HikariCP connection pool, in order for the SQLDataProvider to be able to access the database
        // here we simply configure an in-memory H2 database
        final HikariConfig cfg = new HikariConfig();
        cfg.setDriverClassName(Driver.class.getName());
        cfg.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        cfg.setUsername("sa");
        cfg.setPassword("");
        VokOrmPluginKt.setDataSource(VaadinOnKotlin.INSTANCE, new HikariDataSource(cfg));
        VaadinOnKotlin.INSTANCE.init();

        // create the 'Person' table
        final Flyway flyway = Flyway.configure()
                .dataSource(VokOrmPluginKt.getDataSource(VaadinOnKotlin.INSTANCE))
                .load();
        flyway.migrate();

        // create some testing data
        jdbi().useTransaction(ctx -> {
            for (int i = 0; i < 100; i++) {
                final Person person = new Person();
                person.setName("Person " + i);
                person.setAge(i + 10);
                person.setAlive(i % 2 == 0);
                person.setCreated(Instant.now());
                person.setDateOfBirth(LocalDate.of(1990, 1, i % 28 + 1));
                ctx.createUpdate("insert into Person (name, age, alive, created, dateOfBirth) values (:name, :age, :alive, :created, :dateOfBirth)")
                        .bindBean(person)
                        .execute();
            }
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        VaadinOnKotlin.INSTANCE.destroy();
    }
}
