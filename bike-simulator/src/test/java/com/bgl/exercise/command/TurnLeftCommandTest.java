package com.bgl.exercise.command;

import com.bgl.exercise.model.Bike;
import com.bgl.exercise.model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TurnLeftCommandTest {
    private TurnLeftCommand command;
    @Mock
    private Bike bike;
    @Mock
    private Grid grid;

    @BeforeEach
    public void setUp() {
        command = new TurnLeftCommand();
    }

    @Test
    public void shouldNotTurnLeftIfBikeNotPlaced() {
        when(bike.isPlaced()).thenReturn(false);

        command.execute(bike, grid);

        verify(bike, times(0)).turnLeft();
    }

    @Test
    public void shouldTurnLeftIfBikePlaced() {
        when(bike.isPlaced()).thenReturn(true);

        command.execute(bike, grid);

        verify(bike, times(1)).turnLeft();
    }
}
