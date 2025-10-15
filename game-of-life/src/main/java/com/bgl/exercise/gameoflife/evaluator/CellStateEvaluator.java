package com.bgl.exercise.gameoflife.evaluator;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.rule.CellStateTransitionRule;
import java.util.List;

public class CellStateEvaluator {

    private final List<CellStateTransitionRule> cellStateTransitionRules;

    public CellStateEvaluator(List<CellStateTransitionRule> cellStateTransitionRules) {
        this.cellStateTransitionRules = cellStateTransitionRules;
    }

    public CellLifeState evaluate(CellLifeState existingCellState, int aliveNeighbours) {
        for (CellStateTransitionRule cellStateTransitionRule : cellStateTransitionRules) {
            if (cellStateTransitionRule.matches(existingCellState, aliveNeighbours)) {
                return cellStateTransitionRule.nextState();
            }
        }
        return existingCellState;
    }
}
