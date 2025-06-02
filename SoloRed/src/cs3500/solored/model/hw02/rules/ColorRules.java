package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;

import cs3500.solored.model.hw02.PlayingCard;

/**
 * A collection of Colors and their respective rules.
 */
public interface ColorRules {
  /**
   * The respective rule of each color.
   * @param palettes all the palettes of the model
   * @return the index of the palette that wins in that rule
   */
  int rule(ArrayList<ArrayList<PlayingCard>> palettes);
}
