package cs3500.solored.model.hw2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.solored.model.hw02.Card;
import cs3500.solored.model.hw02.Color;
import cs3500.solored.model.hw02.PlayingCard;

/**
 * The tests for the cards of SoloRedGameModel.
 */
public class CardTest {
  PlayingCard startingCard;
  PlayingCard r7;
  PlayingCard b2;
  PlayingCard v;

  @Before
  public void setup() {
    startingCard = new PlayingCard(Color.Red);
    r7 = new PlayingCard(Color.Red, 7);
    b2 = new PlayingCard(Color.Blue, 2);
    v = new PlayingCard(Color.Violet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardsConstructorNumberOutOfRange() {
    Card r8 = new PlayingCard(Color.Red, 8);
    Card i0 = new PlayingCard(Color.Indigo, 0);
  }

  @Test
  public void testStartingCardToString() {
    Assert.assertEquals(startingCard.toString(), "R");
  }

  @Test
  public void testCanvasCardToString() {
    Assert.assertNotEquals(v.toString(), "V-1");
  }

  @Test
  public void testPlayingCardToString() {
    Assert.assertEquals(r7.toString(), "R7");
  }

  @Test
  public void testStartingCardGetColor() {
    Assert.assertEquals(startingCard.getColor(), Color.Red);
  }

  @Test
  public void testPlayingCardGetColor() {
    Assert.assertEquals(b2.getColor(), Color.Blue);
  }

  @Test
  public void testPlayingCardGetNumber() {
    Assert.assertEquals(b2.getNumber(), 2);
  }

  @Test
  public void testStartingCardEquals() {
    Assert.assertNotEquals(startingCard, r7);
    Assert.assertEquals(startingCard, new PlayingCard(Color.Red));
  }

  @Test
  public void testPlayingCardEquals() {
    Assert.assertNotEquals(b2, r7);
    Assert.assertEquals(b2, new PlayingCard(Color.Blue, 2));
  }
}
