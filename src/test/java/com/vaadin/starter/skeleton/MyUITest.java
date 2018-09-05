package com.vaadin.starter.skeleton;

import com.github.karibu.testing.v10.GridKt;
import com.github.karibu.testing.v10.LocatorKt;
import com.github.karibu.testing.v10.MockVaadin;
import com.github.karibu.testing.v10.Routes;
import com.vaadin.flow.component.grid.Grid;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        final Routes routes = new Routes();
        routes.autoDiscoverViews("com.vaadin.starter.skeleton");
        MockVaadin.setup(routes);
    }

    @Test
    public void testGridHas100Rows() {
        final Grid<Person> grid = LocatorKt._get(Grid.class, gridSearchSpec -> null);
        GridKt.expectRows(grid, 100);
    }
}
