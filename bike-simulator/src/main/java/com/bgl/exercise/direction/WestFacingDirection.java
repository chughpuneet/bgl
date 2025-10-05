package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;

public class WestFacingDirection implements FacingDirection {
    private static final WestFacingDirection INSTANCE = new WestFacingDirection();

    @Override
    public GridLocation getNextGridLocation(GridLocation currentGridLocation) {
        return new GridLocation(currentGridLocation.getColumn() - 1, currentGridLocation.getRow());
    }

    @Override
    public FacingDirection turnLeft() {
        return SouthFacingDirection.getInstance();
    }

    @Override
    public FacingDirection turnRight() {
        return NorthFacingDirection.getInstance();
    }

    @Override
    public String getName() {
        return "West";
    }

    public static FacingDirection getInstance() {
        return INSTANCE;
    }
}
