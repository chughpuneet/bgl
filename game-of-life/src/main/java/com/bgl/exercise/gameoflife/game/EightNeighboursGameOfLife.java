package com.bgl.exercise.gameoflife.game;

import com.bgl.exercise.gameoflife.controller.GameOfLifeController;
import com.bgl.exercise.gameoflife.model.AliveGeneration;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import com.bgl.exercise.gameoflife.rule.GameOfLifeRulesFactory;
import com.bgl.exercise.gameoflife.rule.RuleEngine;
import com.bgl.exercise.gameoflife.strategy.EightNeighboursStrategy;
import com.bgl.exercise.gameoflife.validator.GameOfLifeBoardValidator;
import com.bgl.exercise.gameoflife.validator.ValidatorFactory;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EightNeighboursGameOfLife {

    private final GameOfLifeBoard gameOfLifeBoard;
    private final GameOfLifeController controller;
    private final GameOfLifeBoardValidator validator;

    public EightNeighboursGameOfLife(int columns, int rows, int[][] aliveCellCoordinates) {
        this.gameOfLifeBoard = new GameOfLifeBoard(new Grid(columns, rows),
                new AliveGeneration(toGridCells(aliveCellCoordinates)),
                EightNeighboursStrategy.getInstance());
        this.controller = new GameOfLifeController(new RuleEngine(GameOfLifeRulesFactory.getInstance().getGameOfLifeRules()));
        this.validator = new GameOfLifeBoardValidator(ValidatorFactory.getInstance().defaultValidators());
        validateGameOfLifeBoard();
    }

    public AliveGeneration advanceToNextGeneration() {
        AliveGeneration nextGeneration = controller.computeNextGeneration(gameOfLifeBoard);
        gameOfLifeBoard.advanceToNextGeneration(nextGeneration);
        return nextGeneration;
    }

    private Set<Cell> toGridCells(int[][] cellCoordinates) {
        return Arrays.stream(cellCoordinates)
                .map(cellCoordinate -> new Cell(cellCoordinate[0], cellCoordinate[1]))
                .collect(Collectors.toSet());
    }

    private void validateGameOfLifeBoard() {
        Optional<String> validationErrorMessage = validator.validate(gameOfLifeBoard);
        if (validationErrorMessage.isPresent()) {
            throw new IllegalArgumentException(validationErrorMessage.get());
        }
    }
}
