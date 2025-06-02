package cs3500.solored.model.hw02.rules;

import java.util.ArrayList;

import cs3500.solored.model.hw02.PlayingCard;

/**
 * A class that represents the rule for the color Red.
 * The palette with the highest card wins.
 */
public class RedRule implements ColorRules {

  /**
   * Constructs the rule for Red.
   */
  public RedRule() {
    // no fields to construct
  }

  @Override
  public int rule(ArrayList<ArrayList<PlayingCard>> palettes) {
    return new RedRuleAbstraction().rule(palettes);
  }
}
