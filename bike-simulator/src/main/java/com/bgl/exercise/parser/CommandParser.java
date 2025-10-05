package com.bgl.exercise.parser;

import com.bgl.exercise.command.Command;

public interface CommandParser {
    Command parseCommand(String input);
}
