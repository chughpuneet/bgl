package com.bgl.exercise.command;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;

public class TurnLeftCommand implements Command {

    @Override
    public void execute(Bike bike, Grid grid) {
        bike.turnLeft();
    }
}
