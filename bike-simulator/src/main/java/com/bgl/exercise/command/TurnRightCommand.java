package com.bgl.exercise.command;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;

public class TurnRightCommand implements Command {
    @Override
    public void execute(Bike bike, Grid grid) {
        if(bike.isPlaced()) {
            bike.turnRight();
        }else {
            System.out.println("Bike is not placed");
        }
    }
}
