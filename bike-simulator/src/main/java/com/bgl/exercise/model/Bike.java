package com.bgl.exercise.model;

import com.bgl.exercise.direction.FacingDirection;

public class Bike {
    private FacingDirection currentFacingDirection;
    private GridLocation gridLocation;
    private boolean placed;

    public Bike() {
    }

    public GridLocation getNextGridLocation() {
        return this.currentFacingDirection.getNextGridLocation(gridLocation);
    }

    public void turnLeft() {
        this.currentFacingDirection = this.currentFacingDirection.turnLeft();
    }

    public void turnRight() {
        this.currentFacingDirection = this.currentFacingDirection.turnRight();
    }

    public void placeOnGrid(GridLocation gridLocation, FacingDirection direction) {
        this.gridLocation = gridLocation;
        this.currentFacingDirection = direction;
        placed = true;
    }

    public void setGridLocation(GridLocation gridLocation) {
        this.gridLocation = gridLocation;
    }

    public String getCurrentPosition() {
        if (!placed) return "Bike not placed yet";
        return String.format("(%d,%d), %s", gridLocation.getColumn(), gridLocation.getRow(), currentFacingDirection.getName());
    }

}
