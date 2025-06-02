package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;

import cs3500.solored.model.hw02.PlayingCard;

/**
 * A class that represents the tie rule for the colors.
 * The palettes of this class are the palettes that are
 * tied in the prior rule.
 */
public class TieRule implements ColorRules {

  /**
   * Constructs a tie rule.
   */
  public TieRule() {
    // no fields to construct
  }

  @Override
  public int rule(ArrayList<ArrayList<PlayingCard>> palettes) {
    return new RedRuleAbstraction().rule(palettes);
  }
}
