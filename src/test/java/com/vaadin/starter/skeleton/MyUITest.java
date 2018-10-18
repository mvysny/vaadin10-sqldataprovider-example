package com.vaadin.starter.skeleton;

import com.github.karibu.testing.v10.MockVaadin;
import com.github.karibu.testing.v10.Routes;
import com.vaadin.flow.component.grid.Grid;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.karibu.testing.v10.GridKt.*;
import static com.github.karibu.testing.v10.LocatorJ.*;

/**
 * Look Ma, no Spring/JavaEE/Servlet container necessary! That's what you get when you keep things simple.
 * @author mavi
 */
public class MyUITest {
    @BeforeAll
    public static void mockContainer() {
        new Bootstrap().contextInitialized(null);
    }

    @AfterAll
    public static void stopMockedContainer() {
        new Bootstrap().contextDestroyed(null);
    }

    @BeforeEach
    public void mockVaadin() {
        MockVaadin.setup(new Routes().autoDiscoverViews("com.vaadin.starter.skeleton"));
    }

    @AfterEach
    public void tearDownVaadin() {
        MockVaadin.tearDown();
    }

    @Test
    public void testGridHas100Rows() {
        final Grid<Person> grid = _get(Grid.class);
        expectRows(grid, 100);
    }
}
