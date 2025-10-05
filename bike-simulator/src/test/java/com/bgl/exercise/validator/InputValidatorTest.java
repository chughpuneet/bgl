package com.bgl.exercise.validator;

import com.bgl.exercise.constants.BikeSimulatorInput;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputValidatorTest {

    private final InputValidator validator = InputValidator.getInstance();

    @Test
    void shouldReturnEmptyWithInvalidCommand() {
        String input = "JUMP 1,2,NORTH";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyWithEmptyCommand() {
        String input = "";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnPlaceInputWithPlaceCommand() {
        String input = "PLACE 1,2,NORTH";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertTrue(result.isPresent());
        assertEquals(BikeSimulatorInput.PLACE, result.get());
    }

    @Test
    void shouldReturnForwardInputWithForwardCommand() {
        String input = "FORWARD";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertTrue(result.isPresent());
        assertEquals(BikeSimulatorInput.FORWARD, result.get());
    }

    @Test
    void shouldReturnTurnLeftInputWithTurnLeftCommand() {
        String input = "TURN_LEFT";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertTrue(result.isPresent());
        assertEquals(BikeSimulatorInput.TURN_LEFT, result.get());
    }

    @Test
    void shouldReturnTurnRightInputWithTurnRightCommand() {
        String input = "TURN_RIGHT";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertTrue(result.isPresent());
        assertEquals(BikeSimulatorInput.TURN_RIGHT, result.get());
    }

    @Test
    void shouldReturnGpsReportInputWithGpsReportCommand() {
        String input = "GPS_REPORT";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertTrue(result.isPresent());
        assertEquals(BikeSimulatorInput.GPS_REPORT, result.get());
    }

    @Test
    void shouldIgnoreSpacesInPlaceCommand() {
        String input = "  PLACE  3 ,  4 , EAST ";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertTrue(result.isPresent());
        assertEquals(BikeSimulatorInput.PLACE, result.get());
    }

    @Test
    void shouldValidateInputCaseInsensitive() {
        String input = "place 0,0,south";
        Optional<BikeSimulatorInput> result = validator.validateAndExtractCommand(input);

        assertTrue(result.isPresent());
        assertEquals(BikeSimulatorInput.PLACE, result.get());
    }
}
