package com.bgl.exercise.gameoflife.finder;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;

import java.util.Set;

public abstract class CellNeighboursFinder {

    public abstract Set<Cell> find(Cell cell, GameOfLifeBoard gameOfLifeBoard);
    public abstract Set<Cell> findWithState(Cell cell, CellLifeState cellLifeState, GameOfLifeBoard gameOfLifeBoard);

    protected Cell getNeighbour(Cell cell, CellNeighbourOffset neighbourOffset) {
        return new Cell(cell.column() + neighbourOffset.getColumnOffset(), cell.row() + neighbourOffset.getRowOffset());
    }
}
