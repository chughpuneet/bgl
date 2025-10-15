package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import java.util.Optional;

public class GridColumnsValidator implements Validator {
    private static final GridColumnsValidator INSTANCE = new GridColumnsValidator();

    private GridColumnsValidator() {}
    @Override
    public Optional<String> validateAndGetErrorMessage(GameOfLifeBoard board) {
        int gridColumns = board.getGrid().columns();

        if (gridColumns < 1) {
            return Optional.of("Grid columns must be greater than zero");
        }

        return Optional.empty();
    }

    public static GridColumnsValidator getInstance() {
        return INSTANCE;
    }
}
