package com.bgl.exercise.command;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;

public interface Command {
    void execute(Bike bike, Grid grid);
}
