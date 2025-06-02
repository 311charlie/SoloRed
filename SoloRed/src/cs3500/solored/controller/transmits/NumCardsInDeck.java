package cs3500.solored.controller.transmits;

import java.io.IOException;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * A message outputting the current number of cards in the deck.
 * @param <C> the playing cards
 */
public class NumCardsInDeck<C extends Card> implements Transmit {
  private final Appendable ap;
  private final RedGameModel<C> model;

  /**
   * To construct the current Number of Cards in Deck message with the given appendable and model.
   * @param ap the appendable of the controller
   * @param model the model of the controller
   */
  public NumCardsInDeck(Appendable ap, RedGameModel<C> model) {
    this.ap = ap;
    this.model = model;
  }

  @Override
  public void transmit() throws IOException {
    ap.append("Number of cards in deck: ");
    ap.append(Integer.toString(model.numOfCardsInDeck()));
    new NewLine(ap).transmit();
  }
}
