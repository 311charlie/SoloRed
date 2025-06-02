package cs3500.solored.model.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import cs3500.solored.model.hw02.rules.BlueRule;
import cs3500.solored.model.hw02.rules.IndigoRule;
import cs3500.solored.model.hw02.rules.OrangeRule;
import cs3500.solored.model.hw02.rules.RedRule;
import cs3500.solored.model.hw02.rules.VioletRule;

/**
 * Represents an abstract RedGameModel for different game types of RedGameModel to extend and
 * implement methods from.
 */
public abstract class AbstractRedGameModel implements RedGameModel<PlayingCard> {
  // The deck from which cards are drawn.
  protected List<PlayingCard> deck;

  /**
   * palette: A list of lists, where each inner list represents a palette of cards for a player.
   * The last card in each inner list is the most recently added card to the palette.
   */
  protected ArrayList<ArrayList<PlayingCard>> palette;

  // The cards currently in the player's hand.
  protected ArrayList<PlayingCard> hand;

  // Maximum number of cards the player can hold in their hand.
  protected int maxHandSize;

  // The current card on the canvas, which defines the game's winning rule.
  protected PlayingCard canvas;

  // Random object used for shuffling the deck.
  protected Random shuffleObject;

  // Keeps track of the last turn played ("playToPalette" or "playToCanvas").
  protected String lastTurn;

  // Flags indicating if the game has been lost or started.
  protected boolean gameLost;
  protected boolean startGame;

  /**
   * Constructs an AbstractRedGameModel with flexible fields according to startGame.
   */
  public AbstractRedGameModel() {
    // Creates a state of the game to be used with startGame.
  }

  /**
   * Constructs an AbstractRedGameModel according to startGame with a shuffled deck.
   *
   * @param object To shuffle the deck
   */
  public AbstractRedGameModel(Random object) {
    this.shuffleObject = Objects.requireNonNull(object);
  }

  @Override
  public void playToPalette(int paletteIdx, int cardIdxInHand) {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    if (this.isGameOver()) {
      throw new IllegalStateException("Game has already ended");
    }
    if (isPaletteWinning(paletteIdx)) {
      throw new IllegalStateException("Palette referred to by index is already winning");
    }
    if (paletteIdx < 0 || paletteIdx >= this.numPalettes()) {
      throw new IllegalArgumentException("Palette index cannot be < 0 or >= number of palettes: "
              + paletteIdx);
    }
    if (cardIdxInHand < 0 || cardIdxInHand >= this.hand.size()) {
      throw new IllegalArgumentException("Card index cannot be < 0 or >= number of cards in hand: "
              + cardIdxInHand);
    }

    ArrayList<PlayingCard> losingPalette = this.palette.get(paletteIdx);
    PlayingCard cardToPalette = this.hand.remove(cardIdxInHand);
    losingPalette.add(cardToPalette);
    this.lastTurn = "playToPalette";
    checkWinningIndex(paletteIdx);
  }

  /**
   * Checks if the given palette index is already winning.
   * @param paletteIdx the given palette index
   * @return a boolean whether the palette index is already winning or not
   */
  private boolean isPaletteWinning(int paletteIdx) {
    return paletteIdx == this.winningPaletteIndex();
  }

  /**
   * Checks if the palette index played to was already winning.
   * @param paletteIdx the given palette index
   */
  private void checkWinningIndex(int paletteIdx) {
    if (winningPaletteIndex() != paletteIdx) {
      this.gameLost = true;
    }
  }

  @Override
  public void playToCanvas(int cardIdxInHand) {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    if (this.isGameOver()) {
      throw new IllegalStateException("Game has already ended");
    }
    if (this.lastTurn.equals("playToCanvas")) {
      throw new IllegalStateException("Already played to canvas");
    }
    if (cardIdxInHand < 0 || cardIdxInHand >= this.hand.size()) {
      throw new IllegalArgumentException("Card index cannot be < 0 or > number of cards in hand: "
              + cardIdxInHand);
    }
    if (this.hand.size() == 1) {
      throw new IllegalStateException("Cannot use last card to canvas");
    }

    PlayingCard canvasCard = this.hand.remove(cardIdxInHand);
    this.canvas = new PlayingCard(canvasCard.getColor());
    this.lastTurn = "playToCanvas";
  }

