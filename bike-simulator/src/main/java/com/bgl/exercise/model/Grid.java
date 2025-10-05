package com.bgl.exercise.model;

public class Grid {
    private final int columns;
    private final int rows;

    public Grid(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public boolean isValidGridLocation(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < this.columns && yCoordinate >= 0 && yCoordinate < this.rows;
    }
}
