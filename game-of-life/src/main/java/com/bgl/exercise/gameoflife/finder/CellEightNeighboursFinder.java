package com.bgl.exercise.gameoflife.finder;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.model.Cell;
import com.bgl.exercise.gameoflife.model.GameOfLifeBoard;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CellEightNeighboursFinder extends CellNeighboursFinder {

    private static final CellEightNeighboursFinder INSTANCE = new CellEightNeighboursFinder();

    private Set<Cell> getNeighbours(Cell cell) {
        return Arrays.stream(CellNeighbourOffset.values())
                .map(neighbourOffset -> getNeighbour(cell, neighbourOffset))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Cell> find(Cell cell, GameOfLifeBoard gameOfLifeBoard) {
        return getNeighbours(cell)
                .stream()
                .filter(gameOfLifeBoard.getGrid()::isCellWithinGrid)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Cell> findWithState(Cell cell, CellLifeState cellLifeState, GameOfLifeBoard gameOfLifeBoard) {
        return getNeighbours(cell)
                .stream()
                .filter(neighbour -> gameOfLifeBoard.getCellState(neighbour) == cellLifeState)
                .collect(Collectors.toSet());
    }

    public static CellEightNeighboursFinder getInstance() {
        return INSTANCE;
    }
}
