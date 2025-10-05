package com.bgl.exercise;

import com.bgl.exercise.controller.BikeGridController;
import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;

import java.util.Scanner;

public class BikeSimulatorApplication {
    public static void main(String[] args) {
        Grid grid = new Grid(7, 7);
        Bike bike = new Bike();
        BikeGridController controller = new BikeGridController(bike, grid);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter commands (e.g., PLACE 0,0,NORTH, FORWARD, TURN_LEFT, GPS_REPORT). Ctrl+D to exit.");

        while (sc.hasNextLine()) {
            try {
                controller.executeCommand(sc.nextLine().trim());
            }  catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
