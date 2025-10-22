package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import com.bgl.exercise.gameoflife.model.AliveGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OutOfBoundCellValidatorTest {

    @Mock
    private GameOfLifeBoard board;

    @Mock
    private Grid grid;

    @Mock
    private AliveGeneration aliveGeneration;

    @Test
    void shouldReturnErrorMessageWhenCellsOutOfBounds() {
        Cell inBoundsCell = new Cell(1, 1);
        Cell outOfBoundsCell = new Cell(5, 5);

        when(board.getGrid()).thenReturn(grid);
        when(grid.columns()).thenReturn(3);
        when(grid.rows()).thenReturn(3);
        when(grid.isCellWithinGrid(inBoundsCell)).thenReturn(true);
        when(grid.isCellWithinGrid(outOfBoundsCell)).thenReturn(false);

        when(board.getAliveGeneration()).thenReturn(aliveGeneration);
        when(aliveGeneration.aliveCells()).thenReturn(Set.of(inBoundsCell, outOfBoundsCell));

        Optional<String> result = OutOfBoundCellValidator.getInstance().validate(board);

        assertTrue(result.isPresent());
        assertEquals(String.format("%s cells out of grid bounds", List.of(outOfBoundsCell)), result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenAllCellsInBounds() {
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 2);

        when(board.getGrid()).thenReturn(grid);
        when(grid.columns()).thenReturn(3);
        when(grid.rows()).thenReturn(3);
        when(grid.isCellWithinGrid(cell1)).thenReturn(true);
        when(grid.isCellWithinGrid(cell2)).thenReturn(true);

        when(board.getAliveGeneration()).thenReturn(aliveGeneration);
        when(aliveGeneration.aliveCells()).thenReturn(Set.of(cell1, cell2));

        Optional<String> result = OutOfBoundCellValidator.getInstance().validate(board);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotValidateWhenGridHasNoColumns() {
        when(board.getGrid()).thenReturn(grid);
        when(grid.columns()).thenReturn(0);

        Optional<String> result = OutOfBoundCellValidator.getInstance().validate(board);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotValidateWhenGridHasNoRows() {
        when(board.getGrid()).thenReturn(grid);
        when(grid.columns()).thenReturn(1);
        when(grid.rows()).thenReturn(0);

        Optional<String> result = OutOfBoundCellValidator.getInstance().validate(board);

        assertTrue(result.isEmpty());
    }
}
