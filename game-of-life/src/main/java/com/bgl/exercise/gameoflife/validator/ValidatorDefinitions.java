package com.bgl.exercise.gameoflife.validator;

import java.util.Set;

public enum ValidatorDefinitions {
    DEFAULT(Set.of(
            GridColumnsValidator.getInstance(),
            GridRowsValidator.getInstance(),
            OutOfBoundCellValidator.getInstance()
    ));

    private final Set<Validator> validators;

    ValidatorDefinitions(Set<Validator> validators) {
        this.validators = validators;
    }

    public Set<Validator> getValidators() {
        return validators;
    }
}
