package com.bgl.exercise.gameoflife.strategy;

import com.bgl.exercise.gameoflife.model.Cell;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EightNeighbourStrategy extends CellNeighbourStrategy {

    private static final EightNeighbourStrategy INSTANCE = new EightNeighbourStrategy();

    @Override
    public Set<Cell> getNeighbours(Cell cell) {
        return Arrays.stream(CellNeighbourOffset.values())
                .map(neighbourOffset -> getNeighbour(cell, neighbourOffset))
                .collect(Collectors.toSet());
    }

    public static EightNeighbourStrategy getInstance() {
        return INSTANCE;
    }
}
