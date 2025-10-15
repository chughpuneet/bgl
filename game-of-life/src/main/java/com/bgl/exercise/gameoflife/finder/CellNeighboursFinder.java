package com.bgl.exercise.gameoflife.finder;

import com.bgl.exercise.gameoflife.model.Cell;
import java.util.Set;

public abstract class CellNeighboursFinder {
    public abstract Set<Cell> find(Cell cell);

    protected Cell getNeighbour(Cell cell, CellNeighbourOffset neighbourOffset) {
        return new Cell(cell.column() + neighbourOffset.getColumnOffset(), cell.row() + neighbourOffset.getRowOffset());
    }
}
