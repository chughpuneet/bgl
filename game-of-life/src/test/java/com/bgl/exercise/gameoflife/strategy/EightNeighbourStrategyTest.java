package com.bgl.exercise.gameoflife.strategy;

import com.bgl.exercise.gameoflife.model.Cell;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EightNeighbourStrategyTest {

    private final EightNeighbourStrategy strategy = EightNeighbourStrategy.getInstance();

    @Test
    void testGetNeighboursForMiddleCell() {
        Cell cell = new Cell(2, 2);

        Set<Cell> neighbours = strategy.getNeighbours(cell);

        assertEquals(8, neighbours.size(), "Should return 8 neighbours");

        Set<Cell> expected = Set.of(
                new Cell(1, 1), new Cell(1, 2), new Cell(1, 3),
                new Cell(2, 1),                  new Cell(2, 3),
                new Cell(3, 1), new Cell(3, 2), new Cell(3, 3)
        );

        assertEquals(expected, neighbours);
    }

    @Test
    void testNeighboursDoNotIncludeOriginalCell() {
        Cell cell = new Cell(5, 5);
        Set<Cell> neighbours = strategy.getNeighbours(cell);
        assertFalse(neighbours.contains(cell));
    }
}
