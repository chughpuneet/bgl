package com.bgl.exercise.factory;

import com.bgl.exercise.command.Command;
import com.bgl.exercise.command.ForwardCommand;
import com.bgl.exercise.command.GpsReportCommand;
import com.bgl.exercise.command.TurnLeftCommand;
import com.bgl.exercise.command.TurnRightCommand;
import com.bgl.exercise.exception.InvalidUserInputException;
import com.bgl.exercise.parser.PlaceCommandParser;
import com.bgl.exercise.constants.BikeSimulatorInput;
import com.bgl.exercise.validator.InputValidator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class CommandFactory {

    private static final CommandFactory INSTANCE = new CommandFactory();

    private final Map<BikeSimulatorInput, Function<String, Command>> INPUT_COMMAND_REGISTRY = new HashMap<>();

    private CommandFactory() {
        registerCommands();
    }

    private void registerCommands() {
        INPUT_COMMAND_REGISTRY.put(
                BikeSimulatorInput.PLACE,
                input -> PlaceCommandParser.getInstance().parseCommand(input)
        );

        INPUT_COMMAND_REGISTRY.put(BikeSimulatorInput.FORWARD, input -> new ForwardCommand());
        INPUT_COMMAND_REGISTRY.put(BikeSimulatorInput.TURN_LEFT, input -> new TurnLeftCommand());
        INPUT_COMMAND_REGISTRY.put(BikeSimulatorInput.TURN_RIGHT, input -> new TurnRightCommand());
        INPUT_COMMAND_REGISTRY.put(BikeSimulatorInput.GPS_REPORT, input -> new GpsReportCommand());
    }

    public Command buildCommand(String input) {
        Optional<BikeSimulatorInput> bikeSimulatorInput =
                InputValidator.getInstance().validateAndExtractCommand(input);

        if(bikeSimulatorInput.isPresent()) {
            return INPUT_COMMAND_REGISTRY.get(bikeSimulatorInput.get()).apply(input);
        }

       throw new InvalidUserInputException(String.format("Invalid user input: %s", input));
    }

    public static CommandFactory getInstance() {
        return INSTANCE;
    }
}
