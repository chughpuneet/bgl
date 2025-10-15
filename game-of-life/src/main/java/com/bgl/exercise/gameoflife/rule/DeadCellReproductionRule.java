package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;

public class DeadCellReproductionRule implements CellStateTransitionRule {

    private static final DeadCellReproductionRule INSTANCE = new DeadCellReproductionRule();

    private DeadCellReproductionRule() {}
    @Override
    public CellLifeState nextState() {
        return CellLifeState.ALIVE;
    }

    @Override
    public boolean matches(CellLifeState currentCellLifeState, int aliveNeighbours) {
        return currentCellLifeState == CellLifeState.DEAD && aliveNeighbours == 3;
    }

    public static DeadCellReproductionRule getInstance() {
        return INSTANCE;
    }
}
