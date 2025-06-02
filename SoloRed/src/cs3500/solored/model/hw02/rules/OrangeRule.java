package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;

import cs3500.solored.model.hw02.PlayingCard;

/**
 * A class that represents the rule for the color Orange.
 * The palette with the most of a single number wins.
 */
public class OrangeRule implements ColorRules {

  /**
   * Constructs the rule for Orange.
   */
  public OrangeRule() {
    // no fields to construct
  }

  @Override
  public int rule(ArrayList<ArrayList<PlayingCard>> palettes) {
    int winningPaletteIndex = 0;
    int currMaxCount = 1;
    ArrayList<PlayingCard> winningActiveCards = new ArrayList<>();

    for (int paletteIndex = 0; paletteIndex < palettes.size(); paletteIndex++) {
      ArrayList<PlayingCard> currPalette = palettes.get(paletteIndex);

      for (int cardIndex = 0; cardIndex < currPalette.size(); cardIndex++) {
        ArrayList<PlayingCard> activeCards = new ArrayList<>();
        PlayingCard currCard = currPalette.get(cardIndex);
        int currCount = 0;

        for (PlayingCard nextCard : currPalette) {
          if (nextCard.getNumber() == currCard.getNumber()) {
            currCount++;
            activeCards.add(nextCard);
          }
        }
        if (currCount == currMaxCount) {
          ArrayList<ArrayList<PlayingCard>> tiePalettes = new ArrayList<>();
          tiePalettes.add(winningActiveCards);
          tiePalettes.add(activeCards);
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
    }
    return winningPaletteIndex;
  }
}
