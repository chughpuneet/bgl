package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;

public interface CellStateTransitionRule {
    CellLifeState nextState();

    boolean matches(CellLifeState currentCellLifeState, int aliveNeighbours);
}
