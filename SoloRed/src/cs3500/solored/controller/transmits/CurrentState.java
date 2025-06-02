package cs3500.solored.controller.transmits;

import java.io.IOException;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.view.hw02.RedGameView;
import cs3500.solored.view.hw02.SoloRedGameTextView;

/**
 * A message that contains the current state of the SoloRedGame.
 * @param <C> the playing cards
 */
public class CurrentState<C extends Card> implements Transmit {
  private final Appendable ap;
  private final RedGameModel<C> model;

  /**
   * To construct a Current State message with given appendable and model.
   * @param ap the appendable of the controller
   * @param model the model of the controller
   */
  public CurrentState(Appendable ap, RedGameModel<C> model) {
    this.ap = ap;
    this.model = model;
  }

  @Override
  public void transmit() throws IOException {
    RedGameView currentView = new SoloRedGameTextView(model, ap);
    currentView.render();
    new NewLine(ap).transmit();
  }
}
