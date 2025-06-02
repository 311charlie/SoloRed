package cs3500.solored.controller.transmits;

import java.io.IOException;

/**
 * A message providing information to the user that their move was invalid.
 */
public class InvalidMoveMessage implements Transmit {
  private final Appendable ap;
  private final String type;

  /**
   * To construct an Invalid Move Message with the given appendable and type for specifics.
   * @param ap the appendable of the controller
   * @param type the type of move
   */
  public InvalidMoveMessage(Appendable ap, String type) {
    this.ap = ap;
    this.type = type;
  }

  @Override
  public void transmit() throws IOException {
    ap.append("Invalid move. Try again. ");
    ap.append(type);
    new NewLine(ap).transmit();
  }
}
