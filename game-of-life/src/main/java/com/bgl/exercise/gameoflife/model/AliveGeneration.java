package com.bgl.exercise.gameoflife.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public record AliveGeneration(Set<Cell> aliveCells) {

    public AliveGeneration {
        aliveCells = Set.copyOf(aliveCells);
    }

    public AliveGeneration(Cell[] aliveCells) {
        this(Arrays.stream(aliveCells)
                .collect(Collectors.toSet()));
    }

    public boolean contains(Cell cell) {
        return aliveCells.contains(cell);
    }

    @Override
    public String toString() {
        return aliveCells.toString();
    }
}
