package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AliveCellUnderPopulationDeathRuleTest {

    @ParameterizedTest(name = "Dead cell with {0} neighbours should NOT match")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldNotMatchWhenCellIsDeadRegardlessOfNeighbours(int aliveNeighbours) {
        assertFalse(AliveCellUnderPopulationDeathRule.getInstance().matches(CellLifeState.DEAD, aliveNeighbours));
    }

    @ParameterizedTest(name = "Alive cell with {0} neighbours should match")
    @ValueSource(ints = {0, 1})
    void shouldMatchIfAliveCellHasLessThanTwoAliveNeighbours(int aliveNeighbours) {
        assertTrue(AliveCellUnderPopulationDeathRule.getInstance().matches(CellLifeState.ALIVE, aliveNeighbours));
    }

    @ParameterizedTest(name = "Alive cell with {0} neighbours should NOT match")
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8})
    void shouldNotMatchIfAliveCellHasMoreThanOneAliveNeighbours(int aliveNeighbours) {
        assertFalse(AliveCellUnderPopulationDeathRule.getInstance().matches(CellLifeState.ALIVE, aliveNeighbours));
    }

    @Test
    void shouldReturnNextStateAsDead() {
        assertEquals(CellLifeState.DEAD, AliveCellUnderPopulationDeathRule.getInstance().nextState());
    }
}
