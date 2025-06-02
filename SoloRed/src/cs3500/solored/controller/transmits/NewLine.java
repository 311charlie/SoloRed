package cs3500.solored.controller.transmits;

import java.io.IOException;

/**
 * Transmits a new line to a given appendable.
 */
public class NewLine implements Transmit {
  private Appendable ap;

  /**
   * Constructs the new line transmit for the appendable.
   * @param ap the appendable to provide a new line to
   */
  public NewLine(Appendable ap) {
    this.ap = ap;
  }


  @Override
  public void transmit() throws IOException {
    ap.append("\n");
  }
}
