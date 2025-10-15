package com.bgl.exercise.gameoflife;

import com.bgl.exercise.gameoflife.game.EightNeighboursGameOfLife;

public class GameOfLifeApplication {

    public static void main(String[] args) {
        int[][] aliveCellCoordinates = {
                {5, 5}, {6, 5}, {7, 5},
                {5, 6}, {6, 6}, {7, 6}
        };

        EightNeighboursGameOfLife game = new EightNeighboursGameOfLife(200, 200, aliveCellCoordinates);

        try {
            for (int i = 0; i < 100; i++)
                System.out.printf("%d:%s%n", i + 1, game.advanceToNextGeneration());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}