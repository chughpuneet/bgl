package com.bgl.exercise.parser;

import com.bgl.exercise.command.Command;
import com.bgl.exercise.command.PlaceCommand;
import com.bgl.exercise.exception.InvalidUserInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaceCommandParserTest {

    private final PlaceCommandParser parser = PlaceCommandParser.getInstance();

    @Test
    void shouldThrowInvalidUserInputExceptionWhenInvalidDirectionIsPassed() {
        String input = "PLACE 1,2,UP";

        InvalidUserInputException exception = assertThrows(
                InvalidUserInputException.class,
                () -> parser.parseCommand(input)
        );

        assertEquals("Invalid facing direction: UP", exception.getMessage());
    }

    @Test
    void shouldReturnPlaceCommandWithValidNorthInput() {
        String input = "PLACE 1,2,NORTH";

        Command command = parser.parseCommand(input);

        assertInstanceOf(PlaceCommand.class, command);
    }

    @Test
    void shouldReturnPlaceCommandWithValidSouthInput() {
        String input = "PLACE 0,0,SOUTH";

        Command command = parser.parseCommand(input);

        assertInstanceOf(PlaceCommand.class, command);
    }

    @Test
    void shouldReturnPlaceCommandWithValidEastInput() {
        String input = "PLACE 0,0,EAST";

        Command command = parser.parseCommand(input);

        assertInstanceOf(PlaceCommand.class, command);
    }

    @Test
    void shouldReturnPlaceCommandWithValidWestInput() {
        String input = "PLACE 0,0,WEST";

        Command command = parser.parseCommand(input);

        assertInstanceOf(PlaceCommand.class, command);
    }

    @Test
    void shouldIgnoreExtraSpacesInInput() {
        String input = "  PLACE   2 ,  3 , EAST  ";

        Command command = parser.parseCommand(input);

        assertInstanceOf(PlaceCommand.class, command);
    }

    @Test
    void shouldParseInputCaseInsensitive() {
        String input = "Place 0,0,West";

        Command command = parser.parseCommand(input);

        assertInstanceOf(PlaceCommand.class, command);
    }
}
