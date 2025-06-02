package cs3500.solored.controller;

import java.util.List;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * Represents a RedGameController interface.
 */
public interface RedGameController {
  /**
   * The controller used to play the game.
   * @param model the model used to play
   * @param deck  the deck used to play
   * @param shuffle whether the deck should be shuffled or not
   * @param numPalettes the max number of palettes in the game
   * @param handSize the max hand size of the game
   * @param <C> To be the PlayingCard used to play the game
   */
  <C extends Card> void playGame(RedGameModel<C> model, List<C> deck, boolean shuffle,
                                 int numPalettes, int handSize);
}
