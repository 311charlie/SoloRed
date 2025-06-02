package cs3500.solored.model.hw02;

import java.util.Objects;

/**
 * Represents a Card for the SoloRedGame, either a canvas card, or numbered.
 */
public class PlayingCard implements Card {
  private final Color color;
  private final int number;

  /**
   * Constructs a PlayingCard with a color and number.
   * @param color the color of the card
   * @param number the number of the card
   */
  public PlayingCard(Color color, int number) {
    if (number < 1 || number > 7) {
      throw new IllegalArgumentException("numbers must be between 1 and 7: " + number);
    }
    this.color = color;
    this.number = number;
  }

  /**
   * Constructs a PlayingCard for the canvas.
   * @param color the color of the card
   */
  public PlayingCard(Color color) {
    this.color = color;
    this.number = -1;
  }

  public int getNumber() {
    return this.number;
  }

  public Color getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    if (this.number == -1) {
      return this.color.getColorString();
    }
    return this.color.getColorString() + this.number;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof PlayingCard)) {
      return false;
    }
    PlayingCard other = (PlayingCard) obj;

    return this.color == other.color && this.number == other.number;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getColor(), this.getNumber());
  }
}
