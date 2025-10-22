package com.bgl.exercise.gameoflife.model;

import com.bgl.exercise.gameoflife.constant.CellLifeState;

public class GameOfLifeBoard {
    private final Grid grid;
    private AliveGeneration aliveGeneration;

    public GameOfLifeBoard(Grid grid, AliveGeneration aliveGeneration) {
        this.grid = grid;
        this.aliveGeneration = aliveGeneration;
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
