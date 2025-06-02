package cs3500.solored.controller.transmits;

import java.io.IOException;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * The message to be outputted when the game is either won or lost.
 * @param <C> the playing cards
 */
public class GameOverMessage<C extends Card> implements Transmit {
  private final Appendable ap;
  private final RedGameModel<C> model;

  /**
   * To construct a Game Over Message with the given appendable and model.
   * @param ap the appendable of the controller
   * @param model the model of the controller
   */
  public GameOverMessage(Appendable ap, RedGameModel<C> model) {
    this.ap = ap;
    this.model = model;
  }

  @Override
  public void transmit() throws IOException {
    if (model.isGameWon()) {
      ap.append("Game won.");
      new NewLine(ap).transmit();
    } else {
      ap.append("Game lost.");
      new NewLine(ap).transmit();
    }
    new CurrentState<>(ap, model).transmit();
    new NumCardsInDeck<>(ap, model).transmit();
  }
}
