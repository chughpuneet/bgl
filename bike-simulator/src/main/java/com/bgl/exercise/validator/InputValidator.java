package com.bgl.exercise.validator;

import com.bgl.exercise.constants.BikeSimulatorInput;
import java.util.Arrays;
import java.util.Optional;

public class InputValidator {

    private static final InputValidator INSTANCE = new InputValidator();
    private InputValidator() {}

    public static InputValidator getInstance() {
        return INSTANCE;
    }

    public Optional<BikeSimulatorInput> validateAndExtractCommand(String input) {
        return Arrays.stream(BikeSimulatorInput.values())
                .filter(bikeSimulatorInput -> bikeSimulatorInput.getInputPattern().matcher(input.trim()).matches())
                .findFirst();
    }
}