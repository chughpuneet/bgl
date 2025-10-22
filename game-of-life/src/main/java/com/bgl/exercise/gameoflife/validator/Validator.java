package com.bgl.exercise.gameoflife.validator;

import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;
import java.util.Optional;

public interface Validator {
    Optional<String> validate(GameOfLifeBoard board);
}
