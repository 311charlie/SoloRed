package cs3500.solored.controller.exceptions;

/**
 * Exception to be caught outside playGame method to quit game.
 */
public class QuitGameException extends RuntimeException {

  /**
   * Constructs the exception quitting the game.
   */
  public QuitGameException() { /* Empty */ }
}
