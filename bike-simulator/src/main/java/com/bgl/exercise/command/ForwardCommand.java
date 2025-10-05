package com.bgl.exercise.command;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;
import com.bgl.exercise.model.GridLocation;

public class ForwardCommand implements Command {
    @Override
    public void execute(Bike bike, Grid grid) {
        if(bike.isPlaced()){
            GridLocation nextGridLocation = bike.getNextGridLocation();
            if (grid.isValidGridLocation(nextGridLocation.getColumn(), nextGridLocation.getRow())) {
                bike.setGridLocation(nextGridLocation);
            }
        }else {
            System.out.println("Bike is not placed");
        }
    }
}
