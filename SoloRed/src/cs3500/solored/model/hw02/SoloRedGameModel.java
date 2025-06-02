package cs3500.solored.model.hw02;

import java.util.Random;

/**
 * Represents a basic SoloRedGameModel that contains the standard information of the SoloRedGame.
 */
public class SoloRedGameModel extends AbstractRedGameModel {

  /**
   * Constructs a SoloRedGameModel with flexible fields according to startGame.
   */
  public SoloRedGameModel() {
    // Creates a state of the game to be used with startGame.
  }

  /**
   * Constructs a SoloRedGameModel according to startGame with a shuffled deck.
   * @param object To shuffle the deck
   */
  public SoloRedGameModel(Random object) {
    super(object);
  }
}

