package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ScaleTest {
  Oval oval1;
  Oval oval2;
  Oval oval3;
  Oval oval4;
  ArrayList<IAnimator> animatorList1;
  ArrayList<IAnimator> animatorList2;
  ArrayList<IAnimator> animatorList3;
  ArrayList<IAnimator> animatorList4;
  IAnimator a2;
  IAnimator a5;

  /**
   * Initializes shapes and moves for testing.
   */
  @Before
  public void exampleShapes() {
    animatorList1 = new ArrayList<>();
    oval1 = new Oval("o1", 500.0, 100.0, 60.0, 30.0,
            1, 5, 0.0, 0.0, 1.0, animatorList1);
    this.a2 = new Scale("o1", 100, 200, 200, 400,
            1, 5);
    animatorList1.add(a2);
    // Tests for the constructor
    oval2 = new Oval("o2", 0.0, 0.0, 0.0, 0.0, 0,
            0, 0.0, 0.0, 0.0, animatorList3);
    animatorList2 = new ArrayList<>();
    oval3 = new Oval("o3", 50.0, 100.0, 6.0, 3.0,
            2, 6, 1.0, 0.0, 0.0, animatorList3);
    this.a5 = new Scale("o3", 6.0, 3.0, 10.0, 9.0,
            1, 6);
    oval4 = new Oval("o3", 1.0, 2.0, 0.1, 0.5,
            1, 4, 1.0, 1.0, 1.0, animatorList4);
  }


  // Gets the startTime of an oval
  @Test
  public void getStartTime() {
    assertEquals(1, oval1.getAppearAt(), 0.001);
    assertEquals(0, oval2.getAppearAt(), 0.001);
    assertEquals(2, oval3.getAppearAt(), 0.001);
  }

  @Test
  public void testToString() {
    assertEquals("[Shape o1 scales from Width: 100.0, Height: 200.0 to Width: 200.0,"
            + " Height: 400.0 from t=1.0s to t=5.0s]", animatorList1.toString());
  }

  @Test
  public void testCommandType() {
    Scale scale = new Scale("o1", 100, 200, 200, 400,
            1, 5);
    assertEquals(CommandType.CHANGESCALE, scale.getCommandType());
  }

  @Test
  public void applyAnimationAt() {
    assertEquals("oval", oval1.getType());
    assertEquals(500.0, oval1.getPositionX(), 0.001);
    assertEquals(100.0, oval1.getPositionY(), 0.001);
    assertEquals(60.0, oval1.getWidth(), 0.001);
    assertEquals(30.0, oval1.getHeight(), 0.001);
    assertEquals(1.0, oval1.getAppearAt(), 0.001);
    assertEquals(5.0, oval1.getDisappearAt(), 0.001);
    assertEquals(0.0, oval1.getColorR(), 0.001);
    assertEquals(0.0, oval1.getColorG(), 0.001);
    assertEquals(1.0, oval1.getColorB(), 0.001);
    Scale scale = new Scale("o1", 100, 200, 200, 400,
            1, 5);
    scale.applyAnimationAt(oval1, 2);
    assertEquals("oval", oval1.getType());
    assertEquals(500.0, oval1.getPositionX(), 0.001);
    assertEquals(100.0, oval1.getPositionY(), 0.001);
    assertEquals(125.0, oval1.getWidth(), 0.001);
    assertEquals(250.0, oval1.getHeight(), 0.001);
    assertEquals(1.0, oval1.getAppearAt(), 0.001);
    assertEquals(5.0, oval1.getDisappearAt(), 0.001);
    assertEquals(0.0, oval1.getColorR(), 0.001);
    assertEquals(0.0, oval1.getColorG(), 0.001);
    assertEquals(1.0, oval1.getColorB(), 0.001);
  }

  @Test
  public void printSVGCommand() {
    assertEquals("oval", oval1.getType());
    Scale scaleShape = new Scale("o3", 6.0, 3.0, 10.0,
            9.0, 1, 6);
    assertEquals("\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"5000.0ms\""
                    + " attributeName=\"rx\" from=\"6.0\" to=\"10.0\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"5000.0ms\" " +
                    "attributeName=\"ry\" from=\"3.0\" to=\"9.0\" fill=\"freeze\" />\n",
            scaleShape.printSVGCommand("oval"));
  }
}

