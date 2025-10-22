package com.bgl.exercise.gameoflife.evaluator;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.finder.CellNeighboursFinder;
import com.bgl.exercise.gameoflife.model.AliveGeneration;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerationEvaluator {

    private final CellStateEvaluator cellStateEvaluator;
    private final CellNeighboursFinder cellNeighboursFinder;

    public GenerationEvaluator(CellStateEvaluator cellStateEvaluator, CellNeighboursFinder cellNeighboursFinder) {
        this.cellStateEvaluator = cellStateEvaluator;
        this.cellNeighboursFinder = cellNeighboursFinder;
    }

    public AliveGeneration evaluate(GameOfLifeBoard gameOfLifeBoard) {
        Set<Cell> candidateCellsForStateChange = collectAliveAndNeighbouringCells(gameOfLifeBoard);

        Set<Cell> nextGenerationAliveCells = candidateCellsForStateChange
                .stream()
                .filter(cell -> isCellAliveInNextGeneration(cell, gameOfLifeBoard))
                .collect(Collectors.toSet());

        return new AliveGeneration(nextGenerationAliveCells);
    }

    private Set<Cell> collectAliveAndNeighbouringCells(GameOfLifeBoard gameOfLifeBoard) {
        return gameOfLifeBoard.getAliveGeneration().aliveCells()
                .stream()
                .flatMap(cell ->
                        Stream.concat(
                                Stream.of(cell),
                                cellNeighboursFinder.find(cell, gameOfLifeBoard).stream()))
                .collect(Collectors.toSet());
    }

    private boolean isCellAliveInNextGeneration(Cell cell, GameOfLifeBoard gameOfLifeBoard) {
        CellLifeState nextCellState = evaluateNextCellState(cell, gameOfLifeBoard);
        return nextCellState == CellLifeState.ALIVE;
    }

    private CellLifeState evaluateNextCellState(Cell cell, GameOfLifeBoard gameOfLifeBoard) {
        return cellStateEvaluator.evaluate(
                gameOfLifeBoard.getCellState(cell),
                cellNeighboursFinder.findWithState(cell, CellLifeState.ALIVE, gameOfLifeBoard).size());
    }
}
