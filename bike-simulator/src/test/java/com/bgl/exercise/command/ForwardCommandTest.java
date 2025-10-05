package com.bgl.exercise.command;

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
public class ForwardCommandTest {
    private ForwardCommand forwardCommand;
    @Mock
    private Bike bike;
    @Mock
    private Grid grid;
    @Mock
    private GridLocation nextGridLocation;

    @BeforeEach
    public void setUp() {
        forwardCommand = new ForwardCommand();
    }

    @Test
    public void shouldNotChangeBikeLocationWhenNextMoveIsOutOfGridBound() {
        when(bike.getNextGridLocation()).thenReturn(nextGridLocation);
        when(grid.isValidGridLocation(anyInt(), anyInt())).thenReturn(false);

        forwardCommand.execute(bike, grid);

        verify(bike, times(0)).setGridLocation(eq(nextGridLocation));
    }

    @Test
    public void shouldChangeBikeLocationWhenNextMoveIsWithinGridBound() {
        when(bike.getNextGridLocation()).thenReturn(nextGridLocation);
        when(grid.isValidGridLocation(anyInt(), anyInt())).thenReturn(true);

        forwardCommand.execute(bike, grid);

        verify(bike, times(1)).setGridLocation(eq(nextGridLocation));
    }
}
