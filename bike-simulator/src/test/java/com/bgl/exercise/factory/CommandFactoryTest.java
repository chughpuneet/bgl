package com.bgl.exercise.factory;

import com.bgl.exercise.command.Command;
import com.bgl.exercise.command.ForwardCommand;
import com.bgl.exercise.command.GpsReportCommand;
import com.bgl.exercise.command.TurnLeftCommand;
import com.bgl.exercise.command.TurnRightCommand;
import com.bgl.exercise.command.PlaceCommand;
import com.bgl.exercise.exception.InvalidUserInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandFactoryTest {

    private final CommandFactory factory = CommandFactory.getInstance();

    @Test
    void shouldThrowExceptionForEmptyInput() {
        String input = "";

        assertThrows(
                InvalidUserInputException.class,
                () -> factory.buildCommand(input)
        );
    }

    @Test
    void shouldThrowExceptionForInvalidCommand() {
        String input = "JUMP 1,2,NORTH";

        InvalidUserInputException exception = assertThrows(
                InvalidUserInputException.class,
                () -> factory.buildCommand(input)
        );

        assertEquals("Invalid user input: JUMP 1,2,NORTH", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForMalformedPlaceCommand() {
        String input = "PLACE 1,2";

        assertThrows(
                InvalidUserInputException.class,
                () -> factory.buildCommand(input)
        );
    }

    @Test
    void shouldReturnPlaceInputWithPlaceCommand() {
        String input = "PLACE 1,2,NORTH";

        Command command = factory.buildCommand(input);

        assertInstanceOf(PlaceCommand.class, command);
    }

    @Test
    void shouldReturnForwardCommandForForwardInput() {
        Command command = factory.buildCommand("FORWARD");

        assertInstanceOf(ForwardCommand.class, command);
    }

    @Test
    void shouldReturnTurnLeftCommandForTurnLeftInput() {
        Command command = factory.buildCommand("TURN_LEFT");

        assertInstanceOf(TurnLeftCommand.class, command);
    }

    @Test
    void shouldReturnTurnRightCommandForTurnRightInput() {
        Command command = factory.buildCommand("TURN_RIGHT");

        assertInstanceOf(TurnRightCommand.class, command);
    }

    @Test
    void shouldReturnGpsReportCommandForGpsReportInput() {
        Command command = factory.buildCommand("GPS_REPORT");

        assertInstanceOf(GpsReportCommand.class, command);
    }

    @Test
    void shouldReturnForwardCommandForLowerCaseInput() {
        Command command = factory.buildCommand("forward");

        assertInstanceOf(ForwardCommand.class, command);
    }

    @Test
    void shouldReturnTurnRightCommandForInputWithExtraSpaces() {
        Command command = factory.buildCommand("   TURN_RIGHT   ");

        assertInstanceOf(TurnRightCommand.class, command);
    }
}
