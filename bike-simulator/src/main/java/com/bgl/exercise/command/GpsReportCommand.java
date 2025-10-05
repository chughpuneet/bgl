package com.bgl.exercise.command;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;

public class GpsReportCommand implements Command {
    @Override
    public void execute(Bike bike, Grid grid) {
        System.out.println(bike.getCurrentPosition());
    }
}
