package com.bgl.exercise.direction;

import com.bgl.exercise.model.GridLocation;

public interface FacingDirection {
    GridLocation getNextGridLocation(GridLocation currentGridLocation);

    FacingDirection turnLeft();

    FacingDirection turnRight();

    String getName();
}
