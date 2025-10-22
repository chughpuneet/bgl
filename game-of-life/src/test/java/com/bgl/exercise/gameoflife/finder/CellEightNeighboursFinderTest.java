package com.bgl.exercise.gameoflife.finder;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CellEightNeighboursFinderTest {

    private final CellEightNeighboursFinder neighboursFinder = CellEightNeighboursFinder.getInstance();

    @Mock
    private GameOfLifeBoard gameOfLifeBoard;
    @Mock
    private Grid grid;

    @Test
    void shouldNotIncludeSelfAsNeighbour() {
        Cell cell = new Cell(5, 5);
        when(gameOfLifeBoard.getGrid()).thenReturn(grid);
        when(grid.isCellWithinGrid(any())).thenReturn(true);

        Set<Cell> neighbours = neighboursFinder.find(cell, gameOfLifeBoard);
        assertFalse(neighbours.contains(cell));
    }

    @Test
    public void shouldReturnAllNeighboursForCenterCell() {
        Cell center = new Cell(1, 1);
        when(gameOfLifeBoard.getGrid()).thenReturn(grid);
        when(grid.isCellWithinGrid(any())).thenReturn(true);

        Set<Cell> neighbours = neighboursFinder.find(center, gameOfLifeBoard);

        Set<Cell> expected = Set.of(
                new Cell(1, 0), new Cell(2, 0),
                new Cell(2, 1), new Cell(2, 2),
                new Cell(1, 2), new Cell(0, 2),
                new Cell(0, 1), new Cell(0, 0)
        );
        assertEquals(expected.size(), neighbours.size());
        assertTrue(expected.containsAll(neighbours));
    }

    @Test
    public void shouldReturnNeighboursWithinBoundsForCornerCell() {
        Cell corner = new Cell(0, 0);
        when(gameOfLifeBoard.getGrid()).thenReturn(grid);
        when(grid.isCellWithinGrid(eq(new Cell(0, -1)))).thenReturn(false);
        when(grid.isCellWithinGrid(eq(new Cell(1, -1)))).thenReturn(false);
        when(grid.isCellWithinGrid(eq(new Cell(1, 0)))).thenReturn(true);
        when(grid.isCellWithinGrid(eq(new Cell(1, 1)))).thenReturn(true);
        when(grid.isCellWithinGrid(eq(new Cell(0, 1)))).thenReturn(true);
        when(grid.isCellWithinGrid(eq(new Cell(-1, 1)))).thenReturn(false);
        when(grid.isCellWithinGrid(eq(new Cell(-1, 0)))).thenReturn(false);
        when(grid.isCellWithinGrid(eq(new Cell(-1, -1)))).thenReturn(false);

        Set<Cell> neighbours = neighboursFinder.find(corner, gameOfLifeBoard);

        Set<Cell> expected = Set.of(
                new Cell(1, 0), new Cell(1, 1),
                new Cell(0, 1));

        assertEquals(expected.size(), neighbours.size());
        assertTrue(expected.containsAll(neighbours));
    }

    @Test
    public void shouldReturnEmptyWhenNoNeighbourWithGivenStateIsPresent() {
        Cell cell = new Cell(1, 1);
        when(gameOfLifeBoard.getCellState(any())).thenReturn(CellLifeState.DEAD);

        Set<Cell> neighbours = neighboursFinder.findWithState(cell, CellLifeState.ALIVE, gameOfLifeBoard);

        assertEquals(0, neighbours.size());
    }

    @Test
    public void shouldReturnOnlyNeighboursWithGivenState() {
        Cell cell = new Cell(1, 1);
        when(gameOfLifeBoard.getCellState(any())).thenReturn(CellLifeState.ALIVE);

        Set<Cell> neighbours = neighboursFinder.findWithState(cell, CellLifeState.ALIVE, gameOfLifeBoard);

        Set<Cell> expected = Set.of(
                new Cell(1, 0), new Cell(2, 0),
                new Cell(2, 1), new Cell(2, 2),
                new Cell(1, 2), new Cell(0, 2),
                new Cell(0, 1), new Cell(0, 0)
        );
        assertEquals(expected.size(), neighbours.size());
        assertTrue(expected.containsAll(neighbours));

    }
}
