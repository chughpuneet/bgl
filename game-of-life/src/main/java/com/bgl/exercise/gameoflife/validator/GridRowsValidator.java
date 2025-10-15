package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import java.util.Optional;

public class GridRowsValidator implements Validator {
    private static final GridRowsValidator INSTANCE = new GridRowsValidator();
    private GridRowsValidator() {}
    @Override
    public Optional<String> validateAndGetErrorMessage(GameOfLifeBoard board) {
        int gridRows = board.getGrid().rows();

        if (gridRows < 1) {
            return Optional.of("Grid rows must be greater than zero");
        }

        return Optional.empty();
    }

    public static GridRowsValidator getInstance() {
        return INSTANCE;
    }
}
