package cs3500.solored.model.hw04;

import cs3500.solored.model.hw02.AbstractRedGameModel;

/**
 * Represents an advanced SoloRedGameModel that changes the way playToCanvas and drawForHand
 * are handled.
 */
public class AdvancedSoloRedGameModel extends AbstractRedGameModel {
  private boolean drawExtraCard = false;

  @Override
  public void playToCanvas(int cardIdxInHand) {
    if (cardIdxInHand < 0 || cardIdxInHand >= getHand().size()) {
      throw new IllegalArgumentException("Card index cannot be < 0 or > number of cards in hand: "
              + cardIdxInHand);
    }
    int cardNumber = getHand().get(cardIdxInHand).getNumber();
    super.playToCanvas(cardIdxInHand);
    int winningPaletteSize = getPalette(winningPaletteIndex()).size();
    if (cardNumber > 1) {
      this.drawExtraCard = cardNumber > winningPaletteSize;
    }
  }

  @Override
  public void drawForHand() {
    if (!this.startGame) {
      throw new IllegalStateException("Game has not started");
    }
    if (this.isGameOver()) {
      throw new IllegalStateException("Game has already ended");
    }
    advancedDrawForHand();
  }

  private void advancedDrawForHand() {
    if (this.getHand().isEmpty()) {
      super.drawForHand();
    } else if (this.hand.size() < super.maxHandSize && !super.deck.isEmpty()) {
      this.getHand().add(super.deck.remove(0));
      super.lastTurn = "drawForHand";
      if (this.drawExtraCard && !this.isGameOver()) {
        this.drawExtraCard = false;
        if (this.hand.size() < super.maxHandSize && !super.deck.isEmpty()) {
          this.hand.add(super.deck.remove(0));
        }
      }
    }
  }
}
