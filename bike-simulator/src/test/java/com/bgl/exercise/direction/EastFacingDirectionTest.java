package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

public class EastFacingDirectionTest {
    @Test
    void shouldIncreaseColumnOnNextMove() {
        GridLocation current = new GridLocation(2, 3);

        GridLocation next = EastFacingDirection.getInstance().getNextGridLocation(current);

        assertEquals(3, next.getColumn());
        assertEquals(3, next.getRow());
    }

    @Test
    void shouldReturnNorthOnTurnLeft() {
        FacingDirection left = EastFacingDirection.getInstance().turnLeft();

        assertInstanceOf(NorthFacingDirection.class, left);
    }

    @Test
    void shouldReturnSouthOnTurnRight() {
        FacingDirection right = EastFacingDirection.getInstance().turnRight();

        assertInstanceOf(SouthFacingDirection.class, right);
    }

    @Test
    void shouldReturnSingleton() {
        FacingDirection instance1 = EastFacingDirection.getInstance();
        FacingDirection instance2 = EastFacingDirection.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void shouldReturnEastAsName() {
        assertEquals("East", EastFacingDirection.getInstance().getName());
    }
}
