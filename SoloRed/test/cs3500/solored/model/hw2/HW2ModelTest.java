package cs3500.solored.model.hw2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.solored.controller.ControllerTest;
import cs3500.solored.model.hw02.Color;
import cs3500.solored.model.hw02.PlayingCard;
import cs3500.solored.model.hw02.SoloRedGameModel;
import cs3500.solored.view.hw02.SoloRedGameTextView;

/**
 * The tests for the basic model of SoloRedGameModel.
 */
public class HW2ModelTest extends ControllerTest {

  // to replicate 2.7 game setup
  @Test
  public void testPlayingCardToPalette() {
    mainDeck.add(new PlayingCard(Color.Red, 4));
    model.startGame(mainDeck, false, 4, 7);

    // setup palettes
    Assert.assertFalse(model.getPalette(1).contains(new PlayingCard(Color.Blue, 5)));
    Assert.assertFalse(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));
    model.getPalette(1).add(new PlayingCard(Color.Blue, 5));
    model.getPalette(2).add(new PlayingCard(Color.Violet, 7));
    Assert.assertTrue(model.getPalette(1).contains(new PlayingCard(Color.Blue, 5)));
    Assert.assertTrue(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));

    Assert.assertEquals(model.winningPaletteIndex(), 2);
    model.playToPalette(0, 3);
    Assert.assertEquals(model.winningPaletteIndex(), 0);

    Assert.assertFalse(model.isGameOver());
    model.playToPalette(2, 0);
    Assert.assertEquals(model.winningPaletteIndex(), 0);
    Assert.assertTrue(model.isGameOver());
  }

  // to replicate 2.8 Option 1 setup
  @Test
  public void testChangingCanvasThenPlayingCardToPaletteOption1() {
    mainDeck.add(new PlayingCard(Color.Red, 4));
    mainDeck.add(new PlayingCard(Color.Blue, 1));
    mainDeck.add(new PlayingCard(Color.Indigo, 5));
    model.startGame(mainDeck, false, 4, 7);

    // setup palettes
    Assert.assertFalse(model.getPalette(0).contains(b7));
    Assert.assertFalse(model.getPalette(1).contains(new PlayingCard(Color.Red, 7)));
    Assert.assertFalse(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));
    model.playToPalette(0, 3);
    model.getPalette(1).add(new PlayingCard(Color.Red, 7));
    model.getPalette(2).add(new PlayingCard(Color.Violet, 7));
    Assert.assertTrue(model.getPalette(0).contains(b7));
    Assert.assertTrue(model.getPalette(1).contains(new PlayingCard(Color.Red, 7)));
    Assert.assertTrue(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));

    Assert.assertEquals(model.getHand().size(), 6);
    Assert.assertFalse(model.getHand().contains(new PlayingCard(Color.Red, 4)));
    model.drawForHand();
    Assert.assertEquals(model.getHand().size(), 7);
    Assert.assertTrue(model.getHand().contains(new PlayingCard(Color.Red, 4)));

    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Red));
    Assert.assertEquals(model.winningPaletteIndex(), 1);
    Assert.assertEquals(model.getHand().size(), 7);
    model.playToCanvas(3);
    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Indigo));
    Assert.assertEquals(model.winningPaletteIndex(), 1);
    Assert.assertEquals(model.getHand().size(), 6);

    Assert.assertFalse(model.getPalette(3).contains(r3));
    Assert.assertEquals(model.winningPaletteIndex(), 1);
    model.playToPalette(3, 4);
    Assert.assertTrue(model.getPalette(3).contains(r3));
    Assert.assertEquals(model.winningPaletteIndex(), 3);
  }

  // to replicate 2.8 Option 2 setup
  @Test
  public void testChangingCanvasThenPlayingCardToPaletteOption2() {
    mainDeck.add(new PlayingCard(Color.Red, 4));
    mainDeck.add(new PlayingCard(Color.Blue, 1));
    mainDeck.add(new PlayingCard(Color.Indigo, 5));
    model.startGame(mainDeck, false, 4, 7);

    // setup palettes
    Assert.assertFalse(model.getPalette(0).contains(b7));
    Assert.assertFalse(model.getPalette(1).contains(new PlayingCard(Color.Red, 7)));
    Assert.assertFalse(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));
    model.playToPalette(0, 3);
    model.getPalette(1).add(new PlayingCard(Color.Red, 7));
    model.getPalette(2).add(new PlayingCard(Color.Violet, 7));
    Assert.assertTrue(model.getPalette(0).contains(b7));
    Assert.assertTrue(model.getPalette(1).contains(new PlayingCard(Color.Red, 7)));
    Assert.assertTrue(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));

    Assert.assertEquals(model.getHand().size(), 6);
    Assert.assertFalse(model.getHand().contains(new PlayingCard(Color.Red, 4)));
    model.drawForHand();
    Assert.assertEquals(model.getHand().size(), 7);
    Assert.assertTrue(model.getHand().contains(new PlayingCard(Color.Red, 4)));

    // difference from option 1
    Assert.assertEquals(model.getCanvas().getColor(), Color.Red);
    Assert.assertEquals(model.winningPaletteIndex(), 1);
    model.playToCanvas(2);
    Assert.assertEquals(model.getCanvas().getColor(),Color.Violet);
    Assert.assertEquals(model.winningPaletteIndex(), 0);
    model.drawForHand();

    Assert.assertFalse(model.getPalette(1).contains(i1));
    Assert.assertEquals(model.winningPaletteIndex(), 0);
    model.playToPalette(1, 2);
    Assert.assertTrue(model.getPalette(1).contains(i1));
    Assert.assertEquals(model.winningPaletteIndex(), 1);

  }

  // to replicate 2.9 game setup
  @Test
  public void testDrawingCardWhenDeckEmpty() {
    mainDeck.add(new PlayingCard(Color.Red, 4));
    mainDeck.add(new PlayingCard(Color.Blue, 1));
    mainDeck.add(new PlayingCard(Color.Orange, 2));
    model.startGame(mainDeck, false, 4, 8);

    // setup palettes
    Assert.assertFalse(model.getPalette(0).contains(b7));
    Assert.assertFalse(model.getPalette(1).contains(new PlayingCard(Color.Blue, 5)));
    Assert.assertFalse(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));
    model.playToPalette(0, 3);
    model.getPalette(1).add((new PlayingCard(Color.Blue, 5)));
    model.getPalette(2).add(new PlayingCard(Color.Violet, 7));
    Assert.assertTrue(model.getPalette(0).contains(b7));
    Assert.assertTrue(model.getPalette(1).contains(new PlayingCard(Color.Blue, 5)));
    Assert.assertTrue(model.getPalette(2).contains(new PlayingCard(Color.Violet, 7)));
    model.drawForHand();

    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Red));
    Assert.assertEquals(model.winningPaletteIndex(), 0);
    model.playToCanvas(2);
    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Violet));
    Assert.assertEquals(model.winningPaletteIndex(),0);
    model.playToPalette(2, 0);
    Assert.assertEquals(model.winningPaletteIndex(), 2);

    Assert.assertEquals(model.getHand().size(), 6);
    Assert.assertFalse(model.isGameOver());
    model.drawForHand();
    Assert.assertEquals(model.getHand().size(), 7);
    Assert.assertFalse(model.isGameOver());
  }

  // to replicate 2.10 game setup
  @Test
  public void testGameOverStateA() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Indigo, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    model.startGame(deck, false, 3, 7);

    Assert.assertEquals(model.winningPaletteIndex(), 1);
    model.playToPalette(2, 0);
    Assert.assertEquals(model.winningPaletteIndex(), 2);
    Assert.assertTrue(model.getHand().isEmpty());

    try {
      model.drawForHand();
    } catch (IllegalStateException ignored) {
      // ignore as it should throw exception
    }

    Assert.assertTrue(model.getHand().isEmpty());

    Assert.assertTrue(model.isGameOver());
    Assert.assertTrue(model.isGameWon());
  }

  @Test
  public void testGameNotOverStateB() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Violet, 1));
    deck.add(new PlayingCard(Color.Violet));
    deck.add(new PlayingCard(Color.Blue, 1));
    model.startGame(deck, false, 3, 7);
    model.getPalette(0).add((new PlayingCard(Color.Blue, 7)));
    model.getPalette(1).add((new PlayingCard(Color.Blue, 2)));

    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Red));
    Assert.assertEquals(model.winningPaletteIndex(), 0);
    model.playToCanvas(0);
    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Violet));
    Assert.assertEquals(model.winningPaletteIndex(),1);

    Assert.assertTrue(model.getHand().contains(new PlayingCard(Color.Blue, 1)));
    Assert.assertEquals(model.getHand().size(), 1);
    model.drawForHand();
    Assert.assertEquals(model.getHand().size(), 1);

    Assert.assertFalse(model.isGameOver());
  }

  @Test
  public void testGameNotOverStateC() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Blue, 2));
    model.startGame(deck, false, 3, 7);

    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Red));
    Assert.assertEquals(model.winningPaletteIndex(), 2);

    Assert.assertTrue(model.getHand().contains(new PlayingCard(Color.Blue, 2)));
    Assert.assertEquals(model.getHand().size(), 1);
    model.drawForHand();
    Assert.assertEquals(model.getHand().size(), 1);

    Assert.assertFalse(model.isGameOver());
  }

  @Test
  public void testPlayToWinningPalette() {
    model.startGame(expectedCards, false, 3, 7);
    Assert.assertEquals(model.winningPaletteIndex(), 2);

    boolean exceptionCaught = false;
    try {
      model.playToPalette(2, 3);
    } catch (Exception e) {
      exceptionCaught = true;
    }
    Assert.assertTrue(exceptionCaught);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToPaletteGameNotStarted() {
    model.playToPalette(2, 3);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToPaletteGameAlreadyOver() {
    model.startGame(expectedCards, false, 3, 7);
    model.playToPalette(1, 5);
    model.playToPalette(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayToPalettePaletteOutOfBounds() {
    model.startGame(expectedCards, false, 3, 7);
    model.playToPalette(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayToPaletteCardOutOfBounds() {
    model.startGame(expectedCards, false, 3, 7);
    model.playToPalette(0, 10);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToCanvasGameNotStarted() {
    model.playToCanvas(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToCanvasGameAlreadyOver() {
    model.startGame(expectedCards, false, 3, 7);
    model.playToPalette(1, 5);
    model.playToCanvas(0);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToCanvasTwice() {
    model.startGame(expectedCards, false, 3, 7);

    model.playToCanvas(0);
    model.playToCanvas(1);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayToCanvasLastCardToCanvas() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Blue, 1));
    model.startGame(deck, false, 3, 7);
    model.playToCanvas(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayToCanvasCardOutOfBounds() {
    model.startGame(expectedCards, false, 3, 7);
    model.playToCanvas( 7);
  }

  @Test(expected = IllegalStateException.class)
  public void testDrawForHandGameNotStarted() {
    model.drawForHand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameNullDeck() {
    model.startGame(null, false, 3, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameHandSizeLessThan0() {
    model.startGame(expectedCards, false, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGamePalettesLessThan1() {
    model.startGame(expectedCards, false, 0, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameDeckSizeLessThanPalettes() {
    model.startGame(expectedCards, false, 36, 7);
  }

  @Test(expected = IllegalStateException.class)
  public void testStartGameGameAlreadyStarted() {
    model.startGame(expectedCards, false, 3, 7);
    model.startGame(expectedCards, false, 2, 7);
  }

  @Test(expected = IllegalStateException.class)
  public void testStartGameGameAlreadyOver() {
    model.startGame(expectedCards, false, 3, 7);
    model.playToPalette(1, 5);
    model.startGame(expectedCards, false, 3, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameDeckContainsDuplicates() {
    expectedCards.add(new PlayingCard(Color.Red, 1));
    model.startGame(expectedCards, false, 3, 7);
  }

  @Test(expected = IllegalStateException.class)
  public void testNumOfCardsInDeckGameNotStarted() {
    model.numOfCardsInDeck();
  }

  @Test(expected = IllegalStateException.class)
  public void testNumPalettesGameNotStarted() {
    model.numOfCardsInDeck();
  }

  @Test(expected = IllegalStateException.class)
  public void testWinningPaletteIndexGameNotStarted() {
    model.winningPaletteIndex();
  }

  @Test(expected = IllegalStateException.class)
  public void testIsGameOverGameNotStarted() {
    model.isGameOver();
  }

  @Test(expected = IllegalStateException.class)
  public void testIsGameWonGameNotStarted() {
    model.isGameWon();
  }

  @Test
  public void testIsGameWonGameAlreadyOver() {
    model.startGame(expectedCards, false, 3, 7);
    model.playToPalette(1, 5);
    Assert.assertTrue(model.isGameOver());
    Assert.assertFalse(model.isGameWon());

  }

  @Test(expected = IllegalStateException.class)
  public void testGetHandGameNotStarted() {
    model.getHand();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetPaletteGameNotStarted() {
    model.getPalette(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPalettePaletteOutOfBounds() {
    model.startGame(expectedCards, false, 3, 7);
    model.getPalette(4);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCanvasGameNotStarted() {
    model.getCanvas();
  }

  @Test
  public void testGetAllCards() {
    // tests total number of playable cards
    Assert.assertEquals(expectedCards.size(), 35);

    // tests that no card has a number out of range
    for (PlayingCard card : expectedCards) {
      Assert.assertFalse(card.getNumber() < 1 || card.getNumber() > 7);
    }
  }

  @Test
  public void testOneTurnGameLoss() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Blue, 1));
    model.startGame(deck, false, 3, 1);

    Assert.assertFalse(model.isGameOver());
    Assert.assertFalse(model.isGameWon());
    model.playToPalette(0, 0);
    Assert.assertTrue(model.isGameOver());
    Assert.assertFalse(model.isGameWon());
  }

  @Test
  public void testRedRule() {
    model.startGame(expectedCards, false, 3, 7);

    Assert.assertEquals(model.winningPaletteIndex(), 2);
    model.playToPalette(0, 0);
    Assert.assertEquals(model.winningPaletteIndex(), 0);
  }

  @Test
  public void testOrangeRule() {
    model.startGame(expectedCards, false, 3, 7);

    model.playToCanvas(4);
    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Orange));
    Assert.assertEquals(model.winningPaletteIndex(), 2);
    model.playToPalette(1, 4);
    Assert.assertEquals(model.winningPaletteIndex(), 1);
  }

  @Test
  public void testBlueRule() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Blue, 1));
    deck.add(new PlayingCard(Color.Blue, 2));
    model.startGame(deck, false, 3, 7);

    model.playToCanvas(0);
    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Blue));
    Assert.assertEquals(model.winningPaletteIndex(), 2);
    model.playToPalette(1, 0);
    Assert.assertEquals(model.winningPaletteIndex(), 1);
  }

  @Test
  public void testIndigoRule() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Indigo, 1));
    deck.add(new PlayingCard(Color.Blue, 4));
    model.startGame(deck, false, 3, 7);

    model.playToCanvas(0);
    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Indigo));
    Assert.assertEquals(model.winningPaletteIndex(), 0);
    model.playToPalette(2, 0);
    Assert.assertEquals(model.winningPaletteIndex(), 2);
  }

  @Test
  public void testVioletRule() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Violet, 1));
    deck.add(new PlayingCard(Color.Blue, 2));
    model.startGame(deck, false, 3, 7);

    model.playToCanvas(0);
    Assert.assertEquals(model.getCanvas(), new PlayingCard(Color.Violet));
    Assert.assertEquals(model.winningPaletteIndex(), 2);
    model.playToPalette(1, 0);
    Assert.assertEquals(model.winningPaletteIndex(), 1);
  }

  @Test
  public void testSoloRedGameView() {
    SoloRedGameTextView view = new SoloRedGameTextView(model);

    List<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Red, 1));
    deck.add(new PlayingCard(Color.Red, 2));
    deck.add(new PlayingCard(Color.Red, 3));
    deck.add(new PlayingCard(Color.Blue, 1));
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Blue, 3));
    model.startGame(deck, false, 2, 2);

    String expectedOutput = "Canvas: R\n"
            + "P1: R1\n"
            + "> P2: R2\n"
            + "Hand: R3 B1";

    Assert.assertEquals(expectedOutput, view.toString());
  }

  /**
   * Initializes model with the same deck, but one model is shuffled, the other is not.
   */
  @Test
  public void testShuffleDeck() {
    SoloRedGameModel notShuffledModel = new SoloRedGameModel();
    notShuffledModel.startGame(mainDeck, false, 3, 4);
    model.startGame(mainDeck, true, 3, 4);

    Assert.assertNotEquals(model.getHand(), notShuffledModel.getHand());
  }

  @Test
  public void testGetHandMutability() {
    model.startGame(mainDeck, false, 3, 4);
    Assert.assertEquals(model.getHand().size(), 4);
    model.getHand().add(new PlayingCard(Color.Blue, 1));
    Assert.assertEquals(model.getHand().size(), 5);
  }

  @Test(expected = IllegalStateException.class)
  public void testDrawForHandAfterGameEnded() {
    mock.startGame(mainDeck, false, 4, 5);

    mock.gameOverTrue();
    mock.drawForHand();
  }

  @Test
  public void testTieRuleRed() {
    model.startGame(mainDeck, false, 4, 7);
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4]
    model.playToPalette(0, 1); // [B2 B4]
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4]
    Assert.assertTrue(model.isGameOver());
  }

  @Test
  public void testTieRuleOrange() {
    model.startGame(mainDeck, false, 4, 7);
    model.playToCanvas(5);
    Assert.assertEquals(new PlayingCard(Color.Orange), model.getCanvas());
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4]
    model.playToPalette(0, 1); // [B2 B4]
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4]
    Assert.assertTrue(model.isGameOver());
  }

  @Test
  public void testTieRuleBlue() {
    model.startGame(mainDeck, false, 4, 7);
    model.getPalette(0).add(new PlayingCard(Color.Indigo, 7));
    model.getPalette(0).add(new PlayingCard(Color.Red, 4));
    Assert.assertEquals(0, model.winningPaletteIndex()); // [B2 I7 R4]
    model.playToPalette(1, 3); // [R1 B7]
    model.playToCanvas(1);
    Assert.assertEquals(new PlayingCard(Color.Blue), model.getCanvas());
    Assert.assertEquals(0, model.winningPaletteIndex()); // [B2 I7 R4]
    model.playToPalette(1, 1); // [R1 B7 V6]
    Assert.assertEquals(1, model.winningPaletteIndex()); // [R1 B7 V6]
  }

  @Test
  public void testTieRuleIndigo() {
    model.startGame(mainDeck, false, 4, 7);
    model.getPalette(0).add(new PlayingCard(Color.Violet, 4));
    model.getPalette(0).add(new PlayingCard(Color.Red, 5));
    model.getPalette(0).add(new PlayingCard(Color.Violet, 6));
    Assert.assertEquals(0, model.winningPaletteIndex()); // [B2 V4 R5 V6]

    model.getPalette(1).add(new PlayingCard(Color.Orange, 2));
    model.getPalette(1).add(new PlayingCard(Color.Blue, 3));
    Assert.assertNotEquals(1, model.winningPaletteIndex()); // [R1 O2 B3]

    model.getPalette(3).add(new PlayingCard(Color.Blue, 6));
    model.getPalette(3).add(new PlayingCard(Color.Blue, 5));
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4 B6 B5]

    model.playToCanvas(4);
    Assert.assertEquals(new PlayingCard(Color.Indigo), model.getCanvas());
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4 B6 B5]
  }

  @Test
  public void testTieRuleViolet() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Orange, 3));
    deck.add(new PlayingCard(Color.Indigo, 6));
    deck.add(new PlayingCard(Color.Violet, 7));
    deck.add(new PlayingCard(Color.Blue, 2));

    model.startGame(deck, false, 2, 3);
    model.getPalette(1).add(new PlayingCard(Color.Blue, 2));
    model.getPalette(1).add(new PlayingCard(Color.Blue, 5));
    model.getPalette(1).add(new PlayingCard(Color.Violet, 7));
    Assert.assertEquals(4, model.getPalette(1).size());

    Assert.assertEquals(1, model.winningPaletteIndex()); // [I6 B2 B5 V7]
    model.playToCanvas(0);
    Assert.assertEquals(new PlayingCard(Color.Violet), model.getCanvas());
    Assert.assertEquals(0, model.winningPaletteIndex()); // [O3]
  }

  @Test
  public void testTieRuleOrangeMultipleSameCounts() {
    model.startGame(mainDeck, false, 4, 7);
    model.playToCanvas(5);
    Assert.assertEquals(new PlayingCard(Color.Orange), model.getCanvas());
    model.getPalette(3).add(new PlayingCard(Color.Red, 4));
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4 R4]
    model.playToPalette(0, 2); // [B2 V6]
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4 R4]
    model.getPalette(0).add(new PlayingCard(Color.Red, 2)); // [B2 V6 R2]
    Assert.assertEquals(3, model.winningPaletteIndex()); // [O4 R4]
  }


  @Test
  public void testTieRuleBlueUsesEntirePalette() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Red, 6));
    deck.add(new PlayingCard(Color.Blue, 7));
    deck.add(new PlayingCard(Color.Orange, 7));

    model.startGame(deck, false, 2, 7);
    model.getPalette(0).add(new PlayingCard(Color.Blue, 3));
    model.getPalette(0).add(new PlayingCard(Color.Orange, 6));
    model.getPalette(0).add(new PlayingCard(Color.Blue, 7));
    Assert.assertEquals(0, model.winningPaletteIndex()); // [B2 B3 O6 B7]
    model.getPalette(1).add(new PlayingCard(Color.Violet, 2));
    Assert.assertNotEquals(1, model.winningPaletteIndex()); // [R6 V2]

    model.playToCanvas(0);
    Assert.assertEquals(new PlayingCard(Color.Blue), model.getCanvas());
    Assert.assertEquals(0, model.winningPaletteIndex());
  }

  @Test
  public void testTieRuleVioletNoCardsUnder4() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Indigo, 6));
    deck.add(new PlayingCard(Color.Orange, 4));
    deck.add(new PlayingCard(Color.Violet, 5));
    deck.add(new PlayingCard(Color.Blue, 2));

    model.startGame(deck, false, 2, 3);
    model.getPalette(0).add(new PlayingCard(Color.Red, 5));
    model.getPalette(0).add(new PlayingCard(Color.Violet, 5));
    Assert.assertEquals(0, model.winningPaletteIndex()); // [I6 R5 V5]
    model.getPalette(1).add(new PlayingCard(Color.Violet, 7));
    model.getPalette(1).add(new PlayingCard(Color.Indigo, 4));
    Assert.assertEquals(1, model.winningPaletteIndex()); // [O4 V7 I4]

    model.playToCanvas(0);
    Assert.assertEquals(new PlayingCard(Color.Violet), model.getCanvas());
    Assert.assertEquals(1, model.winningPaletteIndex());
  }

  @Test
  public void testTieRuleIndigoMultipleSameRuns() {
    model.startGame(mainDeck, false, 4, 7);
    model.getPalette(0).add(new PlayingCard(Color.Violet, 6));
    model.getPalette(0).add(new PlayingCard(Color.Red, 5));
    model.getPalette(0).add(new PlayingCard(Color.Orange, 1));
    Assert.assertEquals(0, model.winningPaletteIndex()); // [B2 V6 R5 O1]

    model.getPalette(1).add(new PlayingCard(Color.Red, 6));
    model.getPalette(1).add(new PlayingCard(Color.Violet, 7));
    model.getPalette(1).add(new PlayingCard(Color.Orange, 2));
    Assert.assertEquals(1, model.winningPaletteIndex()); // [R1 R6 V7 O2]

    model.getPalette(3).add(new PlayingCard(Color.Blue, 5));
    Assert.assertNotEquals(0, model.winningPaletteIndex()); // [O4 B5]

    model.playToCanvas(4);
    Assert.assertEquals(new PlayingCard(Color.Indigo), model.getCanvas());
    Assert.assertEquals(1, model.winningPaletteIndex()); // [R1 O2 R6 V7]
  }

  @Test
  public void testTieRuleIndigoMultipleSameNumbersInSameRun() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Indigo, 5));
    deck.add(new PlayingCard(Color.Indigo, 7));
    deck.add(new PlayingCard(Color.Red, 1));

    model.startGame(deck, false, 2, 3);
    model.getPalette(0).add(new PlayingCard(Color.Violet, 5));
    model.getPalette(0).add(new PlayingCard(Color.Indigo, 6));
    model.getPalette(0).add(new PlayingCard(Color.Red, 6));
    Assert.assertEquals(0, model.winningPaletteIndex()); // [B2 V5 I6 R6]
    model.getPalette(1).add(new PlayingCard(Color.Orange, 6));
    Assert.assertNotEquals(1, model.winningPaletteIndex()); // [I5 O6]

    model.playToCanvas(0);
    Assert.assertEquals(new PlayingCard(Color.Indigo), model.getCanvas());
    Assert.assertEquals(0, model.winningPaletteIndex());
  }

  @Test
  public void testTieRuleIndigoMultipleSameRunOf1() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 1));
    deck.add(new PlayingCard(Color.Indigo, 6));
    deck.add(new PlayingCard(Color.Indigo, 2));
    deck.add(new PlayingCard(Color.Red, 1));

    model.startGame(deck, false, 2, 3);
    model.getPalette(0).add(new PlayingCard(Color.Violet, 5));
    model.getPalette(0).add(new PlayingCard(Color.Red, 3));
    Assert.assertNotEquals(0, model.winningPaletteIndex()); // [B1 V5 R3]
    model.getPalette(1).add(new PlayingCard(Color.Red, 2));
    model.getPalette(1).add(new PlayingCard(Color.Orange, 4));
    Assert.assertEquals(1, model.winningPaletteIndex()); // [I6 R2 O4]

    model.playToCanvas(0);
    Assert.assertEquals(new PlayingCard(Color.Indigo), model.getCanvas());
    Assert.assertEquals(1, model.winningPaletteIndex()); // [I6 R2 O4]
  }

  @Test
  public void testTieRuleBlueAllColors() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Red, 6));
    deck.add(new PlayingCard(Color.Blue, 7));
    deck.add(new PlayingCard(Color.Orange, 7));

    model.startGame(deck, false, 2, 7);
    model.getPalette(0).add(new PlayingCard(Color.Red, 3));
    model.getPalette(0).add(new PlayingCard(Color.Orange, 6));
    model.getPalette(0).add(new PlayingCard(Color.Violet, 7));
    model.getPalette(0).add(new PlayingCard(Color.Indigo, 4));
    Assert.assertEquals(0, model.winningPaletteIndex()); // [B2 R3 O6 V7 I4]
    model.getPalette(1).add(new PlayingCard(Color.Violet, 2));
    model.getPalette(1).add(new PlayingCard(Color.Blue, 3));
    model.getPalette(1).add(new PlayingCard(Color.Indigo, 5));
    model.getPalette(1).add(new PlayingCard(Color.Orange, 7));
    Assert.assertEquals(1, model.winningPaletteIndex()); // [R6 V2 B3 I5 O7]

    model.playToCanvas(0);
    Assert.assertEquals(new PlayingCard(Color.Blue), model.getCanvas());
    Assert.assertEquals(1, model.winningPaletteIndex()); // [R6 V2 B3 I5 O7]
  }
}
