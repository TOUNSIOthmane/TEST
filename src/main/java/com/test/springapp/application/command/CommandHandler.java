package com.test.springapp.application.command;

/**
 * Interface for command handlers.
 * Command handlers execute commands and return results.
 * 
 * @param <C> the command type
 * @param <R> the result type
 */
public interface CommandHandler<C extends Command, R> {
    
    /**
     * Handles the given command.
     * @param command the command to handle
     * @return the result
     */
    R handle(C command);
}
