package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadCellReproductionRuleTest {

    @ParameterizedTest(name = "Alive cell with {0} neighbours should NOT match")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldNotMatchWhenCellIsAliveRegardlessOfNeighbours(int aliveNeighbours) {
        assertFalse(DeadCellReproductionRule.getInstance().matches(CellLifeState.ALIVE, aliveNeighbours));
    }

    @ParameterizedTest(name = "Dead cell with {0} neighbours should NOT match")
    @ValueSource(ints = {0, 1, 2, 4, 5, 6, 7, 8})
    void shouldNotMatchWhenDeadCellDoesNotHaveThreeNeighbours(int aliveNeighbours) {
        assertFalse(DeadCellReproductionRule.getInstance().matches(CellLifeState.DEAD, aliveNeighbours));
    }

    @Test
    void shouldMatchWhenDeadCellHasThreeAliveNeighbours() {
        assertTrue(DeadCellReproductionRule.getInstance().matches(CellLifeState.DEAD, 3));
    }

    @Test
    void shouldReturnNextStateAsAlive() {
        assertEquals(CellLifeState.ALIVE, DeadCellReproductionRule.getInstance().nextState());
    }
}
