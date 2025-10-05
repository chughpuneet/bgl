package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

public class WestFacingDirectionTest {
    @Test
    void shouldDecreaseColumnOnNextMove() {
        GridLocation current = new GridLocation(2, 3);

        GridLocation next = WestFacingDirection.getInstance().getNextGridLocation(current);

        assertEquals(1, next.getColumn());
        assertEquals(3, next.getRow());
    }

    @Test
    void shouldReturnSouthOnTurnLeft() {
        FacingDirection left = WestFacingDirection.getInstance().turnLeft();

        assertInstanceOf(SouthFacingDirection.class, left);
    }

    @Test
    void shouldReturnNorthOnTurnRight() {
        FacingDirection right = WestFacingDirection.getInstance().turnRight();

        assertInstanceOf(NorthFacingDirection.class, right);
    }

    @Test
    void shouldReturnSingleton() {
        FacingDirection instance1 = WestFacingDirection.getInstance();
        FacingDirection instance2 = WestFacingDirection.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void shouldReturnWestAsName() {
        assertEquals("West", WestFacingDirection.getInstance().getName());
    }
}
