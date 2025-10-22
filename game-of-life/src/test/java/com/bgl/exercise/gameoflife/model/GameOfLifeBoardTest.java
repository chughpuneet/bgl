package com.bgl.exercise.gameoflife.model;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameOfLifeBoardTest {

    @Mock
    Grid grid;
    @Mock
    AliveGeneration aliveGeneration;

    GameOfLifeBoard board;

    @BeforeEach
    public void setUp() {
        board = new GameOfLifeBoard(grid, aliveGeneration);
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
