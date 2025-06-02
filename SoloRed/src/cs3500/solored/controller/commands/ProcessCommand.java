package cs3500.solored.controller.commands;

import java.io.IOException;
import java.util.Scanner;

import cs3500.solored.controller.exceptions.QuitGameException;
import cs3500.solored.controller.transmits.InvalidCommandMessage;
import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw04.RedGameCreator;

/**
 * Represents a way to process commands from user input.
 * @param <C> the playing cards
 */
public class ProcessCommand<C extends Card> implements Command {
  private final RedGameModel<C> model;
  private final Appendable ap;
  private final Scanner scanner;
  private final String command;

  /**
   * Constructs a process command object to process from user input.
   * @param model the current model
   * @param ap the output to append to
   * @param scanner the scanner to retrieve input from
   * @param command the initial command from user input
   */
  public ProcessCommand(RedGameModel<C> model, Appendable ap, Scanner scanner, String command) {
    this.model = model;
    this.ap = ap;
    this.scanner = scanner;
    this.command = command;
  }

  @Override
  public void execute() throws IOException {
    int paletteIdx;
    int cardIdx;
    RedGameCreator.GameType gameType;
    switch (command) {
      case "q":
        throw new QuitGameException();

      case "palette":
        paletteIdx = nextValidInput(scanner);
        cardIdx = nextValidInput(scanner);
        try {
          new SendToPalette<>(model, paletteIdx, cardIdx, this.ap).execute();
        } catch (IOException ioe) {
          throw new IllegalStateException(ioe.getMessage(), ioe);
        }
        break;

      case "canvas":
        cardIdx = nextValidInput(scanner);
        try {
          new SendToCanvas<>(model, cardIdx, this.ap).execute();
        } catch (IOException ioe) {
          throw new IllegalStateException(ioe.getMessage(), ioe);
        }
        break;
      default:
        try {
          new InvalidCommandMessage(this.ap).transmit();
        } catch (IOException ioe) {
          throw new IllegalStateException(ioe.getMessage(), ioe);
        }
    }
  }

  private int nextValidInput(Scanner scanner) {
    while (scanner.hasNext()) {
      String input = scanner.next();
      if (input.equalsIgnoreCase("q")) {
        throw new QuitGameException();
      }
      try {
        if (Integer.parseInt(input) >= 0) {
          return Integer.parseInt(input) - 1;
        }
      } catch (NumberFormatException ignored) {
        // Skip invalid input and keep reading
      }
    }
    throw new IllegalStateException("Failed to read valid input");
  }
}
