package com.oleksii.constants;

import java.util.stream.Stream;

public enum Command {

    ADD("@add"),
    GET("@get"),
    HELP("@help"),
    LIST("@list");

    private final String commandName;

    Command(String commandName) {
        this.commandName = commandName;
    }

    public static Command getCommand(String text) {
        return Stream.of(values())
                     .filter(c -> text.contains(c.getCommandName())).findFirst()
                     .orElseThrow(() -> new IllegalArgumentException(String.format("Command name{%s} not found", text)));
    }

    public String getCommandName() {
        return commandName;
    }
}
