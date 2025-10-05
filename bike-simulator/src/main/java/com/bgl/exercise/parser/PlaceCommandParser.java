package com.bgl.exercise.parser;

import com.bgl.exercise.command.Command;
import com.bgl.exercise.command.PlaceCommand;
import com.bgl.exercise.direction.EastFacingDirection;
import com.bgl.exercise.direction.FacingDirection;
import com.bgl.exercise.direction.NorthFacingDirection;
import com.bgl.exercise.direction.SouthFacingDirection;
import com.bgl.exercise.direction.WestFacingDirection;
import com.bgl.exercise.exception.InvalidUserInputException;
import com.bgl.exercise.model.GridLocation;
import java.util.Map;

public class PlaceCommandParser implements CommandParser {

    private static final PlaceCommandParser INSTANCE = new PlaceCommandParser();
    private static final String PLACE_COMMAND = "PLACE";

    private static final Map<String, FacingDirection> DIRECTION_MAP = Map.of(
            "NORTH", NorthFacingDirection.getInstance(),
            "SOUTH", SouthFacingDirection.getInstance(),
            "EAST",  EastFacingDirection.getInstance(),
            "WEST",  WestFacingDirection.getInstance()
    );

    private PlaceCommandParser() {}

    public static PlaceCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public Command parseCommand(String input) {
        String placeCommandAttributesInput = input.toUpperCase().replace(PLACE_COMMAND, "");
        String[] placeCommandAttributes = placeCommandAttributesInput.split(",");

        GridLocation location = getLocation(placeCommandAttributes);
        FacingDirection direction = getDirection(placeCommandAttributes);

        return new PlaceCommand(location, direction);
    }

    private GridLocation getLocation(String[] placeCommandAttributes) {
        int column = Integer.parseInt(placeCommandAttributes[0].trim());
        int row = Integer.parseInt(placeCommandAttributes[1].trim());
        return new GridLocation(column, row);
    }

    private FacingDirection getDirection(String[] placeCommandAttributes) {
        String directionStr = placeCommandAttributes[2].trim().toUpperCase();
        FacingDirection direction = DIRECTION_MAP.get(directionStr);

        if (direction == null) {
            throw new InvalidUserInputException("Invalid facing direction: " + directionStr);
        }

        return direction;
    }
}
