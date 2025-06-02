package cs3500.solored.controller.commands;

import java.io.IOException;

import cs3500.solored.controller.transmits.InvalidMoveMessage;
import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * Command to send card to the canvas.
 * @param <C> the playing cards
 */
public class SendToCanvas<C extends Card> implements Command {
  private final RedGameModel<C> model;
  private final int cardIdx;
  private final Appendable ap;

  /**
   * Constructs a command to send a card to a canvas.
   * @param model the current model
   * @param cardIdx the card index to send
   * @param ap the appendable of the controller
   */
  public SendToCanvas(RedGameModel<C> model, int cardIdx, Appendable ap) {
    this.model = model;
    this.cardIdx = cardIdx;
    this.ap = ap;
  }

  @Override
  public void execute() throws IOException {
    try {
      model.playToCanvas(cardIdx);
    } catch (IllegalStateException ise) {
      String message = ise.getMessage();
      if ("Already played to canvas".equals(message)) {
        new InvalidMoveMessage(ap, "Already played to canvas").transmit();
      } else if ("Cannot use last card to canvas".equals(message)) {
        new InvalidMoveMessage(ap, "Cannot use last card to canvas").transmit();
      } else {
        new InvalidMoveMessage(ap, "Invalid move").transmit();
      }
    } catch (IllegalArgumentException iae) {
      new InvalidMoveMessage(ap, "Card Index Out of Range").transmit();
    }
  }
}
