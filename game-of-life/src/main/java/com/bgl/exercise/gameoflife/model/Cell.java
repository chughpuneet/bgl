package com.bgl.exercise.gameoflife.model;

public record Cell(int column, int row) {
    @Override
    public String toString() {
        return String.format("[%d, %d]", column, row);
    }
}
