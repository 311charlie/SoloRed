package cs3500.solored.controller.transmits;

import java.io.IOException;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * A message that contains the current full state of the SoloRedGame.
 * @param <C> the playing cards
 */
public class FullGame<C extends Card> implements Transmit {
  private final Appendable ap;
  private final RedGameModel<C> model;

  /**
   * To construct a Current Full State message with given appendable and model.
   * @param ap the appendable of the controller
   * @param model the model of the controller
   */
  public FullGame(Appendable ap, RedGameModel<C> model) {
    this.ap = ap;
    this.model = model;
  }

  @Override
  public void transmit() throws IOException {
    new CurrentState<>(ap, model).transmit();
    new NumCardsInDeck<>(ap, model).transmit();
  }
}
