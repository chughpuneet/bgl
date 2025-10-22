package com.bgl.exercise.gameoflife.evaluator;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.finder.CellNeighboursFinder;
import com.bgl.exercise.gameoflife.model.AliveGeneration;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenerationEvaluatorTest {
    private GenerationEvaluator generationEvaluator;
    private GameOfLifeBoard board;

    @Mock
    private CellStateEvaluator cellStateEvaluator;
    @Mock
    private CellNeighboursFinder cellNeighboursFinder;

    @BeforeEach
    public void setUp() {
        generationEvaluator = new GenerationEvaluator(cellStateEvaluator, cellNeighboursFinder);

        board = new GameOfLifeBoard(
                new Grid(3, 3),
                new AliveGeneration(new Cell[]{new Cell(1, 1)})
        );
    }

    @Test
    void shouldEvaluateNextGenerationIfCellSurvivesByRule() {
        when(cellStateEvaluator.evaluate(CellLifeState.ALIVE, 0)).thenReturn(CellLifeState.ALIVE);
        when(cellNeighboursFinder.find(eq(new Cell(1, 1)), eq(board))).thenReturn(Set.of(new Cell(2, 2)));

        AliveGeneration nextGeneration = generationEvaluator.evaluate(board);

        assertTrue(nextGeneration.contains(new Cell(1, 1)), "Cell (1,1) should remain alive");
    }

    @Test
    void shouldEvaluateNextGenerationIfCellDiesByRule() {
        when(cellStateEvaluator.evaluate(CellLifeState.ALIVE, 0)).thenReturn(CellLifeState.DEAD);
        when(cellNeighboursFinder.find(eq(new Cell(1, 1)), eq(board))).thenReturn(Set.of(new Cell(2, 2)));

        AliveGeneration nextGeneration = generationEvaluator.evaluate(board);

        assertFalse(nextGeneration.contains(new Cell(1, 1)), "Cell (1,1) should be dead");
        assertEquals(0, nextGeneration.aliveCells().size(), "No alive cells expected");
    }

    @Test
    void shouldCallRuleEngineForAliveAndItsNeighbourCells() {
        when(cellNeighboursFinder.find(eq(new Cell(1, 1)), eq(board))).thenReturn(Set.of(new Cell(2, 2)));

        generationEvaluator.evaluate(board);

        verify(cellStateEvaluator, times(2)).evaluate(any(CellLifeState.class), anyInt());
    }

    @Test
    void shouldIncludeAliveCellNeighbourCellsForStateChangeCandidates() {
        when(cellNeighboursFinder.find(eq(new Cell(1, 1)), eq(board))).thenReturn(Set.of(new Cell(2, 2)));

        generationEvaluator.evaluate(board);

        verify(cellNeighboursFinder, atLeastOnce()).find(new Cell(1, 1), board);
    }
}
