package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;
import java.util.Comparator;

import cs3500.solored.model.hw02.PlayingCard;

/**
 * A class that represents the rule for the color Indigo.
 * The palette with the cards that form the longest run wins.
 */
public class IndigoRule implements ColorRules {

  /**
   * Constructs the rule for Indigo.
   */
  public IndigoRule() {
    // no fields to construct
  }

  @Override
  public int rule(ArrayList<ArrayList<PlayingCard>> palettes) {
    int winningPaletteIndex = 0;
    ArrayList<PlayingCard> winningActiveCards = new ArrayList<>();
    int currMaxRun = 1;

    for (int paletteIndex = 0; paletteIndex < palettes.size(); paletteIndex++) {
      ArrayList<PlayingCard> currPalette = palettes.get(paletteIndex);
      ArrayList<PlayingCard> currPaletteCopy = new ArrayList<>(currPalette);
      currPaletteCopy.sort(Comparator.comparingInt(PlayingCard::getNumber));
      ArrayList<PlayingCard> activeCards = new ArrayList<>();
      int currRun = 1;

      for (int cardIndex = 0; cardIndex < currPalette.size() - 1; cardIndex++) {
        PlayingCard currCard = currPaletteCopy.get(cardIndex);
        if (currRun == 1) {
          activeCards.add(currCard);
        }

        PlayingCard nextCard = currPaletteCopy.get(cardIndex + 1);

        if (nextCard.getNumber() - currCard.getNumber() == 1) {
          activeCards.add(nextCard);
          currRun++;
        } else if (nextCard.getNumber() == currCard.getNumber()) {
          activeCards.add(nextCard);
        } else {
          activeCards.clear();
          currRun = 1;
        }

        if (currRun == currMaxRun) {
          ArrayList<ArrayList<PlayingCard>> tiePalettes = new ArrayList<>();
          if (currMaxRun == 1 && nextCard == currPaletteCopy.get(currPaletteCopy.size() - 1)) {
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
        } else if (currRun > currMaxRun) {
          currMaxRun = currRun;
          winningActiveCards = activeCards;
          winningPaletteIndex = paletteIndex;
        }
      }
    }
    return winningPaletteIndex;
  }
}
