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
public class GpsReportCommandTest {
    private GpsReportCommand command;
    @Mock
    private Bike bike;
    @Mock
    private Grid grid;

    @BeforeEach
    public void setUp() {
        command = new GpsReportCommand();
    }

    @Test
    public void shouldNotPrintReportIfBikeNotPlaced() {
        when(bike.isPlaced()).thenReturn(false);

        command.execute(bike, grid);

        verify(bike, times(0)).getCurrentPosition();
    }

    @Test
    public void shouldPrintReportIfBikePlaced() {
        when(bike.isPlaced()).thenReturn(true);

        command.execute(bike, grid);

        verify(bike, times(1)).getCurrentPosition();
    }
}
