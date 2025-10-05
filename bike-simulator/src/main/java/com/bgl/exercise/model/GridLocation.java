package com.bgl.exercise.model;

public class GridLocation {
    private int column;
    private int row;

    public GridLocation(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String toString() {
        return String.format("(%d, %d)", column, row);
    }
}
