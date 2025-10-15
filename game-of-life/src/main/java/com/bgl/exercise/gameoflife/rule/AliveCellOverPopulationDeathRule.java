package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;

public class AliveCellOverPopulationDeathRule implements CellStateTransitionRule {
    private static final AliveCellOverPopulationDeathRule INSTANCE = new AliveCellOverPopulationDeathRule();

    private AliveCellOverPopulationDeathRule() {
    }

    public static AliveCellOverPopulationDeathRule getInstance() {
        return INSTANCE;
    }

    @Override
    public CellLifeState nextState() {
        return CellLifeState.DEAD;
    }

    @Override
    public boolean matches(CellLifeState currentCellLifeState, int aliveNeighbours) {
        return currentCellLifeState == CellLifeState.ALIVE && aliveNeighbours > 3;
    }
}
