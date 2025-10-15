package com.bgl.exercise.gameoflife.finder;

public enum CellNeighbourOffset {
    TOP(0, -1),
    TOP_RIGHT(1, -1),
    RIGHT(1, 0),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(0, 1),
    BOTTOM_LEFT(-1, 1),
    LEFT(-1, 0),
    TOP_LEFT(-1, -1);

    private final int columnOffset;
    private final int rowOffset;

    CellNeighbourOffset(int columnOffset, int rowOffset) {
        this.columnOffset = columnOffset;
        this.rowOffset = rowOffset;
    }

    public int getColumnOffset() {
        return columnOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }
}
