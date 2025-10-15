package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GameOfLifeBoardValidator {
    private final Set<Validator> validators;

    public GameOfLifeBoardValidator(Set<Validator> validators) {
        this.validators = validators;
    }

    public Optional<String> validate(GameOfLifeBoard gameOfLifeBoard) {
        List<String> validationErrorMessages = validators.stream()
                .map(v -> v.validateAndGetErrorMessage(gameOfLifeBoard))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (!validationErrorMessages.isEmpty()) {
            return Optional.of(String.join("; ", validationErrorMessages));
        }

        return Optional.empty();
    }
}
