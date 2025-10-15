package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;

public class AliveCellUnderPopulationDeathRule implements StateTransitionRule {

    private static final AliveCellUnderPopulationDeathRule INSTANCE = new AliveCellUnderPopulationDeathRule();

    private AliveCellUnderPopulationDeathRule() {
    }

    public static AliveCellUnderPopulationDeathRule getInstance() {
        return INSTANCE;
    }

    @Override
    public CellLifeState nextState() {
        return CellLifeState.DEAD;
    }

    @Override
    public boolean matches(CellLifeState currentCellLifeState, int aliveNeighbours) {
        return currentCellLifeState == CellLifeState.ALIVE && aliveNeighbours < 2;
    }
}
