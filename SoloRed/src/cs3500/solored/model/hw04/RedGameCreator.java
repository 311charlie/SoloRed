package cs3500.solored.model.hw04;

import cs3500.solored.model.hw02.PlayingCard;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.SoloRedGameModel;

/**
 * Creates a type of SoloRedModel to play with, works with a factory
 * method to produce the correct game type.
 */
public class RedGameCreator {
  /**
   * Represents a type of SoloRedModel.
   */
  public enum GameType {
    BASIC, ADVANCED;
  }

  /**
   * Creates the model based on the game type given.
   * @param gameType the game type of the model
   * @return the specified model with the game type
   */
  public static RedGameModel<PlayingCard> createGame(GameType gameType) {
    switch (gameType) {
      case BASIC:
        return new SoloRedGameModel();
      case ADVANCED:
        return new AdvancedSoloRedGameModel();
      default:
        throw new IllegalArgumentException("Invalid game type");
    }
  }
}
