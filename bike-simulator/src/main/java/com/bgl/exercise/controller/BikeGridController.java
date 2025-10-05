package com.bgl.exercise.controller;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;
import com.bgl.exercise.factory.CommandFactory;

public class BikeGridController {
    private final Bike bike;
    private final Grid grid;

    public BikeGridController(Bike bike, Grid grid) {
        this.grid = grid;
        this.bike = bike;
    }

    public void executeCommand(String commandStr) {
        CommandFactory.getInstance().buildCommand(commandStr).execute(bike, grid);
    }
}
