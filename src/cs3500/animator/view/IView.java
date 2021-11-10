package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

/**
 * This interface represents a view of an animation, which promises a run method.
 */
public interface IView {

  /**
   * This runs the animation.
   *
   * @param model          The model that the animation will run from.
   * @param ticksPerSecond the tempo at which the animation will run
   * @param a              if the view is textual, appends the view to this appendable.
   */
  void run(AnimatorModel model, int ticksPerSecond, Appendable a);
}
