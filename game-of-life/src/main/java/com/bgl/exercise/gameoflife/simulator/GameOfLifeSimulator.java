package com.bgl.exercise.gameoflife.simulator;

import com.bgl.exercise.gameoflife.evaluator.GenerationEvaluator;
import com.bgl.exercise.gameoflife.model.AliveGeneration;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import com.bgl.exercise.gameoflife.rule.GameOfLifeRulesFactory;
import com.bgl.exercise.gameoflife.evaluator.CellStateEvaluator;
import com.bgl.exercise.gameoflife.finder.CellEightNeighboursFinder;
import com.bgl.exercise.gameoflife.validator.GameOfLifeBoardValidator;
import com.bgl.exercise.gameoflife.validator.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfLifeSimulator {

    private final GameOfLifeBoard gameOfLifeBoard;
    private final GenerationEvaluator generationEvaluator;
    private final GameOfLifeBoardValidator validator;

    public GameOfLifeSimulator(int columns, int rows, int[][] aliveCellCoordinates) {
        this.validator = new GameOfLifeBoardValidator(ValidatorFactory.getInstance().defaultValidators());
        this.generationEvaluator = new GenerationEvaluator(new CellStateEvaluator(GameOfLifeRulesFactory.getInstance().getGameOfLifeRules()));

        GameOfLifeBoard tempBoard = new GameOfLifeBoard(
                new Grid(columns, rows),
                new AliveGeneration(toGridCells(aliveCellCoordinates)),
                CellEightNeighboursFinder.getInstance());
        validateGameOfLifeBoard(tempBoard);
        this.gameOfLifeBoard = tempBoard;
    }

    public AliveGeneration advanceToNextGeneration() {
        AliveGeneration nextGeneration = generationEvaluator.evaluate(gameOfLifeBoard);
        gameOfLifeBoard.advanceToNextGeneration(nextGeneration);
        return nextGeneration;
    }

    private Set<Cell> toGridCells(int[][] cellCoordinates) {
        return Arrays.stream(cellCoordinates)
                .map(cellCoordinate -> new Cell(cellCoordinate[0], cellCoordinate[1]))
                .collect(Collectors.toSet());
    }

    private void validateGameOfLifeBoard(GameOfLifeBoard board) {
        Optional<List<String>> validationErrorMessages = validator.validate(board);
        if (validationErrorMessages.isPresent()) {
            throw new IllegalArgumentException(String.join("; ", validationErrorMessages.get()));
        }
    }
}
