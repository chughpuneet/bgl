package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import java.util.List;

public class RuleEngine {

    private final List<StateTransitionRule> rules;

    public RuleEngine(List<StateTransitionRule> rules) {
        this.rules = rules;
    }

    public CellLifeState applyNextStateRule(CellLifeState current, int aliveNeighbours) {
        for (StateTransitionRule rule : rules) {
            if (rule.matches(current, aliveNeighbours)) {
                return rule.nextState();
            }
        }
        return current;
    }
}
