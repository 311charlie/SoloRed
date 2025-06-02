package cs3500.solored.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.solored.controller.mocks.MockFailingAppendable;
import cs3500.solored.controller.mocks.MockFailingReadable;
import cs3500.solored.controller.mocks.MockSoloRedModel;
import cs3500.solored.controller.mocks.MockSoloRedModelThrowsIAE;
import cs3500.solored.controller.mocks.MockSoloRedModelThrowsISE;
import cs3500.solored.model.hw02.Color;
import cs3500.solored.model.hw02.PlayingCard;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.SoloRedGameModel;

/**
 * Class to store the tests for the SoloRedTextController.
 */
public class ControllerTest {

  protected RedGameModel<PlayingCard> model;
  protected MockSoloRedModel mock;
  protected List<PlayingCard> expectedCards;
  protected ArrayList<PlayingCard> mainDeck;

  protected PlayingCard b2;
  protected PlayingCard r1;
  protected PlayingCard o1;
  protected PlayingCard o4;
  protected PlayingCard r2;
  protected PlayingCard b4;
  protected PlayingCard v6;
  protected PlayingCard b7;
  protected PlayingCard i1;
  protected PlayingCard o3;
  protected PlayingCard r3;

  @Before
  public void setup() {
    model = new SoloRedGameModel();
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


  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructWithNullAppendableReadable() {
    RedGameController controller = new SoloRedTextController(null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerConstructWithNullAppendable() {
    RedGameController controller = new SoloRedTextController(new StringReader("test"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameWithNullModel() {
    Readable rd = new StringReader("palette");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(null, new ArrayList<>(), false, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorWithStartGameExceptionsNullDeck() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, null, false, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorISE() {
    Readable rd = new StringReader("q");
    Appendable ap = new StringBuilder();
    RedGameModel<PlayingCard> mock = new MockSoloRedModelThrowsISE();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(mock, new ArrayList<>(), false, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorIAE() {
    Readable rd = new StringReader("q");
    Appendable ap = new StringBuilder();
    RedGameModel<PlayingCard> mock = new MockSoloRedModelThrowsIAE();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(mock, new ArrayList<>(), false, 2, 5);
  }

  @Test
  public void testPlayToCanvasWithLegalModelInput() {
    Readable rd = new StringReader("canvas 1 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(mock, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertEquals(mock.getPlayToCanvasCount(), 1);
    Assert.assertEquals(1, gameQuitCount);
  }

  @Test
  public void testPlayToPaletteWithLegalModelInput() {
    Readable rd = new StringReader("palette 1 3 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(mock, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertEquals(mock.getPlayToPaletteCount(), 1);
    Assert.assertEquals(1, gameQuitCount);
  }

  @Test
  public void testConfirmInputsToPlayToXModelMethods() {
    Readable rd = new StringReader("palette 0 1 canvas 1 q palette 2 3");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(mock, mainDeck, false, 2, 5);

    Assert.assertEquals(mock.getPlayToPaletteCount(), 1);
    Assert.assertEquals(mock.getPlayToCanvasCount(), 1);
  }


  // Tests if program halts when indices out of bounds
  @Test
  public void testPlayToPaletteExceptionsDoNotHaltProgramIndicesOutBounds() {
    Readable rd = new StringReader("palette 12 0 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, model.getAllCards(), false, 2, 5);

    String output = ap.toString();

    Assert.assertTrue(output.contains("Invalid move"));
    Assert.assertFalse(model.isGameOver());
  }


  // Tests if program halts when played to winning palette.
  @Test
  public void testPlayToPaletteExceptionsDoNotHaltProgramWinningPalette() {
    Readable rd = new StringReader("palette 2 1 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, model.getAllCards(), false, 2, 5);

    String output = ap.toString();

    Assert.assertTrue(output.contains("Palette referred to by index is already winning"));
    Assert.assertFalse(model.isGameOver());
  }

  @Test
  public void testPlayToPaletteWithNaturalNumbersBehavior() {
    Readable rd = new StringReader("palette 0 1 q q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(mock, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertEquals(gameQuitCount, 1);
  }

  @Test
  public void testSmallGameThatEndsWithMockModelWithInvalidControllerInput() {
    Readable rd = new StringReader("invalid input q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertTrue(output.contains("Invalid command"));
    Assert.assertFalse(model.isGameOver());
    Assert.assertEquals(gameQuitCount, 1);
  }

  @Test
  public void testPlayToPaletteWithLettersAsNumbersInput() {
    Readable rd = new StringReader("palette a b q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(mock, mainDeck, false, 2, 5);

    String output = ap.toString();
    int invalidCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Invalid")) {
        invalidCount++;
      }
    }

    Assert.assertEquals(invalidCount, 0);
  }

  @Test
  public void testPlayToCanvasWithZeroAsInput() {
    Readable rd = new StringReader("canvas 0 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertEquals(gameQuitCount, 1);
  }

  @Test
  public void testPlayToPaletteWithLegalControllerAndModelInput() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    List<PlayingCard> mainDeck = mock.getAllCards();
    controller.playGame(mock, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertEquals(mock.getPlayToPaletteCount(), 1);
    Assert.assertEquals(gameQuitCount, 1);
  }

  @Test
  public void testQuittingMidCommand() {
    Readable rd = new StringReader("palette 1 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    List<PlayingCard> mainDeck = mock.getAllCards();
    controller.playGame(mock, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertEquals(gameQuitCount, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameWithEmptyDeck() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(new SoloRedGameModel(), new ArrayList<>(), false, 2, 5);
  }

  @Test
  public void testMultipleValidCommands() {
    Readable rd = new StringReader("palette 1 2 canvas 3 q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, mainDeck, false, 2, 5);

    String output = ap.toString();
    Assert.assertTrue(output.contains("Game quit!"));
  }

  @Test(expected = IllegalStateException.class)
  public void testAppendableFailureBehavior() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new MockFailingAppendable();

    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    controller.playGame(model, mainDeck, false, 2, 5);
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableFailureBehavior() {
    Readable rd = new MockFailingReadable();
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, mainDeck, false, 2, 5);
  }

  @Test
  public void testMultipleQuitCommands() {
    Readable rd = new StringReader("q q q");
    Appendable ap = new StringBuilder();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);

    controller.playGame(model, mainDeck, false, 2, 5);

    String output = ap.toString();
    int gameQuitCount = 0;
    for (String line : output.split("\n")) {
      if (line.contains("Game quit!")) {
        gameQuitCount++;
      }
    }

    Assert.assertEquals(gameQuitCount, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorWithStartGameExceptionsOnePalette() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new StringBuilder();

    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    controller.playGame(model, mainDeck, false, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorWithStartGameExceptionsZeroHandSize() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new StringBuilder();

    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    controller.playGame(model, mainDeck, false, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorWithStartGameExceptionsPalettesGreaterThanDeck() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new StringBuilder();

    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    controller.playGame(model, mainDeck, false, 30, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorWithStartGameExceptionsGameStart() {
    Readable rd = new StringReader("palette 1 2 q");
    Appendable ap = new StringBuilder();
    MockSoloRedModel mock = new MockSoloRedModel();
    mock.startGameTrue();

    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    controller.playGame(mock, mainDeck, false, 30, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorWithStartGameExceptionsGameOver() {
    Readable rd = new StringReader("palette 3 2");
    Appendable ap = new StringBuilder();
    MockSoloRedModel mock = new MockSoloRedModel();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    mock.gameOverTrue();

    controller.playGame(mock, new ArrayList<>(), false, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameBehaviorWithStartGameExceptionsDuplicateInDeck() {
    mainDeck.add(new PlayingCard(Color.Blue, 2));
    Readable rd = new StringReader("palette 3 2");
    Appendable ap = new StringBuilder();
    MockSoloRedModel mock = new MockSoloRedModel();
    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    mock.gameOverTrue();

    controller.playGame(mock, mainDeck, false, 2, 5);
  }

  @Test
  public void testSmallGameWin() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Blue, 3));
    deck.add(new PlayingCard(Color.Blue, 4));

    Readable rd = new StringReader("palette 1 1");
    Appendable ap = new StringBuilder();

    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    controller.playGame(model, deck, false, 2, 5);

    String output = ap.toString();
    Assert.assertTrue(output.contains("Game won."));
  }

  @Test
  public void testSmallGameWinWithCommandError() {
    ArrayList<PlayingCard> deck = new ArrayList<>();
    deck.add(new PlayingCard(Color.Blue, 2));
    deck.add(new PlayingCard(Color.Blue, 3));
    deck.add(new PlayingCard(Color.Blue, 4));

    Readable rd = new StringReader("fwa palette 1 1");
    Appendable ap = new StringBuilder();

    SoloRedTextController controller = new SoloRedTextController(rd, ap);
    controller.playGame(model, deck, false, 2, 5);

    String output = ap.toString();
    Assert.assertTrue(output.contains("Invalid command. Try again"));
    Assert.assertTrue(output.contains("Game won."));
  }
}

