package cs3500.solored.model.hw02;

/**
 * Represents a Color of playing card of SoloRedGame.
 */
public enum Color {
  Red("R"),
  Orange("O"),
  Blue("B"),
  Indigo("I"),
  Violet("V");

  private final String color;

  Color(String color) {
    this.color = color;
  }

  /**
   * The color of a card.
   * @return the color of the card
   */
  public String getColorString() {
    return this.color;
  }
}
