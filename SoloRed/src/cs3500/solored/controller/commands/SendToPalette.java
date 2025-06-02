package cs3500.solored.controller.commands;

import java.io.IOException;

import cs3500.solored.controller.transmits.InvalidMoveMessage;
import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * Command to send card to palette.
 * @param <C> the playing cards
 */
public class SendToPalette<C extends Card> implements Command {
  private final RedGameModel<C> model;
  private final int paletteIdx;
  private final int cardIdx;
  private final Appendable ap;

  /**
   * Constructs a command to send a card to a palette.
   * @param model the current model
   * @param paletteIdx the palette index to send to
   * @param cardIdx the card index to send
   * @param ap the appendable of the controller
   */
  public SendToPalette(RedGameModel<C> model, int paletteIdx, int cardIdx, Appendable ap) {
    this.model = model;
    this.paletteIdx = paletteIdx;
    this.cardIdx = cardIdx;
    this.ap = ap;
  }

  @Override
  public void execute() throws IOException {
    try {
      model.playToPalette(paletteIdx, cardIdx);
      if (!model.isGameOver()) {
        model.drawForHand();
      }
    } catch (IllegalArgumentException iae) {
      String message = iae.getMessage();
      if ("Invalid palette index.".equals(message)) {
        new InvalidMoveMessage(ap, "Invalid palette index.").transmit();
      } else if ("Invalid card index.".equals(message)) {
        new InvalidMoveMessage(ap, "Invalid card index.").transmit();
      } else {
        new InvalidMoveMessage(ap, "").transmit();
      }
    } catch (IllegalStateException ise) {
      new InvalidMoveMessage(ap, "Palette referred to by index is already winning").transmit();
    }
  }
}
