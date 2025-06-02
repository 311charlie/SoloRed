package cs3500.solored.model.hw4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import cs3500.solored.controller.mocks.MockSoloRedModel;
import cs3500.solored.model.hw02.Color;
import cs3500.solored.model.hw02.PlayingCard;
import cs3500.solored.model.hw04.AdvancedSoloRedGameModel;
import cs3500.solored.model.hw2.HW2ModelTest;

/**
 * The tests for the advanced model of SoloRedGameModel.
 */
public class HW4ModelTest extends HW2ModelTest {

  @Before
  public void setup() {
    model = new AdvancedSoloRedGameModel();
    mock = new MockSoloRedModel();
    expectedCards = model.getAllCards();

    b2 = new PlayingCard(Color.Blue, 2);
    r1 = new PlayingCard(Color.Red, 1);
    o1 = new PlayingCard(Color.Orange, 1);
    o4 = new PlayingCard(Color.Orange, 4);
    r2 = new PlayingCard(Color.Red, 2);
    b4 = new PlayingCard(Color.Blue, 4);
    v6 = new PlayingCard(Color.Violet, 6);
    b7 = new PlayingCard(Color.Blue, 7);
    i1 = new PlayingCard(Color.Indigo, 1);
    o3 = new PlayingCard(Color.Orange, 3);
    r3 = new PlayingCard(Color.Red, 3);

    /*
     * Deck to initialize game as shown
     * Canvas: R
     * P1: B2
     * P2: R1
     * P3: O1
     * > P4: O4
     * Hand: R2 B4 V6 B7 I1 O3 R3
     */
    mainDeck = new ArrayList<>();
    mainDeck.add(b2);
    mainDeck.add(r1);
    mainDeck.add(o1);
    mainDeck.add(o4);
    mainDeck.add(r2);
    mainDeck.add(b4);
    mainDeck.add(v6);
    mainDeck.add(b7);
    mainDeck.add(i1);
    mainDeck.add(o3);
    mainDeck.add(r3);
  }

  @Test
  public void testAdvancedModelNoDrawExtra() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Orange, 1));
    deck.add(new PlayingCard(Color.Orange, 4));

    deck.add(new PlayingCard(Color.Blue, 1));
    deck.add(new PlayingCard(Color.Orange, 7));
    deck.add(new PlayingCard(Color.Orange, 3));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Red, 4));
    deck.add(new PlayingCard(Color.Indigo, 7));
    deck.add(new PlayingCard(Color.Red, 5));
    model.startGame(deck, false, 4, 5);

    model.getPalette(0).add(new PlayingCard(Color.Blue, 7));
    Assert.assertEquals(2, model.getPalette(0).size()); // [B2 B7]
    model.getPalette(1).add(new PlayingCard(Color.Blue, 5));
    Assert.assertEquals(2, model.getPalette(1).size()); // [R1 B5]
    model.getPalette(2).add(new PlayingCard(Color.Violet, 7));
    model.getPalette(2).add(new PlayingCard(Color.Red, 2));
    Assert.assertEquals(3, model.getPalette(2).size()); // [O1 V7 R2]
    model.getPalette(3).add(new PlayingCard(Color.Indigo, 1));
    Assert.assertEquals(2, model.getPalette(3).size()); // [O4 I1]

    Assert.assertEquals(5, model.getHand().size());

    Assert.assertEquals(0, model.winningPaletteIndex());
    model.playToCanvas(0);
    Assert.assertEquals(new PlayingCard(Color.Blue), model.getCanvas());
    Assert.assertEquals(2, model.winningPaletteIndex());

    Assert.assertEquals(4, model.getHand().size());

    Assert.assertFalse(model.getPalette(1).contains(new PlayingCard(Color.Orange, 7)));
    model.playToPalette(1, 0);
    Assert.assertTrue(model.getPalette(1).contains(new PlayingCard(Color.Orange, 7)));

    model.drawForHand();
    Assert.assertEquals(4, model.getHand().size());
  }

  @Test
  public void testAdvancedModelDrawExtra() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Orange, 1));
    deck.add(new PlayingCard(Color.Orange, 4));

    deck.add(new PlayingCard(Color.Blue, 1));
    deck.add(new PlayingCard(Color.Orange, 7));
    deck.add(new PlayingCard(Color.Orange, 3));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Red, 4));
    deck.add(new PlayingCard(Color.Indigo, 7));
    deck.add(new PlayingCard(Color.Red, 5));
    model.startGame(deck, false, 4, 5);

    model.getPalette(0).add(new PlayingCard(Color.Blue, 7));
    Assert.assertEquals(2, model.getPalette(0).size()); // [B2 B7]
    model.getPalette(1).add(new PlayingCard(Color.Blue, 5));
    Assert.assertEquals(2, model.getPalette(1).size()); // [R1 B5]
    model.getPalette(2).add(new PlayingCard(Color.Violet, 7));
    model.getPalette(2).add(new PlayingCard(Color.Red, 2));
    Assert.assertEquals(3, model.getPalette(2).size()); // [O1 V7 R2]
    model.getPalette(3).add(new PlayingCard(Color.Indigo, 1));
    Assert.assertEquals(2, model.getPalette(3).size()); // [O4 I1]

    Assert.assertEquals(5, model.getHand().size());

    Assert.assertEquals(0, model.winningPaletteIndex());
    model.playToCanvas(1);
    Assert.assertEquals(new PlayingCard(Color.Orange), model.getCanvas());
    Assert.assertEquals(0, model.winningPaletteIndex());

    Assert.assertEquals(4, model.getHand().size());

    Assert.assertFalse(model.getPalette(3).contains(new PlayingCard(Color.Blue, 1)));
    model.playToPalette(3, 0);
    Assert.assertTrue(model.getPalette(3).contains(new PlayingCard(Color.Blue, 1)));

    Assert.assertEquals(3, model.getHand().size());
    model.drawForHand();
    Assert.assertEquals(5, model.getHand().size());
  }
}
