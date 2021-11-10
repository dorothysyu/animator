package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ColorTest {

  Oval oval1;
  Oval oval2;
  Oval oval3;
  ArrayList<IAnimator> animatorList1;
  ArrayList<IAnimator> animatorList2;
  ArrayList<IAnimator> animatorList3;
  IAnimator a3;
  IAnimator a6;
  IAnimator a4;

  /**
   * Initializes shapes and moves for testing.
   */
  @Before
  public void exampleShapes() {
    animatorList1 = new ArrayList<>();
    oval1 = new Oval("o1", 500.0, 100.0, 60.0, 30.0,
            1, 5, 1.0, 0.0, 1.0, animatorList1);
    this.a3 = new Color("o1", 1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 1, 3);
    this.a4 = new Color("o1", 0.0, 0.0, 1.0, 0.5,
            1.0, 0.0, 5, 10);
    // adds the color into a list
    animatorList1.add(a3);
    animatorList1.add(a4);
    // Tests for the constructor
    oval2 = new Oval("o2", 0.0, 0.0, 0.0, 0.0, 0,
            0, 0.0, 0.0, 0.0, animatorList3);
    animatorList2 = new ArrayList<>();
    oval3 = new Oval("o3", 50.0, 100.0, 6.0, 3.0,
            2, 6, 1.0, 0.0, 0.0, animatorList3);
    this.a6 = new Color("o3", 1.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 1, 10);
    animatorList2.add(a6);
  }

  @Test
  public void getCommandType() {
    Color color = new Color("o1", 1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 1, 3);
    assertEquals(CommandType.CHANGECOLOR, color.getCommandType());
  }

  @Test
  public void testToString() {
    assertEquals("[Shape o1 changes color from (1.0, 0.0, 0.0) to (0.0, 0.0, 1.0) from "
            + "t=1.0s to t=3.0s, Shape o1 changes color from (0.0, 0.0, 1.0) to (0.5, 1.0, 0.0) "
            + "from t=5.0s to t=10.0s]", animatorList1.toString());
  }

  @Test
  public void applyAnimationAt() {
    assertEquals("oval", oval3.getType());
    assertEquals(50.0, oval3.getPositionX(), 0.001);
    assertEquals(100.0, oval3.getPositionY(), 0.001);
    assertEquals(6.0, oval3.getWidth(), 0.001);
    assertEquals(3.0, oval3.getHeight(), 0.001);
    assertEquals(2.0, oval3.getAppearAt(), 0.001);
    assertEquals(6.0, oval3.getDisappearAt(), 0.001);
    assertEquals(1.0, oval3.getColorR(), 0.001);
    assertEquals(0.0, oval3.getColorG(), 0.001);
    assertEquals(0.0, oval3.getColorB(), 0.001);
    Color newColor = new Color("o3", 1.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 1, 10);
    newColor.applyAnimationAt(oval3, 3);
    assertEquals("oval", oval3.getType());
    assertEquals(50.0, oval3.getPositionX(), 0.001);
    assertEquals(100.0, oval3.getPositionY(), 0.001);
    assertEquals(6.0, oval3.getWidth(), 0.001);
    assertEquals(3.0, oval3.getHeight(), 0.001);
    assertEquals(2.0, oval3.getAppearAt(), 0.001);
    assertEquals(6.0, oval3.getDisappearAt(), 0.001);
    assertEquals(0.7777777777777778, oval3.getColorR(), 0.001);
    assertEquals(0.0, oval3.getColorG(), 0.001);
    assertEquals(0.0, oval3.getColorB(), 0.001);
  }

  @Test
  public void printSVGCommand() {
    assertEquals("oval", oval2.getType());
    Color changeColor = new Color("o3", 0.0, 0.0, 0.0,
            1.0, 0.0, 1.0, 1.0, 5.0);
    assertEquals("\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0.0,0.0,0.0)\" to=\"rgb(255.0,0.0,255.0)\" fill=\""
            + "freeze\" />\n", changeColor.printSVGCommand("oval"));
  }
}
