package cs3500.solored.controller.transmits;

import java.io.IOException;

/**
 * Represents a collection of messages to transmit to RedGameController.
 */
public interface Transmit {
  /**
   * Transmits a given message to the controller.
   */
  void transmit() throws IOException;
}
