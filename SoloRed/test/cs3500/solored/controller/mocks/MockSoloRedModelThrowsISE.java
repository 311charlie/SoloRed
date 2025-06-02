package cs3500.solored.controller.mocks;

import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.PlayingCard;
import java.util.List;

/**
 * A mock model that throws an IllegalStateException when startGame is called.
 */
public class MockSoloRedModelThrowsISE implements RedGameModel<PlayingCard> {

  @Override
  public void startGame(List<PlayingCard> deck, boolean shuffle, int numPalettes, int handSize) {
    throw new IllegalStateException();
  }

  @Override
  public void playToPalette(int paletteIdx, int cardIdxInHand) {
    // empty
  }

  @Override
  public void playToCanvas(int cardIdxInHand) {
    // empty
  }

  @Override
  public void drawForHand() {
    // empty
  }

  @Override
  public int numOfCardsInDeck() {
    return 0;
  }

  @Override
  public int numPalettes() {
    return 0;
  }

  @Override
  public int winningPaletteIndex() {
    return 0;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public boolean isGameWon() {
    return false;
  }

  @Override
  public List<PlayingCard> getHand() {
    return null;
  }

  @Override
  public List<PlayingCard> getPalette(int paletteNum) {
    return null;
  }

  @Override
  public PlayingCard getCanvas() {
    return null;
  }

  @Override
  public List<PlayingCard> getAllCards() {
    return null;
  }
}