package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SouthFacingDirectionTest {
    @Test
    void shouldDecreaseRowOnNextMove() {
        GridLocation current = new GridLocation(2, 3);

        GridLocation next = SouthFacingDirection.getInstance().getNextGridLocation(current);

        assertEquals(2, next.getColumn());
        assertEquals(2, next.getRow());
    }

    @Test
    void shouldReturnEastOnTurnLeft() {
        FacingDirection left = SouthFacingDirection.getInstance().turnLeft();

        assertInstanceOf(EastFacingDirection.class, left);
    }

    @Test
    void shouldReturnWestOnTurnRight() {
        FacingDirection right = SouthFacingDirection.getInstance().turnRight();

        assertInstanceOf(WestFacingDirection.class, right);
    }

    @Test
    void shouldReturnSingleton() {
        FacingDirection instance1 = SouthFacingDirection.getInstance();
        FacingDirection instance2 = SouthFacingDirection.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void shouldReturnSouthAsName() {
        assertEquals("South", SouthFacingDirection.getInstance().getName());
    }
}
