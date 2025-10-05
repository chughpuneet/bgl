package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;

public class SouthFacingDirection implements FacingDirection {
    private static final SouthFacingDirection INSTANCE = new SouthFacingDirection();

    private SouthFacingDirection() {
    }

    @Override
    public GridLocation getNextGridLocation(GridLocation currentGridLocation) {
        return new GridLocation(currentGridLocation.getColumn(),currentGridLocation.getRow() -1);
    }

    @Override
    public FacingDirection turnLeft() {
        return EastFacingDirection.getInstance();
    }

    @Override
    public FacingDirection turnRight() {
        return WestFacingDirection.getInstance();
    }

    @Override
    public String getName() {
        return "South";
    }

    public static FacingDirection getInstance() {
        return INSTANCE;
    }
}
