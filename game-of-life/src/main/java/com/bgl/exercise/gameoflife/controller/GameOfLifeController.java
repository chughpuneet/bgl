package com.bgl.exercise.gameoflife.controller;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.model.AliveGeneration;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.rule.RuleEngine;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameOfLifeController {

    private final RuleEngine ruleEngine;

    public GameOfLifeController(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public AliveGeneration computeNextGeneration(GameOfLifeBoard gameOfLifeBoard) {
        Set<Cell> candidateCellsForStateChange = collectAliveAndNeighbouringCells(gameOfLifeBoard);

        Set<Cell> nextGenerationAliveCells = candidateCellsForStateChange
                .stream()
                .filter(cell -> computeNextCellState(cell, gameOfLifeBoard) == CellLifeState.ALIVE)
                .collect(Collectors.toSet());

        return new AliveGeneration(nextGenerationAliveCells);
    }

    private CellLifeState computeNextCellState(Cell cell, GameOfLifeBoard gameOfLifeBoard) {
        return ruleEngine.applyNextStateRule(
                gameOfLifeBoard.getCellState(cell),
                gameOfLifeBoard.getNeighboursWithState(cell, CellLifeState.ALIVE).size());
    }

    private Set<Cell> collectAliveAndNeighbouringCells(GameOfLifeBoard gameOfLifeBoard) {
        return gameOfLifeBoard.getAliveGeneration().aliveCells()
                .stream()
                .flatMap(cell ->
                        Stream.concat(
                                Stream.of(cell),
                                gameOfLifeBoard.getNeighbours(cell).stream()))
                .collect(Collectors.toSet());
    }
}
