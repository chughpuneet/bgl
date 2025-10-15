package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameOfLifeBoardValidatorTest {

    @Mock
    private Validator validator1;
    @Mock
    private Validator validator2;
    @Mock
    private GameOfLifeBoard gameOfLifeBoard;

    @Test
    void shouldReturnEmptyOptionalWhenNoValidatorsPresent() {
        GameOfLifeBoardValidator boardValidator = new GameOfLifeBoardValidator(Set.of());
        Optional<List<String>> result = boardValidator.validate(gameOfLifeBoard);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalWhenAllValidatorsPass() {
        when(validator1.validateAndGetErrorMessage(gameOfLifeBoard)).thenReturn(Optional.empty());
        when(validator2.validateAndGetErrorMessage(gameOfLifeBoard)).thenReturn(Optional.empty());

        GameOfLifeBoardValidator boardValidator = new GameOfLifeBoardValidator(Set.of(validator1, validator2));
        Optional<List<String>> result = boardValidator.validate(gameOfLifeBoard);

        assertTrue(result.isEmpty());
        verify(validator1).validateAndGetErrorMessage(gameOfLifeBoard);
        verify(validator2).validateAndGetErrorMessage(gameOfLifeBoard);
    }

    @Test
    void shouldReturnErrorMessageWhenSomeValidatorsFail() {
        when(validator1.validateAndGetErrorMessage(gameOfLifeBoard)).thenReturn(Optional.of("Columns invalid"));
        when(validator2.validateAndGetErrorMessage(gameOfLifeBoard)).thenReturn(Optional.empty());

        GameOfLifeBoardValidator boardValidator = new GameOfLifeBoardValidator(Set.of(validator1, validator2));
        Optional<List<String>> result = boardValidator.validate(gameOfLifeBoard);

        assertTrue(result.isPresent());
        assertTrue(result.get().contains("Columns invalid"));
    }

    @Test
    void shouldReturnConcatenatedErrorMessagesWhenMultipleValidatorsFail() {
        when(validator1.validateAndGetErrorMessage(gameOfLifeBoard)).thenReturn(Optional.of("Columns invalid"));
        when(validator2.validateAndGetErrorMessage(gameOfLifeBoard)).thenReturn(Optional.of("Rows invalid"));

        GameOfLifeBoardValidator boardValidator = new GameOfLifeBoardValidator(Set.of(validator1, validator2));
        Optional<List<String>> result = boardValidator.validate(gameOfLifeBoard);

        assertTrue(result.isPresent());
        assertTrue(result.get().contains("Columns invalid"));
        assertTrue(result.get().contains("Rows invalid"));
    }
}
