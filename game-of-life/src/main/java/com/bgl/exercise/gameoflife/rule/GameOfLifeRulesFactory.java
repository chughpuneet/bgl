package com.bgl.exercise.gameoflife.rule;

import java.util.List;

public class GameOfLifeRulesFactory {
    private static final GameOfLifeRulesFactory INSTANCE = new GameOfLifeRulesFactory();
    private GameOfLifeRulesFactory() {
    }

    public List<CellStateTransitionRule> getGameOfLifeRules() {
        return List.of(
                AliveCellUnderPopulationDeathRule.getInstance(),
                AliveCellSurvivalRule.getInstance(),
                AliveCellOverPopulationDeathRule.getInstance(),
                DeadCellReproductionRule.getInstance()
        );
    }

    public static GameOfLifeRulesFactory getInstance() {
        return INSTANCE;
    }
}
