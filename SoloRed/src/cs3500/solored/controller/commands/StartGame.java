package cs3500.solored.controller.commands;

import java.io.IOException;
import java.util.List;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * Represents a command to start the Solo Red Game.
 * @param <C> the playing cards
 */
public class StartGame<C extends Card> implements Command {
  private final RedGameModel<C> model;
  private final List<C> deck;
  private final boolean shuffle;
  private final int numPalettes;
  private final int handSize;
  private final Appendable ap;

  /**
   * Constructs a game to play for Solo Red Game.
   * @param model the current model
   * @param deck the deck to play with
   * @param shuffle to shuffle the deck or not
   * @param numPalettes the number of palettes in the game
   * @param handSize the max hand size of the game
   * @param ap the appendable to provide input to
   */
  public StartGame(RedGameModel<C> model, List<C> deck, boolean shuffle,
                   int numPalettes, int handSize, Appendable ap) {
    this.model = model;
    this.deck = deck;
    this.shuffle = shuffle;
    this.numPalettes = numPalettes;
    this.handSize = handSize;
    this.ap = ap;
  }

  @Override
  public void execute() throws IOException {
    try {
      if (model == null) {
        throw new IllegalArgumentException("Model cannot be null");
      }
      model.startGame(deck, shuffle, numPalettes, handSize);
    } catch (IllegalArgumentException | IllegalStateException ie) {
      throw new IllegalArgumentException(ie.getMessage(), ie);
    }
    try {
      new ShowView<>(model, ap).execute();
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe.getMessage(), ioe);
    }
  }
}
