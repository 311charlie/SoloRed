package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;

import cs3500.solored.model.hw02.Color;
import cs3500.solored.model.hw02.PlayingCard;

/**
 * A class that represents the rule for the color Blue.
 * The palette with the most cards of different colors wins.
 */
public class BlueRule implements ColorRules {

  /**
   * Constructs the rule for Blue.
   */
  public BlueRule() {
    // no fields to construct
  }

  @Override
  public int rule(ArrayList<ArrayList<PlayingCard>> palettes) {
    int winningPaletteIndex = 0;
    int currMaxCount = 1;

    for (int paletteIndex = 0; paletteIndex < palettes.size(); paletteIndex++) {
      ArrayList<PlayingCard> currPalette = palettes.get(paletteIndex);
      ArrayList<Color> currColors = new ArrayList<>();

      for (PlayingCard currCard : currPalette) {
        if (!currColors.contains(currCard.getColor())) {
          currColors.add(currCard.getColor());
        }
      }

      if (currColors.size() == currMaxCount) {
        ArrayList<ArrayList<PlayingCard>> tiePalettes = new ArrayList<>();
        tiePalettes.add(palettes.get(winningPaletteIndex));
        tiePalettes.add(currPalette);
        if (new TieRule().rule(tiePalettes) == 1) {
          winningPaletteIndex = paletteIndex;
        }
      } else if (currColors.size() > currMaxCount) {
        currMaxCount = currColors.size();
        winningPaletteIndex = paletteIndex;
      }
    }
    return winningPaletteIndex;
  }
}
