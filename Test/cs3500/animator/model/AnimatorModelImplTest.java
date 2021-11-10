package cs3500.animator.model;

import cs3500.animator.model.AnimatorModelImpl.Builder;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnimatorModelImplTest {
  Builder builder;
  AnimatorModel model;

  /**
   * Builds a model using the TweenModelBuilder.
   */
  @Before
  public void before() {
    builder = new Builder();

    builder.addOval("oval1", 100, 100, 20, 40, 1, 0, 0, 1, 6);
    builder.addMove("oval1", 100, 100, 1, 1, 4, 6);
    builder.addColorChange("oval1", 1, 1, 1, 1, 0, 0, 3, 10);

    builder.addRectangle("rect1", 100, 100, 20, 40, 1, 0, 0, 1, 6);
    builder.addMove("rect1", 1, 1, 100, 100, 1, 4);
    builder.addScaleToChange("rect1", 1, 1, 100, 100, 1, 4);

    model = builder.build();
  }

  /**
   * Tests that each time component is divided by the double that is passed into setTempo.
   */
  @Test
  public void setTempo() {
    List<Double> allAppearAt = new ArrayList<Double>();
    List<Double> allDisappearAt = new ArrayList<Double>();
    List<Double> animatorStartTime = new ArrayList<Double>();
    List<Double> animatorEndTime = new ArrayList<Double>();
    for (IShape shape : model.getShapesAtFrame(1)) {
      allAppearAt.add(shape.getAppearAt());
      allDisappearAt.add(shape.getDisappearAt());
      for (IAnimator animator : shape.getCommands()) {
        animatorStartTime.add(animator.getStartTime());
        animatorEndTime.add(animator.getEndTime());
      }
    }
    List<Double> allAppearAt10 = new ArrayList<Double>();
    List<Double> allDisappearAt10 = new ArrayList<Double>();
    List<Double> animatorStartTime10 = new ArrayList<Double>();
    List<Double> animatorEndTime10 = new ArrayList<Double>();
    for (Double d : allAppearAt) {
      allAppearAt10.add(d / 10.0);
    }
    for (Double d : allDisappearAt) {
      allDisappearAt10.add(d / 10.0);
    }
    for (Double d : animatorStartTime10) {
      animatorStartTime.add(d / 10.0);
    }
    for (Double d : animatorEndTime10) {
      animatorEndTime.add(d / 10.0);
    }

    model.setTempo(10);

    boolean tempoWasSet = true;
    for (int i = 0; i < allAppearAt.size(); i++) {
      tempoWasSet &= ((allAppearAt.get(0)) / 10.00) == allAppearAt10.get(0);
    }

    assertEquals(true, tempoWasSet);
  }

  @Test
  public void getShapesAtFrame() {
    List<IShape> shapes = new ArrayList<>();
    for (IShape shape : model.getShapesAtFrame(2)) {
      shapes.add(shape);
    }
    assertEquals(2, shapes.size());
  }

  @Test
  public void getMaxDisappearTime() {
    assertEquals(6, model.getMaxDisappearTime(), 0.001);
  }

  @Test
  public void printAsString() {
    assertEquals("Shapes:\n" +
            "Name: oval1\n" +
            "Type: oval\n" +
            "Center: (100.0, 100.0), X radius: 20.0, Y radius: 40.0, Color: (1.0, 0.0, 0.0)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=6.0s\n" +
            "Name: rect1\n" +
            "Type: Rectangle\n" +
            "Corner: (100.0, 100.0), Width: 20.0, Height: 40.0, Color: (1.0, 0.0, 0.0)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=6.0s\n" +
            "\n" + "Shape oval1 moves from (100.0, 100.0) to (1.0, 1.0) from t=4.0s to t=6.0s\n" +
            "Shape oval1 changes color from (1.0, 1.0, 1.0) to (1.0, 0.0, 0.0) " +
            "from t=3.0s to t=10.0s\n" + "Shape rect1 moves from (1.0, 1.0) to (100.0, 100.0) " +
            "from t=1.0s to t=4.0s\n" + "Shape rect1 scales from Width: 1.0, Height: 1.0 to " +
            "Width: 100.0, Height: 100.0 from t=1.0s to t=4.0s\n", model.printAsString());
  }

  @Test
  public void printSVGDescription() {
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"oval1\" cx=\"100.0\" cy=\"100.0\" rx=\"20.0\" ry=\"40.0 \" " +
            "fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" + "<set " +
            "attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" begin=\"1.0s\" " +
            "dur=\"-994.0s\" fill=\"freeze\"/>\t<animate attributeType=\"xml\" " +
            "begin=\"4000.0ms\" dur=\"2000.0ms\" attributeName=\"cx\" from=\"100.0\" to=\"1.0\" " +
            "fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"4000.0ms\" dur=\"2000.0ms\"" +
            " attributeName=\"cy\" from=\"100.0\" to=\"1.0\" fill=\"freeze\" />\t" +
            "<animate attributeType=\"xml\" begin=\"3000.0ms\" dur=\"7000.0ms\"" +
            " attributeName=\"fill\" from=\"rgb(255.0,255.0,255.0)\" to=\"rgb(255.0,0.0,0.0)\"" +
            " fill=\"freeze\" />\n" +
            "</ellipse><rect id=\"rect1\" x=\"100.0\" y=\"100.0\" width=\"20.0\" " +
            "height=\"40.0 \" fill=\"rgb(255.0,0.0,0.0)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"visible\" " +
            "begin=\"1.0s\" dur=\"-994.0s\" fill=\"freeze\"/>\t<animate attributeType=\"" +
            "xml\" begin=\"1000.0ms\" dur=\"3000.0ms\" attributeName=\"x\" from=\"1.0\"" +
            " to=\"100.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"3000.0ms\" " +
            "attributeName=\"y\" from=\"1.0\" to=\"100.0\" fill=\"freeze\" />\t" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"3000.0ms\" " +
            "attributeName=\"width\" from=\"1.0\" to=\"100.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"3000.0ms\" " +
            "attributeName=\"height\" from=\"1.0\" to=\"100.0\" fill=\"freeze\" />\n" +
            "</rect>\n</svg>", model.printSVGDescription());
  }
}