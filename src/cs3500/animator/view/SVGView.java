package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

import java.io.IOException;

/**
 * The class SVGView is a view format that runs the program and returns the view in a textual form
 * (returns a String).
 */
public class SVGView implements IView {

  /**
   * The run method takes in the animatorMode, ticksPerSecond and an appendable and appends
   * all the SVGDescription into a file.
   *
   * @param m              the AnimatorModel
   * @param ticksPerSecond the amount of ticks per second in ints
   * @param a              what you will be appending.
   */
  @Override
  public void run(AnimatorModel m, int ticksPerSecond, Appendable a) {
    m.setTempo(ticksPerSecond);
    try {
      a.append(m.printSVGDescription());
    } catch (IOException e) {
      throw new IllegalArgumentException("Your file is closed.");
    }
  }
}



