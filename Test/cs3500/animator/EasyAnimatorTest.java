package cs3500.animator;

import static org.junit.Assert.assertEquals;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

import org.junit.Test;

public class EasyAnimatorTest {

  @Test
  public void getView() {
    String[] arg2 = {
      "-jar",
      "Assignment6.jar",
      "-if",
      "big-bang-big-crunch.txt",
      "-iv",
      "visual",
      "-o",
      "out",
      "-speed", "500"};
    IView view = EasyAnimator.getView(arg2);
    assertEquals(true, view instanceof VisualView);

    String[] arg3 = {
      "-jar",
      "Assignment6.jar",
      "-if",
      "big-bang-big-crunch.txt",
      "-iv",
      "svg",
      "-o",
      "out",
      "-speed",
      "500"};
    IView svg = EasyAnimator.getView(arg3);
    assertEquals(true, svg instanceof SVGView);

    String[] arg4 = {
      "-jar",
      "Assignment6.jar",
      "-if",
      "big-bang-big-crunch.txt",
      "-iv",
      "text",
      "-o",
      "out",
      "-speed",
      "500"};
    IView text = EasyAnimator.getView(arg4);
    assertEquals(true, text instanceof TextView);

  }

  @Test
  public void testGetSpeed() {
    String[] args = {
      "-if",
      "big-bang-big-crunch.txt",
      "-iv",
      "visual",
      "-o",
      "out",
      "-speed",
      "500"};
    assertEquals(500, EasyAnimator.getSpeed(args));

    String[] arg2 = {"y"};
    assertEquals(1, EasyAnimator.getSpeed(arg2));
  }

  @Test
  public void testThrowsException() {
    String[] args = {
      "-if",
      "big-bang-big-crunch.txt",
      "-iv",
      "visual",
      "-o",
      "out",
      "-speed",
      "500"};
    assertEquals(false, EasyAnimator.throwsException(args));

    String[] arg2 = {"y"};
    assertEquals(true, EasyAnimator.throwsException(arg2));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidView() {
    String[] arg2 = {"y"};
    assertEquals(true, EasyAnimator.getView(arg2));
  }
}

