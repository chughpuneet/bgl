# Bike Simulator

## Overview

This project simulates a bike moving on a 7x7 grid. The bike can move forward, turn left or right, and report its position using GPS commands. It ensures the bike **never exits the grid** and ignores invalid commands.

The project is designed following **SOLID principles**, **Command design pattern**, **Factory pattern**, **Singleton pattern** and **TDD practices** for maintainable and testable code.

---

## Features

- PLACE the bike at a specific location with a facing direction (NORTH, SOUTH, EAST, WEST)
- Move the bike **FORWARD** in the direction it is currently facing
- Rotate the bike **TURN_LEFT** or **TURN_RIGHT**
- Output the bike's current position and direction using **GPS_REPORT**
- Ignores invalid commands without breaking execution

---

## Design

- **Command**:  
  Each command (`PlaceCommand`, `ForwardCommand`, `TurnLeftCommand`, `TurnRightCommand`, `GpsReportCommand`) implements a `Command` interface and execute respective actions.

- **Direction**:
    - Interface: `FacingDirection`
    - Implementations: `NorthFacingDirection`, `EastFacingDirection`, `SouthFacingDirection`, `WestFacingDirection`
    - Handles direction-specific movement and rotation logic
    - Each direction is a **singleton**

- **Model & Controller**:
    - `Bike` maintains current position and facing direction on the `Grid`
    - `Grid` contains the information about rows (Y-axis) and columns (X-axis) and validates a location (x,y) exist on the grid
    - `BikeGridController` coordinates command execution for `Bike` on `Grid`

- **Factory & Parser**:
    - `CommandFactory` creates `Command` instances from respective user input
    - `PlaceCommandParser` parses PLACE command inputs

- **Validator**:
    - `InputValidator` validates user input
    - `InvalidUserInputException` is thrown when input is invalid
---

## Input Format

### Commands supported:
    - PLACE X,Y, DirectionFacing (NORTH | EAST | SOUTH | WEST)
    - FORWARD
    - TURN_LEFT
    - TURN_RIGHT
    - GPS_REPORT

---

## Start Application

- **Run the `BikeSimulatorApplication` to start **:  

---

| Category   | Tool / Framework            |
| ---------- | --------------------------- |
| Language   | Java 17                     |
| Build Tool | Maven                       |
| Testing    | JUnit 5, Mockito            |
| Design     | SOLID, Command Pattern, TDD |


Author: Puneet


