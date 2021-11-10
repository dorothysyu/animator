package cs3500.animator;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.VisualView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import javax.swing.JOptionPane;

import util.AnimationFileReader;

/**
 * This is the class that provides a main method that will
 * provide an entry point for a user to run the animation.
 */
public class EasyAnimator {

  /**
   * Runs the animation based on the args taken from the command line.
   *
   * @param args comes from the command line and specifies what the animation should do.
   */
  public static void main(String[] args) {
    String str = "";
    Arrays.asList(args).contains(str);
    if (throwsException(args)) {
      JOptionPane.showMessageDialog(null, "Arguments aren't working!");
    }
    try {
      AnimatorModel model = buildModelFromFile(args);
      IView view = getView(args);
      int speed = getSpeed(args);
      Appendable a = getAppendable(args);
      view.run(model, speed, a);
    } catch (FileNotFoundException | IllegalArgumentException e) {
      throw new IllegalArgumentException(e);
    }
  }


  /**
   * Determines if the arguments that the user gave is invalid.
   * It is invalid if :
   * the argument is uneven (because each command has to come with a descriptor).
   * it does not contain the commands "-if" and "-iv"
   * it does not contain an alternating pattern of command and descriptor.
   */
  public static boolean throwsException(String[] args) {
    boolean oddArguments = (args.length % 2 != 0);
    boolean containsIfAndIv = (Arrays.asList(args).contains("-if") || Arrays.asList(args)
            .contains("-iv"));
    boolean everyOtherIsDash = true;
    for (int i = 0; i < args.length; i++) {
      if (i % 2 == 0) {
        everyOtherIsDash &= (args[i].substring(0, 1).equals("-"));
        i++;
      }
    }
    boolean everyOtherIsNotDash = true;
    for (int j = 0; j < args.length; j++) {
      if (j % 2 != 0) {
        everyOtherIsNotDash &= (!args[j].substring(0, 1).equals("-"));
        j++;
      }
    }
    return (oddArguments || !containsIfAndIv || !everyOtherIsDash || !everyOtherIsNotDash);
  }

  /**
   * Gets the file that the user passed in and builds the model from it.
   *
   * @param args the arguments from the command line
   * @return a model built from the text file that the user gave.
   * @throws FileNotFoundException if the file is not found.
   */
  public static AnimatorModel buildModelFromFile(String[] args) throws FileNotFoundException {
    AnimationFileReader reader = new AnimationFileReader();
    int indexOfIf = Arrays.asList(args).indexOf("-if");
    if (indexOfIf >= args.length - 1) {
      throw new IllegalArgumentException("You've reached the end of the arguments!");
    }
    String fileName = Arrays.asList(args).get(indexOfIf + 1);
    return reader.readFile(fileName, new AnimatorModelImpl.Builder());
  }

  /**
   * Returns the type of view that the user wants.
   *
   * @param args the arguments from the command line.
   */
  public static IView getView(String[] args) {
    int indexOfIv = Arrays.asList(args).indexOf("-iv");
    if (indexOfIv >= args.length - 1) {
      throw new IllegalArgumentException("You did not pass in a view type!");
    }
    String viewType = Arrays.asList(args).get(indexOfIv + 1);
    switch (viewType) {
      case "text":
        return new TextView();
      case "svg":
        return new SVGView();
      case "visual":
        return new VisualView();
      default:
        throw new IllegalArgumentException("You did not pass in a valid view!");
    }
  }

  /**
   * Gets the speed that the animation will run from the command line.
   */
  public static int getSpeed(String[] args) {
    try {
      int indexOfSpeed = Arrays.asList(args).indexOf("-speed");
      int speed = Integer.parseInt(Arrays.asList(args).get(indexOfSpeed + 1));
      return speed;
    } catch (IllegalArgumentException e) {
      return 1;
    }
  }

  /**
   * Returns the speed that the user wants the animation to go.
   */
  public static Appendable getAppendable(String[] args) {
    try {
      int indexOfOut = Arrays.asList(args).indexOf("-o");
      String appendable = Arrays.asList(args).get(indexOfOut + 1);
      if (!appendable.equals("out")) {
        return new PrintStream(new FileOutputStream(appendable));
      } else {
        return new PrintStream(System.out);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Incorrect file.");
    }
  }
}
