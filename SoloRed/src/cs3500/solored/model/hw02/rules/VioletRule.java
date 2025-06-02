package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;

import cs3500.solored.model.hw02.PlayingCard;

/**
 * A class that represents the rule for the color Violet.
 */
public class VioletRule implements ColorRules {

  /**
   * Constructs the rule for Violet.
   * The palette with the most cards below the value of 4 wins.
   */
  public VioletRule() {
    // no fields to construct
  }

  @Override
  public int rule(ArrayList<ArrayList<PlayingCard>> palettes) {
    int winningPaletteIndex = 0;
    int currMaxCount = 0;
    ArrayList<PlayingCard> winningActiveCards = new ArrayList<>();

    for (int paletteIndex = 0; paletteIndex < palettes.size(); paletteIndex++) {
      ArrayList<PlayingCard> currPalette = palettes.get(paletteIndex);
      ArrayList<PlayingCard> activeCards = new ArrayList<>();
      int currCount = 0;

      for (PlayingCard currCard : currPalette) {
        if (currCard.getNumber() < 4) {
          currCount++;
          activeCards.add(currCard);
        }
      }

      if (currCount == currMaxCount) {
        ArrayList<ArrayList<PlayingCard>> tiePalettes = new ArrayList<>();
        if (currCount == 0) {
          tiePalettes.add(palettes.get(winningPaletteIndex));
          tiePalettes.add(currPalette);
        } else {
          tiePalettes.add(winningActiveCards);
          tiePalettes.add(activeCards);
        }
        if (new TieRule().rule(tiePalettes) == 1) {
          winningActiveCards = activeCards;
          winningPaletteIndex = paletteIndex;
        }
      } else if (currCount > currMaxCount) {
        currMaxCount = currCount;
        winningPaletteIndex = paletteIndex;
        winningActiveCards = activeCards;
      }
    }
    return winningPaletteIndex;
  }
}
