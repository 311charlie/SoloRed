package cs3500.solored.controller.mocks;

import cs3500.solored.model.hw02.Color;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.PlayingCard;
import java.util.ArrayList;
import java.util.List;

/**
 * A mock SoloRedModel for testing purposes.
 */
public class MockSoloRedModel implements RedGameModel<PlayingCard> {
  private final ArrayList<PlayingCard> hand = new ArrayList<>();
  private final ArrayList<ArrayList<PlayingCard>> palettes = new ArrayList<>();
  private final PlayingCard canvas = new PlayingCard(Color.Red, 1);
  private int playToPaletteCount = 0;
  private int playToCanvasCount = 0;

  private boolean startGame;
  private boolean gameOver;

  @Override
  public void startGame(List<PlayingCard> deck, boolean shuffle, int numPalettes, int handSize) {
    if (this.startGame) {
      throw new IllegalStateException("Game already started");
    }
    else if (this.gameOver) {
      throw new IllegalStateException("Game has already ended");
    }

    for (int i = 0; i < handSize; i++) {
      hand.add(new PlayingCard(Color.Red, i + 1));
    }
    for (int i = 0; i < numPalettes; i++) {
      ArrayList<PlayingCard> palette = new ArrayList<>();
      palette.add(new PlayingCard(Color.Blue, i + 1));
      palettes.add(palette);
    }

    startGame = true;
    gameOver = false;
  }

  @Override
  public void playToPalette(int paletteIdx, int cardIdxInHand) {
    playToPaletteCount++;
  }

  @Override
  public void playToCanvas(int cardIdxInHand) {
    playToCanvasCount++;
  }

  @Override
  public void drawForHand() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    if (this.isGameOver()) {
      throw new IllegalStateException("Game has already ended");
    }
  }

  @Override
  public int numOfCardsInDeck() {
    return 0;
  }

  @Override
  public int numPalettes() {
    return palettes.size();
  }

  @Override
  public int winningPaletteIndex() {
    return 0;
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public boolean isGameWon() {
    return true;
  }

  @Override
  public List<PlayingCard> getHand() {
    return hand;
  }

  @Override
  public List<PlayingCard> getPalette(int paletteNum) {
    return palettes.get(paletteNum);
  }

  @Override
  public PlayingCard getCanvas() {
    return canvas;
  }

  @Override
  public List<PlayingCard> getAllCards() {
    List<PlayingCard> allPlayingCards = new ArrayList<>();
    for (Color color : Color.values()) {
      for (int i = 1; i < 8; i++) {
        allPlayingCards.add(new PlayingCard(color, i));
      }
    }
    return allPlayingCards;
  }

  public int getPlayToPaletteCount() {
    return playToPaletteCount;
  }

  public int getPlayToCanvasCount() {
    return playToCanvasCount;
  }

  public void startGameTrue() {
    startGame = true;
  }

  public void gameOverTrue() {
    gameOver = true;
  }
}
