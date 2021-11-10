package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModelImpl.Builder;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OvalTest {

  Oval oval1;
  Oval oval2;
  Oval oval3;
  Oval oval4;
  Oval ov;
  ArrayList<IAnimator> animatorList1;
  ArrayList<IAnimator> animatorList2;
  ArrayList<IAnimator> animatorList3;
  ArrayList<IAnimator> animatorList4;
  IAnimator a1;
  IAnimator a2;
  IAnimator a3;
  IAnimator a4;
  IAnimator a5;
  IAnimator a6;

  /**
   * Initializes shapes and moves for testing.
   */
  @Before
  public void exampleShapes() {
    animatorList1 = new ArrayList<>();
    oval1 = new Oval("o1", 500.0, 100.0, 60.0, 30.0,
            1, 5, 0.0, 0.0, 1.0, animatorList1);
    this.a1 = new Move("o1", 500.0, 100.0, 500.0, 300.0, 1,
            3);
    this.a2 = new Scale("o1", 100, 200, 200, 400,
            1, 5);
    this.a3 = new Color("o1", 1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 1, 3);
    // adds the moves into a list
    animatorList1.add(a1);
    animatorList1.add(a2);
    animatorList1.add(a3);
    // Tests for the constructor
    oval2 = new Oval("o2", 0.0, 0.0, 0.0, 0.0, 0,
            0, 0.0, 0.0, 0.0, animatorList3);
    //model1 = new AnimatorModelImpl(1);

    animatorList2 = new ArrayList<>();
    oval3 = new Oval("o3", 50.0, 100.0, 6.0, 3.0,
            2, 6, 1.0, 0.0, 0.0, animatorList3);
    this.a4 = new Move("o3", 50.0, 100.0, 100.0, 50.0, 1,
            5);
    this.a5 = new Scale("o3", 6.0, 3.0, 10.0, 9.0,
            1, 6);
    this.a6 = new Color("o3", 1.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 1, 10);

    oval4 = new Oval("o3", 1.0, 2.0, 0.1, 0.5,
            1, 4, 1.0, 1.0, 1.0, animatorList4);
  }

  @Test
  public void oval1Test() {
    assertEquals("o1", oval1.getName());
    // This tests for the type of the shape.
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
    assertEquals("Shape o1 moves from (500.0, 100.0) to (500.0, 300.0) from t=1.0s to "
            + "t=3.0s\nShape o1 scales from Width: 100.0, Height: 200.0 to Width: 200.0, Height: "
            + "400.0 from t=1.0s to t=5.0s\nShape o1 changes color from (1.0, 0.0, 0.0) to "
            + "(0.0, 0.0, 1.0) from t=1.0s to t=3.0s\n", oval1.commandsToStrings());
    // This checks for the entire list of shape and commands too
    assertEquals("Name: o1\nType: oval\nCenter: (500.0, 100.0),"
            + " X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)"
            + "\nAppears at t=1.0s\nDisappears at t=5.0s\n", oval1.printShapeDescription());

  }

  // This tests the constructor for oval
  @Test
  public void constructorTest() {
    assertEquals("Name: o2\nType: oval\nCenter: (0.0, 0.0),"
            + " X radius: 0.0, Y radius: 0.0, Color: (0.0, 0.0, 0.0)"
            + "\nAppears at t=0.0s\nDisappears at t=0.0s\n", oval2.printShapeDescription());
  }

  // This test for the SVGDescription()
  @Test
  public void testSVGDescription() {
    assertEquals("<ellipse id=\"o1\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" "
                    + "ry=\"30.0 \" fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >",
            oval1.printSVGDescription());
  }

  @Test
  public void testSVGShape() {
    assertEquals("<ellipse id=\"o1\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0 \""
            + " fill=\"rgb(0.0,0.0,255.0)\" visibility=\"hidden\" >\n"
            + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" "
            + "begin=\"1.0s\" dur=\"-995.0s\" fill=\"freeze\"/>\t<animate attributeType=\"xml\" "
            + "begin=\"1000.0ms\" dur=\"2000.0ms\" attributeName=\"cx\" from=\"500.0\" "
            + "to=\"500.0\" fill=\"freeze\" />\n<animate attributeType=\"xml\" "
            + "begin=\"1000.0ms\" dur=\"2000.0ms\" attributeName=\"cy\" from=\"100.0\" to=\""
            + "300.0\" fill=\"freeze\" />\t<animate attributeType=\"xml\" begin=\""
            + "1000.0ms\" dur=\"4000.0ms\" attributeName=\"rx\" from=\"100.0\" to=\"200.0\" "
            + "fill=\"freeze\" />\n<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\""
            + "4000.0ms\" attributeName=\"ry\" from=\"200.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)\" "
            + "fill=\"freeze\" />\n</ellipse>", oval1.printSVGShape());
  }

  @Test
  public void testPrintSVGCommand() {
    assertEquals("\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
                    + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\" "
                    + "attributeName=\"cy\" from=\"100.0\" to=\"300.0\" fill=\"freeze\" />\t"
                    + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\""
                    + " attributeName=\"rx\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
                    + "attributeName=\"ry\" from=\"200.0\" to=\"400.0\" fill=\"freeze\" />\n"
                    + "\t<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2000.0ms\""
                    + " attributeName=\"fill\" from=\"rgb(255.0,0.0,0.0)\" to=\"rgb(0.0,0.0,255.0)"
                    + "\" fill=\"freeze\" />\n",
            oval1.printSVGCommands());
    assertEquals("<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\""
            + " begin=\"1.0s\" dur=\"-995.0s\" fill=\"freeze\"/>", oval1.makeVisible());
  }

  // This tests for the copyShape method
  @Test
  public void copyShapeTest() {
    ov = new Oval("ov", 1.0, 2.0, 0.1, 0.5,
            1, 4, 1.0, 1.0, 1.0, animatorList4);
    IShape ov1 = ov.copyShape(ov);
    assertEquals("ov", ov1.getName());
    // This tests for the type of the shape.
    assertEquals("oval", ov1.getType());
    assertEquals(1.0, ov1.getPositionX(), 0.001);
    assertEquals(2.0, ov1.getPositionY(), 0.001);
    assertEquals(0.1, ov1.getWidth(), 0.001);
    assertEquals(0.5, ov1.getHeight(), 0.001);
    assertEquals(1.0, ov1.getAppearAt(), 0.001);
    assertEquals(4.0, ov1.getDisappearAt(), 0.001);
    assertEquals(1.0, ov1.getColorR(), 0.001);
    assertEquals(1.0, ov1.getColorG(), 0.001);
    assertEquals(1.0, ov1.getColorB(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddInvalidCommand() {
    Builder builder = new Builder();
    builder.addMove("o1", -150, 244, 100, 0,
            -250, -20);
    builder.build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSameCommandTwice() {
    Oval ooo;
    IAnimator sameMove1;
    IAnimator sameMove2;
    ArrayList<IAnimator> listOfSameMoves;
    listOfSameMoves = new ArrayList<>();
    ooo = new Oval("ooo", 500.0, 500.0, 5.0, 5.0,
            1, 4, 1.0, 0.0, 0.0, listOfSameMoves);
    sameMove1 = new Move("ooo", 500.0, 100.0, 5.0, 5.0,
            1, 3);
    sameMove2 = new Move("ooo", 500.0, 100.0, 5.0, 5.0,
            1, 3);
    ooo.addCommand(sameMove1);
    ooo.addCommand(sameMove2);
  }

  @Test
  public void setTempoTest() {
    assertEquals(1, oval1.getAppearAt(), 0.001);
    assertEquals(5.0, oval1.getDisappearAt(), 0.001);
    oval1.setTempo(2);
    assertEquals(0.5, oval1.getAppearAt(), 0.001);
    assertEquals(2.5, oval1.getDisappearAt(), 0.001);
  }

  @Test
  public void getStateAtTest() {
    // this tests for the getStateAt
    IShape myOval = oval1.getStateAt(2);
    assertEquals("o1", myOval.getName());
    assertEquals("oval", myOval.getType());
    assertEquals(500.0, myOval.getPositionX(), 0.001);
    assertEquals(200.0, myOval.getPositionY(), 0.001);
    assertEquals(125.0, myOval.getWidth(), 0.001);
    assertEquals(250.0, myOval.getHeight(), 0.001);
    assertEquals(1, myOval.getAppearAt(), 0.001);
    assertEquals(5, myOval.getDisappearAt(), 0.001);
    assertEquals(0.5, myOval.getColorR(), 0.001);
    assertEquals(0.0, myOval.getColorG(), 0.001);
    assertEquals(0.5, myOval.getColorB(), 0.001);
  }
}
