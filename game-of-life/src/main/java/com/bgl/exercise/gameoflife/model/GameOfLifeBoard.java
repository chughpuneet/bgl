package com.bgl.exercise.gameoflife.model;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.finder.CellNeighboursFinder;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfLifeBoard {
    private final Grid grid;
    private AliveGeneration aliveGeneration;
    private final CellNeighboursFinder cellNeighboursFinder;

    public GameOfLifeBoard(Grid grid, AliveGeneration aliveGeneration, CellNeighboursFinder cellNeighboursFinder) {
        this.grid = grid;
        this.aliveGeneration = aliveGeneration;
        this.cellNeighboursFinder = cellNeighboursFinder;
    }

    public Set<Cell> getNeighbours(Cell cell) {
        return cellNeighboursFinder.find(cell)
                .stream()
                .filter(grid::isCellWithinGrid)
                .collect(Collectors.toSet());
    }

    public Set<Cell> getNeighboursWithState(Cell cell, CellLifeState cellLifeState) {
        return getNeighbours(cell)
                .stream()
                .filter(neighbour -> getCellState(neighbour) == cellLifeState)
                .collect(Collectors.toSet());
    }

    public CellLifeState getCellState(Cell cell) {
        return aliveGeneration.contains(cell) ? CellLifeState.ALIVE : CellLifeState.DEAD;
    }

    public void advanceToNextGeneration(AliveGeneration nextGeneration) {
        this.aliveGeneration = nextGeneration;
    }

    public AliveGeneration getAliveGeneration() {
        return aliveGeneration;
    }

    public Grid getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        return aliveGeneration.toString();
    }
}
