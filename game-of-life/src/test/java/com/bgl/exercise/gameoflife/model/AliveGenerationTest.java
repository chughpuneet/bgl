package com.bgl.exercise.gameoflife.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AliveGenerationTest {

    private AliveGeneration aliveGeneration;

    @Test
    public void shouldReturnFalseForContainsWhenNoCellIsAlive() {
        aliveGeneration = new AliveGeneration(new Cell[]{});

        assertFalse(aliveGeneration.contains(new Cell(0, 0)));
    }

    @Test
    public void shouldReturnFalseForContainsWhenCellIsNotAlive() {
        aliveGeneration = new AliveGeneration(new Cell[]{new Cell(0, 0)});

        assertFalse(aliveGeneration.contains(new Cell(1, 1)));
    }

    @Test
    public void shouldReturnTrueForContainsWhenCellIsAlive() {
        aliveGeneration = new AliveGeneration(new Cell[]{new Cell(0, 0)});

        assertTrue(aliveGeneration.contains(new Cell(0, 0)));
    }
}
