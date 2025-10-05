package com.bgl.exercise.command;

import com.bgl.exercise.direction.FacingDirection;
import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;
import com.bgl.exercise.model.GridLocation;

public class PlaceCommand implements Command {
    private final GridLocation gridLocation;
    private final FacingDirection direction;

    public PlaceCommand(GridLocation gridLocation, FacingDirection direction) {
        this.gridLocation = gridLocation;
        this.direction = direction;
    }

    @Override
    public void execute(Bike bike, Grid grid) {
        if (grid.isValidGridLocation(gridLocation.getRow(), gridLocation.getColumn())) {
            bike.placeOnGrid(gridLocation, direction);
        }

    }
}
