package com.bgl.exercise.gameoflife.finder;

import com.bgl.exercise.gameoflife.model.Cell;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CellEightNeighboursFinder extends CellNeighboursFinder {

    private static final CellEightNeighboursFinder INSTANCE = new CellEightNeighboursFinder();

    @Override
    public Set<Cell> find(Cell cell) {
        return Arrays.stream(CellNeighbourOffset.values())
                .map(neighbourOffset -> getNeighbour(cell, neighbourOffset))
                .collect(Collectors.toSet());
    }

    public static CellEightNeighboursFinder getInstance() {
        return INSTANCE;
    }
}
