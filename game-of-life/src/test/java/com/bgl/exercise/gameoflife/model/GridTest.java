package com.bgl.exercise.gameoflife.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridTest {
    private Grid grid;

    private static final int GRID_ROWS = 3;
    private static final int GRID_COLUMNS = 3;

    @BeforeEach
    public void setUp() {
        grid = new Grid(GRID_COLUMNS, GRID_ROWS);
    }

    @Test
    public void shouldReturnFalseForCellWithinGridWhenCellRowIsNegative() {
        Cell cell = new Cell(0, -1);

        assertFalse(grid.isCellWithinGrid(cell));
    }

    @Test
    public void shouldReturnFalseForCellWithinGridWhenCellColumnIsNegative() {
        Cell cell = new Cell(-1, 0);

        assertFalse(grid.isCellWithinGrid(cell));
    }

    @Test
    public void shouldReturnFalseForCellWithinGridWhenCellRowAndColumnIsNegative() {
        Cell cell = new Cell(-1, -1);

        assertFalse(grid.isCellWithinGrid(cell));
    }

    @Test
    public void shouldReturnFalseForCellWithinGridWhenCellRowIsMoreThanGridRows() {
        Cell cell = new Cell(0, GRID_ROWS);

        assertFalse(grid.isCellWithinGrid(cell));
    }

    @Test
    public void shouldReturnFalseForCellWithinGridWhenCellColumnIsMoreThanGridColumns() {
        Cell cell = new Cell(GRID_COLUMNS, 0);

        assertFalse(grid.isCellWithinGrid(cell));
    }

    @Test
    public void shouldReturnFalseForCellWithinGridWhenCellColumnAndRowIsMoreThanGridColumnsAndRows() {
        Cell cell = new Cell(GRID_COLUMNS, GRID_ROWS);

        assertFalse(grid.isCellWithinGrid(cell));
    }

    @Test
    public void shouldReturnTrueWhenCellColumnAndRowIsWithinGridColumnsAndRows() {
        Cell cell = new Cell(GRID_COLUMNS - 1, GRID_ROWS - 1);

        assertTrue(grid.isCellWithinGrid(cell));
    }
}
