package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

import java.io.IOException;

/**
 * The TextView class is a class that outputs the view as text descriptions of the shapes.
 */
public class TextView implements IView {
  /**
   * The run method takes in the animatorMode, ticksPerSecond and an appendable and appends
   * all the text description into the Appendable.
   *
   * @param model          the animatorModel
   * @param ticksPerSecond the amount of ticks per second in ints
   * @param a              what you will be appending.
   */
  public void run(AnimatorModel model, int ticksPerSecond, Appendable a) {
    model.setTempo(ticksPerSecond);
    try {
      a.append(model.printAsString());
    } catch (IOException e) {
      throw new IllegalArgumentException("Your file is closed.");
    }
  }
}


