package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class OutOfBoundCellValidator implements Validator {
    private static final OutOfBoundCellValidator INSTANCE = new OutOfBoundCellValidator();

    private OutOfBoundCellValidator() {
    }

    @Override
    public Optional<String> validateAndGetErrorMessage(GameOfLifeBoard board) {
        if(board.getGrid().columns() > 0 && board.getGrid().rows() > 0){
            List<Cell> outOfBoundCells = getOutOfBoundsCell(board.getAliveGeneration().aliveCells(), board.getGrid());
            if (!outOfBoundCells.isEmpty()) {
                return Optional.of(String.format("%s cells out of grid bounds", outOfBoundCells));
            }
        }
        return Optional.empty();
    }

    public static OutOfBoundCellValidator getInstance() {
        return INSTANCE;
    }

    private List<Cell> getOutOfBoundsCell(Collection<Cell> cells, Grid grid) {
        return cells.stream()
                .filter(cell -> !grid.isCellWithinGrid(cell))
                .toList();
    }
}
