package com.vaadin.starter.skeleton;

import com.github.vok.framework.flow.DefaultFilterFieldFactory;
import com.github.vok.framework.flow.FilterRow;
import com.github.vok.framework.sql2o.vaadin.SqlFilterFactory;
import com.github.vok.framework.sql2o.vaadin.VaadinFiltersKt;
import com.github.vokorm.Filter;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/**
 * The main view contains a button and a click listener.
 */
@Route
public class MainView extends VerticalLayout {

    public MainView() {
        setSizeFull();

        final Grid<Person> grid = new Grid<>(Person.class);
        grid.setSizeFull();
        grid.setDataProvider(Person.createDataProvider());

        final KClass<Person> kclass = (KClass<Person>) Reflection.getOrCreateKotlinClass(Person.class);
        final FilterRow<Person, Filter<Person>> filterRow = VaadinFiltersKt.generateFilterComponents(grid.appendHeaderRow(),
                grid,
                kclass,
                new DefaultFilterFieldFactory<>(Person.class, new SqlFilterFactory<>(Person.class)),
                ValueChangeMode.EAGER
        );
        add(grid);
    }
}
