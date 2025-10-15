# Game of Life Simulator

## Overview

This project simulates **Conway's Game of Life** on a configurable 2D grid. Each cell can be **alive** or **dead**, and the grid evolves generation by generation based on standard rules:

- Any live cell with fewer than 2 live neighbours dies (**underpopulation**)
- Any live cell with 2 or 3 live neighbours survives (**survival**)
- Any live cell with more than 3 live neighbours dies (**overpopulation**)
- Any dead cell with exactly 3 live neighbours becomes alive (**reproduction**)

The project is designed following **SOLID principles**, **Singleton pattern**, **Factory pattern**, **Strategy pattern**, **Rule Engine pattern**, and **TDD practices** for maintainable and testable code.

---

## Features

- Configurable **grid size**
- Place initial **alive cells** at specific coordinates
- Advance the simulation **generation by generation**
- Rule-based evolution using a **RuleEngine**
- Validate **grid dimensions** and **cell positions**
- Support for **eight-neighbour** cell evaluation
- Output the alive cells at each generation

---

## Design

- **Rules**:  
  Each rule (`AliveCellUnderPopulationDeathRule`, `AliveCellOverPopulationDeathRule`, `AliveCellSurvivalRule`, `DeadCellReproductionRule`) implements `StateTransitionRule` and defines conditions for a cell’s next state. All rules are implemented as **Singletons** to avoid multiple instances.

- **Rule Engine**:
    - `RuleEngine` applies all rules to determine a cell's next state in a generation.
    - Follows **Open/Closed principle**, allowing easy addition of new rules.

- **Neighbour Strategy**:
    - `CellNeighbourStrategy` abstract class defines how to fetch neighbours.
    - `EightNeighbourStrategy` implements the classic eight-neighbour logic using the **Strategy pattern** and is a **Singleton**.

- **Models & State**:
    - `Cell`: Represents a single cell with `row` and `column`.
    - `Grid`: Represents the 2D grid and validates if a cell is within bounds.
    - `AliveGeneration`: Maintains the **current state of alive cells** for a generation.
    - `GameOfLifeBoard`: Combines `Grid` and `AliveGeneration`, exposes neighbour and state operations, and acts as the **central model holding the current generation state**.

- **Validator**:
    - Ensures board integrity using `Validator` interface implementations: `GridRowsValidator`, `GridColumnsValidator`, `OutOfBoundCellValidator`.
    - Validators are implemented as **Singletons**.
    - `ValidatorFactory` acts as a **Factory** to create pre-defined sets of validators for the board.

- **Controller / Application**:
    - `EightNeighbourGameOfLife` acts as the **controller** coordinating the simulation.
    - It interacts with models, applies rules via the `RuleEngine`, and advances generations.
    - Responsible for exposing methods like `advanceToNextGeneration()` to manage the simulation flow.

---

## Input Format

### Initial setup:
- Grid dimensions (columns, rows) (x, y)
- Initial alive cells coordinates (e.g., `{{5,5},{6,5},{7,5}}`)

### Simulation:
- Advance the simulation to the **next generation** using `advanceToNextGeneration()` method

---

## Start Application

- **Run `GameOfLifeApplication` main class** to start the simulation.

---

| Category   | Tool / Framework                                         |
| ---------- | -------------------------------------------------------- |
| Language   | Java 17                                                  |
| Build Tool | Maven                                                    |
| Testing    | JUnit 5, Mockito                                         |
| Design     | SOLID principles, Strategy Pattern, Rule Engine,        |
|           | Factory Pattern, Singleton Pattern, TDD practices       |

---

Author: Puneet
