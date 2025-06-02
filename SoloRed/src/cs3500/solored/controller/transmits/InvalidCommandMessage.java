package cs3500.solored.controller.transmits;

import java.io.IOException;

/**
 * A message providing information to the user that their command was invalid.
 */
public class InvalidCommandMessage implements Transmit {
  private final Appendable ap;

  /**
   * To construct an Invalid Command message with the given appendable.
   * @param ap the appendable of the controller
   */
  public InvalidCommandMessage(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void transmit() throws IOException {
    ap.append("Invalid command. Try again. ");
    ap.append("Enter valid command (q, palette, canvas): ");
    new NewLine(ap).transmit();
  }
}
