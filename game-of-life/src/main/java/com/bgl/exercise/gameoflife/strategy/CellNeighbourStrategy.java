package com.bgl.exercise.gameoflife.strategy;

import com.bgl.exercise.gameoflife.model.Cell;
import java.util.Set;

public abstract class CellNeighbourStrategy {
    public abstract Set<Cell> getNeighbours(Cell cell);

    protected Cell getNeighbour(Cell cell, CellNeighbourOffset neighbourOffset) {
        return new Cell(cell.column() + neighbourOffset.getColumnOffset(), cell.row() + neighbourOffset.getRowOffset());
    }
}
