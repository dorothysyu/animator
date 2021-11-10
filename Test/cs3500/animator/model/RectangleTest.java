package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest {
  IAnimator moveR1a;
  IAnimator moveR1b;
  IAnimator scaleR1a;
  IAnimator colorR1a;

  ArrayList<IAnimator> rect1Transforms;

  Rectangle rect1;

  /**
   * Initializes shapes and moves for testing.
   */
  @Before
  public void rectangleExamples() {
    rect1Transforms = new ArrayList<>();
    moveR1a = new Move("rect1", 5, 5, 10, 10, 1, 10);
    moveR1b = new Move("rect1", 20, 5, 40, 40, 11, 13);
    scaleR1a = new Scale("rect1", 20, 5, 40, 40, 11, 13);
    colorR1a = new Color("rect1", 1.0, 0.6, 0.2, 1, 1, 1, 1, 10);
    rect1 = new Rectangle("rect1", 100.0, 100.0, 20.0, 40.0,
            1.0, 6.0, 1.0, 0.0, 0.0, rect1Transforms);
    rect1Transforms.add(moveR1a);
    rect1Transforms.add(moveR1b);
    rect1Transforms.add(scaleR1a);
    rect1Transforms.add(colorR1a);
  }

  @Test
  public void testGetters() {
    assertEquals("rect1", rect1.getName());
    assertEquals("Rectangle", rect1.getType());
    assertEquals(100.0, rect1.getPositionX(), 0.001);
    assertEquals(100.0, rect1.getPositionY(), 0.001);
    assertEquals(20, rect1.getWidth(), 0.001);
    assertEquals(40, rect1.getHeight(), 0.001);
    assertEquals(1, rect1.getAppearAt(), 0.001);
    assertEquals(6, rect1.getDisappearAt(), 0.001);
    assertEquals(1, rect1.getColorR(), 0.001);
    assertEquals(0.0, rect1.getColorG(), 0.001);
    assertEquals(0.0, rect1.getColorB(), 0.001);
    assertEquals(rect1Transforms, rect1.getCommands());
  }

  @Test
  public void testSetters() {
    assertEquals(100.0, rect1.getPositionX(), 0.001);
    assertEquals(100.0, rect1.getPositionY(), 0.001);
    assertEquals(20, rect1.getWidth(), 0.001);
    assertEquals(40, rect1.getHeight(), 0.001);
    assertEquals(1, rect1.getAppearAt(), 0.001);
    assertEquals(6, rect1.getDisappearAt(), 0.001);
    assertEquals(1, rect1.getColorR(), 0.001);
    assertEquals(0.0, rect1.getColorG(), 0.001);
    assertEquals(0.0, rect1.getColorB(), 0.001);
    rect1.setPositionX(10);
    rect1.setPositionY(10);
    rect1.setColorB(1);
    rect1.setColorG(1);
    rect1.setColorB(1);
    rect1.setHeight(1);
    rect1.setWidth(1);
    assertEquals(10, rect1.getPositionX(), 0.001);
    assertEquals(10, rect1.getPositionY(), 0.001);
    assertEquals(1, rect1.getWidth(), 0.001);
    assertEquals(1, rect1.getHeight(), 0.001);
    assertEquals(1, rect1.getColorR(), 0.001);
    assertEquals(1, rect1.getColorG(), 0.001);
    assertEquals(1, rect1.getColorB(), 0.001);
  }

  @Test
  public void setTempo() {
    assertEquals(1, rect1.getAppearAt(), 0.001);
    assertEquals(6, rect1.getDisappearAt(), 0.001);

    rect1.setTempo(40);

    assertEquals(0.025, rect1.getAppearAt(), 0.001);
    assertEquals(0.15, rect1.getDisappearAt(), 0.001);
  }

  @Test
  public void rect1StringMethods() {
    assertEquals("Shape rect1 moves from (5.0, 5.0) to (10.0, 10.0) from t=1.0s to t=10.0s"
            + "\nShape rect1 moves from (20.0, 5.0) to (40.0, 40.0) from t=11.0s to t=13.0s"
            + "\nShape rect1 scales from Width: 20.0, Height: 5.0 to Width: 40.0, Height: "
            + "40.0 from t=11.0s to t=13.0s"
            + "\nShape rect1 changes color from (1.0, 0.6, 0.2) to (1.0, 1.0, 1.0)"
            + " from t=1.0s to t=10.0s\n", rect1.commandsToStrings());
    assertEquals("Name: rect1\nType: Rectangle\nCorner: (100.0, 100.0), Width: 20.0, "
                    + "Height: 40.0, Color: (1.0, 0.0, 0.0)\nAppears at t=1.0s\nDisappears" +
                    " at t=6.0s\n",
            rect1.printShapeDescription());
    assertEquals("<rect id=\"rect1\" x=\"100.0\" y=\"100.0\" width=\"20.0\" he" +
            "ight=\"40.0 \""
            + " fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >", rect1.printSVGDescription());
    assertEquals("\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"9000.0ms\"" +
            " attributeName=\"x\" from=\"5.0\" to=\"10.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"9000.0ms\"" +
            " attributeName=\"y\" from=\"5.0\" to=\"10.0\" fill=\"freeze\" />" +
            "\t<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"2000.0ms\" " +
            "attributeName=\"x\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"2000.0ms\"" +
            " attributeName=\"y\" from=\"5.0\" to=\"40.0\" fill=\"freeze\" />\t" +
            "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"2000.0ms\" " +
            "attributeName=\"width\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"2000.0ms\" " +
            "attributeName=\"height\" from=\"5.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"9000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(255.0,153.0,51.0)\" to=\"rgb(255.0,255.0,255.0)\"" +
            " fill=\"freeze\" />\n", rect1.printSVGCommands());
    assertEquals("\t<animate attributeType=\"xml\" begin=\"1000.0ms\" " +
            "dur=\"9000.0ms\" attributeName=\"x\" from=\"5.0\" to=\"10.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"9000.0ms\" " +
            "attributeName=\"y\" from=\"5.0\" to=\"10.0\" fill=\"freeze\" />\t" +
            "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"2000.0ms\" attributeName=" +
            "\"x\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"2000.0ms\" attributeName=" +
            "\"y\" from=\"5.0\" to=\"40.0\" fill=\"freeze\" />\t<animate attributeType=\"xml\"" +
            " begin=\"11000.0ms\" dur=\"2000.0ms\" attributeName=\"width\" from=\"20.0\" to=\"" +
            "40.0\" fill=\"freeze\" />\n" + "<animate attributeType=\"xml\" begin=\"11000.0ms\" " +
            "dur=\"2000.0ms\" attributeName=\"height\" from=\"5.0\" to=\"40.0\" fill=\"freeze\" " +
            "/>\n" + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"9000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(255.0,153.0,51.0)\" to=\"rgb(255.0,255.0,255.0)\" " +
            "fill=\"freeze\" />\n", rect1.printSVGCommands());
    assertEquals("<set attributeName=\"visibility\" attributeType=\"xml\" to=\"" +
            "visible\" begin=\"" +
            "1.0s\" dur=\"-994.0s\" fill=\"freeze\"/>", rect1.makeVisible());
  }

  @Test
  public void testCopyShape() {
    IShape rectCopy = rect1.copyShape(rect1);
    assertEquals(false, rectCopy == rect1);
    assertEquals(rect1.getName(), rectCopy.getName());
    assertEquals(rect1.getType(), rectCopy.getType());
    assertEquals(rect1.getPositionX(), rectCopy.getPositionX(), 0.001);
    assertEquals(rect1.getPositionY(), rectCopy.getPositionY(), 0.001);
    assertEquals(rect1.getWidth(), rectCopy.getWidth(), 0.001);
    assertEquals(rect1.getHeight(), rectCopy.getHeight(), 0.001);
    assertEquals(rect1.getAppearAt(), rectCopy.getAppearAt(), 0.001);
    assertEquals(rect1.getDisappearAt(), rectCopy.getDisappearAt(), 0.001);
    assertEquals(rect1.getColorR(), rectCopy.getColorR(), 0.001);
    assertEquals(rect1.getColorG(), rectCopy.getColorG(), 0.001);
    assertEquals(rect1.getColorB(), rectCopy.getColorB(), 0.001);
    assertEquals(rect1.getCommands(), rectCopy.getCommands());
  }

  @Test
  public void testGetStateAt() {
    IShape rectAt5 = rect1.getStateAt(5);
    assertEquals("rect1", rectAt5.getName());
    assertEquals("Rectangle", rectAt5.getType());
    assertEquals(7.222222222222222, rectAt5.getPositionX(), 0.001);
    assertEquals(7.222222222222222, rectAt5.getPositionY(), 0.001);
    assertEquals(20.0, rectAt5.getWidth(), 0.001);
    assertEquals(40, rectAt5.getHeight(), 0.001);
    assertEquals(1, rectAt5.getAppearAt(), 0.001);
    assertEquals(6, rectAt5.getDisappearAt(), 0.001);
    assertEquals(1, rectAt5.getColorR(), 0.001);
    assertEquals(0.7777777777777777, rectAt5.getColorG(), 0.001);
    assertEquals(0.5555555555555556, rectAt5.getColorB(), 0.001);
    assertEquals(rect1Transforms, rectAt5.getCommands());
  }

  @Test
  public void testAddCommand() {
    IAnimator scale2 = new Scale("rect1", 20, 30, 50, 50, 1, 10);
    rect1.addCommand(scale2);

    IShape scaledRect = rect1.getStateAt(10);
    assertEquals(50, scaledRect.getWidth(), 0.001);
    assertEquals(50, scaledRect.getHeight(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddConflictingMove() {
    IAnimator scale1 = new Scale("rect1", 4, 4, 6, 6, 1, 3);
    IAnimator scale2 = new Scale("rect1", 4, 4, 6, 6, 1, 3);
    rect1.addCommand(scale1);
    rect1.addCommand(scale2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    IShape rect = new Rectangle("INVALID", 1, 1, -1, 1, 1, 2, 1, 1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, -1, 1, 2, 1, 1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAppearAt() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, -1, 1, 1, 1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDisappearAt() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 1, -1, 1, 1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisappearLessThanAppear() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 3, 2, 1, 1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeR() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 1, 2, -1, 1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeG() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 1, 1, 1, -1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeB() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 1, 1, 1, 1, -1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooBigR() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 1, 2, 2, 1, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooBigG() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 1, 1, 1, 2, 1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooBigB() {
    IShape rect = new Rectangle("INVALID", 1, 1, 1, 1, 1, 1, 1, 1, 2, new ArrayList<>());
  }
}