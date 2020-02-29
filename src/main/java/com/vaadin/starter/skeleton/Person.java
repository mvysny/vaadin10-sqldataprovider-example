package com.vaadin.starter.skeleton;

import com.github.mvysny.vokdataloader.Filter;
import com.github.vokorm.dataloader.EntityDataLoader;
import com.gitlab.mvysny.jdbiorm.Dao;
import com.gitlab.mvysny.jdbiorm.Entity;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;

import eu.vaadinonkotlin.vaadin10.DataLoaderAdapter;
import eu.vaadinonkotlin.vaadin10.VokDataProviderKt;
import eu.vaadinonkotlin.vaadin10.vokdb.DataProvidersKt;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * The class that will hold the outcome of the SQL select in the {@link PersonDao#createDataProvider()} function.
 * The Sql2o library is used to map the JDBC ResultSet into a Java Bean.
 * The mapping directly maps column name to a bean property name; to modify the mapping just
 * alias the columns in your SELECT command.
 * @author mavi
 */
public class Person implements Entity<Long> {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate dateOfBirth;
    private Instant created;
    private Boolean alive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", created=" + created +
                ", alive=" + alive +
                '}';
    }

    public static final PersonDao dao = new PersonDao();

    public static final class PersonDao extends Dao<Person, Long> {
        public PersonDao() {
            super(Person.class);
        }

        /**
         * Creates the data provider which provides instances of the Person class. You can create a class that will hold an outcome of a JOIN
         * SQL statement; for simplicity reason we're demoing a simple select here.
         * @return the data provider, not null.
         */
        @NotNull
        public ConfigurableFilterDataProvider<Person, Filter<Person>, Filter<Person>> createSqlDataProvider() {
            return DataProvidersKt.sqlDataProvider(Person.class,
                    "select * from Person where 1=1 {{WHERE}} order by 1=1{{ORDER}} {{PAGING}}",
                    new HashMap<>(),
                    Person::getId);
        }

        /**
         * Since {@link Person} is an {@link Entity}, it's possible to use the {@link EntityDataLoader} also.
         * @return the data provider, not null.
         */
        @NotNull
        public ConfigurableFilterDataProvider<Person, Filter<Person>, Filter<Person>> createDataProvider() {
            final EntityDataLoader<Person> dataLoader = new EntityDataLoader<>(Person.dao);
            final DataLoaderAdapter<Person> adapter = new DataLoaderAdapter<>(dataLoader, Person::getId);
            return VokDataProviderKt.withConfigurableFilter2(adapter);
        }
    }
}
