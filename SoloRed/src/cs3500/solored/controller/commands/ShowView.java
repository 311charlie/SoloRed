package cs3500.solored.controller.commands;

import java.io.IOException;

import cs3500.solored.controller.transmits.FullGame;
import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * Represents a way to show the entire Red Game View.
 * @param <C> the playing cards
 */
public class ShowView<C extends Card> implements Command {
  private final RedGameModel<C> model;
  private final Appendable ap;

  /**
   * Constructs an entire Red Game View.
   * @param model the current model
   * @param ap the output to append the view to
   */
  public ShowView(RedGameModel<C> model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void execute() throws IOException {
    try {
      new FullGame<>(ap, model).transmit();
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe.getMessage(), ioe);
    }
  }
}
