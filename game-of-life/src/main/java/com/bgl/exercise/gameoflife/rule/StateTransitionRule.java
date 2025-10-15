package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;

public interface StateTransitionRule {
    CellLifeState nextState();

    boolean matches(CellLifeState currentCellLifeState, int aliveNeighbours);
}
