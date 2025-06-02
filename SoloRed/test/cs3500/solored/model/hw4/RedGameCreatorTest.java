package cs3500.solored.model.hw4;

import org.junit.Assert;
import org.junit.Test;

import cs3500.solored.model.hw02.PlayingCard;
import cs3500.solored.model.hw02.RedGameModel;
import cs3500.solored.model.hw02.SoloRedGameModel;
import cs3500.solored.model.hw04.AdvancedSoloRedGameModel;
import cs3500.solored.model.hw04.RedGameCreator;

/**
 * Represents the tests for the methods of the RedGameCreator class.
 */
public class RedGameCreatorTest {

  @Test
  public void testCreateBasicGame() {
    RedGameModel<PlayingCard> game = RedGameCreator.createGame(RedGameCreator.GameType.BASIC);
    Assert.assertNotNull(game);
    Assert.assertTrue(game instanceof SoloRedGameModel);
  }

  @Test
  public void testCreateAdvancedGame() {
    RedGameModel<PlayingCard> game = RedGameCreator.createGame(RedGameCreator.GameType.ADVANCED);
    Assert.assertNotNull(game);
    Assert.assertTrue(game instanceof AdvancedSoloRedGameModel);
  }
}
