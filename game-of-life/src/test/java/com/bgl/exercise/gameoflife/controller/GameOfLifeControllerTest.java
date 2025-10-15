package com.bgl.exercise.gameoflife.controller;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.model.AliveGeneration;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import com.bgl.exercise.gameoflife.rule.RuleEngine;
import com.bgl.exercise.gameoflife.strategy.CellNeighbourStrategy;
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
public class GameOfLifeControllerTest {
    private GameOfLifeController controller;
    private GameOfLifeBoard board;

    @Mock
    private RuleEngine ruleEngine;
    @Mock
    private CellNeighbourStrategy cellNeighbourStrategy;

    @BeforeEach
    public void setUp() {
        controller = new GameOfLifeController(ruleEngine);

        board = new GameOfLifeBoard(
                new Grid(3, 3),
                new AliveGeneration(new Cell[]{new Cell(1, 1)}),
                cellNeighbourStrategy
        );
    }

    @Test
    void shouldReturnNextGenerationWithAliveCells() {
        when(ruleEngine.applyNextStateRule(CellLifeState.ALIVE, 0)).thenReturn(CellLifeState.ALIVE);
        when(cellNeighbourStrategy.getNeighbours(eq(new Cell(1, 1)))).thenReturn(Set.of(new Cell(2, 2)));

        AliveGeneration nextGen = controller.computeNextGeneration(board);

        assertTrue(nextGen.contains(new Cell(1, 1)), "Cell (1,1) should remain alive");
    }

    @Test
    void shouldReturnNextGenerationWithDeadCellsIfRuleSaysDead() {
        when(ruleEngine.applyNextStateRule(CellLifeState.ALIVE, 0)).thenReturn(CellLifeState.DEAD);
        when(cellNeighbourStrategy.getNeighbours(eq(new Cell(1, 1)))).thenReturn(Set.of(new Cell(2, 2)));

        AliveGeneration nextGen = controller.computeNextGeneration(board);

        assertFalse(nextGen.contains(new Cell(1, 1)), "Cell (1,1) should be dead");
        assertEquals(0, nextGen.aliveCells().size(), "No alive cells expected");
    }

    @Test
    void shouldCallRuleEngineForAliveAndNeighbourCells() {
        when(cellNeighbourStrategy.getNeighbours(eq(new Cell(1, 1)))).thenReturn(Set.of(new Cell(2, 2)));

        controller.computeNextGeneration(board);

        verify(ruleEngine, times(2)).applyNextStateRule(any(CellLifeState.class), anyInt());
    }

    @Test
    void shouldIncludeNeighbourCellsInCandidateSet() {
        when(cellNeighbourStrategy.getNeighbours(eq(new Cell(1, 1)))).thenReturn(Set.of(new Cell(2, 2)));

        controller.computeNextGeneration(board);

        verify(cellNeighbourStrategy, atLeastOnce()).getNeighbours(new Cell(1, 1));
    }
}
