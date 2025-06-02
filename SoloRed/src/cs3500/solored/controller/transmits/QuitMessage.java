package cs3500.solored.controller.transmits;

import java.io.IOException;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * The message to be transmitted if the user decides to quit the game.
 * @param <C> the playing cards
 */
public class QuitMessage<C extends Card> implements Transmit {
  private final Appendable ap;
  private final RedGameModel<C> model;

  /**
   * To construct the Quit Message with the given appendable and model.
   * @param ap the appendable of the controller
   * @param model the model of the controller
   */
  public QuitMessage(Appendable ap, RedGameModel<C> model) {
    this.ap = ap;
    this.model = model;
  }

  @Override
  public void transmit() throws IOException {
    ap.append("Game quit!");
    new NewLine(ap).transmit();
    ap.append("State of game when quit:");
    new NewLine(ap).transmit();
    new CurrentState<>(ap, model).transmit();
    new NumCardsInDeck<>(ap, model).transmit();
  }
}
