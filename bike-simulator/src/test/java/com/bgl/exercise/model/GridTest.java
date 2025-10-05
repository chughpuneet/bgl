package com.bgl.exercise.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(7, 7);
    }

    @Test
    void shouldReturnTrueForValidGridLocation() {
        assertTrue(grid.isValidGridLocation(0, 0));
        assertTrue(grid.isValidGridLocation(3, 4));
        assertTrue(grid.isValidGridLocation(6, 6)); // upper boundary
    }

    @Test
    void shouldReturnFalseForNegativeCoordinates() {
        assertFalse(grid.isValidGridLocation(-1, 0));
        assertFalse(grid.isValidGridLocation(0, -1));
        assertFalse(grid.isValidGridLocation(-2, -3));
    }

    @Test
    void shouldReturnFalseForCoordinatesOutsideGridBounds() {
        assertFalse(grid.isValidGridLocation(7, 0)); // beyond max column
        assertFalse(grid.isValidGridLocation(0, 7)); // beyond max row
        assertFalse(grid.isValidGridLocation(8, 10));
    }

    @Test
    void shouldReturnTrueForBoundaryValuesWithinGrid() {
        assertTrue(grid.isValidGridLocation(0, 6));
        assertTrue(grid.isValidGridLocation(6, 0));
    }

    @Test
    void shouldReturnFalseWhenGridHasZeroSize() {
        Grid zeroGrid = new Grid(0, 0);
        assertFalse(zeroGrid.isValidGridLocation(0, 0));
    }
}
