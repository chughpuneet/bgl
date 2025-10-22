package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import com.bgl.exercise.gameoflife.model.Grid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GridColumnsValidatorTest {

    @Mock
    private GameOfLifeBoard board;

    @Mock
    private Grid grid;

    @Test
    void shouldReturnErrorMessageWhenColumnsLessThanOne() {
        when(board.getGrid()).thenReturn(grid);
        when(grid.columns()).thenReturn(0);

        Optional<String> result = GridColumnsValidator.getInstance().validate(board);

        assertTrue(result.isPresent());
        assertEquals("Grid columns must be greater than zero", result.get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenColumnsAreMoreThanZero() {
        when(board.getGrid()).thenReturn(grid);
        when(grid.columns()).thenReturn(1);

        Optional<String> result = GridColumnsValidator.getInstance().validate(board);

        assertTrue(result.isEmpty());
    }
}
