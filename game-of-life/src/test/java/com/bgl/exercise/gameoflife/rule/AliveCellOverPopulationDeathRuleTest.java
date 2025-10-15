package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AliveCellOverPopulationDeathRuleTest {

    @ParameterizedTest(name = "Dead cell with {0} neighbours should NOT match")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldNotMatchWhenCellIsDeadRegardlessOfNeighbours(int neighbours) {
        assertFalse(AliveCellOverPopulationDeathRule.getInstance().matches(CellLifeState.DEAD, neighbours));
    }

    @ParameterizedTest(name = "Alive cell with {0} neighbours should NOT match")
    @ValueSource(ints = {0, 1, 2, 3})
    void shouldNotMatchWhenAliveCellHasLessThanFourAliveNeighbours(int neighbours) {
        assertFalse(AliveCellOverPopulationDeathRule.getInstance().matches(CellLifeState.ALIVE, neighbours));
    }

    @ParameterizedTest(name = "Alive cell with {0} neighbours should match")
    @ValueSource(ints = {4, 5, 6, 7, 8})
    void shouldMatchWhenAliveCellHasMoreThanThreeAliveNeighbours(int neighbours) {
        assertTrue(AliveCellOverPopulationDeathRule.getInstance().matches(CellLifeState.ALIVE, neighbours));
    }

    @Test
    void shouldReturnNextStateAsDead() {
        assertEquals(CellLifeState.DEAD, AliveCellOverPopulationDeathRule.getInstance().nextState());
    }
}
