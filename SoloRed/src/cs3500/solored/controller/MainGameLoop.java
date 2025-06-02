package cs3500.solored.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.solored.controller.commands.ProcessCommand;
import cs3500.solored.controller.exceptions.QuitGameException;
import cs3500.solored.controller.transmits.FullGame;
import cs3500.solored.controller.transmits.GameOverMessage;
import cs3500.solored.controller.transmits.QuitMessage;
import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * Represents the main loop of the game that takes in user input continuously and processes
 * commands accordingly until game over or quit.
 * @param <C> the playing cards
 */
public class MainGameLoop<C extends Card> {
  private final Readable rd;
  private final Appendable ap;
  private final RedGameModel<C> model;

  /**
   * Constructs the main loop of the game meant to continuously take user input.
   * @param rd The readable to get user input from
   * @param ap The appendable to output to
   * @param model the current model
   */
  public MainGameLoop(Readable rd, Appendable ap, RedGameModel<C> model) {
    this.rd = rd;
    this.ap = ap;
    this.model = model;
  }

  /**
   * The main loop of the game meant to continuously run until quit or game over.
   */
  public void mainGameLoop() {
    try {
      Scanner scanner = new Scanner(this.rd);
      readInputLoop(scanner);
    } catch (QuitGameException qge) {
      try {
        new QuitMessage<>(ap, model).transmit();
      } catch (IOException ioe) {
        throw new IllegalStateException("Failed to transmit quit message", ioe);
      }
    }
  }

  private void readInputLoop(Scanner scanner) {
    while (true) {
      try {
        if (model.isGameOver()) {
          new GameOverMessage<>(ap, model).transmit();
          return;
        }
        String command = scanner.next().toLowerCase();
        new ProcessCommand<C>(model, ap, scanner, command).execute();
        new FullGame<>(ap, model).transmit();
      } catch (IOException ioe) {
        throw new IllegalStateException(ioe.getMessage(), ioe);
      } catch (QuitGameException qge) {
        throw new QuitGameException();
      } catch (NoSuchElementException nse) {
        throw new IllegalStateException("Failed to read input", nse);
      }
    }
  }
}
