package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AliveCellSurvivalRuleTest {

    @ParameterizedTest(name = "Dead cell with {0} neighbours should NOT match")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8})
    void shouldNotMatchWhenCellIsDeadRegardlessOfNeighbours(int neighbours) {
        assertFalse(AliveCellSurvivalRule.getInstance().matches(CellLifeState.DEAD, neighbours));
    }

    @ParameterizedTest(name = "Alive cell with {0} neighbours should match")
    @ValueSource(ints = {2, 3})
    void shouldMatchWhenAliveCellHasTwoOrThreeAliveNeighbours(int neighbours) {
        assertTrue(AliveCellSurvivalRule.getInstance().matches(CellLifeState.ALIVE, neighbours));
    }

    @ParameterizedTest(name = "Alive cell with {0} neighbours should NOT match")
    @ValueSource(ints = {0, 1, 4, 5, 6, 7, 8})
    void shouldNotMatchWhenAliveCellDoesNotHaveTwoAndThreeAliveNeighbours(int neighbours) {
        assertFalse(AliveCellSurvivalRule.getInstance().matches(CellLifeState.ALIVE, neighbours));
    }

    @Test
    void shouldReturnNextStateAsAlive() {
        assertEquals(CellLifeState.ALIVE, AliveCellSurvivalRule.getInstance().nextState());
    }

}
