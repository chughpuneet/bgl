package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;

public class AliveCellSurvivalRule implements StateTransitionRule {

    private static final AliveCellSurvivalRule INSTANCE = new AliveCellSurvivalRule();

    private AliveCellSurvivalRule() {
    }

    public static AliveCellSurvivalRule getInstance() {
        return INSTANCE;
    }

    @Override
    public CellLifeState nextState() {
        return CellLifeState.ALIVE;
    }

    @Override
    public boolean matches(CellLifeState currentCellLifeState, int aliveNeighbours) {
        return currentCellLifeState == CellLifeState.ALIVE && (aliveNeighbours == 2 || aliveNeighbours == 3);
    }
}
