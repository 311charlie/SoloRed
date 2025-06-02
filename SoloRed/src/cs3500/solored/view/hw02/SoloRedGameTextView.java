package cs3500.solored.view.hw02;

import java.io.IOException;
import java.util.List;

import cs3500.solored.model.hw02.RedGameModel;

/**
 * Represents the view for SoloRedGameModel, providing a visual for the actual game.
 */
public class SoloRedGameTextView implements RedGameView {
  private final RedGameModel<?> model;
  // ... any other fields you need
  private Appendable appendable;

  /**
   * Constructs the view for SoloRedGameModel.
   *
   * @param model the current model of the game
   */
  public SoloRedGameTextView(RedGameModel<?> model) {
    this.model = model;
  }

  /**
   * Constructs view with appendable.
   * @param model the current model of the game
   * @param appendable to store the current textual output
   */
  public SoloRedGameTextView(RedGameModel<?> model, Appendable appendable) {
    if (appendable == null) {
      throw new IllegalArgumentException("Appendable may not be null");
    }

    this.model = model;
    this.appendable = appendable;
  }

  @Override
  public void render() throws IOException {
    appendable.append(this.toString());
  }

  @Override
  public String toString() {
    StringBuilder modelString = new StringBuilder("Canvas: ");
    modelString.append(this.model.getCanvas().toString().charAt(0));
    modelString.append("\n");

    for (int i = 0; i < this.model.numPalettes(); i++) {
      List<?> currPalette = this.model.getPalette(i);
      StringBuilder paletteString = new StringBuilder();

      if (this.model.winningPaletteIndex() == i) {
        paletteString.append("> P").append(i + 1).append(":");
      } else {
        paletteString.append("P").append(i + 1).append(":");
      }

      if (currPalette.isEmpty()) {
        paletteString.append(" ");
      } else {
        for (Object object : currPalette) {
          String cardString = object.toString();
          paletteString.append(" ").append(cardString);
        }
      }
      modelString.append(paletteString).append("\n");
    }

    StringBuilder handString = new StringBuilder("Hand:");
    if (this.model.getHand().isEmpty()) {
      handString.append(" ");
    } else {
      for (int i = 0; i < this.model.getHand().size(); i++) {
        handString.append(" ").append(this.model.getHand().get(i).toString());
      }
    }
    modelString.append(handString);
    return modelString.toString();
  }
}