package com.bgl.exercise.gameoflife.validator;

import java.util.Set;

public final class ValidatorFactory {

    private static final ValidatorFactory INSTANCE = new ValidatorFactory();

    private ValidatorFactory() {
    }

    public Set<Validator> defaultValidators() {
        return Set.of(
                GridColumnsValidator.getInstance(),
                GridRowsValidator.getInstance(),
                OutOfBoundCellValidator.getInstance()
        );
    }

    public static ValidatorFactory getInstance() {
        return INSTANCE;
    }
}
