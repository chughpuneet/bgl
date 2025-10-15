package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GridRowsValidatorTest {

    @Mock
    private GameOfLifeBoard board;

    @Mock
    private Grid grid;

    @Test
    void shouldReturnErrorMessageWhenRowsLessThanOne() {
        when(board.getGrid()).thenReturn(grid);
        when(grid.rows()).thenReturn(0);

        Optional<String> result = GridRowsValidator.getInstance().validateAndGetErrorMessage(board);

        assertTrue(result.isPresent());
        assertEquals("Grid rows must be greater than zero", result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenRowsAreMoreThanZero() {
        when(board.getGrid()).thenReturn(grid);
        when(grid.rows()).thenReturn(1);

        Optional<String> result = GridRowsValidator.getInstance().validateAndGetErrorMessage(board);

        assertTrue(result.isEmpty());
    }
}
