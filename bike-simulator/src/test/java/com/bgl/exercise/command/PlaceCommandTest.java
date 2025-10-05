package com.bgl.exercise.command;

import com.bgl.exercise.direction.FacingDirection;
import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;
import com.bgl.exercise.model.GridLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PlaceCommandTest {
    private PlaceCommand placeCommand;

    @Mock
    private Bike bike;
    @Mock
    private Grid grid;
    @Mock
    private GridLocation gridLocation;
    @Mock
    private FacingDirection facingDirection;

    @BeforeEach
    public void setUp() {
        placeCommand = new PlaceCommand(gridLocation, facingDirection);
    }

    @Test
    public void shouldNotPlaceBikeIfGridLocationIsOutOfGridBounds() {
        when(grid.isValidGridLocation(anyInt(), anyInt())).thenReturn(false);

        placeCommand.execute(bike, grid);

        verify(bike, times(0)).placeOnGrid(eq(gridLocation), eq(facingDirection));
    }

    @Test
    public void shouldPlaceBikeIfGridLocationIsWithinGridBounds() {
        when(grid.isValidGridLocation(anyInt(), anyInt())).thenReturn(true);

        placeCommand.execute(bike, grid);

        verify(bike, times(1)).placeOnGrid(eq(gridLocation), eq(facingDirection));
    }
}
