package cs3500.solored;

import java.io.InputStreamReader;
import java.util.List;

import cs3500.solored.controller.SoloRedTextController;
import cs3500.solored.model.hw02.PlayingCard;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw04.RedGameCreator;

/**
 * Represents the SoloRed program that plays with different types of RedGameModels.
 */
public final class SoloRed {
  /**
   * Acts as the entry point for the program with the given command line inputs.
   * @param args the command line inputs to begin the game with
   */
  public static void main(String[] args) {

    RedGameModel<PlayingCard> model;
    switch (args[0].toLowerCase()) {
      case "basic":
        model = RedGameCreator.createGame(RedGameCreator.GameType.BASIC);
        break;
      case "advanced":
        model = RedGameCreator.createGame(RedGameCreator.GameType.ADVANCED);
        break;
      default:
        throw new IllegalArgumentException("Invalid game type.");
    }

    List<PlayingCard> allCards = model.getAllCards();
    if (allCards.size() != 35) {
      throw new IllegalStateException("The deck must contain 35 cards.");
    }

    // default values
    int numPalettes = 4;
    int maxHandSize = 7;

    try {
      if (args.length >= 2) {
        numPalettes = Integer.parseInt(args[1]);
      }
    } catch (IllegalArgumentException ignored) {
      // ignore
    }

    try {
      if (args.length >= 3) {
        maxHandSize = Integer.parseInt(args[2]);
      }
    } catch (IllegalArgumentException ignored) {
      // ignore
    }

    SoloRedTextController controller = new SoloRedTextController(new InputStreamReader(System.in),
            System.out);
    try {
      controller.playGame(model, allCards, true, numPalettes, maxHandSize);
    } catch (IllegalArgumentException ignored) {
      // ignore
    }
  }
}
