package cs3500.solored.controller;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.solored.model.hw02.Color;
import cs3500.solored.model.hw02.PlayingCard;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.SoloRedGameModel;

/**
 * Class to manually play SoloRedGame with given setup.
 */
public class Main {
  /**
   * The main function to start the game.
   * @param args the input values of the game
   */
  public static void main(String[] args) {
    RedGameModel<PlayingCard> model;
    List<PlayingCard> allCards;
    ArrayList<PlayingCard> mainDeck;

    PlayingCard b2;
    PlayingCard r1;
    PlayingCard o1;
    PlayingCard o4;
    PlayingCard r2;
    PlayingCard b4;
    PlayingCard v6;
    PlayingCard b7;
    PlayingCard i1;
    PlayingCard o3;
    PlayingCard r3;

    model = new SoloRedGameModel();
    allCards = model.getAllCards();

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
     * > P3: O1
     * P4: O4
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

    SoloRedTextController controller = new SoloRedTextController(new InputStreamReader(System.in),
            System.out);
    controller.playGame(model, mainDeck, true, 2, 4);
  }
}
