package com.vaadin.starter.skeleton;

import com.github.vok.framework.sql2o.vaadin.DataProvidersKt;
import com.github.vokorm.Filter;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * The class that will hold the outcome of the SQL select in the {@link #createDataProvider()} function.
 * @author mavi
 */
public class Person implements Serializable {
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

    /**
     * Creates the data provider which provides instances of the Person class. You can create a class that will hold an outcome of a JOIN
     * SQL statement; for simplicity reason we're demoing a simple select here.
     * <p></p>
     * The data provider will use {@link com.github.vokorm.VokOrm#dataSourceConfig} to connect to the database.
     * @return the data provider, not null.
     */
    @NotNull
    public static ConfigurableFilterDataProvider<Person, Filter<Person>, Filter<Person>> createDataProvider() {
        return DataProvidersKt.sqlDataProvider(Person.class,
                "select * from Person where 1=1 {{WHERE}} order by 1=1{{ORDER}} {{PAGING}}",
                new HashMap<>(),
                Person::getId);
    }
}
