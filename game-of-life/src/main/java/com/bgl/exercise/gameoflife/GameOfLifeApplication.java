package com.bgl.exercise.gameoflife;

import com.bgl.exercise.gameoflife.simulator.GameOfLifeSimulator;

public class GameOfLifeApplication {

    public static void main(String[] args) {
        int[][] aliveCellCoordinates = {
                {5, 5}, {6, 5}, {7, 5},
                {5, 6}, {6, 6}, {7, 6}
        };

        GameOfLifeSimulator gameSimulator = new GameOfLifeSimulator(200, 200, aliveCellCoordinates);

        try {
            for (int i = 0; i < 100; i++)
                System.out.printf("%d:%s%n", i + 1, gameSimulator.advanceToNextGeneration());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}