package com.bgl.exercise.gameoflife.model;

public record Grid(int columns, int rows) {

    public boolean isCellWithinGrid(Cell cell) {
        return cell.column() >= 0 && cell.column() < columns && cell.row() >= 0 && cell.row() < rows;
    }
}