  @Override
  public void drawForHand() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    if (this.isGameOver()) {
      throw new IllegalStateException("Game has already ended");
    }
    while (this.hand.size() < this.maxHandSize && !this.deck.isEmpty()) {
      this.hand.add(this.deck.remove(0));
      this.lastTurn = "drawForHand";
    }
  }

  @Override
  public void startGame(List<PlayingCard> deck, boolean shuffle, int numPalettes, int handSize) {
    // given deck cannot be null or contain null elements or else the game will not start.
    if (deck == null || deck.contains(null)) {
      throw new IllegalArgumentException("Deck is null or contains null elements");
    }
    if (handSize <= 0) {
      throw new IllegalArgumentException("Hand size must be greater than 0: " + handSize);
    }
    if (numPalettes <= 1) {
      throw new IllegalArgumentException("Number of palettes must be greater than 1: "
              + numPalettes);
    }
    if (deck.size() <= numPalettes) {
      throw new IllegalArgumentException("Deck size must be greater than number of Palettes: "
              + deck.size());
    }
    if (this.startGame) {
      throw new IllegalStateException("Game already started");
    }

    if (shuffle) {
      if (this.shuffleObject == null) {
        this.shuffleObject = new Random();
        Collections.shuffle(deck, this.shuffleObject);
      } else {
        Collections.shuffle(deck, this.shuffleObject);
      }
    }
    this.deck = new ArrayList<>(deck);

    this.hand = new ArrayList<>();
    this.maxHandSize = handSize;
    this.canvas = new PlayingCard(Color.Red);
    this.gameLost = false;
    this.startGame = true;

    if (this.isGameOver()) {
      throw new IllegalStateException("Game already is over");
    }
    if (this.duplicatesInDeck()) {
      throw new IllegalArgumentException("Deck contains duplicate cards");
    }

    this.palette = new ArrayList<>();
    while (this.numPalettes() < numPalettes) {
      ArrayList<PlayingCard> newPalette = new ArrayList<>();
      newPalette.add(this.deck.remove(0));
      this.palette.add(newPalette);
    }

    this.drawForHand();
    this.lastTurn = "drawForHand";
  }

  private boolean duplicatesInDeck() {
    for (int i = 0; i < this.deck.size(); i++) {
      for (int j = i + 1; j < this.deck.size(); j++) {
        if (this.deck.get(i).equals(this.deck.get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public int numOfCardsInDeck() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    return this.deck.size();
  }

  @Override
  public int numPalettes() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    return this.palette.size();
  }

  @Override
  public int winningPaletteIndex() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }

    switch (this.canvas.getColor()) {
      case Red:
        return new RedRule().rule(this.palette);
      case Orange:
        return new OrangeRule().rule(this.palette);
      case Blue:
        return new BlueRule().rule(this.palette);
      case Indigo:
        return new IndigoRule().rule(this.palette);
      case Violet:
        return new VioletRule().rule(this.palette);
      default:
        throw new IllegalStateException("Unknown color: " + this.canvas.getColor());
    }
  }

  @Override
  public boolean isGameOver() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    return this.gameLost || isGameWon();
  }

  @Override
  public boolean isGameWon() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    return !this.gameLost && this.hand.isEmpty() && this.deck.isEmpty();
  }

  @Override
  public List<PlayingCard> getHand() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    return this.hand;
  }

  @Override
  public List<PlayingCard> getPalette(int paletteNum) {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    if (paletteNum < 0 || paletteNum > this.numPalettes()) {
      throw new IllegalArgumentException("Palette index cannot be < 0 or > number of palettes: "
              + paletteNum);
    }
    return this.palette.get(paletteNum);
  }

  @Override
  public PlayingCard getCanvas() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    return this.canvas;
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
}
