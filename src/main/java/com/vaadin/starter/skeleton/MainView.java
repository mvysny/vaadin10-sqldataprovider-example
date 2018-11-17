package com.vaadin.starter.skeleton;

import com.github.mvysny.vokdataloader.Filter;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import eu.vaadinonkotlin.vaadin10.DataLoaderFilterFactory;
import eu.vaadinonkotlin.vaadin10.DataLoaderFilterFactoryKt;
import eu.vaadinonkotlin.vaadin10.DefaultFilterFieldFactory;
import eu.vaadinonkotlin.vaadin10.FilterRow;
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
        final FilterRow<Person, Filter<Person>> filterRow = DataLoaderFilterFactoryKt.generateFilterComponents(grid.appendHeaderRow(),
                grid,
                kclass,
                new DefaultFilterFieldFactory<>(Person.class, new DataLoaderFilterFactory<>(Person.class)),
                ValueChangeMode.EAGER
        );
        add(grid);
    }
}
