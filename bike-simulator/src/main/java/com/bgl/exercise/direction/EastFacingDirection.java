package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;

public class EastFacingDirection implements FacingDirection {

    private static final EastFacingDirection INSTANCE = new EastFacingDirection();

    private EastFacingDirection() {
    }

    @Override
    public GridLocation getNextGridLocation(GridLocation currentGridLocation) {
        return new GridLocation(currentGridLocation.getColumn() + 1, currentGridLocation.getRow());
    }

    @Override
    public FacingDirection turnLeft() {
        return NorthFacingDirection.getInstance();
    }

    @Override
    public FacingDirection turnRight() {
        return SouthFacingDirection.getInstance();
    }

    @Override
    public String getName() {
        return "East";
    }

    public static FacingDirection getInstance() {
        return INSTANCE;
    }
}
