package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;

public class NorthFacingDirection implements FacingDirection {

    private static final NorthFacingDirection INSTANCE = new NorthFacingDirection();

    private NorthFacingDirection() {
    }

    @Override
    public GridLocation getNextGridLocation(GridLocation currentGridLocation) {
        return new GridLocation(currentGridLocation.getColumn(),currentGridLocation.getRow() + 1);
    }

    @Override
    public FacingDirection turnLeft() {
        return WestFacingDirection.getInstance();
    }

    @Override
    public FacingDirection turnRight() {
        return EastFacingDirection.getInstance();
    }

    @Override
    public String getName() {
        return "North";
    }

    public static NorthFacingDirection getInstance() {
        return INSTANCE;
    }
}
