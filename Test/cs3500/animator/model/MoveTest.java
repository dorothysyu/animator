package cs3500.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MoveTest {
  IAnimator moveR;
  IAnimator moveO;

  ArrayList<IAnimator> rect1Transforms;
  ArrayList<IAnimator> oval1Transforms;

  Rectangle rect1;
  Oval oval1;

  /**
   * Initializes shapes and moves for testing.
   */
  @Before
  public void before() {
    moveR = new Move("rect1", 1, 1, 10, 10, 3, 5);
    moveO = new Move("oval1", 1, 1, 10, 10, 3, 5);

    rect1Transforms = new ArrayList<>();
    oval1Transforms = new ArrayList<>();

    rect1 = new Rectangle("rect1", 100.0, 100.0, 20.0, 40.0,
            1.0, 6.0, 1.0, 0.0, 0.0, rect1Transforms);
    oval1 = new Oval("oval", 100.0, 100.0, 20.0, 40.0,
            1.0, 6.0, 1.0, 0.0, 0.0, oval1Transforms);
  }

  @Test
  public void testGetCommandType() {
    assertEquals(CommandType.MOVESHAPE, moveO.getCommandType());
    assertEquals(CommandType.MOVESHAPE, moveR.getCommandType());
  }

  @Test
  public void testToString() {
    assertEquals("Shape oval1 moves from (1.0, 1.0) to (10.0, 10.0) "
            + "from t=3.0s to t=5.0s", moveO.toString());
    assertEquals("Shape rect1 moves from (1.0, 1.0) to (10.0, 10.0) "
            + "from t=3.0s to t=5.0s", moveR.toString());
  }

  @Test
  public void testApplyAnimationAt() {
    moveO.applyAnimationAt(oval1, 1);
    assertEquals(10, oval1.getPositionX(), 0.001);
    assertEquals(10, oval1.getPositionY(), 0.001);

    moveO.applyAnimationAt(oval1, 4);
    assertEquals(5.5, oval1.getPositionX(), 0.001);
    assertEquals(5.5, oval1.getPositionY(), 0.001);

    moveO.applyAnimationAt(oval1, 20);
    assertEquals(10, oval1.getPositionX(), 0.001);
    assertEquals(10, oval1.getPositionY(), 0.001);
  }

  @Test
  public void testTween() {
    assertEquals(10, moveR.tween(1, 3, 5, 1, 10), 0.001);
    assertEquals(5.5, moveR.tween(4, 3, 5, 1, 10), 0.001);
    assertEquals(10, moveO.tween(1, 3, 5, 1, 10), 0.001);
    assertEquals(5.5, moveO.tween(4, 3, 5, 1, 10), 0.001);
  }

  @Test
  public void testPrintSVGCommand() {
    assertEquals("\t<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"2000.0ms\"" +
                    " attributeName=\"x\" from=\"1.0\" to=\"10.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"2000.0ms\" " +
                    "attributeName=\"y\" from=\"1.0\" to=\"10.0\" fill=\"freeze\" />",
            moveO.printSVGCommand("Oval"));
    assertEquals("\t<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"2000.0ms\"" +
                    " attributeName=\"x\" from=\"1.0\" to=\"10.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"2000.0ms\" " +
                    "attributeName=\"y\" from=\"1.0\" to=\"10.0\" fill=\"freeze\" />",
            moveR.printSVGCommand("Rectangle"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFromX() {
    IAnimator move = new Move("rect1", -10, 15, 20, 25, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFromY() {
    IAnimator move = new Move("rect1", 10, -15, 20, 25, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidToX() {
    IAnimator move = new Move("rect1", 10, 15, -20, 25, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidToY() {
    IAnimator move = new Move("rect1", 10, 15, 20, -25, 30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartTime() {
    IAnimator move = new Move("rect1", 10, 15, 20, 25, -30, 40);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEndTime() {
    IAnimator move = new Move("rect1", 10, 15, 20, 25, 30, -1);
  }
}