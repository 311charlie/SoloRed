package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;

import cs3500.solored.model.hw02.PlayingCard;

/**
 * An abstraction of the Red Rule code to be used without duplication.
 */
public class RedRuleAbstraction implements ColorRules {

  /**
   * Constructs the abstraction rule for Red.
   */
  public RedRuleAbstraction() {
    // no fields to construct
  }

  @Override
  public int rule(ArrayList<ArrayList<PlayingCard>> palettes) {
    int winningPaletteIndex = 0;
    PlayingCard currHighestCard = null;

    for (int paletteIndex = 0; paletteIndex < palettes.size(); paletteIndex++) {
      ArrayList<PlayingCard> currPalette = palettes.get(paletteIndex);

      for (PlayingCard currCard : currPalette) {
        if (currHighestCard == null || currCard.getNumber() > currHighestCard.getNumber()) {
          currHighestCard = currCard;
          winningPaletteIndex = paletteIndex;
        } else if (currCard.getNumber() == currHighestCard.getNumber()) {
          if (currCard.getColor().ordinal() < currHighestCard.getColor().ordinal()) {
            currHighestCard = currCard;
            winningPaletteIndex = paletteIndex;
          }
        }
      }
    }
    return winningPaletteIndex;
  }
}
