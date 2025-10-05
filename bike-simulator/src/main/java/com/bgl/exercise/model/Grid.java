package com.bgl.exercise.model;

public class Grid {
    private final int columns;
    private final int rows;

    public Grid(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public boolean isValidGridLocation(int column, int row) {
        return column >= 0 && column < this.columns && row >= 0 && row < this.rows;
    }
}
