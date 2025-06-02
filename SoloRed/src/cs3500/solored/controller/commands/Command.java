package cs3500.solored.controller.commands;

import java.io.IOException;

/**
 * Represents a collection of commands.
 */
public interface Command {
  /**
   * The main method to execute a command on a model.
   */
  void execute() throws IOException;
}
