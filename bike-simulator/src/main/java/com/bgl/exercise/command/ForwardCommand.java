package com.bgl.exercise.command;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;
import com.bgl.exercise.model.GridLocation;

public class ForwardCommand implements Command {
    @Override
    public void execute(Bike bike, Grid grid) {
        GridLocation nextGridLocation = bike.getNextGridLocation();
        if (grid.isValidGridLocation(nextGridLocation.getRow(), nextGridLocation.getColumn())) {
            bike.setGridLocation(nextGridLocation);
        }
    }
}
