package cs3500.solored.controller;

import java.io.IOException;
import java.util.List;

import cs3500.solored.controller.commands.StartGame;
import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.RedGameModel;

/**
 * A controller class for the SoloRedGame, processes command line inputs
 * and reacts based on what was inputted.
 */
public class SoloRedTextController implements RedGameController {
  private final Readable rd;
  private final Appendable ap;

  /**
   * Constructs a SoloRedGameController.
   *
   * @param rd readable for inputs
   * @param ap appendable for outputs
   * @throws IllegalArgumentException if readable and appendable are null
   */
  public SoloRedTextController(Readable rd, Appendable ap) throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("input/output cannot be null  rd: " + rd + ", ap: " + ap);
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public <C extends Card> void playGame(RedGameModel<C> model, List<C> deck, boolean shuffle,
                                        int numPalettes, int handSize) {

    // Initialize the game.
    try {
      new StartGame<C>(model, deck, shuffle, numPalettes, handSize, ap).execute();
    } catch (IOException ioe) {
      throw new IllegalArgumentException(ioe.getMessage());
    }

    // Play the game.
    try {
      new MainGameLoop<>(rd, ap, model).mainGameLoop();
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException(iae.getMessage());
    } catch (IllegalStateException ise) {
      throw new IllegalStateException(ise.getMessage(), ise);
    }
  }
}
