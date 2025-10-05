package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

public class NorthFacingDirectionTest {
    @Test
    void shouldIncreaseRowOnNextMove() {
        GridLocation current = new GridLocation(2, 3);

        GridLocation next = NorthFacingDirection.getInstance().getNextGridLocation(current);

        assertEquals(2, next.getColumn());
        assertEquals(4, next.getRow());
    }

    @Test
    void shouldReturnWestOnTurnLeft() {
        FacingDirection left = NorthFacingDirection.getInstance().turnLeft();

        assertInstanceOf(WestFacingDirection.class, left);
    }

    @Test
    void shouldReturnEastOnTurnRight() {
        FacingDirection right = EastFacingDirection.getInstance().turnRight();

        assertInstanceOf(SouthFacingDirection.class, right);
    }

    @Test
    void shouldReturnSingleton() {
        FacingDirection instance1 = NorthFacingDirection.getInstance();
        FacingDirection instance2 = NorthFacingDirection.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void shouldReturnNorthAsName() {
        assertEquals("North", NorthFacingDirection.getInstance().getName());
    }
}
