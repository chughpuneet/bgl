package com.bgl.exercise.gameoflife.model;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.strategy.CellNeighbourStrategy;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameOfLifeBoardTest {

    @Mock
    CellNeighbourStrategy cellNeighbourStrategy;
    @Mock
    Grid grid;
    @Mock
    AliveGeneration aliveGeneration;

    GameOfLifeBoard board;

    @BeforeEach
    public void setUp() {
        board = new GameOfLifeBoard(grid, aliveGeneration, cellNeighbourStrategy);
    }

    @Test
    public void shouldReturnAllNeighboursForCenterCell() {
        Cell center = new Cell(1, 1);
        when(cellNeighbourStrategy.getNeighbours(center)).thenReturn(
                Set.of(
                        new Cell(1, 2),
                        new Cell(0, 1),
                        new Cell(2, 1),
                        new Cell(1, 0))
        );
        when(grid.isCellWithinGrid(any())).thenReturn(true);

        Set<Cell> neighbours = board.getNeighbours(center);

        Set<Cell> expected = Set.of(
                new Cell(1, 2), new Cell(0, 1),
                new Cell(2, 1), new Cell(1, 0)
        );
        assertEquals(expected.size(), neighbours.size());
        assertTrue(expected.containsAll(neighbours));
    }

    @Test
    public void shouldReturnNeighboursWithinBoundsForCornerCell() {
        Cell corner = new Cell(0, 0);
        when(cellNeighbourStrategy.getNeighbours(corner)).thenReturn(
                Set.of(
                        new Cell(0, 1),
                        new Cell(1, 0),
                        new Cell(0, -1),
                        new Cell(1, 1))
        );
        when(grid.isCellWithinGrid(any())).thenReturn(false);

        Set<Cell> neighbours = board.getNeighbours(corner);

        assertEquals(0, neighbours.size());
    }

    @Test
    public void shouldReturnEmptyWhenNoNeighbourWithGivenStateIsPresent() {
        Cell cell = new Cell(0, 0);
        when(cellNeighbourStrategy.getNeighbours(cell)).thenReturn(
                Set.of(
                        new Cell(0, 1),
                        new Cell(1, 0),
                        new Cell(0, -1),
                        new Cell(1, 1))
        );
        when(grid.isCellWithinGrid(any())).thenReturn(true);
        when(aliveGeneration.contains(any())).thenReturn(false);

        Set<Cell> neighbours = board.getNeighboursWithState(cell, CellLifeState.ALIVE);

        assertEquals(0, neighbours.size());

    }

    @Test
    public void shouldReturnOnlyNeighboursWithGivenState() {
        Cell cell = new Cell(0, 0);
        Cell aliveNeighbourCell = new Cell(1, 0);
        when(cellNeighbourStrategy.getNeighbours(cell)).thenReturn(
                Set.of(
                        aliveNeighbourCell,
                        new Cell(1, 1))
        );
        when(grid.isCellWithinGrid(any())).thenReturn(true);
        when(aliveGeneration.contains(eq(aliveNeighbourCell))).thenReturn(true);
        when(aliveGeneration.contains(eq(new Cell(1, 1)))).thenReturn(false);

        Set<Cell> neighbours = board.getNeighboursWithState(cell, CellLifeState.ALIVE);

        assertEquals(1, neighbours.size());
        assertTrue(neighbours.contains(aliveNeighbourCell));

    }

    @Test
    public void shouldReturnAliveWhenCellIsInAliveGeneration() {
        Cell cell = new Cell(2, 3);
        when(aliveGeneration.contains(cell)).thenReturn(true);

        assertEquals(CellLifeState.ALIVE, board.getCellState(cell));
    }

    @Test
    public void shouldAdvanceToNextGeneration() {
        AliveGeneration next = new AliveGeneration(Set.of(new Cell(1, 1)));
        board.advanceToNextGeneration(next);

        assertEquals(next, board.getAliveGeneration());
    }
}
